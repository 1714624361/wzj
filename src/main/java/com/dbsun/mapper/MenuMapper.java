package com.dbsun.mapper;

import java.util.List;

import com.dbsun.entity.SysUserq;
import com.dbsun.entity.system.Menu;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;





/**
 * 
 *
 */
public interface MenuMapper {
	
	/**
	 * 获取导航菜单
	 */
	public List<Menu> listAllParentMenu(SysUserq user);
	
	/**
	 * 获取所有菜单数据
	 * 
	 * */
	public List<PageData> getPageMenuByAll(Page pd);
	
	/**
	 * 获取指定父ID下的子菜单
	 * */
	public  List<Menu> listSubMenuByParentId(String parId);

	/**
	 * 获取所有菜单数据 无分页
	 * 
	 * */
	public List<Menu> getByMenuAllnoPage();
	
	/**
	 * 修改菜单信息
	 * 
	 * */
	public int updateMenu(Menu menu);
	
	/**
	 * 新增菜单信息
	 * 
	 * */
	public int addMenu(Menu menu);
	
	/**
	 * 新增菜单信息
	 * 
	 * */
	public int deleteMenuById(String[] str);
	
	
}
