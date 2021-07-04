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
		<div>
			<%
				UserInfoBean user = (UserInfoBean) session.getAttribute("user");
				if (user == null) {
					response.sendRedirect("index.jsp");
			%>
			<a href="login.jsp">用户登录</a>&nbsp;&nbsp;&nbsp;没有账户？-><a
				href="register.jsp">新用户注册</a>
			<%
				} else {
			%>
			欢迎您，<%=user.getUserName()%>
			<a href="personal.jsp">个人空间</a>&nbsp;&nbsp;&nbsp;<%
				if (user.getRequest() == 1) {
			%>
			<a href="manage.jsp">管理用户</a>&nbsp;&nbsp;&nbsp;
			<%
				}
			%>
			<a href="cart.jsp">查看购物车</a> &nbsp;&nbsp;&nbsp;<a href="storage.jsp">我的仓库</a>&nbsp;&nbsp;&nbsp;<a
				href="user.do?source=logout">退出登录</a>
			<%
				}
			%>
		</div>
		<br>
		<div>
			您的位置：<a href="index.jsp">首页</a>>>管理用户
		</div>
		<h1>用户管理</h1>
		<form action="searchUser.jsp" method="post">
			<input type='text' name="search" placeholder="请输入想搜索的用户" /> <input
				type="submit" value="搜索">
		</form>
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>id</th>
				<th>权限</th>
				<th>用户名</th>
				<th>操作</th>
			</tr>
			<%
				UserDAO userdao = new UserDAO();
				int userNum = userdao.getNumOfUsers();
				int totalPage = (userNum - 1) / 10 + 1;
				int pagesize = 10;
				int pages = (request.getParameter("pages") != null) ? Integer.parseInt(request.getParameter("pages")) : 1;
				int begin = 10 * (pages - 1);
				List<UserInfoBean> users = userdao.getUsersLimitSize(begin, pagesize);
				for (int i = 0; i < users.size(); i++) {
					UserInfoBean user1 = users.get(i);
			%>
			<tr>
				<td align='center'><%=user1.getId()%></td>
				<td align='center'>
					<%
						if (user1.getRequest() == 1)
								out.print("管理员");
							else
								out.print("普通用户");
					%>
				</td>
				<td align='center'><%=user1.getUserName()%></td>
				<td align='center'>
					<%
						if (user1.getRequest() == 0) {
					%><a href="user.do?source=deleteUser&id=<%=user1.getId()%>">删除用户数据</a>
					<%
						} else
								out.print("无法管理管理员用户");
					%>
				</td>
			</tr>
			<%
				}
			%>
		</table>
		一共有<%=userNum%>条数据，共<%=totalPage%>页。<br>
		<%
			for (int i = 1; i <= totalPage; i++) {
		%>
		<a href="manage.jsp?pages=<%=i%>"><%=i%></a>
		<%
			}
			if (users.size() == 0)
				out.print("<h1>目前暂无用户</h1>");
		%>
	</center>
</body>
</html>
