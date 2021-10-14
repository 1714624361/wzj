package com.dbsun.controller;

import com.dbsun.base.BaseController;
import com.dbsun.config.AllConfig;
import com.dbsun.entity.Loginlog;
import com.dbsun.entity.Role;
import com.dbsun.entity.system.Menu;
import com.dbsun.entity.system.PageData;
import com.dbsun.service.LoginlogService;
import com.dbsun.service.MenuService;
import com.dbsun.service.SysRoleService;
import com.dbsun.service.SysUserService;
import com.dbsun.util.Util;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController {
    @Autowired
    private LoginlogService logService;
    @Autowired
    private SysUserService sysuserService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private SysRoleService roleService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index(ModelMap model) {
        //验证session是否有效
        HttpSession session = request.getSession();
        PageData user = (PageData) session.getAttribute(AllConfig.SESSION_KEY);
        if (user != null) {
            List<Menu> lsmenu = new ArrayList<Menu>();
            //先读取session里面是否包含菜单，如果包含则直接使用，不包含则重新获取并设置进入session
            Object menuLst = session.getAttribute(AllConfig.SESSION_ALLMENULIST);
            if (menuLst != null) {
                lsmenu = (List<Menu>) menuLst;
                model.addAttribute("menuLst", lsmenu);
                model.addAttribute("name", user.getString("NAME"));//用户名称
            } else {
                //读取所有的菜单信息
                //lsmenu = menuService.getMenuLst();
                lsmenu = menuService.getMenuByAllnoPage();
                //获取当前用户角色权限(角色权限在登录得时候已经放入usersession里面)
                //传入菜单数据、角色权限得出具有权限的菜单数据
                lsmenu = Util.menuLstToRoleMenuLst(lsmenu, ((Role) user.get("role")).getRIGHTS());
                model.addAttribute("menuLst", lsmenu);
                System.out.println(user.getString("NAME"));
                model.addAttribute("name", user.getString("NAME"));//用户名称
                //
                session.removeAttribute(AllConfig.SESSION_ALLMENULIST);
                session.setAttribute(AllConfig.SESSION_ALLMENULIST, lsmenu);
            }
            return "index";
        } else {
            //返回到首页
            return "login";
        }

    }

    @RequestMapping("/loginPost")
    public @ResponseBody
    Map<String, Object> loginPost(String account, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        PageData pd = getPageData();//前端传入数据
        String passwd = new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString(); // 密码加密
        pd.put("PASSWORD", passwd);
        //获取浏览器信息  2018/06/07 修改
        Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
        //获取浏览器版本号
        Version version = browser.getVersion(request.getHeader("User-Agent"));
        String versionInfo = null;
        try {
            versionInfo = browser.getName() + "/" + version.getVersion();
        } catch (NullPointerException e) {
            //测试工具 版本号会空
            System.out.println("使用的接口测试工具,浏览器版本号会空");
        }

        //获取用户信息
        PageData user = sysuserService.getLoginValidation(pd);
        if (user == null) {
            map.put("msg", "500");
            map.put("result", "密码错误");
            return map;
        } else {//判定是否禁用，禁用需要返回告知客户端
            if (user.getString("STATUS").equals("1")) {//等于1表示已经禁用无法登录
                map.put("msg", "201");
                map.put("result", "您的账号已禁用!");
            } else {
                //判定当前用户是否已经登录过，如果登录不让重新登录
                PageData userPd = (PageData) session.getAttribute(AllConfig.SESSION_KEY);
                user.put("version", versionInfo);//浏览器版本号
                if (userPd != null) {//表示登录过则不需要多次登录直接提醒!
                    map.put("msg", "200");
                    map.put("result", "你已经登录过无需再次登录!");
                } else {
                    System.out.println(session.getAttribute(AllConfig.SESSION_KEY));
                    //谦容夸浏览器保证只有1个用户 2018/06/07
                    //获取用户角色权限信息
                    pd.put("ROLE_ID", user.getString("ROLE_ID"));
                    Role role = roleService.getSysRoleById(pd);
                    user.put("role", role);
                    // 设置session,放入用户信息跟角色权限信息
                    session.setAttribute(AllConfig.SESSION_KEY, user);
                    Loginlog log = new Loginlog();
                    log.setLogtext(account + "登录成功！");
                    //  logService.addLog(log);
                    map.put("msg", "200");
                    map.put("result", "登录成功!");


                }
            }
        }

        return map;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session账户信息
        session.removeAttribute(AllConfig.SESSION_KEY);
        // 移除session菜单信息
        session.removeAttribute(AllConfig.SESSION_ALLMENULIST);
        //移除session数据字典信息
        session.removeAttribute(AllConfig.SESSION_DICT);
        //清除redis 会话
        session.invalidate();

        return "login";
    }

}
