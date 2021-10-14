package com.dbsun.controller.com;

import com.dbsun.base.BaseController;
import com.dbsun.common.ResponseCode;
import com.dbsun.common.ServerResponse;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.service.com.EngineeringMaterialsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//import com.dbsun.core.TokenLimit;

/**
 * 工程材料
 */
@Api(value = "em", description = "工程材料", tags = "工程材料")
@Controller
@RequestMapping("/em")
public class EngineeringMaterialsController extends BaseController {

	@Autowired
	private EngineeringMaterialsService engineeringMaterialsService;

	@RequestMapping(value = "/goEngineeringMaterialsPage", method = RequestMethod.GET)
	@ApiOperation(value = "跳转到工程材料页面")
	public String goEngineeringMaterialsPage(){
		return "";
	}

	/**
	* 获取工程材料列表数据
	* @return
	*/
	@RequestMapping(value = "/getPageEngineeringMaterialsList", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取工程材料列表数据", notes = "分页")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "showCount", value = "每页记录数", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "currentPage", value = "当前页", required = true, dataType = "String"),
	})
	public JSONObject getPageEngineeringMaterialsList(){
		PageData pd = this.getPageData();
		Page page = this.getPage();
		page.setPd(pd);
		List<PageData> engineeringMaterialsList = null;
		try {
			engineeringMaterialsList = engineeringMaterialsService.getPageEngineeringMaterialsList(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewReturnPageData(page, engineeringMaterialsList);
	}

	/**
	 * 添加工程材料
	 * @return
	 */
	@RequestMapping(value = "/addEngineeringMaterials", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "添加工程材料", notes = "添加不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "content", value = "材料内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_status", value = "检测状态(1默认提交申请2审批通过3审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_id", value = "审批人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_name", value = "审批人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "type", value = "类型(1检测2鉴定)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
	})
	//@TokenLimit
	public ServerResponse addEngineeringMaterials(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return engineeringMaterialsService.addEngineeringMaterials(this.putUserPd(pd));
	}

	/**
	 * 更新工程材料
	 * @return
	 */
	@RequestMapping(value = "/updateEngineeringMaterials", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "更新工程材料", notes = "更新不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "content", value = "材料内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_status", value = "检测状态(1默认提交申请2审批通过3审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_id", value = "审批人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_name", value = "审批人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "type", value = "类型(1检测2鉴定)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
	})
	public ServerResponse updateEngineeringMaterialsByid(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return engineeringMaterialsService.updateEngineeringMaterialsByid(pd);
	}

	/**
	 * 获取工程材料列表数据(非分页)
	 * @return
	 */
	@RequestMapping(value = "/getEngineeringMaterialsList", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取工程材料列表数据非分页方法")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "content", value = "材料内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_status", value = "检测状态(1默认提交申请2审批通过3审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_id", value = "审批人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_name", value = "审批人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "type", value = "类型(1检测2鉴定)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
	})
	public ServerResponse getEngineeringMaterialsList(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return engineeringMaterialsService.getEngineeringMaterialsList(pd);
	}

	/**
	 * 获取工程材料单条数据(通过任意列)
	 * @return
	 */
	@RequestMapping(value = "/getEngineeringMaterialsByColumn", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取工程材料单条数据(通过任意列)")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "content", value = "材料内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_status", value = "检测状态(1默认提交申请2审批通过3审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_id", value = "审批人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_name", value = "审批人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "type", value = "类型(1检测2鉴定)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
	})
	public ServerResponse getEngineeringMaterialsByColumn(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return engineeringMaterialsService.getEngineeringMaterialsByColumn(pd);
	}

	/**
	 * 删除工程材料
	 * @return
	 */
	@RequestMapping(value = "/deleteEngineeringMaterials", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "删除工程材料", notes = "删除不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "content", value = "材料内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_status", value = "检测状态(1默认提交申请2审批通过3审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_id", value = "审批人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_name", value = "审批人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "type", value = "类型(1检测2鉴定)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
	})
	public ServerResponse deleteEngineeringMaterialsByColumn(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return engineeringMaterialsService.deleteEngineeringMaterialsByColumn(pd);
	}

}
