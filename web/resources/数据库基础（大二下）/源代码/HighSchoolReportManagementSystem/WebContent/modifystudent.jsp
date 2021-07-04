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
		if(msg == null)
			msg = "学生信息输入";
		String name = request.getParameter("Sname");
		String sex = request.getParameter("Ssex");
		String age = request.getParameter("Sage");
		String credit = request.getParameter("CreditGot");
		String position = request.getParameter("position");
		
	%>
		<h1><%=msg %></h1>
		<form action="my.do" method="POST">
			<table align="center" border="0" bordercolor="red" cellpadding="5"
				cellspacing="0" width="600" bgcolor="#FFD306">
				<tr>
					<td align='center'>姓名</td>
					<td align='center'><input type="text" name="name" value='<%=name %>'/></td>
				</tr>
				<tr bgcolor="#FFE153">
					<td align='center'>性别</td>
					<td align='center'><input type="text" name="sex" value='<%=sex %>'/></td>
					<td align='center'>年龄</td>
					<td align='center'><input type="text" name="age" value='<%=age %>'/></td>
				</tr>
				<tr bgcolor="#FFED97">
					<td align='center'>已修学分</td>
					<td align='center'><input type="text" name="credit" value='<%=credit %>'/></td>
				</tr>
				<tr bgcolor="#FFF4C1">
					<td align='center'>生源地</td>
					<td align='center'><input type="text" name="position" value='<%=position %>'/></td>
				</tr>
				<tr>
            		<input type='hidden' name='source' value='modifyStudent' >
            		<input type='hidden' name='Sno' value='<%=request.getParameter("Sno") %>' >
            		<input type='hidden' name='CLno' value='<%=request.getParameter("CLno")%>' >
            		<input type='hidden' name='CLname' value='<%=request.getParameter("CLname") %>' >
          		</tr>
				<tr bgcolor = "#FFFCEC">
            		<td colspan="2" align = 'center'><input type="submit" value="保存"/></td>
          		</tr>
			</table>
		</form>
	</center>
	<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>