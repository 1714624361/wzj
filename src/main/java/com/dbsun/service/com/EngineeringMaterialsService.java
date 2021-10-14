package com.dbsun.service.com;

import com.dbsun.common.ServerResponse;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.mapper.com.EngineeringMaterialsMapper;
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
public class EngineeringMaterialsService extends BaseService {

	@Autowired
	private EngineeringMaterialsMapper engineeringMaterialsMapper;

	public List<PageData> getPageEngineeringMaterialsList(Page page){
		return engineeringMaterialsMapper.getPageEngineeringMaterialsList(page);
	}

	public ServerResponse<List<PageData>> getEngineeringMaterialsList(PageData pd){
		if(Tools.isObjEmpty("111")){
			return ServerResponse.badArgument();
		}
		return ServerResponse.createBySuccess(engineeringMaterialsMapper.getEngineeringMaterialsList(pd));
	}

	/**
	 * 添加
	 * @param pd
	 * @return
	 */
	@Transactional
	public ServerResponse<String> addEngineeringMaterials(PageData pd) {


		int rowCount = engineeringMaterialsMapper.addEngineeringMaterials(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("添加工程材料成功");
		}

		return ServerResponse.createByErrorMessage("添加工程材料失败");
	}

	/**
	 * 修改工程材料
	 * @param pd
	 * @return
	 */
	@Transactional
	public ServerResponse<String> updateEngineeringMaterialsByid(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("修改工程材料参数错误");
		}

		int rowCount = engineeringMaterialsMapper.updateEngineeringMaterialsByid(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("修改工程材料成功");
		}

		return ServerResponse.createByErrorMessage("修改工程材料失败");
	}

	/**
	 * 获取单条数据
	 * @param pd
	 * @return
	 */
	public ServerResponse<PageData> getEngineeringMaterialsByColumn(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("获取数据参数错误");
		}

		return ServerResponse.createBySuccess(engineeringMaterialsMapper.getEngineeringMaterialsByColumn(pd));
	}

	/**
	 * 删除工程材料
	 * @param pd
	 * @return
	 */
	public ServerResponse<String> deleteEngineeringMaterialsByColumn(PageData pd) {

		if(Tools.isObjEmpty(pd.get("id"))){
			return ServerResponse.createByErrorMessage("删除工程材料参数错误");
		}

		int rowCount = engineeringMaterialsMapper.deleteEngineeringMaterialsByColumn(pd);
		if(rowCount > 0){
			return ServerResponse.createBySuccessMessage("删除工程材料成功");
		}

		return ServerResponse.createByErrorMessage("删除工程材料失败");
	}
}
