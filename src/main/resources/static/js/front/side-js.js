$(function () {
    move("scrollsidebar");
});

window.onscroll = function () {
    move("scrollsidebar");
}

var timer = null;
function startMove(argument, e) {
    var sidebar = document.getElementById(e);
    clearInterval(timer);
    timer = setInterval(function () {
        var speed = (argument - sidebar.offsetTop) / 4;
        speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
        if (argument == scrollsidebar.offsetTop) {
            clearInterval(timer);
        } else {
            sidebar.style.top = sidebar.offsetTop + speed + "px";
        }
    }, 60);
};

function move(e) {
    var sidebar = document.getElementById(e);
    var scrolltop = document.body.scrollTop || document.documentElement.scrollTop;
    startMove(parseInt((document.documentElement.clientHeight - sidebar.offsetHeight) / 2 + scrolltop), e);
}



