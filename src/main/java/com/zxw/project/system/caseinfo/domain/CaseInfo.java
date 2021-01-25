package com.zxw.project.system.caseinfo.domain;

import java.util.Date;

import com.zxw.framework.aspectj.lang.annotation.Excel;
import com.zxw.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 案例对象 sys_case_info
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 案例ID
     */
    private Integer caseId;

    /**
     * 案例ID
     */
    private Integer caseMenuId;

    /**
     * 案例名称
     */
    @Excel(name = "案例名称")
    private String caseName;

    /**
     * 案例简介
     */
    @Excel(name = "案例简介")
    private String caseText;

    /**
     * 图片类型
     */
    @Excel(name = "图片类型")
    private String imgType;

    /**
     * 封面图片
     */
    @Excel(name = "封面图片")
    private String coverImg;

    /**
     * 浏览次数
     */
    @Excel(name = "浏览次数")
    private Integer viewTimes;

    /**
     * 上传时间
     */
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

}
