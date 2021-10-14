package com.dbsun.controller.com;

import com.dbsun.base.BaseController;
import com.dbsun.common.ResponseCode;
import com.dbsun.common.ServerResponse;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.service.com.EngineeringDesignService;
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
 * 工程设计
 */
@Api(value = "ed", description = "工程设计", tags = "工程设计")
@Controller
@RequestMapping("/ed")
public class EngineeringDesignController extends BaseController {

	@Autowired
	private EngineeringDesignService engineeringDesignService;

	@RequestMapping(value = "/goEngineeringDesignPage", method = RequestMethod.GET)
	@ApiOperation(value = "跳转到工程设计页面")
	public String goEngineeringDesignPage(){
		return "";
	}

	/**
	* 获取工程设计列表数据
	* @return
	*/
	@RequestMapping(value = "/getPageEngineeringDesignList", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取工程设计列表数据", notes = "分页")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "showCount", value = "每页记录数", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "currentPage", value = "当前页", required = true, dataType = "String"),
	})
	public JSONObject getPageEngineeringDesignList(){
		PageData pd = this.getPageData();
		Page page = this.getPage();
		page.setPd(pd);
		List<PageData> engineeringDesignList = null;
		try {
			engineeringDesignList = engineeringDesignService.getPageEngineeringDesignList(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewReturnPageData(page, engineeringDesignList);
	}

	/**
	 * 添加工程设计
	 * @return
	 */
	@RequestMapping(value = "/addEngineeringDesign", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "添加工程设计", notes = "添加不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "task", value = "任务", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "apply_user_id", value = "申请人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "apply_user_name", value = "申请人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "status", value = "检测状态(1默认等待申请2提交申请3审批通过4审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "feasibility_study_report", value = "可行性分析报告", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "opinion", value = "单位意见", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "construction_bidding", value = "建设招标", required = false, dataType = "String"),
	})
	//@TokenLimit
	public ServerResponse addEngineeringDesign(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return engineeringDesignService.addEngineeringDesign(this.putUserPd(pd));
	}

	/**
	 * 更新工程设计
	 * @return
	 */
	@RequestMapping(value = "/updateEngineeringDesign", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "更新工程设计", notes = "更新不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "task", value = "任务", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "apply_user_id", value = "申请人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "apply_user_name", value = "申请人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "status", value = "检测状态(1默认等待申请2提交申请3审批通过4审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "feasibility_study_report", value = "可行性分析报告", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "opinion", value = "单位意见", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "construction_bidding", value = "建设招标", required = false, dataType = "String"),
	})
	public ServerResponse updateEngineeringDesignByid(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return engineeringDesignService.updateEngineeringDesignByid(pd);
	}

	/**
	 * 获取工程设计列表数据(非分页)
	 * @return
	 */
	@RequestMapping(value = "/getEngineeringDesignList", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取工程设计列表数据非分页方法")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "task", value = "任务", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "apply_user_id", value = "申请人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "apply_user_name", value = "申请人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "status", value = "检测状态(1默认等待申请2提交申请3审批通过4审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "feasibility_study_report", value = "可行性分析报告", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "opinion", value = "单位意见", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "construction_bidding", value = "建设招标", required = false, dataType = "String"),
	})
	public ServerResponse getEngineeringDesignList(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return engineeringDesignService.getEngineeringDesignList(pd);
	}

	/**
	 * 获取工程设计单条数据(通过任意列)
	 * @return
	 */
	@RequestMapping(value = "/getEngineeringDesignByColumn", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取工程设计单条数据(通过任意列)")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "task", value = "任务", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "apply_user_id", value = "申请人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "apply_user_name", value = "申请人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "status", value = "检测状态(1默认等待申请2提交申请3审批通过4审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "feasibility_study_report", value = "可行性分析报告", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "opinion", value = "单位意见", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "construction_bidding", value = "建设招标", required = false, dataType = "String"),
	})
	public ServerResponse getEngineeringDesignByColumn(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return engineeringDesignService.getEngineeringDesignByColumn(pd);
	}

	/**
	 * 删除工程设计
	 * @return
	 */
	@RequestMapping(value = "/deleteEngineeringDesign", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "删除工程设计", notes = "删除不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "task", value = "任务", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "apply_user_id", value = "申请人员ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "apply_user_name", value = "申请人员姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "status", value = "检测状态(1默认等待申请2提交申请3审批通过4审批未通过)", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "user_id", value = "用户ID", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "feasibility_study_report", value = "可行性分析报告", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "opinion", value = "单位意见", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "construction_bidding", value = "建设招标", required = false, dataType = "String"),
	})
	public ServerResponse deleteEngineeringDesignByColumn(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return engineeringDesignService.deleteEngineeringDesignByColumn(pd);
	}

}
