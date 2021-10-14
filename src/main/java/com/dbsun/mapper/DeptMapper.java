package com.dbsun.mapper;

import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;

import java.util.List;


/**
 * 
 *
 */
public interface DeptMapper {
	

    /**
   	 * 新增部门信息
   	 * 
   	 * */
     public int saveDept(PageData pd);

	/**
	 * 修改部门信息
	 *
	 * */
	public int updateDept(PageData pd);

    /**
  	 * 删除部门
  	 * 
  	 * */
     public int deleteDept(PageData pd);
	/**
	 * 获取父级部门信息
	 *
	 * */
	public PageData getDeptByPId(PageData pd);
	/**
	 * 获取部门列表
	 * */
	public List<PageData> getDeptAllLst(PageData pd);

}
