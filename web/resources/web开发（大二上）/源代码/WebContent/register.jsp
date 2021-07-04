<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
<title>用户信息表单</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
		xmlHttp
				.open("GET", "check.jsp?userName=" + myForm.username.value,
						true);
		xmlHttp.onreadystatechange = processCheckUserName;
		xmlHttp.send();
	}
	function onSubmit() {
		if (checkPswConfirm1 && checkUserName1 && checkPsw1) {
			return true;
		} else {
			alert("信息填入有误！");
			return false;
		}
	}
</script>
</head>
<body>
<center>	<a href="index.jsp">返回首页</a><br><br></center>
	<form action="user.do" name="myForm" method="get"
		onsubmit="return onSubmit()">
		<table align="center" border="0" bordercolor="red" cellpadding="5"
			cellspacing="0" width="600" bgcolor="#ffffcc">
			<tr bgcolor="#ffcc00">
				<td colspan="3" align="center">注册新用户</td>
			</tr>
			<tr>
				<td align="right"><font size="2">用户名</font></td>
				<td><input type="text" name="username" size="10"
					onkeyup="sendCheckUserName()" /></td>
				<td align="left"><div id="check1"></div></td>
			</tr>
			<tr>
				<td align="right"><font size="2">密码</font></td>
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
				<td colspan="3"><font size="2">填写完成后，选择下面的提交按钮提交表单。</font></td>
			</tr>
			<input type='hidden' name='source' value='register'>
			<tr align="center">
				<td colspan="3" align="center"><input type="submit" value="提交" />
					<input type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
</body>
</html>