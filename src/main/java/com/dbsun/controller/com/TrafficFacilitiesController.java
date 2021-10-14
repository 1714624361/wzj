package com.dbsun.controller.com;

import com.dbsun.base.BaseController;
import com.dbsun.common.ResponseCode;
import com.dbsun.common.ServerResponse;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.service.com.TrafficFacilitiesService;
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
 * 交通设施
 */
@Api(value = "tf", description = "交通设施", tags = "交通设施")
@Controller
@RequestMapping("/tf")
public class TrafficFacilitiesController extends BaseController {

	@Autowired
	private TrafficFacilitiesService trafficFacilitiesService;

	@RequestMapping(value = "/goTrafficFacilitiesPage", method = RequestMethod.GET)
	@ApiOperation(value = "跳转到交通设施页面")
	public String goTrafficFacilitiesPage(){
		return "";
	}

	/**
	* 获取交通设施列表数据
	* @return
	*/
	@RequestMapping(value = "/getPageTrafficFacilitiesList", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取交通设施列表数据", notes = "分页")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "showCount", value = "每页记录数", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "currentPage", value = "当前页", required = true, dataType = "String"),
	})
	public JSONObject getPageTrafficFacilitiesList(){
		PageData pd = this.getPageData();
		Page page = this.getPage();
		page.setPd(pd);
		List<PageData> trafficFacilitiesList = null;
		try {
			trafficFacilitiesList = trafficFacilitiesService.getPageTrafficFacilitiesList(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewReturnPageData(page, trafficFacilitiesList);
	}

	/**
	 * 添加交通设施
	 * @return
	 */
	@RequestMapping(value = "/addTrafficFacilities", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "添加交通设施", notes = "添加不为空的内容")
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
	public ServerResponse addTrafficFacilities(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return trafficFacilitiesService.addTrafficFacilities(this.putUserPd(pd));
	}

	/**
	 * 更新交通设施
	 * @return
	 */
	@RequestMapping(value = "/updateTrafficFacilities", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "更新交通设施", notes = "更新不为空的内容")
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
	public ServerResponse updateTrafficFacilitiesByid(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return trafficFacilitiesService.updateTrafficFacilitiesByid(pd);
	}

	/**
	 * 获取交通设施列表数据(非分页)
	 * @return
	 */
	@RequestMapping(value = "/getTrafficFacilitiesList", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取交通设施列表数据非分页方法")
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
	public ServerResponse getTrafficFacilitiesList(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return trafficFacilitiesService.getTrafficFacilitiesList(pd);
	}

	/**
	 * 获取交通设施单条数据(通过任意列)
	 * @return
	 */
	@RequestMapping(value = "/getTrafficFacilitiesByColumn", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取交通设施单条数据(通过任意列)")
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
	public ServerResponse getTrafficFacilitiesByColumn(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return trafficFacilitiesService.getTrafficFacilitiesByColumn(pd);
	}

	/**
	 * 删除交通设施
	 * @return
	 */
	@RequestMapping(value = "/deleteTrafficFacilities", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "删除交通设施", notes = "删除不为空的内容")
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
	public ServerResponse deleteTrafficFacilitiesByColumn(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return trafficFacilitiesService.deleteTrafficFacilitiesByColumn(pd);
	}

}
