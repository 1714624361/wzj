package com.dbsun.controller.system;

import java.math.BigInteger;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbsun.base.BaseController;
import com.dbsun.entity.Role;
import com.dbsun.entity.system.Menu;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.service.MenuService;
import com.dbsun.service.SysRoleService;
import com.dbsun.util.RightsHelper;
import com.dbsun.util.Tools;
import com.dbsun.util.Util;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{
	 @Autowired
	 private SysRoleService roleService;
	 
	 @Autowired
	 private MenuService menuService;
	 
	 /**
	  * 跳转到用户列表主页
	  * */
	@RequestMapping("/roleIndex")
    public String roleIndex(ModelMap model) {
		//构建初始数据
//		PageData pd = new PageData();
//		System.out.println(111);
//		page.setPd(pd);
//		List<PageData> userLst = sysuserService.getSysUserLst(page);
//		model.addAttribute("UserTableLst",userLst);
//		model.addAttribute("page",page);
       return "/html/system/roleList";
    }
	/**
	  * 角色列表数据
	  * */
	@RequestMapping("/roleLst")
	@ResponseBody
   public JSONObject roleLst(ModelMap model) {
		//构建初始数据
		PageData pd = getPageData();//前端传入数据
		Page page = getPage();//分页需要用到这个数据
		page.setPd(pd);
		List<PageData> userLst = roleService.getSysRoleLst(page);
       return viewReturnPageData(page,userLst);
   }
	
	/**
	 * 更新角色权限数据
	 * */
	@RequestMapping("/updateRole")
	@ResponseBody
    public JSONObject updateRole(ModelMap model) {
		//构建初始数据
		PageData pd = getPageData();
		String RIGHTS = pd.getString("RIGHTS");
//		int state = 0;
		if(Tools.isEmpty(pd.getString("ROLE_ID"))){//判定权限ID不能为空否则不能更新
			//roleService.addMenu(pd);
		}else{
			//更新
			if(Tools.notEmpty(RIGHTS)){
				BigInteger rights = RightsHelper.sumRights(Tools.str2StrArray(RIGHTS));
				pd.put("RIGHTS",rights);
			}
			roleService.updateSysRole(pd);
		}
		JSONObject json = new JSONObject();
		json.put("msg","200");
		json.put("result","更新成功！");
		return json;
    }
	
	@RequestMapping("/roleOpen")
    public String roleOpen(Page page,ModelMap model) {
	//构建初始数据
		PageData pd = getPageData();
		//获取选中的权限列表数据
//		Role role = roleService.getSysUserById(pd);
//		//构建权限菜单数据并且默认选中已经有的权限
//		List<Menu> menuLst = menuService.getMenuByAllnoPage();
//		JSONArray ja = Util.lstTreeToJson(menuLst);
//		model.addAttribute("roleTreeData",ja);
		model.addAttribute("ROLE_ID",pd.getString("ROLE_ID"));
		
       return "/html/system/roleOpenList";
    }
	/**
	 * 获取tree数据
	 * */
	@RequestMapping("/getRoleTree")
	@ResponseBody
    public JSONArray getRoleTree(ModelMap model) {
		//构建初始数据
		PageData pd = getPageData();
		System.out.println(pd.getString("ROLE_ID"));
		//获取所有菜单数据
		List<Menu> menuLst = menuService.getMenuByAllnoPage();
		//获取选中的权限列表数据
		Role role = roleService.getSysRoleById(pd);
		JSONArray ja = Util.lstTreeToJson(menuLst,role.getRIGHTS());
       return ja;
    }
	/**
	 * 新增角色跳转页面
	 * */
	@RequestMapping("/roleNewOpen")
	public String roleNewOpen() {
		return "/html/system/roleNewOpen";
	}

	/**
	 * 新增角色
	 * */
	@RequestMapping("/addRole")
	@ResponseBody
	public JSONObject addRole() {
		PageData pd = getPageData();
		int addNum = roleService.addSysRole(pd);
		return getAddOrUpdJson(addNum);
	}
	/**
	 * 删除角色
	 * */
	@RequestMapping("/delRole")
	@ResponseBody
	public JSONObject delRole() {
		PageData pd = getPageData();
		int num = roleService.delSysRole(pd);
		return getAddOrUpdJson(num);
	}
	/**
	 * 更新角色名称
	 * */
	@RequestMapping("/editRoleName")
	@ResponseBody
	public JSONObject editRoleName() {
		PageData pd = getPageData();
		int num = roleService.updateSysRoleName(pd);
		return getAddOrUpdJson(num);
	}
	/**
	 * 边角角色名称跳转页面
	 * */
	@RequestMapping("/roleEditOpen")
	public String roleEditOpen(Model model) {
		PageData pd = getPageData();
		model.addAttribute("ROLE_ID",pd.getString("ROLE_ID"));
		model.addAttribute("ROLE_NAME",pd.getString("ROLE_NAME"));
		return "/html/system/roleEditOpen";
	}
	
}
