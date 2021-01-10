package com.zxw.project.system.banner.service.impl;

import java.util.List;

import com.zxw.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxw.project.system.banner.mapper.BannerInfoMapper;
import com.zxw.project.system.banner.domain.BannerInfo;
import com.zxw.project.system.banner.service.IBannerInfoService;
import com.zxw.common.utils.text.Convert;

/**
 * 轮播图Service业务层处理
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Service
public class BannerInfoServiceImpl implements IBannerInfoService {
    @Autowired
    private BannerInfoMapper bannerInfoMapper;

    /**
     * 查询轮播图
     *
     * @param bannerId 轮播图ID
     * @return 轮播图
     */
    @Override
    public BannerInfo selectBannerInfoById(Integer bannerId) {
        return bannerInfoMapper.selectBannerInfoById(bannerId);
    }

    /**
     * 查询轮播图列表
     *
     * @param bannerInfo 轮播图
     * @return 轮播图
     */
    @Override
    public List<BannerInfo> selectBannerInfoList(BannerInfo bannerInfo) {
        return bannerInfoMapper.selectBannerInfoList(bannerInfo);
    }

    /**
     * 新增轮播图
     *
     * @param bannerInfo 轮播图
     * @return 结果
     */
    @Override
    public int insertBannerInfo(BannerInfo bannerInfo) {
        bannerInfo.setCreateTime(DateUtils.getNowDate());
        return bannerInfoMapper.insertBannerInfo(bannerInfo);
    }

    /**
     * 修改轮播图
     *
     * @param bannerInfo 轮播图
     * @return 结果
     */
    @Override
    public int updateBannerInfo(BannerInfo bannerInfo) {
        bannerInfo.setUpdateTime(DateUtils.getNowDate());
        return bannerInfoMapper.updateBannerInfo(bannerInfo);
    }

    /**
     * 删除轮播图对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBannerInfoByIds(String ids) {
        return bannerInfoMapper.deleteBannerInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除轮播图信息
     *
     * @param bannerId 轮播图ID
     * @return 结果
     */
    @Override
    public int deleteBannerInfoById(Integer bannerId) {
        return bannerInfoMapper.deleteBannerInfoById(bannerId);
    }
}
