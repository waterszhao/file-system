<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="bean.*"%>
<html>
<head>
<script>
	
</script>

<title></title>
</head>

<body>
	<center>
		<%
			request.setCharacterEncoding("UTF-8");
			String msg = request.getParameter("msg");
			if (msg == null)
				msg = "课程信息输入";
		%>
		<h1><%=msg%></h1>
		<form action="my.do" method="POST">
			<table align="center" border="0" bordercolor="red" cellpadding="5"
				cellspacing="0" width="600" bgcolor="#FFD306">
				<tr>
					<td align='center'>课程名</td>
					<td align='center'><input type="text" name="Cname"
						onkeyup="checkName()" /></td>
				</tr>
				<tr bgcolor="#FFE153">
					<td align='center'>学时</td>
					<td align='center'><input type="text" name="learnTime" /></td>
					<td align='center'>学分</td>
					<td align='center'><input type="text" name="credit" /></td>
				</tr>
				<tr bgcolor="#FFED97">
					<td align='center'>考核方式</td>
					<td align='center'><input type="text" name="checkChoice" /></td>
				</tr>
				<tr bgcolor="#FFF4C1">
					<td align='center'>开课学期</td>
					<td align='center'><input type="text" name="Cterm" /></td>
				</tr>
				<tr>
					<input type='hidden' name='source' value='addCourse'>
				</tr>
				<tr bgcolor="#FFFCEC">
					<td colspan="2" align='center'><input type="submit" value="提交" /></td>
				</tr>
			</table>
		</form>
	</center>
	<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>