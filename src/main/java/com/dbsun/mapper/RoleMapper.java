package com.dbsun.mapper;

import java.util.List;

import com.dbsun.entity.Role;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;

/**
 * 
 *
 */
public interface RoleMapper {
	
	/**
   	 * 获取所有角色数据
   	 * 
   	 * */
    public List<PageData> getPageSysRoleLst(Page pd);
    
	/**
	 * 插入新角色
	 * 
	 * */
	 public int addSysRole(PageData pd);
	 
	/**
	 * 获取指定角色数据
	 * 
	 * */
	 public Role getSysRoleById(PageData pd);		 
		 
    /**
   	 * 修改角色信息
   	 * 
   	 * */
     public int updateSysRole(PageData pd);
	/**
	 * 修改角色名称
	 *
	 * */
	public int updateSysRoleName(PageData pd);

    
     /**
  	 * 删除角色
  	 * 
  	 * */
     public int delSysRole(PageData pd);
	/**
	 * 删除前查询当前角色是否有用户数据有则不能删除
	 *
	 * */
	public List<PageData> getUserRoleById(PageData pd);

	
}
