package com.dbsun.controller.com;

import com.dbsun.base.BaseController;
import com.dbsun.common.ResponseCode;
import com.dbsun.common.ServerResponse;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.service.com.BridgeDetectionService;
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
 * 桥梁检测
 */
@Api(value = "bd", description = "桥梁检测", tags = "桥梁检测")
@Controller
@RequestMapping("/bd")
public class BridgeDetectionController extends BaseController {

	@Autowired
	private BridgeDetectionService bridgeDetectionService;

	@RequestMapping(value = "/goBridgeDetectionPage", method = RequestMethod.GET)
	@ApiOperation(value = "跳转到桥梁检测页面")
	public String goBridgeDetectionPage(){
		return "";
	}

	/**
	* 获取桥梁检测列表数据
	* @return
	*/
	@RequestMapping(value = "/getPageBridgeDetectionList", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取桥梁检测列表数据", notes = "分页")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "showCount", value = "每页记录数", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "currentPage", value = "当前页", required = true, dataType = "String"),
	})
	public JSONObject getPageBridgeDetectionList(){
		PageData pd = this.getPageData();
		Page page = this.getPage();
		page.setPd(pd);
		List<PageData> bridgeDetectionList = null;
		try {
			bridgeDetectionList = bridgeDetectionService.getPageBridgeDetectionList(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewReturnPageData(page, bridgeDetectionList);
	}

	/**
	 * 添加桥梁检测
	 * @return
	 */
	@RequestMapping(value = "/addBridgeDetection", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "添加桥梁检测", notes = "添加不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "content", value = "材料内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_status", value = "检测状态(1默认提交申请2审批通过3审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_id", value = "审批人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_name", value = "审批人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
	})
	//@TokenLimit
	public ServerResponse addBridgeDetection(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return bridgeDetectionService.addBridgeDetection(this.putUserPd(pd));
	}

	/**
	 * 更新桥梁检测
	 * @return
	 */
	@RequestMapping(value = "/updateBridgeDetection", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "更新桥梁检测", notes = "更新不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "content", value = "材料内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_status", value = "检测状态(1默认提交申请2审批通过3审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_id", value = "审批人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_name", value = "审批人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
	})
	public ServerResponse updateBridgeDetectionByid(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return bridgeDetectionService.updateBridgeDetectionByid(pd);
	}

	/**
	 * 获取桥梁检测列表数据(非分页)
	 * @return
	 */
	@RequestMapping(value = "/getBridgeDetectionList", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取桥梁检测列表数据非分页方法")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "content", value = "材料内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_status", value = "检测状态(1默认提交申请2审批通过3审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_id", value = "审批人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_name", value = "审批人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
	})
	public ServerResponse getBridgeDetectionList(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return bridgeDetectionService.getBridgeDetectionList(pd);
	}

	/**
	 * 获取桥梁检测单条数据(通过任意列)
	 * @return
	 */
	@RequestMapping(value = "/getBridgeDetectionByColumn", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取桥梁检测单条数据(通过任意列)")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "content", value = "材料内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_status", value = "检测状态(1默认提交申请2审批通过3审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_id", value = "审批人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_name", value = "审批人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
	})
	public ServerResponse getBridgeDetectionByColumn(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return bridgeDetectionService.getBridgeDetectionByColumn(pd);
	}

	/**
	 * 删除桥梁检测
	 * @return
	 */
	@RequestMapping(value = "/deleteBridgeDetection", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "删除桥梁检测", notes = "删除不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "content", value = "材料内容", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_status", value = "检测状态(1默认提交申请2审批通过3审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_id", value = "审批人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "detection_user_name", value = "审批人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
	})
	public ServerResponse deleteBridgeDetectionByColumn(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return bridgeDetectionService.deleteBridgeDetectionByColumn(pd);
	}

}
