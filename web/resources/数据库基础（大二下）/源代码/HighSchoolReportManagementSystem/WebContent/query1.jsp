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
		<h1>请输入学年：（如2019/2020）</h1>
		<form action="query1.jsp" method="POST">
			<h2>
				<input type="text" name="time">
			</h2>
			<input type="submit" value="查询">
		</form>
		<%
			String time = request.getParameter("time");
			if (time == null)
				time = "";
		%>
				<h1><%=time %></h1>
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>序号</th>
				<th>学号</th>
				<th>姓名</th>
				<th>课程名</th>
				<th>开课学期</th>
				<th>成绩</th>
			</tr>
			<%
				DAO dao = new DAO();
				List<SCR> reports = dao.getReportsByYear(time);
				for (int i = 1; i <= reports.size(); i++) {
					SCR report = reports.get(i - 1);
			%>
			<tr>
				<td align='center'><%=i%></td>
				<td align='center'><%=report.getSno()%></td>
				<td align='center'><%=report.getSname()%></td>
				<td align='center'><%=report.getCname()%></td>
				<td align='center'><%=report.getCterm()%></td>
				<td align='center'><%=report.getGrade()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>