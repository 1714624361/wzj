package com.dbsun.service;

import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.mapper.DeptMapper;
import com.dbsun.mapper.SysUserMapper;
import com.dbsun.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService extends  BaseService{
	@Autowired
	private DeptMapper deptMapper;


	/**
	 * 新增新部门
	 * 
	 * */
	public int addDept(PageData pd) {

		String deptid = get32UUID();
		pd.put("DEPT_ID",deptid);//获取uuid
		//获取当前父ID的组织机构代码
		PageData ppd = deptMapper.getDeptByPId(pd);
		pd.put("DEPT_LAYERORDER",ppd.getString("DEPT_LAYERORDER")+"-"+deptid);//生成组织机构代码

		return deptMapper.saveDept(pd);
	}


	/**
	 * 修改部门信息
	 * 
	 * */
	public int updateDept(PageData pd) {

		return deptMapper.updateDept(pd);
	}

	/**
	 * 删除部门
	 * 
	 * */
	public int delDept(PageData pd) {
		return deptMapper.deleteDept(pd);
	}

	/**
	 * 获取部门列表(树形tree)
	 * */
	public JSONArray getDeptTreeLst(PageData pd){
		List<PageData>  pdaLst = deptMapper.getDeptAllLst(pd);
		//获取当前大类的ID传入组装参数
		JSONArray lstAry =  Util.deptTreeList(pdaLst,"0");
		return lstAry;
	}
}
