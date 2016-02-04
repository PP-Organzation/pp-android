<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
table {
	border: 1px;
	border-collapse: collapse;
	border-color: #000
}
</style>
<script type="text/javascript">
function back(){
	  history.go(-1);
}



function checkregister(){

	var name=document.getElementById("aname");	

	if(name.value==""){
		alert("用户名不能为空！");
		name.focus();
		return false;
	}
    var  pwd=document.getElementById("apwd");
    if(pwd.value==""){
	    alert("密码不能为空！");
	    pwd.focus();
	    return false;
    }
	return true;
}
</script>


<title>My JSP 'register.jsp' starting page</title>

</head>

<body>
	<h1 align="center">用户注册</h1>
	<form action="person_register" onsubmit="return checkregister()">
		<table align="center" border="1">
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="person.aname" id="aname"
					style="width: 300px" />
				</td>
			</tr>
			<tr>
				<td>密&nbsp;&nbsp;码：</td>
				<td><input type="password" name="person.apwd" id="apwd"
					style="width: 300px" />
				</td>
			</tr>

			<tr>
				<td>验证码：</td>
				
				
				<td>
				<input type="text" name="yanzheng" id="yanzheng" style="width: 70px" /> 
				<!--  onclick="src='image.jsp'"如果你的浏览器没有设置每次访问重新请求的话那么这么写是无法刷新的，就要改写为 onclick="src='image.jsp?id='+Math.random();"-->
				
				<img src="image.jsp" align="absmiddle" title="看不清,点击刷新" onclick="src='image.jsp'" />
				</td>

			</tr>

			<tr align="center">
				<td colspan="2"><input type="submit" value="注册" />&nbsp;&nbsp;
					<input type="button" value="返回登陆" onclick="back();"></td>
			</tr>
		</table>

	</form>
</body>
</html>
