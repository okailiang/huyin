$(function() {

	var url = window.location.pathname;
	if(url.indexOf("adminFile.jsp")>0||url.indexOf("admin_getAllFiles.do")>0)
		$("#file-manage-li").addClass("active");
	else if(url.indexOf("adminUserOrder.jsp")>0)
		$("#users-order-li").addClass("active");
	else if(url.indexOf("adminOrderStatistic.jsp")>0)
		$("#order-statistic-li").addClass("active");
	else if(url.indexOf("adminUserInfo.jsp")>0||url.indexOf("reloaduserslist.do")>0||url.indexOf("adminlogin.do")>0)
		$("#users-manage-li").addClass("active");
	else if(url.indexOf("adminPrinterPrice.jsp")>0||url.indexOf("getAllPrices.do")>0)
		$("#printer-price-li").addClass("active");
	else if(url.indexOf("getAllPrices.do")>0)
		$("#printer-price").addClass("active");
	else if(url.indexOf("index.jsp")>0)
		$("#index-li").addClass("active");
	else if(url.indexOf("login.jsp")>0||url.indexOf("Login.jsp")>0)
		$("#login-li").addClass("active");
	else if(url.indexOf("register.jsp")>0)
		$("#register-li").addClass("active");
	else if(url.indexOf("print.jsp")>0||url.indexOf("Order.jsp")>0)
		$("#print-li").addClass("active");
    
	if(url.indexOf("printFile.jsp")>0||url.indexOf("userlogin.do")>0||url.indexOf("file.jsp")>0||url.indexOf("Order.jsp")>0||url.indexOf("getFilesByIds.do")>0||url.indexOf("anonymouslogin.do")>0)
		$("#print-file-li").addClass("active");
	else if(url.indexOf("printPicture.jsp")>0)
		$("#print-picture-li").addClass("active");
	else if(url.indexOf("file.jsp")>0 || url.indexOf("getAllFiles.do")>0)
		$("#file-li").addClass("active");
	else if(url.indexOf("order.jsp")>0)
		$("#order-li").addClass("active");
	else if(url.indexOf("modify")>0||url.indexOf("updateemailandtelenumber.do")>0)
		$("#modify-li").addClass("active");
	else if(url.indexOf("feedInfo.jsp")>0||url.indexOf("saveFeedInfo.do")>0)
		$("#feedback-li").addClass("active");
	else if(url.indexOf("getAllFeedInfos.do")>0)
		$("#users-feedback-li").addClass("active");
	else if(url.indexOf("adminPrinterAddress.jsp")>0||url.indexOf("adminAddPrinter.jsp")>0||url.indexOf("savePrinterInfo.do")>0||url.indexOf("adminEditPrinter.jsp")>0)
		$("#printer-address-li").addClass("active");

	$("#check-all").click(function() {
		var chsub = $("input[type='checkbox']").length;
		var checkedsub = $("input[type='checkbox']:checked").length;
		$("input[type='checkbox']").each(function() {
			if (!$(this).is(":checked")&&!this.hasAttribute("disabled")) {
				$(this).prop("checked", true);
			} else if (chsub != checkedsub&&!this.hasAttribute("disabled")) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});
	});	
	$("#back-top a").attr("href",window.location.href+"#");
	
});


/**
 * 初始化Mask
 */
function initMask() {
	$("body").append('<div id="mask" class="mask"></div> ');
};
initMask();
/**
 * 显示遮罩层
 */
function showMask() {
	$("#mask").css("height", $(document).height());
	$("#mask").css("width", $(document).width());
	$("#mask").show();
};
/**
 * 隐藏遮罩层
 */
function hideMask() {
	$("#mask").hide();
};

