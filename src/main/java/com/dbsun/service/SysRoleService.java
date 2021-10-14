package com.dbsun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbsun.entity.Role;
import com.dbsun.mapper.RoleMapper;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;

@Service
public class SysRoleService extends  BaseService{
	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 获取所有角色数据
	 * 
	 * */
	public List<PageData> getSysRoleLst(Page pd) {

		return roleMapper.getPageSysRoleLst(pd);
	}

	/**
	 * 获取指定角色数据
	 * 
	 * */
	public Role getSysRoleById(PageData pd) {
		Role role = roleMapper.getSysRoleById(pd);
		return role;
	}

	/**
	 * 插入新角色
	 * 
	 * */
	public int addSysRole(PageData pd) {
		pd.put("ROLE_ID",get32UUID());
		return roleMapper.addSysRole(pd);
	}

	/**
	 * 修改角色信息
	 * 
	 * */
	public int updateSysRole(PageData pd) {
		return roleMapper.updateSysRole(pd);
	}
	/**
	 * 修改角色名称
	 *
	 * */
	public int updateSysRoleName(PageData pd) {
		return roleMapper.updateSysRoleName(pd);
	}

	/**
	 * 删除角色
	 * 
	 * */
	public int delSysRole(PageData pd) {
		List<PageData> pdlst = roleMapper.getUserRoleById(pd);
		if(pdlst.size()>0){//当前角色已经有关联数据，表示不允许删除
			return 0;
		}
		return roleMapper.delSysRole(pd);
	}
}
