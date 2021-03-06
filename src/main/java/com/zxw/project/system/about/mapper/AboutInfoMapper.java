package com.zxw.project.system.about.mapper;

import java.util.List;

import com.zxw.project.system.about.domain.AboutInfo;

/**
 * 关于我Mapper接口
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
public interface AboutInfoMapper {
    /**
     * 查询关于我
     *
     * @param aboutId 关于我ID
     * @return 关于我
     */
    public AboutInfo selectAboutInfoById(Integer aboutId);

    /**
     * 查询关于我列表
     *
     * @param aboutInfo 关于我
     * @return 关于我集合
     */
    public List<AboutInfo> selectAboutInfoList(AboutInfo aboutInfo);

    /**
     * 新增关于我
     *
     * @param aboutInfo 关于我
     * @return 结果
     */
    public int insertAboutInfo(AboutInfo aboutInfo);

    /**
     * 修改关于我
     *
     * @param aboutInfo 关于我
     * @return 结果
     */
    public int updateAboutInfo(AboutInfo aboutInfo);

    /**
     * 删除关于我
     *
     * @param aboutId 关于我ID
     * @return 结果
     */
    public int deleteAboutInfoById(Integer aboutId);

    /**
     * 批量删除关于我
     *
     * @param aboutIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAboutInfoByIds(String[] aboutIds);
}
