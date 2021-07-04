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
	<h1>班级:<%=request.getParameter("CLname") %></h1>
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>序号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>已修学分</th>
				<th>位置</th>
				<th>查看</th>
				<th>操作</th>
				<th>操作</th>
			</tr>
			<%
				int CLno = Integer.parseInt(request.getParameter("CLno"));
				DAO dao = new DAO();
				List<Student> students = dao.getStudentsByClass(CLno);
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
				<td align='center'><a href = 'checkreport.jsp?CLno=<%=CLno %>&&Sno=<%=student.getSno() %>&&Sname=<%=student.getSname() %>'>查看课程成绩</a></td>
				<td align='center'><a href = 'modifystudent.jsp?Sno=<%=student.getSno() %>&&Sname=<%=student.getSname() %>
				&&Ssex=<%=student.getSsex() %>&&Sage=<%=student.getSage() %>&&CreditGot=<%=student.getCreditGot() %>
				&&position=<%=student.getSposition() %>&&CLno=<%=CLno%>&&CLname=<%=request.getParameter("CLname")%>'>修改学生信息</a></td>
				<td align='center'><a href = 'my.do?source=deleteStudent&&Sno=<%=student.getSno() %>
				&&CLno=<%=CLno%>&&CLname=<%=request.getParameter("CLname")%>'>删除学生信息</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<a href="addstudent.jsp?CLno=<%=CLno%>&&CLname=<%=request.getParameter("CLname")%>">为班级添加学生信息</a>
<h3><a href = "index.jsp">返回首页</a></h3>
</body>
</html>