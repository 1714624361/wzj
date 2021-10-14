package com.dbsun.util;

import java.util.ArrayList;
import java.util.List;

import com.dbsun.service.SysUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dbsun.entity.system.Menu;
import com.dbsun.entity.system.PageData;


public class Util {
	public static SysUserService sysuserService = (SysUserService) SpringContextUtils.getBean(SysUserService.class);

	/**
	 * 对列表数据进行tree转换
	 * */
	public static JSONArray lstTreeToJson(List<Menu> inlst,String roleRights){
		
		JSONArray jsonArray = new JSONArray();
		//选中状态数据
		JSONObject jsoncheck = new JSONObject();
		jsoncheck.put("checked",true);
    	//为选中状态数据
    	JSONObject jsonuncheck = new JSONObject();
    	jsonuncheck.put("checked",false);
		List<XMGLTaskDTO > lst = new ArrayList();
		//先转换数据
		for(int i=0;i<inlst.size();i++){
			Menu menu = inlst.get(i);
			XMGLTaskDTO dto = new XMGLTaskDTO();
			dto.setId(menu.getMENU_ID());
			dto.setText(menu.getMENU_NAME());
			dto.setHref(menu.getMENU_URL());//存放父ID
			dto.setPid(menu.getPARENT_ID());//存放父ID
			lst.add(dto);
		}     
		for(XMGLTaskDTO node1 : lst){//taskDTOList 是数据库获取的List列表数据或者来自其他数据源的List
			int checked = 0;//用户计量每个父节点下得子节点选中数量
			JSONObject json = new JSONObject();
            boolean mark = false;  
            json.put("nodeId", node1.getId());
            json.put("text", node1.getText());
            json.put("href", node1.getHref());
            json.put("pid", node1.getPid());
            JSONArray jsnode2 = null;
            for(XMGLTaskDTO node2 : lst){
                if(node1.getId().equals(node2.getPid())){  //主数据主ID等于从数据父ID
                    mark = true;
                    //转换子节点数据
                    JSONObject json2 = new JSONObject();
                    json2.put("nodeId", node2.getId());
                    json2.put("text", node2.getText());
                    json2.put("href", node2.getHref());
                    json2.put("pid", node2.getPid());
                    if(jsnode2 == null){
                    	jsnode2 = new JSONArray();
                    }
                    //判定结果是否为真，为真表明应该被选中,
                    if(RightsHelper.testRights(roleRights, node2.getId())){
                    	json2.put("state",jsoncheck);//选中数据
                    	json2.put("check","check");//选中数据
                    	json2.put("allid",node2.getId());
                    	checked = checked+1;//选中一次追加一次
                    }else{
                    	json2.put("state",jsonuncheck);//未选中数据
                    	json2.put("check","uncheck");//未选中数据
                    	json2.put("allid",node2.getId());
                    }
                    jsnode2.add(json2);
                }
            }
            if(mark){
            	//判断子节点选中数目跟子节点数目一致则默认勾选当前父节点
            	if(jsnode2.size() == checked){
                	json.put("state",jsoncheck);//选中数据
                	json.put("check","check");//选中数据
                	json.put("allid",node1.getId());
            	}else{
            		json.put("state",jsonuncheck);//未选中数据
            		json.put("check","uncheck");//未选中数据
            		json.put("allid",node1.getId());
            	}
            	json.put("nodes",jsnode2);
            	jsonArray.add(json);
//                jsonArray.add(node1);
            }else if(node1.getPid().equals("0")){//表示没有子节点的父节点页需要显示
            	json.put("state",jsonuncheck);//没有子节点默认未选中
            	json.put("check","uncheck");//未选中数据
            	json.put("allid",node1.getId());
            	jsonArray.add(json);
            }
		}
		return jsonArray;
	}

	/**
	 * 对权限菜单进行重新组装
	 * */
	public static List<Menu> menuLstToRoleMenuLst(List<Menu> inlst,String roleRights){
		
		List<Menu> roleMenuLst = new ArrayList<Menu>(); 
		
		for(Menu node1 : inlst){
			int checked = 0;//用户计量每个父节点下得子节点选中数量
            boolean mark = false;  
           
            List<Menu> roleSubMenuLst = new ArrayList<Menu>();
            for(Menu node2 : inlst){
                if(node1.getMENU_ID().equals(node2.getPARENT_ID())){  //主数据主ID等于从数据父ID
                    mark = true;
                    //转换子节点数据
                    //判定结果是否为真，为真表明应该被选中,
                    if(RightsHelper.testRights(roleRights,node2.getMENU_ID())){
                    	roleSubMenuLst.add(node2);
                    	checked = checked+1;//选中一次追加一次
                    }
                }
            }
            if(mark){
            	//父节点没有勾选则不需要显示
            	if(checked != 0){
            		node1.setSubMenu(roleSubMenuLst);
                	roleMenuLst.add(node1);
            	}
            }
		}
		return roleMenuLst;
	}

