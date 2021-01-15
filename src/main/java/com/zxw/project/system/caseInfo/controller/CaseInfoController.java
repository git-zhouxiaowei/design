package com.zxw.project.system.caseInfo.controller;

import java.util.List;

import com.zxw.project.system.caseMenu.domain.CaseMenu;
import com.zxw.project.system.caseMenu.service.ICaseMenuService;
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
import com.zxw.project.system.caseInfo.domain.CaseInfo;
import com.zxw.project.system.caseInfo.service.ICaseInfoService;
import com.zxw.framework.web.controller.BaseController;
import com.zxw.framework.web.domain.AjaxResult;
import com.zxw.common.utils.poi.ExcelUtil;
import com.zxw.framework.web.page.TableDataInfo;

/**
 * 案例Controller
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/caseInfo")
public class CaseInfoController extends BaseController {
    private String prefix = "system/caseInfo";

    @Autowired
    private ICaseInfoService caseInfoService;
    @Autowired
    private ICaseMenuService caseMenuService;

    @RequiresPermissions("system:caseInfo:view")
    @GetMapping("/{caseMenuId}")
    public String caseInfo(@PathVariable int caseMenuId, ModelMap mmp) {
        mmp.put("caseMenuId", caseMenuId);
        return prefix + "/caseInfo";
    }

    /**
     * 查询案例列表
     */
    @RequiresPermissions("system:caseInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CaseInfo caseInfo) {
        startPage();
        List<CaseInfo> list = caseInfoService.selectCaseInfoList(caseInfo);
        return getDataTable(list);
    }


    /**
     * 导出案例列表
     */
    @RequiresPermissions("system:caseInfo:export")
    @Log(title = "案例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CaseInfo caseInfo) {
        List<CaseInfo> list = caseInfoService.selectCaseInfoList(caseInfo);
        ExcelUtil<CaseInfo> util = new ExcelUtil<CaseInfo>(CaseInfo.class);
        return util.exportExcel(list, "caseInfo");
    }

    /**
     * 新增案例
     */
    @GetMapping("/add/{caseMenuId}")
    public String add(@PathVariable int caseMenuId, ModelMap mmap) {
        CaseMenu caseMenu = caseMenuService.selectCaseMenuById(caseMenuId);
        CaseInfo caseInfo = new CaseInfo();
        caseInfo.setCaseMenuId(caseMenuId);
        caseInfo.setImgType(caseMenu.getImgType());
        mmap.put("caseInfo", caseInfo);
        return prefix + "/add";
    }

    /**
     * 新增保存案例
     */
    @RequiresPermissions("system:caseInfo:add")
    @Log(title = "案例", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CaseInfo caseInfo) {
        return toAjax(caseInfoService.insertCaseInfo(caseInfo));
    }

    /**
     * 修改案例
     */
    @GetMapping("/edit/{caseId}")
    public String edit(@PathVariable("caseId") Integer caseId, ModelMap mmap) {
        CaseInfo caseInfo = caseInfoService.selectCaseInfoById(caseId);
        mmap.put("caseInfo", caseInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存案例
     */
    @RequiresPermissions("system:caseInfo:edit")
    @Log(title = "案例", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CaseInfo caseInfo) {
        return toAjax(caseInfoService.updateCaseInfo(caseInfo));
    }

    /**
     * 删除案例
     */
    @RequiresPermissions("system:caseInfo:remove")
    @Log(title = "案例", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(caseInfoService.deleteCaseInfoByIds(ids));
    }
}
