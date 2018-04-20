<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*,cn.lmq.dmsbyservlet.bean.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

</head>
<!-- 一般用户和审核员加载的文档列表，审核员没有审核发布状态和修改删除的功能，但多一个显示作者的列 -->
<body>
	<table class="table table-bordered table-striped ">
		<thead>
			<tr>
				<th>标题</th>
				<th>类别</th>
				<th>修改时间</th>
				<c:if test="${user.assessor==false }">
					<th>审核发布</th>
				</c:if>
				<c:if test="${user.assessor==true }">
					<th>作者</th>
				</c:if>
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
							<c:if test="${user.assessor==false }">
								<td><c:choose>
										
										<c:when test="${doc.published==true }">已发布</c:when>
										<c:when test="${doc.submitted==true }">已提交</c:when>

										<c:otherwise>未提交</c:otherwise>
									</c:choose></td>
							</c:if>
							<c:if test="${user.assessor==true }">
								<td>${doc.authorName }</td>
							</c:if>
							<td>

								<div class="btn-group">

									<c:if test="${doc.submitted==false }">
										<button type="button" class="btn " onclick="submit(${doc.id})">提交</button>
									</c:if>
									<button type="button" class="btn " onclick="showDoc(${doc.id})">打开</button>
								
									<c:if test="${user.assessor==false }">
										<button type="button" class="btn "
											onclick="modifyDoc(${doc.id})">修改</button>
										<button type="button" class="btn "
											onclick="setDeleteDoc(${doc.id})">删除</button>
									</c:if>
									
									<c:if test="${user.assessor==true }">
										<button type="button" class="btn " onclick="assessDoc(${doc.id},'true')">发布</button>
										<button type="button" class="btn " onclick="assessDoc(${doc.id},'false')">退回作者</button>
									</c:if>
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<div class="btn-group">
		<button class="btn btn-default" onclick="openEditJsp()">新增文件</button>

	</div>

</body>
</html>