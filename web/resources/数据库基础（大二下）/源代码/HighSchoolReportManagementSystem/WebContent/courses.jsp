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
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>序号</th>
				<th>课程名</th>
				<th>学时</th>
				<th>开课学期</th>
				<th>学分</th>
				<th>考核方式</th>
				<th>操作</th>
				<th>操作</th>
			</tr>
			<%
				DAO dao = new DAO();
				List<Course> courses = dao.getAllCourses();
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
				<td align='center'><a href = 'modifycourse.jsp?Cno=<%=course.getCno() %>&&Cname=<%=course.getCname() %>
				&&LearnTime=<%=course.getLearnTime() %>&&Cterm=<%=course.getCterm() %>&&Credit=<%=course.getCredit() %>
				&&CheckChoice=<%=course.getCheckChoice() %>'>修改课程信息</a></td>
				<td align='center'><a href = 'my.do?source=deleteCourse&&Cno=<%=course.getCno() %>'>删除课程信息</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<a href="addCourse.jsp">新增课程信息</a>
	<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>