	private static boolean isNotEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
				|| (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	public static   JSONArray treeMenuList(List<PageData> inlst, String parentId) {//
		JSONArray childDept = new JSONArray();
		String menuId;
		String pid;
		String DEPT_NAME;
		for (PageData object : inlst) {
			PageData jsonMenu = new PageData();
			menuId = object.getString("DEPT_ID");
			pid = object.getString("DEPT_PID");
			DEPT_NAME = object.getString("DEPT_NAME");
			jsonMenu.put("value",menuId);
			jsonMenu.put("label",DEPT_NAME);
			if (parentId.equals(pid)) {
				JSONArray c_node = treeMenuList(inlst, menuId);
				if(c_node.size()!=0){
					jsonMenu.put("children", c_node);
				}
				childDept.add(jsonMenu);
			}
		}
		return childDept;
	}
	/**
	 * 返回包含当前角色的部门信息
	 * */
	public static   JSONArray treeDeptAndMeList(List<PageData> inlst, String parentId) {//
		JSONArray childDept = new JSONArray();
		String DeptId;
		String pid;
		String DEPT_NAME;
		String DEPT_LAYERORDER;

		for (PageData object : inlst) {
			PageData jsonMenu = new PageData();
			DeptId = object.getString("DEPT_ID");
			pid = object.getString("DEPT_PID");
			DEPT_NAME = object.getString("DEPT_NAME");
			DEPT_LAYERORDER = object.getString("DEPT_LAYERORDER");
			jsonMenu.put("value",DeptId);
			jsonMenu.put("label",DEPT_NAME);
			jsonMenu.put("DEPT_LAYERORDER",DEPT_LAYERORDER);
			if (parentId.equals(pid)) {
				JSONArray c_node = treeDeptAndMeList(inlst, DeptId);
				if(c_node.size()!=0){
					jsonMenu.put("children", c_node);
				}
				childDept.add(jsonMenu);
			}
		}
		return childDept;
	}

	/**
	 * 返回包含当前角色的部门信息（列表页面查询专用）
	 * */
	public static   JSONArray searchTreeDeptAndMeList(List<PageData> inlst, String parentId) {//
		JSONArray childDept = new JSONArray();
		String DeptId;
		String pid;
		String DEPT_NAME;
		String DEPT_LAYERORDER;
		for (PageData object : inlst) {
			PageData jsonMenu = new PageData();
			DeptId = object.getString("DEPT_ID");
			pid = object.getString("DEPT_PID");
			DEPT_NAME = object.getString("DEPT_NAME");
			DEPT_LAYERORDER = object.getString("DEPT_LAYERORDER");
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("DEPT_ID",DeptId);
			jsonObj.put("DEPT_LAYERORDER",DEPT_LAYERORDER);
			jsonMenu.put("value",jsonObj);
			jsonMenu.put("label",DEPT_NAME);
			if (parentId.equals(pid)) {
				JSONArray c_node = searchTreeDeptAndMeList(inlst, DeptId);
				if(c_node.size()!=0){
					jsonMenu.put("children", c_node);
				}
				childDept.add(jsonMenu);
			}
		}
		return childDept;
	}
	//组装部门数据(树形选择)
	public static   JSONArray deptTreeList(List<PageData> inlst, String parentId) {//
		JSONArray childDept = new JSONArray();
		String id;
		String pid;
		String NAME;
		for (PageData object : inlst) {
			PageData jsonMenu = new PageData();
			id = object.getString("DEPT_ID");
			pid = object.getString("DEPT_PID");
			NAME = object.getString("DEPT_NAME");
			jsonMenu.put("id",id);
			jsonMenu.put("value",id);
			jsonMenu.put("pid",pid);
			jsonMenu.put("label",NAME);
			if (parentId.equals(pid)) {
				JSONArray c_node = deptTreeList(inlst, id);
				if(c_node.size()!=0){
					jsonMenu.put("children", c_node);
				}
				childDept.add(jsonMenu);
			}
		}
		return childDept;
	}
}
