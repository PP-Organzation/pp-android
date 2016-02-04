<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/MyPage.tld" prefix="pt"%>
<c:if test="${from=='login' }">
	<script type="text/javascript">
		alert("登陆成功");
	</script>
</c:if>

<c:if test="${from=='delete' }">
	<script type="text/javascript">
		alert("删除成功");
	</script>
</c:if>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>管理员登陆界面</title>






</head>

<body>
	<h2>管理员管理用户界面</h2>

	<div align="center">
		<table border="1">
			<tr style="background-color:rgb(255,10,10);">
				<td>编号</td>
				<td>用户名</td>
				<td>删除用户</td>
			</tr>


			<c:forEach items="${list }" var="person" varStatus="status">

				<tr
					<c:if test="${status.index%2==1}">style="background-color:rgb(219,241,212);"</c:if>>
					<td>${person.aid }</td>
					<td>${person.aname }</td>
					<td><a href="person_delete?id=${person.aid }&page=${page }">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<br> <br>

		<div>
			<pt:page pageIndex="${page }" url="person_view?"
				pageMax="${pageCount }" />
		</div>


		<h4>
			<a href="index.jsp">返回首页</a>
		</h4>

	</div>



</body>
</html>
