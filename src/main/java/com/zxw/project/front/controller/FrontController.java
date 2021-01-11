package com.zxw.project.front.controller;

import com.github.pagehelper.PageHelper;
import com.zxw.framework.web.controller.BaseController;
import com.zxw.framework.web.page.TableDataInfo;
import com.zxw.project.system.about.domain.AboutInfo;
import com.zxw.project.system.about.service.IAboutInfoService;
import com.zxw.project.system.banner.domain.BannerInfo;
import com.zxw.project.system.banner.service.IBannerInfoService;
import com.zxw.project.system.caseInfo.domain.CaseInfo;
import com.zxw.project.system.caseInfo.service.ICaseInfoService;
import com.zxw.project.system.caseMenu.domain.CaseMenu;
import com.zxw.project.system.caseMenu.service.ICaseMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        // 关于信息
        AboutInfo aboutInfo = aboutInfoService.selectAboutInfoById(1);
        // 首页案例样图，标志VI前50张列表
        CaseInfo caseInfo = new CaseInfo();
        caseInfo.setCaseMenuId(2);
        PageHelper.startPage(0, 50, "case_id desc");
        List<CaseInfo> caseInfoList = caseInfoService.selectCaseInfoList(caseInfo);

        mmp.put("bannerInfoList", bannerInfoList);
        mmp.put("aboutInfo", aboutInfo);
        mmp.put("caseMenuList",caseMenuList);
        mmp.put("caseInfoList",caseInfoList);
        return prefix + "/index";
    }
}
