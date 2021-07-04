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
	String Mname = request.getParameter("Mname");%>
	<h1>专业名:<%=Mname %></h1>
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>序号</th>
				<th>班级名</th>
				<th>开设课程</th>
				<th>操作</th>
			</tr>
			<%
				int Mno = Integer.parseInt(request.getParameter("Mno"));
				DAO dao = new DAO();
				List<Classes> classes = dao.getClassesByMajor(Mno);
				for (int i = 1; i <= classes.size(); i++) {
					Classes AClass = classes.get(i - 1);
			%>
			<tr>
				<td align='center'><%=i%></td>
				<td align='center'><%=AClass.getCLname()%></td>
				<td align='center'><a href="checksetcourse.jsp?CLno=<%=AClass.getCLno()%>&&CLname=<%=AClass.getCLname()%>">查看开设课程</a></td>
				<td align='center'><a href="checkstudents.jsp?CLno=<%=AClass.getCLno()%>&&CLname=<%=AClass.getCLname()%>">查看学生</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<a href="addClass.jsp?Mno=<%=Mno%>&&Mname=<%=Mname%>">新增班级信息</a>
	<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>