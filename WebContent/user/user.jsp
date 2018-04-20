<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文档管理系统</title>
<!-- <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> -->
</head>
<body>
<!-- 导航栏 -->
	<nav class="navbar navbar-default ">
		<div class="container-fluid ">
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav ">
					<li><a href="javascript:void(0)" role="button" id="doc"
						class="active" onclick="queryList('false')">我的文档</a></li>
					<li><a href="javascript:void(0)" role="button"
						onclick="readArea()">阅览区</a></li>
					<li><a href="javascript:void(0)" role="button"
						onclick="queryList('true')">回收站</a></li>
					<li><a href="javascript:void(0)" role="button"
						onclick="showInfo()">个人资料</a></li>
					<li><a href="javascript:void(0)" role="button"
						onclick="exit()">退出登录</a></li>
				</ul>


			</div>
		</div>
	</nav>

<!-- 主要视图 -->
	<div id="view"></div>
</body>
</html>