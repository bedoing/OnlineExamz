<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考生管理员个人中心</title>
<style type="text/css">

.top_nei{
	width:100%;
	text-align:center;
	height:40px;
	background-color:#1E90FF;
	line-height:40px;
	font-size: 18px;
	font-family: "微软雅黑";
	color:white;
}
.menu{

line-height:50px;
}
.menu a:hover{
color:#FF6600;
}
p a:hover{
color:#FF6600;
}
.STYLE5 {font-size: 18px; font-weight: bold; font-family: "微软雅黑"; text-decoration: none; color: #000000; }
</style>
</head>

<body>

<p align="right"><a href="personal-2.jsp" class="STYLE5">首页</a></p>
<div class="top_nei">
<div align="left">
当前用户：<%=(String)session.getAttribute("Name") //将对象类型转换为String类型；输出用户的名字。
%></div>
</div>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div class="menu">
    <ul>
      <li><a href="addst.jsp" class="STYLE5">考生信息添加</a></li>
      <li><a href="stchange.jsp" class="STYLE5">考生信息修改</a> </li>     
       <li><a href="stqueryo.jsp" class="STYLE5">考生信息查询</a></li>
      <li>
       <a href="deletest.jsp" class="STYLE5">考生信息删除</a></li>
    </ul>
</div>  
<p>&nbsp;</p>
<p>&nbsp;</p>
</body>
</html>
