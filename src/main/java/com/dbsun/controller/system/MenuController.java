package com.dbsun.controller.system;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbsun.base.BaseController;
import com.dbsun.entity.system.Menu;
import com.dbsun.service.MenuService;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.util.Util;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	
	@Autowired
	private MenuService menuService;

	/**
	 * 跳转到菜单列表主页
	 * */
	@RequestMapping("/menuIndex")
	public String menuIndex(Page page, ModelMap model) {
		// 构建初始数据
		// PageData pd = new PageData();
		// pd.put("name", "111");
		// page.setPd(pd);
		// List<PageData> menuLst = menuService.getAllMenuLst(page);
		// model.addAttribute("menuTableLst",menuLst);
		// model.addAttribute("page",page);
		return "/html/system/menuList";
	}

	/**
	 * 获取tree数据
	 * */
	@RequestMapping("/saveMenu")
	public String saveMenu(Page page, ModelMap model) {
		// 构建初始数据
		PageData pd = getPageData();
		System.out.println(pd.get("MENU_ID"));
		// int state = 0;
		if (pd.get("MENU_ID") == null || pd.get("MENU_ID").equals("")) {// 判定是否有主ID，有则表示是更新
			// 新增
			menuService.addMenu(pd);
		} else {
			// 更新
			menuService.updateMenu(pd);
		}
		return "/html/system/menuList";
	}

	/**
	 * 获取tree数据
	 * */
	@RequestMapping("/delMenu")
	@ResponseBody
	public String delMenu(Page page, ModelMap model) {
		// 构建初始数据
		System.out.println("1111");
		PageData pd = getPageData();
		menuService.delMenu(pd);
		return "111";
	}

	@RequestMapping("/menuIndex2")
	public String menuIndex2(Page page, ModelMap model) {
		// 构建初始数据
		PageData pd = new PageData();
		pd.put("name", "111");
		page.setPd(pd);
		List<PageData> menuLst = menuService.getAllMenuLst(page);
		model.addAttribute("menuTableLst", menuLst);
		model.addAttribute("page", page);
		return "/html/system/table_jqgrid";
	}

	@RequestMapping("/menuIndex3")
	@ResponseBody
	public JSONObject menuIndex3(ModelMap model) {
		// 构建初始数据

		PageData pd = getPageData();// 前端传入数据
		Page page = getPage();// 分页需要用到这个数据
		page.setPd(pd);
		System.out.println("传入查询参数" + pd.getString("seachVal"));
		List<PageData> menuLst = menuService.getAllMenuLst(page);
		JSONObject json = new JSONObject();// 返回数据必须包含这个格式
		json.put("total", page.getTotalResult());
		json.put("rows", menuLst);
		return json;
	}

	@RequestMapping("/menuOpen")
	public String menuOpen(Page page, ModelMap model) {
		// 构建初始数据
		PageData pd = new PageData();
		return "/html/system/form_validate";
	}

	/**
	 * 获取tree数据
	 * */
	@RequestMapping("/getTree")
	@ResponseBody
	public JSONArray getTree(Page page, ModelMap model) {
		// 构建初始数据
		PageData pd = new PageData();
		pd.put("name", "111");
		page.setPd(pd);
		List<Menu> menuLst = menuService.getMenuByAllnoPage();
		JSONArray ja = Util.lstTreeToJson(menuLst,null);
		return ja;
	}

}
