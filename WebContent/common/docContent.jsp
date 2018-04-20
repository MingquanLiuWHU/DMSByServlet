<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档显示</title>
</head>
<body>
	<!-- 显示文档的详情 -->
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">标题</h3>
		</div>
		<div class="panel-body">
			<p>${doc.title }</p>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">作者</h3>
		</div>
		<div class="panel-body">

			<p>${doc.authorName }</p>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">类别</h3>
		</div>
		<div class="panel-body">
			<p>${doc.type }</p>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">内容</h3>
		</div>
		<div class="panel-body">
			<p>${doc.content }</p>
		</div>
	</div>

	<c:if test="${ user.assessor==true}">
		<div class="panel panel-default">
			<button type="button" class="btn btn-default"
				onclick="assessDoc(${doc.id},'true')">发布</button>
			<button type="button" class="btn btn-default"
				onclick="assessDoc(${doc.id},'false')">退回作者</button>
		</div>
	</c:if>

</body>
</html>