<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="bean.*"%>
<html>
<head>
	<script>
	var checkCLname = false;
	function checkName() {
		if (myForm.CLname.value == "") {
			checkCLname = false;
		}
		else {
			checkCLname = true;
		}
	}
	
	function onSubmit(){
		if(checkCLname)
			return true;
		else{
			alert("班级名不能为空！");
			return false;
		}
	}
	</script>

<title></title>
</head>

<body>
	<center>
	<%
		request.setCharacterEncoding("UTF-8");
		String msg = request.getParameter("msg");
		if(msg == null)
			msg = "班级信息输入";
	%>
		<h1><%=msg %></h1>
		<form action="my.do" method="POST" onsubmit = "return onSumbit()">
			<table align="center" border="0" bordercolor="red" cellpadding="5"
				cellspacing="0" width="600" bgcolor="#FFD306">
				<tr>
					<td align='center'>班级名</td>
					<td align='center'><input type="text" name="CLname" onkeyup = "checkName()"/></td>
				</tr>
				<tr>
            		<input type='hidden' name='source' value='addClass' >
            		<input type='hidden' name='Mno' value='<%=request.getParameter("Mno") %>'?> 
            		<input type='hidden' name='Mname' value='<%=request.getParameter("Mname") %>'?> 
          		</tr>
				<tr bgcolor = "#FFFCEC">
            		<td colspan="2" align = 'center'><input type="submit" value="提交"/></td>
          		</tr>
			</table>
		</form>
	</center>
	<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>