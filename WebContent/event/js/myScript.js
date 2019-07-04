window.onload=myFunction;
function myFunction(){
    var timer = null,
        index = -1;
        pics = $(".banner-slide");
    //封装一个代替getElementById()的方法
    function byId(id){
        return typeof(id) === "string"?document.getElementById(id):id;
    }
     
    function slideImg() {
        var banner = byId("banner");
        banner.onmouseover = function(){
            stopAutoPlay();
        }
        banner.onmouseout = function(){
            startAutoPlay();
        }
        banner.onmouseout();
    }

    //开始播放轮播图
    function startAutoPlay(){
        timer = setInterval(function(){
            index++;
            if(index>4){
                index = 0;
            }
            if(index<0){
                index=4
            }
            changeImg();
        },1000);
    }
    //暂停播放
    function stopAutoPlay(){
        if (timer) {
            clearInterval(timer);
        }
    }
    slideImg();
    //改变轮播图
    function changeImg(){
        for(var i=0;i<pics.length;i++){
            pics[i].style.display = "none";
        }
        pics[index].style.display = "block";
    }
    function leftArr() {
        if (timer) {
            clearInterval(timer);
        }
        index--;
        if(index<0){
            index = 4;
        }
        changeImg();
    };
    
    function rightArr() {
        if (timer) {
            clearInterval(timer);
        }
        index++;
        if(index>4){
            index = 0;
        }
        changeImg();
    };
}
