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
	<% request.setCharacterEncoding("UTF-8");%>
	<h1>老师:<%=request.getParameter("Tname") %></h1>
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>序号</th>
				<th>课程名</th>
				<th>学时</th>
				<th>开课学期</th>
				<th>学分</th>
				<th>考核方式</th>
				<th>操作</th>
			</tr>
			<%
				int Tno = Integer.parseInt(request.getParameter("Tno"));
				DAO dao = new DAO();
				List<Course> courses = dao.getCoursesByTeacher(Tno);
				for (int i = 1; i <= courses.size(); i++) {
					Course course = courses.get(i - 1);
			%>
			<tr>
				<td align='center'><%=i%></td>
				<td align='center'><%=course.getCname()%></td>
				<td align='center'><%=course.getLearnTime()%></td>
				<td align='center'><%=course.getCterm()%></td>
				<td align='center'><%=course.getCredit()%></td>
				<td align='center'><%=course.getCheckChoice()%></td>
				<td align='center'><a href = 'chooseclass.jsp?&&Cno=<%=course.getCno() %>&&Cname=<%=course.getCname()%>'>发布成绩</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<a href="addteach.jsp?Tno=<%=Tno%>&&Tname=<%=request.getParameter("Tname")%>">为老师增加课程</a>
<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>