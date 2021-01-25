package com.zxw.project.system.casemenu.domain;

import com.zxw.framework.aspectj.lang.annotation.Excel;
import com.zxw.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前台菜单管理对象 sys_case_menu
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 案例菜单表ID
     */
    private Integer caseMenuId;

    /**
     * 案例菜单名称
     */
    @Excel(name = "菜单名称")
    private String caseMenuName;

    /**
     * 案例菜单二级名称
     */
    @Excel(name = "二级名称")
    private String caseMenuSummary;

    /**
     * 案例类型
     */
    @Excel(name = "菜单类型")
    private String caseType;

    /**
     * 通知类型
     */
    @Excel(name = "列表类型")
    private String noticeType;

    /**
     * 图片类型
     */
    @Excel(name = "图片类型")
    private String imgType;

    /**
     * 是否主菜单
     */
    @Excel(name = "主菜单")
    private String menuFlag;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Integer sort;

}
