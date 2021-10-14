package com.dbsun.service;

import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;
import com.dbsun.mapper.SysUserMapper;
import com.dbsun.util.Tools;
import com.dbsun.util.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserService extends BaseService {
    @Autowired
    private SysUserMapper sysuserMapper;

    /**
     * 获取所有用户数据
     */
    public List<PageData> getSysUserLst(Page pd) {

        return sysuserMapper.getPageSysUserLst(pd);
    }

    /**
     * 获取指定用户数据
     */
    public PageData getSysUserById(PageData pd) {

        return sysuserMapper.getSysUserById(pd);
    }

    /**
     * 验证用户账号及密码是否存在
     */
    public PageData getLoginValidation(PageData pd) {
        return sysuserMapper.getLoginValidation(pd);
    }

    /**
     * 插入新用户
     * 有此账户存在返回-1
     */
    public int addSysUser(PageData pd) {
        //2018-04-26 增加需求 增加账号需要判定此账号是否存在
        List<PageData> userPda = sysuserMapper.getSysUserIsTrue(pd);
        if (userPda.size() != 0) {
            return -1;
        }
        pd.put("USER_ID", getUUID());
        String passwd = new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString(); // 密码加密
        pd.put("PASSWORD", passwd);
        return sysuserMapper.addSysUser(pd);
    }

    /**
     * 获取所有的角色信息
     */
    public List<PageData> getSysRoleAllLst(PageData pd) {

        return sysuserMapper.getRoleAllLst(pd);
    }

    /**
     * 修改用户信息
     */
    public int updateSysUser(PageData pd) {

        if (!Tools.isObjEmpty(pd.get("pswIsTrue"))) {//不等于空需要验证密码
            //如果密码跟原来的密码一样则不需要更新密码//老密码跟新密码一致则不需要更新
            if (pd.getString("pswIsTrue").equals("1")) {//0表示未修改1表示已修改
                String passwd = new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString(); // 密码加密
                pd.put("PASSWORD", passwd);
            } else {
                pd.put("PASSWORD", null);
            }
        } else {//等于空表示不需要更新密码
            pd.put("PASSWORD", null);
        }

        return sysuserMapper.updateSysUser(pd);
    }

    /**
     * 修改用户信息(修改密码)
     */
    public int updateSysUserPsw(PageData pd) {
        String passwd = new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString(); // 密码加密
        pd.put("PASSWORD", passwd);
        return sysuserMapper.updateSysUserPsw(pd);
    }

    /**
     * 禁用用户
     */
    public int updateDisableSysUser(PageData pd) {
        return sysuserMapper.updateDisableSysUser(pd);
    }

    /**
     * 删除用户
     */
    public int delSysUser(PageData pd) {

        return 0;
    }

    /**
     * 获取用户树状结构数据
     */
    public List<PageData> getUserLstJson(PageData pd) {
        //获取部门列表（包含当前部门）
        List<PageData> pageData = sysuserMapper.getDeptAllUserLst(pd);
        //获取员工列表
        return pageData;
    }

    /**
     * (根据当前用户角色)获取部门树状结构数据
     */
    public JSONArray getDeptLstJson(PageData pd) {
        //获取部门列表（包含当前部门）
        List<PageData> pageData = sysuserMapper.getDeptAllDeptLst(pd);
        //组装json格式数据
        JSONArray ja = Util.treeMenuList(pageData, pd.getString("DEPT_ID"));
        return ja;
    }

    /**
     * (根据当前用户角色包含当前部门)获取部门数据
     */
    public JSONArray getDeptAndMeLstJson(PageData pd) {
        //获取部门列表（包含当前部门）
        List<PageData> pageData = sysuserMapper.getDeptAllDeptLst(pd);
        //获取当前用户的部门信息
        PageData userDeptInfo = sysuserMapper.getDeptForUserInfo(pd);
        //组装json格式数据
        JSONArray ja = Util.treeDeptAndMeList(pageData, pd.getString("DEPT_ID"));
        JSONObject json = new JSONObject();
        json.put("value", userDeptInfo.getString("DEPT_ID"));
        json.put("label", userDeptInfo.getString("DEPT_NAME"));
        if (ja.size() > 0) {
            json.put("children", ja);
        }
        JSONArray jsonary = new JSONArray();
        jsonary.add(json);

        return jsonary;
    }

    /**
     * (根据当前用户角色包含当前部门)获取部门数据(查询页面专用)
     */
    public JSONArray getSearchDeptAndMeLstJson(PageData pd) {
        //获取部门列表包含当前部门)
        List<PageData> pageData = sysuserMapper.getDeptAllDeptLst(pd);
        //获取当前用户的部门信息
        PageData userDeptInfo = sysuserMapper.getDeptForUserInfo(pd);
        //组装json格式数据
        JSONArray ja = Util.searchTreeDeptAndMeList(pageData, pd.getString("DEPT_ID"));
        JSONObject json = new JSONObject();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("DEPT_ID", userDeptInfo.getString("DEPT_ID"));
        jsonObj.put("DEPT_LAYERORDER", userDeptInfo.getString("DEPT_LAYERORDER"));
        json.put("value", jsonObj);
        json.put("label", userDeptInfo.getString("DEPT_NAME"));
        if (ja.size() > 0) {//有子部门才封装
            json.put("children", ja);
            JSONArray jsonary = new JSONArray();
            jsonary.add(json);
            return jsonary;
        } else {//没有子部门
            JSONArray jsonary = new JSONArray();
            String POSITION = pd.get("POSITION") + "";
            if (POSITION.equals("1")) {//是否主管：主管需要加部门
                jsonary.add(json);
            }
            return jsonary;//返回空部门表明当前用户是最低级
        }
    }

    /**
     * 获取部门树状结构数据
     */
    public JSONArray getDeptAllLstJson(PageData pd) {
        //获取部门列表（包含当前部门）
        List<PageData> pageData = sysuserMapper.getDeptAllDeptLst(pd);
        //组装json格式数据
        JSONArray ja = Util.treeMenuList(pageData, "1");
        return ja;
    }

    /**
     * 获取指定部门下的所有员工数据(只包含当前部门不包含子部门的员工数据)
     */
    public List<PageData> getDeptAllLst(Page pd) {
        //获取部门列表（包含当前部门）
        List<PageData> pageLst = sysuserMapper.getPageUserForDeptIDLst(pd);
        return pageLst;
    }

    /**
     * 获取指定部门下的所有员工数据(用于统计查询坐席呼叫状态)
     */
    public List<PageData> getSeatUserLst(PageData pd) {
        List<PageData> pageLst = sysuserMapper.getSeatUserLst(pd);
        return pageLst;
    }


    /**
     * 给util 使用，查询当前部门下的做单人员做单情况汇总
     */
    public List<PageData> getBackDeptUserLst(PageData pd) {
        List<PageData> pageLst = sysuserMapper.getBackDeptUserLst(pd);
        return pageLst;
    }

    /**
     * 获取所有部门数据(查询页面专用)
     */
    public JSONArray getSearchAllDeptLstJson(PageData pd) {
        //获取部门列表(所有部门)
        PageData nulldta = new PageData();
        //获取部门列表（包含当前部门）
        List<PageData> pageData = sysuserMapper.getDeptAllDeptLst(nulldta);
        //组装json格式数据
        JSONArray ja = Util.searchTreeDeptAndMeList(pageData, "0");
        System.out.println(ja);
        return ja;
    }

    /**
     * 2018-04-26 新增需求
     * 修改用户部门
     */
    public int updateSysUserDept(PageData pd) {
        int ret = 0;
        Map map = new HashMap();
        try {
            map.put("inuserid", pd.get("USER_ID")); //更改用户ID
            map.put("indeptlayerorder", pd.get("DEPT_LAYERORDER"));//更改用户新的组织机构ID
            map.put("indeptid", pd.get("DEPT_ID"));//更改用户新的部门ID
            sysuserMapper.changeDept(map);
            if (map.get("state").equals(0)) ret = 1;//成功
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 查询已经注册的 离线坐席号
     *
     * @param onLineNumber
     * @Author: zhao yi
     * @Date : 2018/6/29 14:54
     * @Param : []
     * @return: java.util.List<com.dbsun.entity.system.PageData>
     */
    public JSONArray getUserRegisteredOffLineNumber(JSONArray onLineNumber, PageData pd) {
        //获得坐席号
        List<String> nums = new ArrayList<>();
        for (int i = 0; i < onLineNumber.size(); i++) {
            JSONObject json = (JSONObject) onLineNumber.get(i);
            JSONArray array = (JSONArray) json.get("seatLst");
            for (int j = 0; j < array.size(); j++) {
                JSONObject temp = (JSONObject) array.get(j);
                nums.add(temp.getString("number"));
            }
        }
        pd.put("numbers", nums);
        //离线用户
        List<PageData> rs = sysuserMapper.getSysUserLstByOffLineNumbers(pd);
        //分组
        JSONArray array = new JSONArray();
        for (PageData r : rs) {
            List<JSONObject> arr = new ArrayList<>();
            JSONObject json1 = new JSONObject();
            json1.put("DEPT_ID", r.getString("DEPT_ID"));
            json1.put("DEPT_NAME", r.getString("DEPT_NAME"));
            final String[] names = r.getString("name").split(",");
            final String[] numbers = r.getString("number").split(",");
            int len = names.length;//长度
            for (int i = 0; i < len; i++) {
                JSONObject json2 = new JSONObject();
                json2.put("name", names[i]);
                json2.put("number", numbers[i]);
                arr.add(json2);
            }
            json1.put("seatLst", arr);
            array.add(json1);
        }
        return array;
    }

    /**
     * 检查用户坐席是否冲突
     *
     * @Author: zhao yi
     * @Date : 2018/6/29 14:54
     * @Param : []
     * @return: java.util.List<com.dbsun.entity.system.PageData>
     */
    private boolean examineUserNumber(int startNum, int endNum, final String NUMBER) {
        //遍历所有的用户编号
        List<PageData> list = sysuserMapper.getUserFreeSeatsNumber();
        //当前存在坐席号码集合
        List<Integer> numberLst = new ArrayList<>();
        for (Integer i = startNum; i <= endNum; i++) {
            for (PageData data : list) {
                if (data.getString("NUMBER").equals(i.toString())) {
                    numberLst.add(i);
                    break;
                }
            }

        }
        //是否存在
        return numberLst.contains(Integer.parseInt(NUMBER));
    }

}
