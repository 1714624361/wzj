package com.dbsun.controller.system;

import com.dbsun.base.BaseController;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.service.SysUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private SysUserService sysuserService;

    /**
     * 跳转到用户列表主页
     */
    @RequestMapping("/userIndex")
    public String userIndex(ModelMap model) {
        // 构建初始数据
        // PageData pd = new PageData();
        // System.out.println(111);
        // page.setPd(pd);
        // List<PageData> userLst = sysuserService.getSysUserLst(page);
        // model.addAttribute("UserTableLst",userLst);
        // model.addAttribute("page",page);
        return "/html/system/userList";
    }

    /**
     * 用户列表数据
     *
     * @param model
     * @return
     */
    @RequestMapping("/userLst")
    @ResponseBody
    public JSONObject userLst(ModelMap model) {
        // 构建初始数据
        PageData pd = getPageData();// 前端传入数据
        Page page = getPage();// 分页需要用到这个数据
        page.setPd(pd);
        List<PageData> userLst = sysuserService.getSysUserLst(page);
        return viewReturnPageData(page, userLst);
    }

    /**
     * 添加新用户
     */
    @RequestMapping("/userNewOpen")
    public String userNewOpen(ModelMap model) {
        // 构建初始数据
        PageData pd = getPageData();
        System.out.println(pd.getString("USER_ID"));
        //获取部门数据
        JSONArray jsonArray = sysuserService.getDeptAllLstJson(pd);
        //获取角色数据
        List<PageData> pdlst = sysuserService.getSysRoleAllLst(pd);
        //获取当前用户的数据
        PageData userPd = sysuserService.getSysUserById(pd);
        //JSONObject deptJsonLst = getSucJson(jsonArray);
        model.addAttribute("deptLst", jsonArray);
        model.addAttribute("roleLst", pdlst);
        model.addAttribute("userInfo", userPd);
        return "/html/system/userInfo";
    }

    /**
     * 修改用户
     */
    @RequestMapping("/userEditOpen")
    public String userEditOpen(ModelMap model) {
        // 构建初始数据
        PageData pd = getPageData();
        System.out.println(pd.getString("USER_ID"));
        //获取部门数据
        JSONArray jsonArray = sysuserService.getDeptAllLstJson(pd);
        //获取角色数据
        List<PageData> pdlst = sysuserService.getSysRoleAllLst(pd);
        //获取当前用户的数据
        PageData userPd = sysuserService.getSysUserById(pd);
        //JSONObject deptJsonLst = getSucJson(jsonArray);
        model.addAttribute("deptLst", jsonArray);
        model.addAttribute("roleLst", pdlst);
        model.addAttribute("userInfo", userPd);
        return "/html/system/userEditInfo";
    }

    /**
     * 用户信息新增
     */
    @RequestMapping("/userAdd")
    @ResponseBody
    public JSONObject userAdd() {
        // 构建初始数据
        PageData pd = getPageData();
        int state = sysuserService.addSysUser(pd);
        if (state > 0) {
            return getSucJson();
        } else if (state == -1) {
            JSONObject faljson = new JSONObject();
            faljson.put("msg", "502");
            faljson.put("result", "此账号已经存在,不能重复添加");
            return faljson;
        } else if (state == -2) {
            JSONObject faljson = new JSONObject();
            faljson.put("msg", "201");
            faljson.put("result", "坐席号冲突");
            return faljson;
        } else {
            return getFalJson();
        }
    }

    /**
     * 用户信息编辑
     */
    @RequestMapping("/userEdit")
    @ResponseBody
    public JSONObject userEdit() {
        // 构建初始数据
        PageData pd = getPageData();
        System.out.println(pd);
        int state = sysuserService.updateSysUser(pd);
        if (state > 0) {
            return getSucJson();
        } else if (state == -1) {
            JSONObject faljson = new JSONObject();
            faljson.put("msg", "201");
            faljson.put("result", "坐席号冲突");
            return faljson;
        } else {
            return getFalJson();
        }
    }

    /**
     * 禁用
     */
    @RequestMapping("/userDisableEdit")
    @ResponseBody
    public JSONObject userDisableEdit() {
        // 构建初始数据
        PageData pd = getPageData();
        int state = sysuserService.updateDisableSysUser(pd);
        if (state > 0) {
            return getSucJson();
        } else {
            return getFalJson();
        }
    }

    /**
     * 当前部门下的员工
     * DEPT_ID部门ID
     *
     * @return
     */
    @RequestMapping("/deptAndUserLst")
    @ResponseBody
    public JSONObject deptAndUserLst() {
        //获取当前用户信息
        PageData userData = getPageData();
        //返回当前人员下的员工数据
        List<PageData> pageData = sysuserService.getUserLstJson(userData);
        JSONObject userJsonLst = getSucJson(pageData);
        return userJsonLst;
    }

    /**
     * 当前部门下的员工(列表数据)
     * DEPT_ID部门ID
     *
     * @return
     */
    @RequestMapping("/userForDeptLst")
    @ResponseBody
    public JSONObject userForDeptLst() {
        PageData pd = getPageData();
        Page page = getPage();
        page.setPd(pd);
        List<PageData> pageData = sysuserService.getDeptAllLst(page);
        return viewReturnPageData(page, pageData);
    }

    /**
     * 列表数据
     * DEPT_ID部门ID
     */
    @RequestMapping("/deptAndMeLst")
    @ResponseBody
    public JSONObject deptAndMeLst() {
        //获取当前用户信息
        PageData userData = getUserPd();
        //返回当前人员下的部门数据
        JSONArray jsonobj = sysuserService.getDeptAndMeLstJson(userData);
        JSONObject deptJsonLst = getSucJson(jsonobj);
        return deptJsonLst;
    }

    /**
     * 列表数据(当前角色下的部门)
     * DEPT_ID部门ID
     */
    @RequestMapping("/deptLst")
    @ResponseBody
    public JSONObject deptLst() {
        //获取当前用户信息
        PageData userData = getUserPd();
        //返回当前人员下的部门数据
        JSONArray jsonArray = sysuserService.getDeptLstJson(userData);
        JSONObject deptJsonLst = getSucJson(jsonArray);
        return deptJsonLst;
    }

    /**
     * 列表数据(所有部门)
     * DEPT_ID部门ID
     */
    @RequestMapping("/deptAllLst")
    @ResponseBody
    public JSONObject deptAllLst() {
        PageData pd = new PageData();
        //返回当前人员下的部门数据
        JSONArray jsonArray = sysuserService.getDeptAllLstJson(pd);
        JSONObject deptJsonLst = getSucJson(jsonArray);
        return deptJsonLst;
    }

    /**
     * 获取所有的角色列表（下拉框格式）
     */
    @RequestMapping("/roleSelectAllLst")
    @ResponseBody
    public JSONObject roleSelectAllLst() {
        //获取角色数据
        PageData pd = new PageData();
        List<PageData> pdlst = sysuserService.getSysRoleAllLst(pd);
        return this.getSucJson(pdlst);
    }

    /**
     * 修改用户密码
     */
    @RequestMapping("/userEditPsw")
    @ResponseBody
    public JSONObject userEditPsw() {
        // 构建初始数据
        PageData pd = getPageData();
        pd.put("USER_ID", getUserPd().getString("USER_ID"));
        pd.put("USERNAME", getUserPd().getString("USERNAME"));//传入username是为了针对账号跟密码进行加密得出自己的密码
        int state = sysuserService.updateSysUserPsw(pd);
        return getAddOrUpdJson(state);
    }

    /**
     * 返回查询页面需要使用的当前角色及当前角色下的部门信息(value包含部门ID跟部门机构ID)
     * DEPT_ID部门ID
     */
    @RequestMapping("/searchDeptAndMeLst")
    @ResponseBody
    public JSONObject searchDeptAndMeLst() {
        //获取当前用户信息
        PageData userData = getUserPd();
        //返回当前人员下的部门数据
        JSONArray jsonobj = sysuserService.getSearchDeptAndMeLstJson(userData);
        JSONObject deptJsonLst = getSucJson(jsonobj);
        return deptJsonLst;
    }

    /**
     * 所有的部门数据
     * DEPT_ID部门ID
     */
    @RequestMapping("/searchDeptAndMeAllLst")
    @ResponseBody
    public JSONObject searchDeptAndMeAllLst() {
        //获取当前用户信息
        PageData userData = getUserPd();
        //返回当前人员下的部门数据
        JSONArray jsonobj = sysuserService.getSearchAllDeptLstJson(userData);
        JSONObject deptJsonLst = getSucJson(jsonobj);
        return deptJsonLst;
    }

    /**
     * 2018-04-26 新增需求
     * 修改用户部门
     * 传入参数
     * 变更用户的ID：USER_ID
     * 变更用户的新部门ID：DEPT_ID
     * 变更用户的新部门组织机构：DEPT_LAYERORDER
     */
    @RequestMapping("/changeDept")
    @ResponseBody
    public JSONObject changeDept() {
        // 构建初始数据
        PageData pd = getPageData();
        int state = sysuserService.updateSysUserDept(pd);
        return getAddOrUpdJson(state);
    }


    /**
     * 跳转在线用户 管理页面
     *
     * @Author: zhao yi
     * @Date : 2018/6/6 20:53
     * @return: net.sf.json.JSONObject
     */
    @RequestMapping("showOnlineIndex")
    public String showOnlineIndex() {
        return "html/system/onlinement/onlineIndex";
    }

    /**
     * 列表数据(所有部门)
     * DEPT_ID部门ID
     */
    @RequestMapping("/deptAllLstByDel")
    @ResponseBody
    public JSONObject deptAllLstByDel() {

        PageData userData = this.getPageData();

        //返回当前人员下的部门数据
        String [] ary = userData.getString("DEPT_LAYERORDER").split("-");
        userData.put("DEPT_ID", ary[ary.length - 1]);

        JSONArray jsonobj = sysuserService.getDeptAndMeLstJson(userData);
        JSONObject deptJsonLst = getSucJson(jsonobj);
        return deptJsonLst;

    }

}

