package com.dbsun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbsun.entity.system.Menu;
import com.dbsun.mapper.MenuMapper;
import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;

@Service
public class MenuService {
	@Autowired
	private MenuMapper menuMapper;

	public List<Menu> getMenuLst() {
		List<Menu> rl = menuMapper.listAllParentMenu(null);
		for (Menu menu : rl) {
			List<Menu> subList = menuMapper.listSubMenuByParentId(menu
					.getMENU_ID());
			menu.setSubMenu(subList);
		}
		return rl;
	}

	public List<PageData> getAllMenuLst(Page pd) {
		List<PageData> rl = menuMapper.getPageMenuByAll(pd);
		return rl;
	}

	/**
	 * 获取所有菜单数据 无分页
	 * 
	 * */
	public List<Menu> getMenuByAllnoPage() {
		List<Menu> rl = menuMapper.getByMenuAllnoPage();
		return rl;
	}

	/**
	 * 插入新菜单数据
	 * 
	 * */
	public int addMenu(PageData pd) {
		Menu menu = new Menu();
		menu.setMENU_NAME(pd.getString("MENU_NAME"));
		menu.setMENU_URL(pd.getString("MENU_URL"));
		menu.setPARENT_ID(pd.getString("PARENT_ID"));
		menuMapper.addMenu(menu);
		return 0;
	}

	/**
	 * 修改菜单数据
	 * 
	 * */
	public int updateMenu(PageData pd) {
		Menu menu = new Menu();
		menu.setMENU_ID(pd.getString("MENU_ID"));
		menu.setMENU_NAME(pd.getString("MENU_NAME"));
		menu.setMENU_URL(pd.getString("MENU_URL"));
		menu.setPARENT_ID(pd.getString("PARENT_ID"));
		menuMapper.updateMenu(menu);
		return 0;
	}

	/**
	 * 删除选中菜单数据
	 * 
	 * */
	public int delMenu(PageData pd) {
		String mid = pd.getString("MENU_ID");
		String[] midarray = mid.split(",");
		menuMapper.deleteMenuById(midarray);
		return 0;
	}
}
