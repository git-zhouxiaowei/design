$(function(){
    move();
});

window.onscroll = function(){
    move();
}

var timer = null;
function startMove(argument) {
    var scrollsidebar = document.getElementById("scrollsidebar");
    clearInterval(timer);
    timer = setInterval(function() {
        var speed = (argument - scrollsidebar.offsetTop) / 4;
        speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
        if (argument == scrollsidebar.offsetTop) {
            clearInterval(timer);
        } else {
            scrollsidebar.style.top = scrollsidebar.offsetTop + speed + "px";
        }
    }, 60);
};

function move() {
    var scrollsidebar = document.getElementById("scrollsidebar");
    var scrolltop = document.body.scrollTop || document.documentElement.scrollTop;
    startMove(
        parseInt((document.documentElement.clientHeight - scrollsidebar.offsetHeight) / 2 + scrolltop)
    );
}
