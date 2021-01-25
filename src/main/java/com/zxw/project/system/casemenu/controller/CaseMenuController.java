package com.zxw.project.system.casemenu.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zxw.framework.aspectj.lang.annotation.Log;
import com.zxw.framework.aspectj.lang.enums.BusinessType;
import com.zxw.project.system.casemenu.domain.CaseMenu;
import com.zxw.project.system.casemenu.service.ICaseMenuService;
import com.zxw.framework.web.controller.BaseController;
import com.zxw.framework.web.domain.AjaxResult;
import com.zxw.common.utils.poi.ExcelUtil;
import com.zxw.framework.web.page.TableDataInfo;

/**
 * 前台菜单管理Controller
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/caseMenu")
public class CaseMenuController extends BaseController {
    private String prefix = "system/caseMenu";

    @Autowired
    private ICaseMenuService caseMenuService;

    @RequiresPermissions("system:caseMenu:view")
    @GetMapping()
    public String caseMenu() {
        return prefix + "/caseMenu";
    }

    /**
     * 查询前台菜单管理列表
     */
    @RequiresPermissions("system:caseMenu:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CaseMenu caseMenu) {
        startPage();
        List<CaseMenu> list = caseMenuService.selectCaseMenuList(caseMenu);
        return getDataTable(list);
    }

    /**
     * 导出前台菜单管理列表
     */
    @RequiresPermissions("system:caseMenu:export")
    @Log(title = "前台菜单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CaseMenu caseMenu) {
        List<CaseMenu> list = caseMenuService.selectCaseMenuList(caseMenu);
        ExcelUtil<CaseMenu> util = new ExcelUtil<CaseMenu>(CaseMenu.class);
        return util.exportExcel(list, "caseMenu");
    }

    /**
     * 新增前台菜单管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存前台菜单管理
     */
    @RequiresPermissions("system:caseMenu:add")
    @Log(title = "前台菜单管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CaseMenu caseMenu) {
        return toAjax(caseMenuService.insertCaseMenu(caseMenu));
    }

    /**
     * 修改前台菜单管理
     */
    @GetMapping("/edit/{caseMenuId}")
    public String edit(@PathVariable("caseMenuId") Integer caseMenuId, ModelMap mmap) {
        CaseMenu caseMenu = caseMenuService.selectCaseMenuById(caseMenuId);
        mmap.put("caseMenu", caseMenu);
        return prefix + "/edit";
    }

    /**
     * 修改保存前台菜单管理
     */
    @RequiresPermissions("system:caseMenu:edit")
    @Log(title = "前台菜单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CaseMenu caseMenu) {
        return toAjax(caseMenuService.updateCaseMenu(caseMenu));
    }

    /**
     * 删除前台菜单管理
     */
    @RequiresPermissions("system:caseMenu:remove")
    @Log(title = "前台菜单管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(caseMenuService.deleteCaseMenuByIds(ids));
    }
}
