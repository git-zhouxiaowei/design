package com.zxw.project.system.casemenu.service.impl;

import java.util.List;

import com.zxw.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxw.project.system.casemenu.mapper.CaseMenuMapper;
import com.zxw.project.system.casemenu.domain.CaseMenu;
import com.zxw.project.system.casemenu.service.ICaseMenuService;
import com.zxw.common.utils.text.Convert;

/**
 * 前台菜单管理Service业务层处理
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Service
public class CaseMenuServiceImpl implements ICaseMenuService {
    @Autowired
    private CaseMenuMapper caseMenuMapper;

    /**
     * 查询前台菜单管理
     *
     * @param caseMenuId 前台菜单管理ID
     * @return 前台菜单管理
     */
    @Override
    public CaseMenu selectCaseMenuById(Integer caseMenuId) {
        return caseMenuMapper.selectCaseMenuById(caseMenuId);
    }

    /**
     * 查询前台菜单管理列表
     *
     * @param caseMenu 前台菜单管理
     * @return 前台菜单管理
     */
    @Override
    public List<CaseMenu> selectCaseMenuList(CaseMenu caseMenu) {
        return caseMenuMapper.selectCaseMenuList(caseMenu);
    }

    /**
     * 新增前台菜单管理
     *
     * @param caseMenu 前台菜单管理
     * @return 结果
     */
    @Override
    public int insertCaseMenu(CaseMenu caseMenu) {
        caseMenu.setCreateTime(DateUtils.getNowDate());
        return caseMenuMapper.insertCaseMenu(caseMenu);
    }

    /**
     * 修改前台菜单管理
     *
     * @param caseMenu 前台菜单管理
     * @return 结果
     */
    @Override
    public int updateCaseMenu(CaseMenu caseMenu) {
        caseMenu.setUpdateTime(DateUtils.getNowDate());
        return caseMenuMapper.updateCaseMenu(caseMenu);
    }

    /**
     * 删除前台菜单管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCaseMenuByIds(String ids) {
        return caseMenuMapper.deleteCaseMenuByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除前台菜单管理信息
     *
     * @param caseMenuId 前台菜单管理ID
     * @return 结果
     */
    @Override
    public int deleteCaseMenuById(Integer caseMenuId) {
        return caseMenuMapper.deleteCaseMenuById(caseMenuId);
    }
}
