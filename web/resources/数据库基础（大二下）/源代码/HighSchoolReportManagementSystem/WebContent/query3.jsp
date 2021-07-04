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
		<h1>已有成绩的各课程平均成绩：</h1>
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>序号</th>
				<th>课程编号</th>
				<th>课程名</th>
				<th>平均成绩</th>
			</tr>
			<%
				DAO dao = new DAO();
				List<CR> reports = dao.getAverageGrade();
				for (int i = 1; i <= reports.size(); i++) {
					CR report = reports.get(i - 1);
			%>
			<tr>
				<td align='center'><%=i%></td>
				<td align='center'><%=report.getCno()%></td>
				<td align='center'><%=report.getCname()%></td>
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