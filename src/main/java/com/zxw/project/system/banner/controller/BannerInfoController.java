package com.zxw.project.system.banner.controller;

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
import com.zxw.project.system.banner.domain.BannerInfo;
import com.zxw.project.system.banner.service.IBannerInfoService;
import com.zxw.framework.web.controller.BaseController;
import com.zxw.framework.web.domain.AjaxResult;
import com.zxw.common.utils.poi.ExcelUtil;
import com.zxw.framework.web.page.TableDataInfo;

/**
 * 轮播图Controller
 *
 * @author Zhouxw
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/banner")
public class BannerInfoController extends BaseController {
    private String prefix = "system/banner";

    @Autowired
    private IBannerInfoService bannerInfoService;

    @RequiresPermissions("system:banner:view")
    @GetMapping()
    public String banner() {
        return prefix + "/banner";
    }

    /**
     * 查询轮播图列表
     */
    @RequiresPermissions("system:banner:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BannerInfo bannerInfo) {
        startPage();
        List<BannerInfo> list = bannerInfoService.selectBannerInfoList(bannerInfo);
        return getDataTable(list);
    }

    /**
     * 导出轮播图列表
     */
    @RequiresPermissions("system:banner:export")
    @Log(title = "轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BannerInfo bannerInfo) {
        List<BannerInfo> list = bannerInfoService.selectBannerInfoList(bannerInfo);
        ExcelUtil<BannerInfo> util = new ExcelUtil<BannerInfo>(BannerInfo.class);
        return util.exportExcel(list, "banner");
    }

    /**
     * 新增轮播图
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存轮播图
     */
    @RequiresPermissions("system:banner:add")
    @Log(title = "轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BannerInfo bannerInfo) {
        return toAjax(bannerInfoService.insertBannerInfo(bannerInfo));
    }

    /**
     * 修改轮播图
     */
    @GetMapping("/edit/{bannerId}")
    public String edit(@PathVariable("bannerId") Integer bannerId, ModelMap mmap) {
        BannerInfo bannerInfo = bannerInfoService.selectBannerInfoById(bannerId);
        mmap.put("bannerInfo", bannerInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存轮播图
     */
    @RequiresPermissions("system:banner:edit")
    @Log(title = "轮播图", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BannerInfo bannerInfo) {
        return toAjax(bannerInfoService.updateBannerInfo(bannerInfo));
    }

    /**
     * 删除轮播图
     */
    @RequiresPermissions("system:banner:remove")
    @Log(title = "轮播图", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(bannerInfoService.deleteBannerInfoByIds(ids));
    }
}
