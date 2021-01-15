$(function () {


});

// 点击案列菜单或者主菜单
function openCaseMenu(caseMenuId, caseType, caseMenuName, imgType) {
    // 先清空
    initData('', '');
    //如果菜单类型是多图，那么分页取出多条
    if ('imgs' == caseType) {
        caseImgPage(1, caseMenuId, caseType, imgType, caseMenuName);
    } else if ('text' == caseType) {
        // 如果菜单类型是富文本，那么取出最新的一条，直接展示div
        var url = ctx + "front/textCaseInfo/" + caseMenuId;
        $.post(url, function (resp) {
            var caseName = resp.data.caseName;
            var textCaeInfo = resp.data.caseText;
            initData(caseName, textCaeInfo);
            //控制显隐
            showNext();
        });
    } else if ('list' == caseType) {
        // 如果是列表类型，根据通知类型，分页查询对应的通知列表
        noticePage(1, caseMenuId, caseMenuName);
    }
}

// 多图案例列表方法
function caseImgPage(pageNum, caseMenuId, caseType, imgType, caseMenuName) {
    var url = ctx + "front/caseInfoList";
    if ('' == pageNum) {
        pageNum = 1;
    }
    var pageSize = 10;
    if ('206*155' == imgType) {
        pageSize = 40;
    } else if ('343*258' == imgType) {
        pageSize = 12;
    } else if ('510*336' == imgType) {
        pageSize = 8;
    }
    $.post(url, {
        pageSize: pageSize,
        pageNum: pageNum,
        caseMenuId: caseMenuId
    }, function (resp) {
        var rows = resp.rows;
        var total = resp.total;
        initPageData(rows, pageNum, total, caseMenuId, caseMenuName, imgType);
    });
    //控制显隐
    showNext(caseType);
}

// 查询图片详情，展示
function caseTextShow(caseInfoId, caseMenuName) {
    var url = ctx + "front/caseText/" + caseInfoId;
    $.post(url, function (resp) {
        var caseInfo = resp.data.caseInfo;
        var preCaseInfo = resp.data.preCaseInfo;
        var nextCaseInfo = resp.data.nextCaseInfo;

        var showContent = caseInfo.caseText;
        // 如果富文本没有内容，默认显示封面图
        if ('' == showContent || null == showContent) {
            showContent = '<img class="tmpCoverShow" src="' + caseInfo.coverImg + '">';
        }
        var s = '<h5>' + caseInfo.caseName + '</h5>' +
            '<div class="timeTimes">' +
            '  时间：' + caseInfo.createTime +
            '  &nbsp&nbsp&nbsp' +
            '  浏览：' + caseInfo.viewTimes + '次' +
            '</div>' +
            '<div>' + showContent + '</div>' +
            '<div class="preAndNext">';
        if ('' != preCaseInfo && null != preCaseInfo) {
            s += '    <span>上一篇：<a href="javascript:caseTextShow(' + preCaseInfo.caseId + ',\'' + caseMenuName + '\')">' + preCaseInfo.caseName + '</a></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp';
        }
        if ('' != nextCaseInfo && null != nextCaseInfo) {
            s += '    <span>下一篇：<a href="javascript:caseTextShow(' + nextCaseInfo.caseId + ',\'' + caseMenuName + '\')">' + nextCaseInfo.caseName + '</a></span>';
        }
        s += '</div>';
        initData(caseMenuName, s);
        //控制显隐
        showNext();
    });
}

// 查询通知详情，展示
function noticeShow(noticeId, caseMenuName) {
    var url = ctx + "front/noticeInfo/" + noticeId;
    $.post(url, function (resp) {
        var notice = resp.data.notice;
        var preNotice = resp.data.preNotice;
        var nextNotice = resp.data.nextNotice;

        var s = '<h5>' + notice.noticeTitle + '</h5>' +
            '<div class="timeTimes">' +
            '  时间：' + notice.createTime +
            '  &nbsp&nbsp&nbsp' +
            '  浏览：' + notice.viewTimes + '次' +
            '</div>' +
            '<div class="noticeContent">' + notice.noticeContent + '</div>' +
            '<div class="preAndNext">';
        if ('' != preNotice && null != preNotice) {
            s += '    <span>上一篇：<a href="javascript:noticeShow(' + preNotice.noticeId + ',\'' + caseMenuName + '\')">' + preNotice.noticeTitle + '</a></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp';
        }
        if ('' != nextNotice && null != nextNotice) {
            s += '    <span>下一篇：<a href="javascript:noticeShow(' + nextNotice.noticeId + ',\'' + caseMenuName + '\')">' + nextNotice.noticeTitle + '</a></span>';
        }
        s += '</div>';
        initData(caseMenuName, s);
        //控制显隐
        showNext();
    });
}

