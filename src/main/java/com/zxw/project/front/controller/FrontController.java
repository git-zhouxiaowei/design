package com.zxw.project.front.controller;

import com.github.pagehelper.PageHelper;
import com.zxw.common.constant.Constants;
import com.zxw.framework.web.controller.BaseController;
import com.zxw.framework.web.domain.AjaxResult;
import com.zxw.framework.web.page.TableDataInfo;
import com.zxw.project.system.about.domain.AboutInfo;
import com.zxw.project.system.about.service.IAboutInfoService;
import com.zxw.project.system.banner.domain.BannerInfo;
import com.zxw.project.system.banner.service.IBannerInfoService;
import com.zxw.project.system.caseInfo.domain.CaseInfo;
import com.zxw.project.system.caseInfo.service.ICaseInfoService;
import com.zxw.project.system.caseMenu.domain.CaseMenu;
import com.zxw.project.system.caseMenu.service.ICaseMenuService;
import com.zxw.project.system.notice.domain.Notice;
import com.zxw.project.system.notice.service.INoticeService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private INoticeService noticeService;

    /**
     * @Author Zhouxw
     * @Date 2021/01/16 16:43
     * @Description 跳转地图页
     */
    @GetMapping("toMap")
    public String toMap() {
        return prefix + "/amap";
    }

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
     * 查询关于我
     */
    @PostMapping("/about")
    @ResponseBody
    public AjaxResult about() {
        AboutInfo aboutInfo = aboutInfoService.selectAboutInfoById(1);
        return AjaxResult.success(aboutInfo);
    }

    /**
     * 查询左侧悬浮案例菜单列表
     */
    @PostMapping("/caseMenuList")
    @ResponseBody
    public AjaxResult caseMenuList() {
        // 案例菜单
        CaseMenu caseMenu = new CaseMenu();
        caseMenu.setMenuFlag(Constants.NO);
        List<CaseMenu> caseMenuList = caseMenuService.selectCaseMenuList(caseMenu);
        return AjaxResult.success(caseMenuList);
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
     * 查询通知列表
     */
    @PostMapping("/noticeList")
    @ResponseBody
    public TableDataInfo noticeList(Notice notice, int caseMenuId) {
        CaseMenu caseMenu = caseMenuService.selectCaseMenuById(caseMenuId);
        notice.setNoticeType(caseMenu.getNoticeType());

        startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 查询富文本类型的菜单详情数据
     */
    @GetMapping("/textCaseInfo/{caseMenuId}")
    @ResponseBody
    public AjaxResult textCaseInfo(@PathVariable Integer caseMenuId) {
        CaseInfo caseInfo = caseInfoService.selectTextCaseInfoByMenuId(caseMenuId);
        return AjaxResult.success(caseInfo);
    }

    /**
     * 查询案例详情
     */
    @GetMapping("/caseText/{caseId}")
    @ResponseBody
    public AjaxResult caseText(@PathVariable Integer caseId) {
        CaseInfo caseInfo = caseInfoService.selectCaseInfoById(caseId);
        CaseInfo preCaseInfo = caseInfoService.selectPreCaseInfoById(caseId);
        CaseInfo nextCaseInfo = caseInfoService.selectNextCaseInfoById(caseId);
        //更新浏览次数
        caseInfo.setViewTimes(caseInfo.getViewTimes() + 1);
        caseInfoService.updateCaseInfo(caseInfo);
        Map<String, CaseInfo> dataMap = new HashedMap();
        dataMap.put("caseInfo", caseInfo);
        dataMap.put("preCaseInfo", preCaseInfo);
        dataMap.put("nextCaseInfo", nextCaseInfo);
        return AjaxResult.success(dataMap);
    }

    /**
     * 查询联系我们
     */
    @PostMapping("me")
    @ResponseBody
    public AjaxResult me() {
        CaseInfo caseInfo = caseInfoService.selectCaseInfoById(234);
        return AjaxResult.success(caseInfo);
    }

    /**
     * 查询通知详情
     */
    @GetMapping("/noticeInfo/{noticeId}")
    @ResponseBody
    public AjaxResult noticeInfo(@PathVariable("noticeId") Long noticeId) {
        Notice notice = noticeService.selectNoticeById(noticeId);
        Notice preNotice = noticeService.selectPreNoticeById(noticeId);
        Notice nextNotice = noticeService.selectNextNoticeById(noticeId);
        //更新浏览次数
        notice.setViewTimes(notice.getViewTimes() + 1);
        noticeService.updateNotice(notice);
        Map<String, Notice> dataMap = new HashedMap();
        dataMap.put("notice", notice);
        dataMap.put("preNotice", preNotice);
        dataMap.put("nextNotice", nextNotice);
        return AjaxResult.success(dataMap);
    }

    /**
     * 小程序首页数据初始化
     *
     * @return com.zxw.framework.web.domain.AjaxResult
     * @author Zhouxw
     * @date 2021/01/20 10:46
     */
    @GetMapping("/mini/initData")
    @ResponseBody
    public AjaxResult miniInitData() {
        // 轮播图列表
        List<BannerInfo> bannerInfoList = bannerInfoService.selectBannerInfoList(new BannerInfo());
        // 首页最新案例滑动列表，10条
        PageHelper.startPage(1, 10, " case_id desc");
        List<CaseInfo> caseInfoList = caseInfoService.selectCoverImgList(null);
        // 最新通知列表，4条
        PageHelper.startPage(1, 4, " create_time desc");
        List<Notice> noticeList = noticeService.selectNoticeList(new Notice());

        Map dataMap = new HashedMap();
        dataMap.put("bannerInfoList", bannerInfoList);
        dataMap.put("caseInfoList", caseInfoList);
        dataMap.put("noticeList", noticeList);
        return AjaxResult.success(dataMap);
    }

    /**
     * 小程序查询案例详情
     */
    @GetMapping("/mini/caseText/{caseId}")
    @ResponseBody
    public AjaxResult miniCaseText(@PathVariable Integer caseId) {
        CaseInfo caseInfo = caseInfoService.selectCaseInfoById(caseId);
        //更新浏览次数
        caseInfo.setViewTimes(caseInfo.getViewTimes() + 1);
        caseInfoService.updateCaseInfo(caseInfo);
        return AjaxResult.success(caseInfo);
    }

    /**
     * 查询通知详情
     */
    @GetMapping("/mini/noticeInfo/{noticeId}")
    @ResponseBody
    public AjaxResult miniNoticeInfo(@PathVariable("noticeId") Long noticeId) {
        Notice notice = noticeService.selectNoticeById(noticeId);
        //更新浏览次数
        notice.setViewTimes(notice.getViewTimes() + 1);
        noticeService.updateNotice(notice);
        return AjaxResult.success(notice);
    }

    /**
     * 最新设计分页查询
     *
     * @param pageNum
     * @return com.zxw.framework.web.domain.AjaxResult
     * @author Zhouxw
     * @date 2021/01/20 12:02
     */
    @GetMapping("/mini/newCaseInfoPage/{pageNum}")
    @ResponseBody
    public AjaxResult newCaseInfoPage(@PathVariable("pageNum") Integer pageNum, Integer caseMenuId) {
        if (null == pageNum) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 20, " case_id desc");
        List<CaseInfo> caseInfoList = caseInfoService.selectCoverImgList(caseMenuId);
        TableDataInfo dataTable = getDataTable(caseInfoList);

        String caseMenuName = "最新设计";
        boolean haveFlag = false;
        if (null != caseMenuId) {
            CaseMenu caseMenu = caseMenuService.selectCaseMenuById(caseMenuId);
            caseMenuName = caseMenu.getCaseMenuName();
        }
        long total = dataTable.getTotal();
        if (total > caseInfoList.size() * pageNum) {
            haveFlag = true;
        }

        Map<String, Object> menuMap = new HashedMap(4);
        menuMap.put("caseMenuId", caseMenuId);
        menuMap.put("caseMenuName", caseMenuName);
        menuMap.put("haveFlag", haveFlag);
        menuMap.put("caseList", caseInfoList);
        return AjaxResult.success(menuMap);
    }

    /**
     * 通知分页查询
     *
     * @param pageNum
     * @return com.zxw.framework.web.domain.AjaxResult
     * @author Zhouxw
     * @date 2021/01/20 12:02
     */
    @GetMapping("/mini/noticePage/{pageNum}")
    @ResponseBody
    public AjaxResult noticePage(@PathVariable("pageNum") Integer pageNum) {
        if (null == pageNum) {
            pageNum = 1;
        }
        // 最新通知列表，4条
        PageHelper.startPage(pageNum, 15, " create_time desc");
        List<Notice> noticeList = noticeService.selectNoticeList(new Notice());
        return AjaxResult.success(noticeList);
    }

    /**
     * 小程序首页数据初始化
     *
     * @return com.zxw.framework.web.domain.AjaxResult
     * @author Zhouxw
     * @date 2021/01/20 10:46
     */
    @GetMapping("/mini/caseTabInitData")
    @ResponseBody
    public AjaxResult miniCaseInitData() {
        // 案例菜单
        CaseMenu caseMenu = new CaseMenu();
        caseMenu.setMenuFlag(Constants.NO);
        List<CaseMenu> caseMenuList = caseMenuService.selectCaseMenuList(caseMenu);

        //组装数据
        List<Map> menuList = new ArrayList<>();

        if (null != caseMenuList && caseMenuList.size() > 0) {
            for (CaseMenu menu : caseMenuList) {
                String caseType = menu.getCaseType();
                if (Constants.LIST.equals(caseType)) {
                    //如果是列表类型的菜单不显示
                    continue;
                }
                CaseInfo caseInfo = new CaseInfo();
                caseInfo.setCaseMenuId(menu.getCaseMenuId());
                PageHelper.startPage(1, 10, " create_time desc");
                List<CaseInfo> caseList = caseInfoService.selectCaseInfoList(caseInfo);
                TableDataInfo dataTable = getDataTable(caseList);
                Map<String, Object> menuMap = new HashedMap(5);
                menuMap.put("caseMenuId", menu.getCaseMenuId());
                menuMap.put("caseMenuName", menu.getCaseMenuName());
                menuMap.put("caseType", caseType);
                long total = dataTable.getTotal();
                if (total > caseList.size()) {
                    menuMap.put("haveFlag", true);
                } else {
                    menuMap.put("haveFlag", false);
                }
                menuMap.put("caseList", caseList);
                menuList.add(menuMap);
            }
        }
        return AjaxResult.success(menuList);
    }

    /**
     * 小程序-关于我们
     *
     * @author Zhouxw
     * @date 2021/01/21 16:38
     * @return com.zxw.framework.web.domain.AjaxResult
     */
    @GetMapping("/mini/aboutInfo")
    @ResponseBody
    public AjaxResult aboutInfo() {
        //固定查询了解达德
        CaseInfo caseInfo = caseInfoService.selectTextCaseInfoByMenuId(4);
        AboutInfo aboutInfo = aboutInfoService.selectAboutInfoById(1);
        Map<String,Object> dataMap = new HashedMap(2);
        dataMap.put("caseInfo",caseInfo);
        dataMap.put("aboutInfo",aboutInfo);
        return AjaxResult.success(dataMap);
    }
}
