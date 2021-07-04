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
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>职称</th>
				<th>联系电话</th>
				<th>查看</th>
				<th>操作</th>
				<th>操作</th>
			</tr>
			<%
				DAO dao = new DAO();
				List<Teacher> teachers = dao.getTeachers();
				for (int i = 1; i <= teachers.size(); i++) {
					Teacher teacher = teachers.get(i - 1);
			%>
			<tr>
				<td align='center'><%=i%></td>
				<td align='center'><%=teacher.getTname()%></td>
				<td align='center'><%=teacher.getTsex()%></td>
				<td align='center'><%=teacher.getTage()%></td>
				<td align='center'><%=teacher.getTtype()%></td>
				<td align='center'><%=teacher.getTelephone()%></td>
				<td align='center'><a href = 'checkteacher.jsp?Tno=<%=teacher.getTno() %>&&Tname=<%=teacher.getTname() %>'>查看所教课程</a></td>
				<td align='center'><a href = 'modifyteacher.jsp?Tno=<%=teacher.getTno() %>&&Tname=<%=teacher.getTname() %>
				&&Tsex=<%=teacher.getTsex() %>&&Tage=<%=teacher.getTage() %>&&Ttype=<%=teacher.getTtype() %>
				&&Telephone=<%=teacher.getTelephone() %>'>修改老师信息</a></td>
				<td align='center'><a href = 'my.do?source=deleteTeacher&&Tno=<%=teacher.getTno() %>'>删除教师信息</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<a href="addTeacher.jsp">新增教师信息</a>
	<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>