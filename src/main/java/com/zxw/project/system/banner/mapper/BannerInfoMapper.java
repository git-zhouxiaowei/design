package com.zxw.project.system.banner.mapper;

import java.util.List;

import com.zxw.project.system.banner.domain.BannerInfo;

/**
 * 轮播图Mapper接口
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
public interface BannerInfoMapper {
    /**
     * 查询轮播图
     *
     * @param bannerId 轮播图ID
     * @return 轮播图
     */
    public BannerInfo selectBannerInfoById(Integer bannerId);

    /**
     * 查询轮播图列表
     *
     * @param bannerInfo 轮播图
     * @return 轮播图集合
     */
    public List<BannerInfo> selectBannerInfoList(BannerInfo bannerInfo);

    /**
     * 新增轮播图
     *
     * @param bannerInfo 轮播图
     * @return 结果
     */
    public int insertBannerInfo(BannerInfo bannerInfo);

    /**
     * 修改轮播图
     *
     * @param bannerInfo 轮播图
     * @return 结果
     */
    public int updateBannerInfo(BannerInfo bannerInfo);

    /**
     * 删除轮播图
     *
     * @param bannerId 轮播图ID
     * @return 结果
     */
    public int deleteBannerInfoById(Integer bannerId);

    /**
     * 批量删除轮播图
     *
     * @param bannerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBannerInfoByIds(String[] bannerIds);
}
