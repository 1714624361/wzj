package com.dbsun.service.com;

import com.dbsun.common.ServerResponse;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.mapper.com.TrafficFacilitiesMapper;
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
public class TrafficFacilitiesService extends BaseService {

	@Autowired
	private TrafficFacilitiesMapper trafficFacilitiesMapper;

	public List<PageData> getPageTrafficFacilitiesList(Page page){
		return trafficFacilitiesMapper.getPageTrafficFacilitiesList(page);
	}

	public ServerResponse<List<PageData>> getTrafficFacilitiesList(PageData pd){
		if(Tools.isObjEmpty("111")){
			return ServerResponse.badArgument();
		}
		return ServerResponse.createBySuccess(trafficFacilitiesMapper.getTrafficFacilitiesList(pd));
	}

	/**
	 * 添加
	 * @param pd
	 * @return
	 */
	@Transactional
	public ServerResponse<String> addTrafficFacilities(PageData pd) {


		int rowCount = trafficFacilitiesMapper.addTrafficFacilities(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("添加交通设施成功");
		}

		return ServerResponse.createByErrorMessage("添加交通设施失败");
	}

	/**
	 * 修改交通设施
	 * @param pd
	 * @return
	 */
	@Transactional
	public ServerResponse<String> updateTrafficFacilitiesByid(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("修改交通设施参数错误");
		}

		int rowCount = trafficFacilitiesMapper.updateTrafficFacilitiesByid(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("修改交通设施成功");
		}

		return ServerResponse.createByErrorMessage("修改交通设施失败");
	}

	/**
	 * 获取单条数据
	 * @param pd
	 * @return
	 */
	public ServerResponse<PageData> getTrafficFacilitiesByColumn(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("获取数据参数错误");
		}

		return ServerResponse.createBySuccess(trafficFacilitiesMapper.getTrafficFacilitiesByColumn(pd));
	}

	/**
	 * 删除交通设施
	 * @param pd
	 * @return
	 */
	public ServerResponse<String> deleteTrafficFacilitiesByColumn(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("删除交通设施参数错误");
		}

		int rowCount = trafficFacilitiesMapper.deleteTrafficFacilitiesByColumn(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("删除交通设施成功");
		}

		return ServerResponse.createByErrorMessage("删除交通设施失败");
	}
}
