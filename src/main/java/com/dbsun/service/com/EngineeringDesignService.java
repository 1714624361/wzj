package com.dbsun.service.com;

import com.dbsun.common.ServerResponse;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.mapper.com.EngineeringDesignMapper;
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
public class EngineeringDesignService extends BaseService {

	@Autowired
	private EngineeringDesignMapper engineeringDesignMapper;

	public List<PageData> getPageEngineeringDesignList(Page page){
		return engineeringDesignMapper.getPageEngineeringDesignList(page);
	}

	public ServerResponse<List<PageData>> getEngineeringDesignList(PageData pd){
		if(Tools.isObjEmpty("111")){
			return ServerResponse.badArgument();
		}
		return ServerResponse.createBySuccess(engineeringDesignMapper.getEngineeringDesignList(pd));
	}

	/**
	 * 添加
	 * @param pd
	 * @return
	 */
	@Transactional
	public ServerResponse<String> addEngineeringDesign(PageData pd) {


		int rowCount = engineeringDesignMapper.addEngineeringDesign(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("添加工程设计成功");
		}

		return ServerResponse.createByErrorMessage("添加工程设计失败");
	}

	/**
	 * 修改工程设计
	 * @param pd
	 * @return
	 */
	@Transactional
	public ServerResponse<String> updateEngineeringDesignByid(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("修改工程设计参数错误");
		}

		int rowCount = engineeringDesignMapper.updateEngineeringDesignByid(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("修改工程设计成功");
		}

		return ServerResponse.createByErrorMessage("修改工程设计失败");
	}

	/**
	 * 获取单条数据
	 * @param pd
	 * @return
	 */
	public ServerResponse<PageData> getEngineeringDesignByColumn(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("获取数据参数错误");
		}

		return ServerResponse.createBySuccess(engineeringDesignMapper.getEngineeringDesignByColumn(pd));
	}

	/**
	 * 删除工程设计
	 * @param pd
	 * @return
	 */
	public ServerResponse<String> deleteEngineeringDesignByColumn(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("删除工程设计参数错误");
		}

		int rowCount = engineeringDesignMapper.deleteEngineeringDesignByColumn(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("删除工程设计成功");
		}

		return ServerResponse.createByErrorMessage("删除工程设计失败");
	}
}
