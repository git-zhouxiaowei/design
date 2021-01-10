package com.zxw.project.system.about.domain;

import com.zxw.framework.aspectj.lang.annotation.Excel;
import com.zxw.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关于我对象 sys_about_info
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 关于ID
     */
    private Integer aboutId;

    /**
     * 单位名称
     */
    @Excel(name = "单位名称")
    private String aboutName;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;

    /**
     * 座机
     */
    @Excel(name = "座机")
    private String telephone;

    /**
     * 手机
     */
    @Excel(name = "手机")
    private String cellphone;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * QQ
     */
    @Excel(name = "QQ")
    private String qq;

    /**
     * 二维码图片
     */
    @Excel(name = "二维码图片")
    private String codeImg;

}
