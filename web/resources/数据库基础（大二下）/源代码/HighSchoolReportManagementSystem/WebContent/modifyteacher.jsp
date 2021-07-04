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
			msg = "教师信息输入";
		String name = request.getParameter("Tname");
		String sex = request.getParameter("Tsex");
		String age = request.getParameter("Tage");
		String type = request.getParameter("Ttype");
		String tel = request.getParameter("Telephone");
		
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
					<td align='center'>职称</td>
					<td align='center'><input type="text" name="type" value='<%=type %>'/></td>
				</tr>
				<tr bgcolor="#FFF4C1">
					<td align='center'>电话</td>
					<td align='center'><input type="text" name="telephone" value='<%=tel %>'/></td>
				</tr>
				<tr>
            		<input type='hidden' name='source' value='modifyTeacher' >
            		<input type='hidden' name='Tno' value='<%=request.getParameter("Tno") %>' >
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