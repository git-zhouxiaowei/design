package com.zxw.project.system.notice.domain;


import com.zxw.framework.aspectj.lang.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zxw.framework.web.domain.BaseEntity;

/**
 * 通知公告表 sys_notice
 *
 * @author Zhouxw
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 公告ID
     */
    private Long noticeId;
    /**
     * 部门ID
     */
    private Long deptId;


    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告 2招聘）
     */
    private String noticeType;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    private String status;

    /**
     * 浏览次数
     */
    @Excel(name = "浏览次数")
    private Integer viewTimes;

}
