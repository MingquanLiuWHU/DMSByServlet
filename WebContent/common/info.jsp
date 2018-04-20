<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">个人信息</h3>
		</div>
		<div class="panel-body">
			帐号：${user.account } <br /> 用户名： ${user.username }
		</div>
	</div>
	<button id="change"type="button" class="btn btn-default" onclick="changeEditArea()">
	打开修改栏
	</button>
	<div id="edit" class="panel panel-default" style="display: none">
		<div class="panel-heading">
			<h3 class="panel-title">修改信息</h3>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label for="username">输入新用户名</label> <input type="text"
					class="form-control" id="username" /> <label for="password">输入新密码</label>
				<input type="password" class="form-control" id="password" />
				<button class="btn btn-default" type="button" onclick="saveInfo()">修改</button>
			</div>
		</div>
	</div>
</body>
</html>