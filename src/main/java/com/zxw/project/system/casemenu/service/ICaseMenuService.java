package com.zxw.project.system.casemenu.service;

import java.util.List;

import com.zxw.project.system.casemenu.domain.CaseMenu;

/**
 * 前台菜单管理Service接口
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
public interface ICaseMenuService {
    /**
     * 查询前台菜单管理
     *
     * @param caseMenuId 前台菜单管理ID
     * @return 前台菜单管理
     */
    public CaseMenu selectCaseMenuById(Integer caseMenuId);

    /**
     * 查询前台菜单管理列表
     *
     * @param caseMenu 前台菜单管理
     * @return 前台菜单管理集合
     */
    public List<CaseMenu> selectCaseMenuList(CaseMenu caseMenu);

    /**
     * 新增前台菜单管理
     *
     * @param caseMenu 前台菜单管理
     * @return 结果
     */
    public int insertCaseMenu(CaseMenu caseMenu);

    /**
     * 修改前台菜单管理
     *
     * @param caseMenu 前台菜单管理
     * @return 结果
     */
    public int updateCaseMenu(CaseMenu caseMenu);

    /**
     * 批量删除前台菜单管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCaseMenuByIds(String ids);

    /**
     * 删除前台菜单管理信息
     *
     * @param caseMenuId 前台菜单管理ID
     * @return 结果
     */
    public int deleteCaseMenuById(Integer caseMenuId);
}
