package com.zxw.project.system.caseinfo.service;

import java.util.List;

import com.zxw.project.system.caseinfo.domain.CaseInfo;

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
     * 根据菜单ID查询最后一条案例
     *
     * @param caseMenuId
     * @return com.zxw.project.system.caseinfo.domain.CaseInfo
     * @author Zhouxw
     * @date 2021/01/25 9:52
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

    /**
     * 查询最新的含封面图片的案例列表
     *
     * @param caseMenuId
     * @return java.util.List<com.zxw.project.system.caseInfo.domain.CaseInfo>
     * @author Zhouxw
     * @date 2021/01/19 14:35
     */
    List<CaseInfo> selectCoverImgList(Integer caseMenuId);
}

