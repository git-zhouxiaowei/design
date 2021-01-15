package com.zxw.project.system.caseInfo.service;

import java.util.List;

import com.zxw.project.system.caseInfo.domain.CaseInfo;

/**
 * 案例Service接口
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
public interface ICaseInfoService {
    /**
     * 查询案例
     *
     * @param caseId 案例ID
     * @return 案例
     */
    public CaseInfo selectCaseInfoById(Integer caseId);

    /**
     * 查询案例列表
     *
     * @param caseInfo 案例
     * @return 案例集合
     */
    public List<CaseInfo> selectCaseInfoList(CaseInfo caseInfo);

    /**
     * 新增案例
     *
     * @param caseInfo 案例
     * @return 结果
     */
    public int insertCaseInfo(CaseInfo caseInfo);

    /**
     * 修改案例
     *
     * @param caseInfo 案例
     * @return 结果
     */
    public int updateCaseInfo(CaseInfo caseInfo);

    /**
     * 批量删除案例
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCaseInfoByIds(String ids);

    /**
     * 删除案例信息
     *
     * @param caseId 案例ID
     * @return 结果
     */
    public int deleteCaseInfoById(Integer caseId);

    /**
     * @Author Zhouxw
     * @Date 2021/01/13 10:59
     * @Description 根据菜单ID查询最后一条案例
     * @Param [caseMenuId]
     * @Return com.zxw.project.system.caseInfo.domain.CaseInfo
     */
    CaseInfo selectTextCaseInfoByMenuId(Integer caseMenuId);

    /**
     * 查询上一条案例
     *
     * @param caseId 案例ID
     * @return 案例
     */
    CaseInfo selectPreCaseInfoById(Integer caseId);

    /**
     * 查询下一条案例
     *
     * @param caseId 案例ID
     * @return 案例
     */
    CaseInfo selectNextCaseInfoById(Integer caseId);
}