// 分页查询通知列表
function noticePage(pageNum, caseMenuId, caseMenuName) {
    var url = ctx + "front/noticeList";

    if ('' == pageNum) {
        pageNum = 1;
    }
    var pageSize = 5;
    $.post(url, {
        pageSize: pageSize,
        pageNum: pageNum,
        caseMenuId: caseMenuId
    }, function (resp) {
        var rows = resp.rows;
        var total = resp.total;
        initNoticePage(rows, pageNum, total, caseMenuId, caseMenuName);
    });

    //控制显隐
    showNext();
}


/**
 * 显示首页中间区域
 */
function showIndex() {
    $('#indexSection').show();
    $('#nextSection').hide();
}

/**
 * 显示其他菜单中间区域
 */
function showNext(e) {
    $('#indexSection').hide();
    $('#nextSection').show();
    if ('imgs' == e) {
        // 如果是多图，隐藏远程服务模块
        $('#onlineService').hide();
        // 内部显示区域加宽
        $('.textCaseInfo').css('width', '1060px');
    } else {
        $('#onlineService').show();
        $('.textCaseInfo').css('width', '750px');
    }
}

/**
 * 填充中间显示区域数据
 * @param caseName
 * @param textCaeInfo
 */
function initData(caseName, textCaeInfo) {
    $('#contentTitle').html(caseName);
    $('#textContent').children('.textCaseInfo').html(textCaeInfo);
}

/**
 * 初始化图片分页列表显示
 * @param rows
 * @param pageNum
 * @param total
 * @param caseMenuId
 * @param caseMenuName
 * @param imgType
 */
function initPageData(rows, pageNum, total, caseMenuId, caseMenuName, imgType) {
    var pageSize = 10;
    var caseImgClass = 'caseImg2';
    var titFlag = false;
    if ('206*155' == imgType) {
        pageSize = 40;
        caseImgClass = 'caseImg2';
    } else if ('343*258' == imgType) {
        pageSize = 12;
        caseImgClass = 'caseImg3';
        titFlag = true;
    } else if ('510*336' == imgType) {
        pageSize = 8;
        caseImgClass = 'caseImg5';
        titFlag = true;
    }
    // 列表数据拼接
    var s = '<div class="imgClick">';
    $.each(rows, function (i, e) {
        s += '<a href="javascript:caseTextShow(' + e.caseId + ',\'' + caseMenuName + '\');">';
        if (titFlag) {
            s += '<div class="biggerImg"><img class="' + caseImgClass + '" src="' + e.coverImg + '"><br><span>' + e.caseName + '</span></div>';
        } else {
            s += '   <img class="' + caseImgClass + '" src="' + e.coverImg + '">';
        }
        s += '</a>';
    });
    s += '</div>';
    // 分页码
    var p = '<div class="pageItem" aria-label="Page navigation">' +
        '    <ul class="pagination">';
    if (1 == pageNum) {
        p += '<li class="disabled">';

    } else {
        p += '<li>';
    }
    p += '  <a href="javascript:caseImgPage(' + (pageNum - 1) + ',' + caseMenuId + ',\'imgs\',\'' + imgType + '\',\'' + caseMenuName + '\');" aria-label="Previous">' +
        '     &laquo;' +
        '  </a>' +
        '</li>';

    //上一页和下一页之间显示的页码数
    var showPages = 10;
    //总页码数
    var totalPages = total % pageSize == 0 ? Math.floor((total / pageSize)) : Math.floor((total / pageSize + 1));
    //显示的起始页码
    var pageStart = Math.max(1, pageNum - Math.floor(showPages / 2));
    //显示的尾页
    var pageEnd = Math.min(totalPages, pageStart + showPages - 1);
    pageStart = Math.max(1, pageEnd - showPages + 1);

    while (pageEnd - pageStart >= 0) {
        if (pageNum == pageStart) {
            p += '<li class="active"><a href="javascript:void(0);">' + pageStart++ + '</a></li>';
        } else {
            p += '<li><a href="javascript:caseImgPage(' + pageStart + ',' + caseMenuId + ',\'imgs\',\'' + imgType + '\',\'' + caseMenuName + '\');">' + pageStart + '</a></li>';
            pageStart++;
        }
    }
    if (pageNum == totalPages) {
        p += '<li class="disabled">';

    } else {
        p += '<li>';
    }
    p += '  <a href="javascript:caseImgPage(' + (pageNum + 1) + ',' + caseMenuId + ',\'imgs\',\'' + imgType + '\'mx,\'' + caseMenuName + '\');" aria-label="Next">' +
        '     &raquo;' +
        '   </a>' +
        '  </li>' +
        ' </ul>' +
        '</div>';
    var textCaeInfo = s + p;
    initData(caseMenuName, textCaeInfo);
}

