<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 对于onsubmit="return checklogin()报错，只要项目右击-myeclipse-Exclude Form Validation单击一下，打上√号 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>用户登陆</title>
<script type="text/javascript">
var msg='${message}';
if(msg!=""){
	alert(msg);
}
</script>
<script type="text/javascript">
 function checklogin(){
	var name=document.getElementById("aname");	
	if(name.value==null){
		alert("用户名不能为空！");
		name.focus();
		return false;
	}
    var  pwd=document.getElementById("apwd");
    if(pwd.value==null){
    alert("密码不能为空！");
    pwd.focus();
    return false;
    }
	return true;
	

	
	
}

    </script>

</head>

<body>
	<h1 align="center">用户登陆</h1>
	<form action="login.action" onsubmit="return checklogin()">
		<table align="center">
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="aname" id="aname" />
				</td>
			</tr>
			<tr>
				<td>密&nbsp;&nbsp;码：</td>
				<td><input type="password" name="apwd" id="apwd" />
				</td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="登陆" />&nbsp;&nbsp;
					<a href="register.jsp">没有账户？点击注册</a>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>
