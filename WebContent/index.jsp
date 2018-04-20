<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- 加载的首页 -->
<head>
<meta charset="UTF-8">
<title>文档管理系统</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<script src="js/ajax.js"></script>
<script src="js/login.js"></script>
<script src="js/userLogic.js"></script>
<script src="js/assessorLogic.js"></script>
</head>
<body onload="initAjax()">
	<!-- 页眉 -->
	<div style="color: #2aabd2" class="page-header ">
		<h1>
			<i>文档管理系统</i>
		</h1>
	</div>

	<!-- 页面主要内容 -->
	<div id="main">
		<!-- 提示消息 -->




		<form role="form" class="form-group-lg">
			<label for="account">帐号</label> <input class="form-control"
				type="text" id="account" /><br /> <label for="password">密码</label>
			<input class="form-control" type="password" id="password" /><br />
			<!-- 必须加上type=buuton,否则会自动提交到本页面 -->
			<button type="button" class="btn btn-default" onclick="login()">登录</button>
		</form>
		<div class="panel panel-default">${message }</div>
	</div>
</body>
</html>