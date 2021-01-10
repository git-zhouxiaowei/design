package com.zxw.project.system.banner.domain;

import com.zxw.framework.aspectj.lang.annotation.Excel;
import com.zxw.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 轮播图对象 sys_banner_info
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 轮播图ID
     */
    private Integer bannerId;

    /**
     * 轮播图描述
     */
    @Excel(name = "轮播图描述")
    private String bannerName;

    /**
     * 轮播图图片
     */
    @Excel(name = "轮播图图片")
    private String bannerImg;

    /**
     * 显示状态（0正常 1关闭）
     */
    @Excel(name = "显示状态", readConverterExp = "0=正常,1=关闭")
    private String status;

}
