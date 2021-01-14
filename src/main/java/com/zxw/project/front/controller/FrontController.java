package com.zxw.project.front.controller;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.zxw.common.constant.Constants;
import com.zxw.common.utils.uuid.IdUtils;
import com.zxw.framework.web.controller.BaseController;
import com.zxw.framework.web.domain.AjaxResult;
import com.zxw.framework.web.page.PageDomain;
import com.zxw.framework.web.page.TableDataInfo;
import com.zxw.framework.web.page.TableSupport;
import com.zxw.project.system.about.domain.AboutInfo;
import com.zxw.project.system.about.service.IAboutInfoService;
import com.zxw.project.system.banner.domain.BannerInfo;
import com.zxw.project.system.banner.service.IBannerInfoService;
import com.zxw.project.system.caseInfo.domain.CaseInfo;
import com.zxw.project.system.caseInfo.service.ICaseInfoService;
import com.zxw.project.system.caseMenu.domain.CaseMenu;
import com.zxw.project.system.caseMenu.service.ICaseMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhouxw
 * @description: PC前端相关接口d
 * @date 2021/1/8 15:23
 **/
@Controller
@RequestMapping("front")
public class FrontController extends BaseController {
    private String prefix = "front";
    @Autowired
    private ICaseMenuService caseMenuService;
    @Autowired
    private IAboutInfoService aboutInfoService;
    @Autowired
    private ICaseInfoService caseInfoService;
    @Autowired
    private IBannerInfoService bannerInfoService;

    @GetMapping
    public String index(ModelMap mmp) {
        // 轮播图列表
        List<BannerInfo> bannerInfoList = bannerInfoService.selectBannerInfoList(new BannerInfo());
        // 案例菜单
        List<CaseMenu> caseMenuList = caseMenuService.selectCaseMenuList(new CaseMenu());
        List<CaseMenu> menuList = new ArrayList<>(6);
        // 拆分成两个列表
        for (int i = 0; i < caseMenuList.size(); i++) {
            if (Constants.YES.equals(caseMenuList.get(i).getMenuFlag())) {
                menuList.add(caseMenuList.get(i));
                caseMenuList.remove(i);
                i--;
            }
        }
        //如果主菜单少于6个，那么补充为6个
        int size = 6 - menuList.size();
        if (0 < size) {
            for (int i = 0; i < size; i++) {
                menuList.add(new CaseMenu());
            }
        }
        // 关于信息
        AboutInfo aboutInfo = aboutInfoService.selectAboutInfoById(1);
        // 首页案例样图，标志VI前50张列表
        CaseInfo caseInfo = new CaseInfo();
        caseInfo.setCaseMenuId(2);
        PageHelper.startPage(0, 50, "case_id desc");
        List<CaseInfo> caseInfoList = caseInfoService.selectCaseInfoList(caseInfo);

        mmp.put("bannerInfoList", bannerInfoList);
        mmp.put("aboutInfo", aboutInfo);
        mmp.put("menuList", menuList);
        mmp.put("caseMenuList", caseMenuList);
        mmp.put("caseInfoList", caseInfoList);
        return prefix + "/index";
    }

    /**
     * 查询案例列表
     */
    @PostMapping("/caseInfoList")
    @ResponseBody
    public TableDataInfo caseInfoList(CaseInfo caseInfo) {
        startPage();
        List<CaseInfo> list = caseInfoService.selectCaseInfoList(caseInfo);
        TableDataInfo dataTable = getDataTable(list);
        return dataTable;
    }

    /**
     * 查询案例富文本
     */
    @PostMapping("/textCaseInfo/{caseMenuId}")
    @ResponseBody
    public AjaxResult textCaseInfo(@PathVariable Integer caseMenuId) {
        CaseInfo caseInfo = caseInfoService.selectTextCaseInfoByMenuId(caseMenuId);
        return AjaxResult.success(caseInfo);
    }

}
