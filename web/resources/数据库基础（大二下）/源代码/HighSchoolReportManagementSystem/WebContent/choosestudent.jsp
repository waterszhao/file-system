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
	<h1><%="选修了:"+request.getParameter("Cname")+"的班级:"+request.getParameter("CLname")+":" %></h1>
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>序号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>已修学分</th>
				<th>位置</th>
				<th>操作</th>
			</tr>
			<%
			    int Cno = Integer.parseInt(request.getParameter("Cno"));
				int CLno = Integer.parseInt(request.getParameter("CLno"));
				DAO dao = new DAO();
				List<Student> students = dao.getStudentsNoReports(CLno,Cno);
				for (int i = 1; i <= students.size(); i++) {
					Student student = students.get(i - 1);
			%>
			<tr>
				<td align='center'><%=i%></td>
				<td align='center'><%=student.getSname()%></td>
				<td align='center'><%=student.getSsex()%></td>
				<td align='center'><%=student.getSage()%></td>
				<td align='center'><%=student.getCreditGot()%></td>
				<td align='center'><%=student.getSposition()%></td>
				<td align='center'><a href = 'addreport.jsp?Cno=<%=Cno %>&&CLno=<%=CLno %>&&Sno=<%=student.getSno() %>
				&&Cname=<%=request.getParameter("Cname") %>&&CLname=<%=request.getParameter("CLname")%>'>选择</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<h3><a href = "index.jsp">返回首页</a></h3>
	</body>
</html>