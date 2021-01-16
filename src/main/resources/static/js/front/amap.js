var infoWindow, text, map, aboutInfo;
$(function () {
    var url = ctx + "front/about";
    $.post(url, function (resp) {
        aboutInfo = resp.data;
        map = new AMap.Map("mapContainer", {
            resizeEnable: true,
            center: [114.63231325, 38.03733484],
            zoom: 13
        });

        text = new AMap.Text({
            text: aboutInfo.aboutName,
            map: map
        });
        text.on('click', function () {
            openInfo();
            text.hide();
        });
        text.hide();
        openInfo();
    });
});

//在指定位置打开信息窗体
function openInfo() {
    //构建信息窗体中显示的内容
    var info = [];
    info.push('<div class="dadeWindow">');
    info.push("<div style=\"padding:7px 0px 0px 0px;\"><h4>" + aboutInfo.aboutName + "<a href='javascript:closeWindow();'>×&nbsp&nbsp</a></h4>");
    info.push("<p >地址：" + aboutInfo.address + "</p>");
    info.push("<p >电话：" + aboutInfo.telephone + "&nbsp" + aboutInfo.cellphone + "</p>");
    info.push("<p >邮箱：" + aboutInfo.email + "</p>");
    info.push('</div>')
    infoWindow = new AMap.InfoWindow({
        isCustom: true,
        content: info.join(""),
        close: function () {
            text.show();
        }
    });
    infoWindow.open(map, [114.63231325, 38.03733484]);
}

function closeWindow() {
    infoWindow.close();
    text.show();
}
