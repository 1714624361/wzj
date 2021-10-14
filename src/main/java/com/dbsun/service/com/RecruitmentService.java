package com.dbsun.service.com;

import com.dbsun.common.ServerResponse;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.mapper.com.RecruitmentMapper;
import com.dbsun.service.BaseService;
import com.dbsun.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 */
@Service
public class RecruitmentService extends BaseService {

	@Autowired
	private RecruitmentMapper recruitmentMapper;

	public List<PageData> getPageRecruitmentList(Page page){
		return recruitmentMapper.getPageRecruitmentList(page);
	}

	public ServerResponse<List<PageData>> getRecruitmentList(PageData pd){
		if(Tools.isObjEmpty("111")){
			return ServerResponse.badArgument();
		}
		return ServerResponse.createBySuccess(recruitmentMapper.getRecruitmentList(pd));
	}

	/**
	 * 添加
	 * @param pd
	 * @return
	 */
	@Transactional
	public ServerResponse<String> addRecruitment(PageData pd) {


		int rowCount = recruitmentMapper.addRecruitment(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("添加招聘成功");
		}

		return ServerResponse.createByErrorMessage("添加招聘失败");
	}

	/**
	 * 修改招聘
	 * @param pd
	 * @return
	 */
	@Transactional
	public ServerResponse<String> updateRecruitmentByid(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("修改招聘参数错误");
		}

		int rowCount = recruitmentMapper.updateRecruitmentByid(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("修改招聘成功");
		}

		return ServerResponse.createByErrorMessage("修改招聘失败");
	}

	/**
	 * 获取单条数据
	 * @param pd
	 * @return
	 */
	public ServerResponse<PageData> getRecruitmentByColumn(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("获取数据参数错误");
		}

		return ServerResponse.createBySuccess(recruitmentMapper.getRecruitmentByColumn(pd));
	}

	/**
	 * 删除招聘
	 * @param pd
	 * @return
	 */
	public ServerResponse<String> deleteRecruitmentByColumn(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("删除招聘参数错误");
		}

		int rowCount = recruitmentMapper.deleteRecruitmentByColumn(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("删除招聘成功");
		}

		return ServerResponse.createByErrorMessage("删除招聘失败");
	}
}
