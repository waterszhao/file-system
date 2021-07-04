<!DOCTYPE html>
<html>
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<meta charset="UTF-8">
<%@ page import="dao.*"%>
<%@ page import="bean.*"%>
</head>
<body>
	<%
		UserInfoBean user = (UserInfoBean)session.getAttribute("user");
		UserDAO dao = new UserDAO();
		String name = request.getParameter("userName");
		if(name == "")
			out.print("用户名不能为空");
		else if (name.equals(user.getUserName())) {
			out.print("");
		}
		else if (dao.validateUserName(name)) {
			out.print("该用户名已被占用！");
		}
		else
			out.print("新用户名可用");
	%>
</body>
</html>