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
				<th>专业号</th>
				<th>专业名</th>
				<th>查看</th>
			</tr>
			<%
				DAO dao = new DAO();
				List<Major> majors = dao.getAllMajors();
				for (int i = 1; i <= majors.size(); i++) {
					Major major = majors.get(i - 1);
			%>
			<tr>
				<td align='center'><%=i%></td>
				<td align='center'><%=major.getMno()%></td>
				<td align='center'><%=major.getMname()%></td>
				<td align='center'><a href="class.jsp?Mno=<%=major.getMno()+"&&Mname="+major.getMname()%>">查看</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<a href="addMajor.jsp">新增专业信息</a>
	<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>