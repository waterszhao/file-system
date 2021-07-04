<!DOCTYPE html>
<html>
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<meta charset="UTF-8">
<%@ page import="dao.*"%>
</head>
<body>
	<%
		UserDAO dao = new UserDAO();
		String name = request.getParameter("userName");
		if (dao.validateUserName(name)) {
			out.print("该用户名已被占用！");
		}
		else
			out.print("用户名可用");
	%>
</body>
</html>