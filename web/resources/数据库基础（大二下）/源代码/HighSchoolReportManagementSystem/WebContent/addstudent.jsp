<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="bean.*"%>
<html>
<head>
<title></title>
</head>

<body>
	<center>
	<%
		request.setCharacterEncoding("UTF-8");
		String msg = request.getParameter("msg");
		String CLno = request.getParameter("CLno");
		String CLname = request.getParameter("CLname");
		if(msg == null)
			msg = "学生信息输入";
	%>
		<h1><%=msg %></h1>
		<form action="my.do" method="POST">
			<table align="center" border="0" bordercolor="red" cellpadding="5"
				cellspacing="0" width="600" bgcolor="#FFFFFF">
				<tr bgcolor = "#FFD306">
					<td align='center'>姓名</td>
					<td align='center'><input type="text" name="name"
						onkeyup="checkName()" /></td>
				</tr>
				<tr bgcolor="#FFE153">
					<td align='center'>性别</td>
					<td align='center'><input type="text" name="sex" /></td>
					<td align='center'>年龄</td>
					<td align='center'><input type="text" name="age" /></td>
				</tr>
				<tr bgcolor="#FFED97">
					<td align='center'>已修学分</td>
					<td align='center'><input type="text" name="credit" /></td>
				</tr>
				<tr bgcolor="#FFF4C1">
					<td align='center'>位置</td>
					<td align='center'><input type="text" name="position" /></td>
				</tr>
				<tr>
            		<input type='hidden' name='source' value='addStudent' >
            		<input type='hidden' name='CLno' value='<%=CLno %>' >
            		<input type='hidden' name='CLname' value='<%=CLname %>' >
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