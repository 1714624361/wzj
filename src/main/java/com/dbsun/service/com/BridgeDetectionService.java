package com.dbsun.service.com;

import com.dbsun.common.ServerResponse;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.mapper.com.BridgeDetectionMapper;
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
public class BridgeDetectionService extends BaseService {

	@Autowired
	private BridgeDetectionMapper bridgeDetectionMapper;

	public List<PageData> getPageBridgeDetectionList(Page page){
		return bridgeDetectionMapper.getPageBridgeDetectionList(page);
	}

	public ServerResponse<List<PageData>> getBridgeDetectionList(PageData pd){
		if(Tools.isObjEmpty("111")){
			return ServerResponse.badArgument();
		}
		return ServerResponse.createBySuccess(bridgeDetectionMapper.getBridgeDetectionList(pd));
	}

	/**
	 * 添加
	 * @param pd
	 * @return
	 */
	@Transactional
	public ServerResponse<String> addBridgeDetection(PageData pd) {


		int rowCount = bridgeDetectionMapper.addBridgeDetection(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("添加桥梁检测成功");
		}

		return ServerResponse.createByErrorMessage("添加桥梁检测失败");
	}

	/**
	 * 修改桥梁检测
	 * @param pd
	 * @return
	 */
	@Transactional
	public ServerResponse<String> updateBridgeDetectionByid(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("修改桥梁检测参数错误");
		}

		int rowCount = bridgeDetectionMapper.updateBridgeDetectionByid(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("修改桥梁检测成功");
		}

		return ServerResponse.createByErrorMessage("修改桥梁检测失败");
	}

	/**
	 * 获取单条数据
	 * @param pd
	 * @return
	 */
	public ServerResponse<PageData> getBridgeDetectionByColumn(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("获取数据参数错误");
		}

		return ServerResponse.createBySuccess(bridgeDetectionMapper.getBridgeDetectionByColumn(pd));
	}

	/**
	 * 删除桥梁检测
	 * @param pd
	 * @return
	 */
	public ServerResponse<String> deleteBridgeDetectionByColumn(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("删除桥梁检测参数错误");
		}

		int rowCount = bridgeDetectionMapper.deleteBridgeDetectionByColumn(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("删除桥梁检测成功");
		}

		return ServerResponse.createByErrorMessage("删除桥梁检测失败");
	}
}
