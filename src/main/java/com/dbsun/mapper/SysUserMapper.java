package com.dbsun.mapper;

import com.dbsun.entity.system.Page;
import com.dbsun.entity.system.PageData;

import java.util.List;
import java.util.Map;


/**
 *
 *
 */
public interface SysUserMapper {

    /**
     * 获取所有用户数据
     */
    public List<PageData> getPageSysUserLst(Page pd);


    /**
     * 验证用户账号及密码是否存在
     */
    public PageData getLoginValidation(PageData pd);

    /**
     * 插入新用户
     */
    public int addSysUser(PageData pd);

    /**
     * 获取指定用户数据
     *
     * @param pd USER_ID
     * @return *
     */
    public PageData getSysUserById(PageData pd);

    /**
     * 修改用户信息
     */
    public int updateSysUser(PageData pd);

    /**
     * 修改用户信息(修改密码)
     */
    public int updateSysUserPsw(PageData pd);

    /**
     * 禁用用户
     */
    public int updateDisableSysUser(PageData pd);

    /**
     * 删除用户
     */
    public int delSysUser(PageData pd);

    /**
     * 获取指定部门下的所有用户数据
     */
    public List<PageData> getDeptAllUserLst(PageData pd);

    /**
     * 获取指定部门下的所有部门数据
     */
    public List<PageData> getDeptAllDeptLst(PageData pd);

    /**
     * 获取所有角色信息
     */
    public List<PageData> getRoleAllLst(PageData pd);

    /**
     * 获取当前用户的部门信息
     */
    public PageData getDeptForUserInfo(PageData pd);

    /**
     * 获取指定部门下的所有员工列表数据
     */
    public List<PageData> getPageUserForDeptIDLst(Page pd);

    /**
     * 获取指定部门下的用户列表数据（坐席状态统计）
     */
    public List<PageData> getSeatUserLst(PageData pd);

    /**
     * 获取指定部门下的所有部门数据包含当前部门(坐席状态统计)
     */
    public List<PageData> getSeatDeptLst(PageData pd);

    /**
     * 获取后台部门数据(房贷信贷)
     */
    public List<PageData> getBackDeptLst(PageData pd);

    /**
     * 获取后台部门员工做单汇总数据
     * 需要传入当前部门的组织机构代码
     */
    public List<PageData> getBackDeptUserLst(PageData pd);

    /**
     * 获取当前账号是否存在
     */
    public List<PageData> getSysUserIsTrue(PageData pd);

    /**
     * 2018-04-26 新增需求
     * 修改用户部门
     */
    public Object changeDept(Map map);

    /**
     * 根据坐席号查询 客服
     *
     * @Author: ZhaoYi
     * @Date : 2018/5/25 15:50
     * @Param : [numbers 坐席号集合]
     * @return: java.util.List<com.dbsun.entity.system.PageData>
     */
    public List<PageData> getSysUserLstByNumbers(PageData  pd);


    /**
     * -查询营销中心的所有部门
     *
     * @Author: ZhaoYi
     * @Date : 2018/5/25 15:50
     * @Param : []
     * @return: java.util.List<com.dbsun.entity.system.PageData>
     */
    public List<PageData> getDeptDeptLstBySalesman(PageData pd);

    /**
     * 获得客户经理所有坐席
     *
     * @Author: zhao yi
     * @Date : 2018/6/29 14:54
     * @Param : []
     * @return: java.util.List<com.dbsun.entity.system.PageData>
     */
    public List<PageData> getUserFreeSeatsNumber();

    /**
     *  离线客户 根据坐席号查询 客服
     * @Author: zhao yi
     * @Date  : 2018/7/11 14:05
     * @Param : [numbers]
     * @return: java.util.List<com.dbsun.entity.system.PageData>
     */
    public List<PageData> getSysUserLstByOffLineNumbers(PageData  pd);

    /**
     * 检查坐席号是否冲突
     * @Author: zhao yi
     * @Date  :  2018/7/11 20:32
     * @Param : [pd]
     * @return:
     */
    public PageData checkSeatNumber(PageData pd);

    /**
     * 通过坐席号获取用户信息
     * @param pd
     * @return
     */
    public PageData getUserByNumber(PageData pd);
}
