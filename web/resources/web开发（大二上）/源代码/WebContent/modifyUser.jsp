<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="bean.*"%>
<html>
<head>
<title></title>
<script>
	var xmlHttp;
	var checkUserName1 = false;
	var checkPsw1 = false;
	var checkPswConfirm1 = false;
	function checkPsw() {
		if (myForm.psw1.value.length<6||myForm.psw1.value.length>16) {
			document.getElementById("check3").innerHTML = "密码长度应在6-16之间";
			checkPsw1 = false;
		} else {
			document.getElementById("check3").innerHTML = "";
			checkPsw1 = true;
		}
	}
	function checkPswConfirm() {
		if (myForm.psw1.value != myForm.psw2.value) {
			document.getElementById("check2").innerHTML = "两次密码不一致";
			checkPswConfirm1 = false;
		} else {
			document.getElementById("check2").innerHTML = "";
			checkPswConfirm1 = true;
		}
	}
	function processCheckUserName() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			if (xmlHttp.responseText == "该用户名已被占用！")
				checkUserName1 = false;
			else if (xmlHttp.responseText == "用户名不能为空")
				checkUserName1 = false;
			else
				checkUserName1 = true;

			document.getElementById("check1").innerHTML = xmlHttp.responseText;
		}
	}
	function createXmlHttpRequest() {
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	function sendCheckUserName() {
		createXmlHttpRequest();
		xmlHttp.open("GET",
				"modifyCheck.jsp?userName=" + myForm.username.value, true);
		xmlHttp.onreadystatechange = processCheckUserName;
		xmlHttp.send();
	}
	function onSubmit() {
		if (checkPswConfirm1 && checkUserName1 && checkPsw1) {
			if (myForm.username.value.length == 0) {
				return true;
			}
		} else {
			alert("信息填入有误！");
			return false;
		}
	}
</script>
</head>
<body>
	<center>
		<div>
			<%
				UserInfoBean user = (UserInfoBean) session.getAttribute("user");
				if (user == null) {
					response.sendRedirect("index.jsp");
			%>
			<a href="login.jsp">用户登录</a>&nbsp;&nbsp;&nbsp;没有账户？-><a
				href="register.jsp">新用户注册</a>
			<%
				} else {
			%>
			欢迎您，<%=user.getUserName()%>
			<a href="personal.jsp">个人空间</a>&nbsp;&nbsp;&nbsp;<%
				if (user.getRequest() == 1) {
			%>
			<a href="manage.jsp">管理用户</a>&nbsp;&nbsp;&nbsp;
			<%
				}
			%>
			<a href="cart.jsp">查看购物车</a> &nbsp;&nbsp;&nbsp;<a href="storage.jsp">我的仓库</a>&nbsp;&nbsp;&nbsp;<a
				href="user.do?source=logout">退出登录</a>
			<%
				}
			%>
		</div>
		<br>
		<div>
			您的位置：<a href="index.jsp">首页</a>>>修改个人信息
		</div>
		<h1>修改个人信息</h1>
		<form action="user.do" name="myForm" method="post"
			onsubmit="return onSubmit()">
			<table align="center" border="0" bordercolor="red" cellpadding="5"
				cellspacing="0" width="600" bgcolor="#ffffcc">
				<tr bgcolor="#ffcc00">
					<td colspan="3" align="center">请输入新的用户信息</td>
				</tr>
				<tr>
					<td align="right"><font size="2">用户名</font></td>
					<td><input type="text" name="username" size="10"
						onkeyup="sendCheckUserName()" /></td>
					<td align="left"><div id="check1"></div></td>
				</tr>
				<tr>
					<td align="right"><font size="2">请输入新的密码</font></td>
					<td><input type="password" name="psw1" size="10"
						onkeyup="checkPsw()" /></td>
					<td align="left"><div id="check3"></div></td>
				</tr>
				<tr>
					<td width="26%" align="right"><font size="2">确认密码</font></td>
					<td><input type="password" name="psw2" size="10"
						onkeyup="checkPswConfirm()" /></td>
					<td align="left"><div id="check2"></div></td>
				</tr>
				<tr align="center" bgcolor="#ffcc00">
					<td colspan="3"><font size="2">填写完成后，点击修改按钮修改信息。</font></td>
				</tr>
				<input type='hidden' name='source' value='modifyUser'>
				<tr align="center">
					<td colspan="3" align="center"><input type="submit" value="修改" />
						<input type="reset" value="重置" /></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>
