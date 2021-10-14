package com.dbsun.controller.system;

import com.dbsun.base.BaseController;
import com.dbsun.entity.system.PageData;
import com.dbsun.service.DeptService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {
	@Autowired
	private DeptService deptService;

	/**
	 * 跳转到部门主页
	 * */
	@RequestMapping("/deptIndex")
	public String userIndex(ModelMap model) {
		return "/html/system/department/department";
	}

	/**
	 * 添加新部门
	 *
	 */
	@RequestMapping("/addDept")
	@ResponseBody
	public JSONObject addDept() {
		// 构建初始数据
		PageData pd = getPageData();
		int state  = deptService.addDept(pd);
		if(state>0){
			return getSucJson();
		}else{
			return getFalJson();
		}
	}
	/**
	 * 部门编辑
	 *
	 */
	@RequestMapping("/updateDept")
	@ResponseBody
	public JSONObject updateDept() {
		// 构建初始数据
		PageData pd = getPageData();
		int state  = deptService.updateDept(pd);
		if(state>0){
			return getSucJson();
		}else{
			return getFalJson();
		}
	}
	/**
	 * 删除部门
	 */
	@RequestMapping("/delDept")
	@ResponseBody
	public JSONObject delDept() {
		PageData pd = getPageData();
		int state  = deptService.delDept(pd);
		if(state>0){
			return getSucJson();
		}else{
			return getFalJson();
		}
	}
	/**
	 * 显示左侧所有的部门树形结构
	 *
	 * */
	@RequestMapping(value = "/getTreeDeptLst",method= RequestMethod.GET)
	@ResponseBody
	public JSONObject getTreeDeptLst(){
		PageData pd = getPageData();
		JSONArray productLst =  deptService.getDeptTreeLst(pd);
		return getSucJson(productLst);
	}
}
