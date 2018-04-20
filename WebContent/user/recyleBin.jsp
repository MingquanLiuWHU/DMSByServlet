<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回收站</title>
</head>
<body>
	<table class="table table-bordered table-striped ">
		<thead>
			<tr>
				<th>标题</th>
				<th>类别</th>
				<th>删除时间</th>
				<th>操作</th>

			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty docList}">
					<tr>
						<td colspan="4">没有数据</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ docList}" var="doc">
						<tr>
							<td>${doc.title}</td>
							<td>${doc.type }</td>
							<td>${doc.modifytime }</td>
							<td>
								<div class="btn-group">
									<button class="btn " onclick="showDoc(${doc.id})">打开</button>
									<button class="btn " onclick="restoreDoc(${doc.id})">恢复</button>
									<button class="btn " onclick="deleteDoc(${doc.id})">删除</button>
								</div>
							</td>
						</tr>
					</c:forEach>

				</c:otherwise>
			</c:choose>


		</tbody>
	</table>
</body>
</html>