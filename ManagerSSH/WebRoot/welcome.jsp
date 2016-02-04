<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
   
<script type="text/javascript">
var msg='${message}';
if(msg!=""){
	alert(msg);
}
</script>
  </head>
  
  <body>
   <div class="namediv">欢迎您：${person.aname }
	<br>

	<form action="person_update" >
		<input type="hidden" name="person.aid" value="${person.aid }" />
		<table align="center" border="1">

			<tr>
				<td>用户名：</td>
				<td><input type="text" name="person.aname" 
					value="${person.aname }" /></td>
			</tr>
			<tr>
				<td>密&nbsp;&nbsp;码：</td>
				<td><input type="text" name="person.apwd" 
					value="${person.apwd }" /></td>
			</tr>

			<tr align="center">
				<td colspan="2"><input type="submit" value="修改" />
				               <input type="button" value="退出" onclick="location='index.jsp'"/>
					
				</td>
				
			</tr>
		</table>

	</form>

  </body>
</html>