/**
 * 初始化通知分页列表显示
 * @param rows
 * @param pageNum
 * @param total
 * @param caseMenuId
 * @param caseMenuName
 */
function initNoticePage(rows, pageNum, total, caseMenuId, caseMenuName) {
    // 列表数据拼接
    var s = '';
    $.each(rows, function (i, e) {
        s += '<div class="noticeRow">' +
            '   <img src="' + e.coverImg + '">' +
            '   <div class="rowInfo">' +
            '      <a href="javascript:noticeShow(' + e.noticeId + ',\'' + caseMenuName + '\');"><h5>' + e.noticeTitle + '</h5></a>' +
            '      <p>' + e.noticeSummary + '</p>' +
            '      <p>时间：' + e.createTime + '</p>' +
            '   </div>' +
            '</div>';
    });
    s += '<hr>';
    // 分页码
    var p = '<div class="pageItem" aria-label="Page navigation">' +
        '    <ul class="pagination">';
    if (1 == pageNum) {
        p += '<li class="disabled">';

    } else {
        p += '<li>';
    }
    p += '  <a href="javascript:noticePage(' + (pageNum - 1) + ',\'' + caseMenuId + '\',\'' + caseMenuName + '\');" aria-label="Previous">' +
        '     &laquo;' +
        '  </a>' +
        '</li>';

    //上一页和下一页之间显示的页码数
    var showPages = 10;
    // 页容量
    var pageSize = 5;
    //总页码数
    var totalPages = total % pageSize == 0 ? Math.floor((total / pageSize)) : Math.floor((total / pageSize + 1));
    //显示的起始页码
    var pageStart = Math.max(1, pageNum - Math.floor(showPages / 2));
    //显示的尾页
    var pageEnd = Math.min(totalPages, pageStart + showPages - 1);
    pageStart = Math.max(1, pageEnd - showPages + 1);

    while (pageEnd - pageStart >= 0) {
        if (pageNum == pageStart) {
            p += '<li class="active"><a href="javascript:void(0);">' + pageStart++ + '</a></li>';
        } else {
            p += '<li><a href="javascript:noticePage(' + pageStart + ',\'' + caseMenuId + '\',\'' + caseMenuName + '\');">' + pageStart + '</a></li>';
            pageStart++;
        }
    }
    if (pageNum == totalPages) {
        p += '<li class="disabled">';

    } else {
        p += '<li>';
    }
    p += '  <a href="javascript:noticePage(' + (pageNum + 1) + ',\'' + caseMenuId + '\',\'' + caseMenuName + '\');" aria-label="Next">' +
        '     &raquo;' +
        '   </a>' +
        '  </li>' +
        ' </ul>' +
        '</div>';
    var textCaeInfo = s + p;
    initData(caseMenuName, textCaeInfo);
}
