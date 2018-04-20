<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改文档</title>
</head>
<body>
	<form role="form">
		<div class="form-group">
			<label for="name">标题</label> <input type="text" class="form-control"
				id="title" value="${doc.title }" />
		</div>
		<div class="form-group">
			<label for="type">类别</label> <input class="form-control" type="text"
				id="type" value="${doc.type }" />
		</div>
		<div class="form-group">
			<label for="content">内容</label>
			<textarea rows="15" class="form-control" id="content"></textarea>
		</div>
		<button type="button" class="btn btn-default"
			onclick="saveDoc(${doc.id})">保存</button>
	</form>
</body>
</html>