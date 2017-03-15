
/*自定义alert，confirm，tip*/
var a4print = window.a4print || {};
/*定义弹出框方法*/
a4print.dialog = function() {

    var e, t, n = {}, 
/*显示弹出框，背景遮罩，提示nav，title*/
        r = function() {
            var n = ['<div class="mod-dialog-bg"></div>', '<div class="mod-dialog">', '<div class="dialog-nav">',
             '<span class="dialog-title"></span>',
             '<a href="#" onclick="return false" class="dialog-close glyphicon glyphicon-remove"></a>',   
              "</div>", '<div class="dialog-main"></div>', "</div>"].join(""),
                r = $(n).hide().appendTo("body");
                e = r.filter(".mod-dialog-bg"),
                t = r.filter(".mod-dialog"),  //找到class为mod-dialog的div元素
                t.find(".dialog-close").click(function() {//找class为mod-dialog的div的子类中class为dialog-close这个的对象添加方法
                u()
            })
        }, 

/*把标题和html添加进去*/
        i = function() {
             t.css("width", n.width || ""),
             t.find(".dialog-title").html(n.title), 
             t.find(".dialog-main").html(n.html), 
             t.show(), 
             e.show(), 
             s()
        }, 
 /*确定位置*/
        s = function() {
            var e = ($(window).width() - t.width()) / 2,
                n = ($(window).height() - t.height()) / 2;
            n = n > 0 ? n + $(window).scrollTop() : 0, t.css({
                left: e,
                top: n
            })
        }, 

/*{html: r}*/
        o = function(e) {
            return typeof e != "object" && (e = {
                html: e || ""
            }), n = $.extend({
                title: "提示",
                html: "",
                closeFn: null
            }, e), t || r(), i(), t
        }, 
 /*关闭窗口，背景遮罩e隐藏，关闭按钮t隐藏，弹窗n关闭*/
        u = function() {
            e && e.hide(), t && t.hide(), n.closeFn && n.closeFn.call(this)
        };
    return {
        show: o,
        hide: u
    };
}();

/*确认框
e表示提示的内容dialog-content，t表示确认按钮方法，n表示取消按钮方法*/
window.confirm=a4print.dialog.confirm = function(e, t, n) {
    var r = ['<div class="dialog-content">', "<p>" + e + "</p>", "</div>", '<div class="dialog-console clearfix_new">', '<a class="console-btn-confirm btn btn-info" href="#" onclick="return false;">确定</a>', '<a class="console-btn-cancel btn btn-default" href="#" onclick="return false;">取消</a>', "</div>"].join(""),
        i = a4print.dialog.show({
            html: r
        });
     $('.mod-dialog').css("animation","");
    return i.find(".console-btn-confirm").click(function() {
        var e = t && t.call(i);
        e !== !1 && a4print.dialog.hide()
    }), 

    i.find(".console-btn-cancel").click(function() {
        n && n.call(i), a4print.dialog.hide()
    }), i;

};

/*alert提示框*/
 window.alert =a4print.dialog.alert = function(e, t) { 
    var n = ['<div class="dialog-content">', "<p>" + e + "</p>", "</div>", '<div class="dialog-console clearfix_new">', '<a class="console-btn-confirm btn btn-info" href="#" onclick="return false;">确定</a>', "</div>"].join(""),
        r = a4print.dialog.show({
            html: n
        });
        $('.mod-dialog').css("animation","");
    return r.find(".console-btn-confirm").click(function() {
        var e = t && t.call(r);
        e !== !1 && a4print.dialog.hide()
    }), r;
}; 

/*小提示tip*/
a4print.dialog.tip=function(e,t){
     var n = ['<div class="dialog-content">', "<p>" + e + "</p>", "</div>", '<div class="dialog-console clearfix_new">', "</div>"].join(""),
        
        r = a4print.dialog.show({
            html: n
        });

    return r;
};


/*tip淡出*/
var tip=function fadeout(e,t){

    a4print.dialog.tip(e,t);
    /*添加帧*/
    $.keyframe.define([{
     name: 'myfirst',
    '0%':{
        'margin':'0 auto',
        'top':'10px'
    },
    '100%':{
        'margin':'0 auto',
        'top':'50px'
    }
    }]);
     $('.mod-dialog').playKeyframe({
            name: 'myfirst',
            duration: "1s",           
            fillMode: 'forwards',
          });

    $('.dialog-close ').removeClass("glyphicon glyphicon-remove");

    setTimeout("$('.mod-dialog-bg').fadeOut('2000'),$('.mod-dialog').fadeOut('2000')",1000);
    
}

var payFalseTip = function(e,t){
    a4print.dialog.tip(e,t);
    $('.dialog-close ').removeClass("glyphicon glyphicon-remove");
    $('.dialog-content p').text('支付失败，在3秒后自动关闭');
    setTimeout("$('.dialog-content p').text('支付失败，在2秒后自动关闭')",1000);
    setTimeout("$('.dialog-content p').text('支付失败，在1秒后自动关闭')",2000);
    setTimeout("$('.mod-dialog-bg').hide(),$('.mod-dialog').hide()",3000);
}
var paySuccessTip = function(e,t){
    a4print.dialog.tip(e,t);
    $('.dialog-close ').removeClass("glyphicon glyphicon-remove");
    $('.dialog-content p').text('支付成功，在3秒后自动关闭');
    setTimeout("$('.dialog-content p').text('支付成功，在2秒后自动关闭')",1000);
    setTimeout("$('.dialog-content p').text('支付成功，在1秒后自动关闭')",2000);
    setTimeout("$('.mod-dialog-bg').hide(),$('.mod-dialog').hide()",3000);
}
