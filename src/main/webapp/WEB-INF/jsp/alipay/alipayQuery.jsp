<%
/* *
 *功能：即时到账交易接口接入页
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *************************注意*****************
 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
 *1、开发文档中心（https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.KvddfJ&treeId=62&articleId=103740&docType=1）
 *2、商户帮助中心（https://cshall.alipay.com/enterprise/help_detail.htm?help_id=473888）
 *3、支持中心（https://support.open.alipay.com/alipay/support/index.htm）
 *如果不想使用扩展功能请把扩展功能参数赋空值。
 **********************************************
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.alibaba.fastjson.*"%>
<%@ page import="com.alipay.api.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝即时到账交易接口</title>
	</head>
	<%
		////////////////////////////////////请求参数//////////////////////////////////////

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = request.getParameter("orderNo");
	    String tradeNo = request.getParameter("tradeNo");

		
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> params = new HashMap<String, String>();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("out_trade_no", out_trade_no);
		jsonObject.put("trade_no", tradeNo);
		params.put("app_id", "2016031601219033");
	    params.put("biz_content", jsonObject.toJSONString());
		params.put("charset", "utf-8");
		params.put("method", "alipay.trade.query");
		//params.put("out_trade_no", "2016042910401700001");
		params.put("sign_type", "RSA");
		params.put("timestamp", "2016-04-29 16:17:40");
		params.put("version", "1.0");
		//其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
        //如sParaTemp.put("参数名","参数值");
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(params,"get","确认");
		out.println(sHtmlText);
	%>
	<body>
	</body>
</html>
