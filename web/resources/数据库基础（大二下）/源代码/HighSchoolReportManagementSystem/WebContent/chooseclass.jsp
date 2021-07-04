<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.*"%>
<%@ page import="bean.*"%>
<html>
<head>
<title></title>
</head>
<body>
	<center>
	<% request.setCharacterEncoding("UTF-8");
	String Cname = request.getParameter("Cname");%>
	<h1>开设 <%=Cname %> 的班级：</h1>
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>序号</th>
				<th>班级名</th>
				<th>操作</th>
			</tr>
			<%
				int Cno = Integer.parseInt(request.getParameter("Cno"));
				DAO dao = new DAO();
				List<Classes> classes = dao.getClassesByCourse(Cno);
				for (int i = 1; i <= classes.size(); i++) {
					Classes AClass = classes.get(i - 1);
			%>
			<tr>
				<td align='center'><%=i%></td>
				<td align='center'><%=AClass.getCLname()%></td>
				<td align='center'><a href="choosestudent.jsp?Cno=<%=Cno%>&&CLno=<%=AClass.getCLno()%>
				&&CLname=<%=AClass.getCLname()%>&&Cname=<%=Cname%>">选择学生</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>