package com.zxw.project.system.about.service.impl;

import java.util.List;

import com.zxw.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxw.project.system.about.mapper.AboutInfoMapper;
import com.zxw.project.system.about.domain.AboutInfo;
import com.zxw.project.system.about.service.IAboutInfoService;
import com.zxw.common.utils.text.Convert;

/**
 * 关于我Service业务层处理
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Service
public class AboutInfoServiceImpl implements IAboutInfoService {
    @Autowired
    private AboutInfoMapper aboutInfoMapper;

    /**
     * 查询关于我
     *
     * @param aboutId 关于我ID
     * @return 关于我
     */
    @Override
    public AboutInfo selectAboutInfoById(Integer aboutId) {
        return aboutInfoMapper.selectAboutInfoById(aboutId);
    }

    /**
     * 查询关于我列表
     *
     * @param aboutInfo 关于我
     * @return 关于我
     */
    @Override
    public List<AboutInfo> selectAboutInfoList(AboutInfo aboutInfo) {
        return aboutInfoMapper.selectAboutInfoList(aboutInfo);
    }

    /**
     * 新增关于我
     *
     * @param aboutInfo 关于我
     * @return 结果
     */
    @Override
    public int insertAboutInfo(AboutInfo aboutInfo) {
        aboutInfo.setCreateTime(DateUtils.getNowDate());
        return aboutInfoMapper.insertAboutInfo(aboutInfo);
    }

    /**
     * 修改关于我
     *
     * @param aboutInfo 关于我
     * @return 结果
     */
    @Override
    public int updateAboutInfo(AboutInfo aboutInfo) {
        aboutInfo.setUpdateTime(DateUtils.getNowDate());
        return aboutInfoMapper.updateAboutInfo(aboutInfo);
    }

    /**
     * 删除关于我对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAboutInfoByIds(String ids) {
        return aboutInfoMapper.deleteAboutInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除关于我信息
     *
     * @param aboutId 关于我ID
     * @return 结果
     */
    @Override
    public int deleteAboutInfoById(Integer aboutId) {
        return aboutInfoMapper.deleteAboutInfoById(aboutId);
    }
}
