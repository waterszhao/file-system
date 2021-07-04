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
			msg = "成绩信息输入";
	%>
		<h1><%="课程名："+request.getParameter("Cname") %></h1>
		<h1><%=msg %></h1>
		<form action="my.do" method="POST">
			<table align="center" border="0" bordercolor="red" cellpadding="5"
				cellspacing="0" width="600" bgcolor="#FFD306">
				<tr>
					<td align='center'>成绩</td>
					<td align='center'><input type="text" name="grade"/></td>
				</tr>
				<tr>
            		<input type='hidden' name='source' value='addreport' >
            		<input type='hidden' name='Cno' value='<%=request.getParameter("Cno") %>'>
            		<input type='hidden' name='CLno' value='<%=request.getParameter("CLno") %>'> 
            		<input type='hidden' name='Sno' value='<%=request.getParameter("Sno") %>'> 
            		<input type='hidden' name='CLname' value='<%=request.getParameter("CLname") %>'> 
            		<input type='hidden' name='Cname' value='<%=request.getParameter("Cname") %>'> 
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