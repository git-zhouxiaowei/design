package com.zxw.project.system.about.controller;

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
import com.zxw.project.system.about.domain.AboutInfo;
import com.zxw.project.system.about.service.IAboutInfoService;
import com.zxw.framework.web.controller.BaseController;
import com.zxw.framework.web.domain.AjaxResult;
import com.zxw.common.utils.poi.ExcelUtil;
import com.zxw.framework.web.page.TableDataInfo;

/**
 * 关于我Controller
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/about")
public class AboutInfoController extends BaseController {
    private String prefix = "system/about";

    @Autowired
    private IAboutInfoService aboutInfoService;

    @RequiresPermissions("system:about:view")
    @GetMapping()
    public String about(ModelMap mmap) {
        AboutInfo aboutInfo = aboutInfoService.selectAboutInfoById(1);
        mmap.put("aboutInfo", aboutInfo);
        return prefix + "/about";
    }

    /**
     * 查询关于我列表
     */
    @RequiresPermissions("system:about:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AboutInfo aboutInfo) {
        startPage();
        List<AboutInfo> list = aboutInfoService.selectAboutInfoList(aboutInfo);
        return getDataTable(list);
    }

    /**
     * 导出关于我列表
     */
    @RequiresPermissions("system:about:export")
    @Log(title = "关于我", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AboutInfo aboutInfo) {
        List<AboutInfo> list = aboutInfoService.selectAboutInfoList(aboutInfo);
        ExcelUtil<AboutInfo> util = new ExcelUtil<AboutInfo>(AboutInfo.class);
        return util.exportExcel(list, "about");
    }

    /**
     * 新增关于我
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存关于我
     */
    @RequiresPermissions("system:about:add")
    @Log(title = "关于我", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AboutInfo aboutInfo) {
        return toAjax(aboutInfoService.insertAboutInfo(aboutInfo));
    }

    /**
     * 修改关于我
     */
    @GetMapping("/edit/{aboutId}")
    public String edit(@PathVariable("aboutId") Integer aboutId, ModelMap mmap) {
        AboutInfo aboutInfo = aboutInfoService.selectAboutInfoById(aboutId);
        mmap.put("aboutInfo", aboutInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存关于我
     */
    @RequiresPermissions("system:about:edit")
    @Log(title = "关于我", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AboutInfo aboutInfo) {
        return toAjax(aboutInfoService.updateAboutInfo(aboutInfo));
    }

    /**
     * 删除关于我
     */
    @RequiresPermissions("system:about:remove")
    @Log(title = "关于我", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(aboutInfoService.deleteAboutInfoByIds(ids));
    }
}
