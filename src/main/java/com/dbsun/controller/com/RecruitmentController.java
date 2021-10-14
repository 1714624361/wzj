package com.dbsun.controller.com;

import com.dbsun.base.BaseController;
import com.dbsun.common.ResponseCode;
import com.dbsun.common.ServerResponse;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.service.com.RecruitmentService;
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
 * 招聘
 */
@Api(value = "rec", description = "招聘", tags = "招聘")
@Controller
@RequestMapping("/rec")
public class RecruitmentController extends BaseController {

	@Autowired
	private RecruitmentService recruitmentService;

	@RequestMapping(value = "/goRecruitmentPage", method = RequestMethod.GET)
	@ApiOperation(value = "跳转到招聘页面")
	public String goRecruitmentPage(){
		return "";
	}

	/**
	* 获取招聘列表数据
	* @return
	*/
	@RequestMapping(value = "/getPageRecruitmentList", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取招聘列表数据", notes = "分页")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "showCount", value = "每页记录数", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "currentPage", value = "当前页", required = true, dataType = "String"),
	})
	public JSONObject getPageRecruitmentList(){
		PageData pd = this.getPageData();
		Page page = this.getPage();
		page.setPd(pd);
		List<PageData> recruitmentList = null;
		try {
			recruitmentList = recruitmentService.getPageRecruitmentList(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewReturnPageData(page, recruitmentList);
	}

	/**
	 * 添加招聘
	 * @return
	 */
	@RequestMapping(value = "/addRecruitment", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "添加招聘", notes = "添加不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "position", value = "职位", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "pos_desc", value = "职位描述", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
	})
	//@TokenLimit
	public ServerResponse addRecruitment(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return recruitmentService.addRecruitment(this.putUserPd(pd));
	}

	/**
	 * 更新招聘
	 * @return
	 */
	@RequestMapping(value = "/updateRecruitment", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "更新招聘", notes = "更新不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "position", value = "职位", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "pos_desc", value = "职位描述", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
	})
	public ServerResponse updateRecruitmentByid(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return recruitmentService.updateRecruitmentByid(pd);
	}

	/**
	 * 获取招聘列表数据(非分页)
	 * @return
	 */
	@RequestMapping(value = "/getRecruitmentList", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取招聘列表数据非分页方法")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "position", value = "职位", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "pos_desc", value = "职位描述", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
	})
	public ServerResponse getRecruitmentList(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return recruitmentService.getRecruitmentList(pd);
	}

	/**
	 * 获取招聘单条数据(通过任意列)
	 * @return
	 */
	@RequestMapping(value = "/getRecruitmentByColumn", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取招聘单条数据(通过任意列)")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "position", value = "职位", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "pos_desc", value = "职位描述", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
	})
	public ServerResponse getRecruitmentByColumn(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return recruitmentService.getRecruitmentByColumn(pd);
	}

	/**
	 * 删除招聘
	 * @return
	 */
	@RequestMapping(value = "/deleteRecruitment", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "删除招聘", notes = "删除不为空的内容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "xxx", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "position", value = "职位", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "pos_desc", value = "职位描述", required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "create_time", value = "创建时间", required = false, dataType = "String"),
	})
	public ServerResponse deleteRecruitmentByColumn(){
		PageData pd = this.getPageData();

		PageData userPd = this.getUserPd();
		if(userPd == null){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
		}

		return recruitmentService.deleteRecruitmentByColumn(pd);
	}

}
