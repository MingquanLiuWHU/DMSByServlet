<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核员</title>
</head>
<body>
	<nav class="navbar navbar-default ">
		<div class="container-fluid ">
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav ">
					<li><a href="javascript:void(0)" role="button" id="doc"
						 onclick="docsSubmitted()">审核文档</a></li>
					<li><a href="javascript:void(0)" role="button"
						onclick="showInfo()">个人资料</a></li>
					<li><a href="javascript:void(0)" role="button"
						onclick="exit()">退出登录</a></li>
				</ul>
			</div>
		</div>
	</nav>


	<div id="view"></div>
</body>
</html>