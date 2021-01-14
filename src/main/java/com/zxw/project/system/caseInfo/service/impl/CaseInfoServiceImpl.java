package com.zxw.project.system.caseInfo.service.impl;

import java.util.List;

import com.zxw.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxw.project.system.caseInfo.mapper.CaseInfoMapper;
import com.zxw.project.system.caseInfo.domain.CaseInfo;
import com.zxw.project.system.caseInfo.service.ICaseInfoService;
import com.zxw.common.utils.text.Convert;

/**
 * 案例Service业务层处理
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Service
public class CaseInfoServiceImpl implements ICaseInfoService {
    @Autowired
    private CaseInfoMapper caseInfoMapper;

    /**
     * 查询案例
     *
     * @param caseId 案例ID
     * @return 案例
     */
    @Override
    public CaseInfo selectCaseInfoById(Integer caseId) {
        return caseInfoMapper.selectCaseInfoById(caseId);
    }

    /**
     * 查询案例列表
     *
     * @param caseInfo 案例
     * @return 案例
     */
    @Override
    public List<CaseInfo> selectCaseInfoList(CaseInfo caseInfo) {
        return caseInfoMapper.selectCaseInfoList(caseInfo);
    }

    /**
     * 新增案例
     *
     * @param caseInfo 案例
     * @return 结果
     */
    @Override
    public int insertCaseInfo(CaseInfo caseInfo) {
        caseInfo.setCreateTime(DateUtils.getNowDate());
        return caseInfoMapper.insertCaseInfo(caseInfo);
    }

    /**
     * 修改案例
     *
     * @param caseInfo 案例
     * @return 结果
     */
    @Override
    public int updateCaseInfo(CaseInfo caseInfo) {
        caseInfo.setUpdateTime(DateUtils.getNowDate());
        return caseInfoMapper.updateCaseInfo(caseInfo);
    }

    /**
     * 删除案例对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCaseInfoByIds(String ids) {
        return caseInfoMapper.deleteCaseInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除案例信息
     *
     * @param caseId 案例ID
     * @return 结果
     */
    @Override
    public int deleteCaseInfoById(Integer caseId) {
        return caseInfoMapper.deleteCaseInfoById(caseId);
    }

    @Override
    public CaseInfo selectTextCaseInfoByMenuId(Integer caseMenuId) {
        return caseInfoMapper.selectTextCaseInfoByMenuId(caseMenuId);
    }
}
