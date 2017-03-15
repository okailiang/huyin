<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
HttpSession s = request.getSession(false);
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0);
if(s==null){
	response.sendRedirect("../users/index.jsp");
}
String role = (String) s.getAttribute("role");
s.invalidate();

if(role.equals("1")||role.equals("4")){
	response.sendRedirect("login.jsp");
}else if(role.equals("3")){
	response.sendRedirect("../printer/printerLogin.jsp");
}else if(role.equals("2")){
	response.sendRedirect("../admin/adminLogin.jsp");
}
%>