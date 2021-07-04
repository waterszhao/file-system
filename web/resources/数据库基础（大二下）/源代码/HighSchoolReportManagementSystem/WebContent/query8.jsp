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
		<h1>班级课程开设查询</h1>
		<h1>请输入班级名称：（如软工1803）</h1>
		<form action="query8.jsp" method="POST">
			<h2>
				<input type="text" name="name">
			</h2>
			<input type="submit" value="查询">
		</form>
		<%
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			if (name == null)
				name = "";
		%>
				<h1><%=name %></h1>
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>序号</th>
				<th>课程名</th>
				<th>学时</th>
				<th>开课学期</th>
				<th>学分</th>
				<th>考核方式</th>
			</tr>
			<%
				DAO dao = new DAO();
				List<Course> courses = dao.getCoursesByClassName(name);
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
				</tr>
			<%
				}
			%>
		</table>
	</center>
	<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>