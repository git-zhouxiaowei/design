package com.zxw.project.system.caseInfo.mapper;

import java.util.List;

import com.zxw.project.system.caseInfo.domain.CaseInfo;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 案例Mapper接口
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
public interface CaseInfoMapper {
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
     * 删除案例
     *
     * @param caseId 案例ID
     * @return 结果
     */
    public int deleteCaseInfoById(Integer caseId);

    /**
     * 批量删除案例
     *
     * @param caseIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCaseInfoByIds(String[] caseIds);

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

    /**
     * 查询最新的含封面图片的案例列表
     *
     * @author Zhouxw
     * @date 2021/01/19 14:35
     * @return java.util.List<com.zxw.project.system.caseInfo.domain.CaseInfo>
     */
    List<CaseInfo> selectCoverImgList();
}
