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
	<h1>为班级： <%=request.getParameter("CLname") %>添加课程：</h1>
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
				String CLno = request.getParameter("CLno");
				DAO dao = new DAO();
				List<Course> courses = dao.getCoursesNoSet(Integer.parseInt(CLno));
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
				<td align='center'><a href = 'my.do?source=addsetcourse&&CLno=<%=request.getParameter("CLno") %>&&Cno=<%=course.getCno()%>&&CLname=<%=request.getParameter("CLname")%>'>添加课程</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>