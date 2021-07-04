<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
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
			您的位置：<a href="index.jsp">首页</a>>>个人信息
		</div>
		<h1>个人中心</h1>
		<table align="center" border="1" cellpadding="5" cellspacing="0"
			width="300" bgcolor="#FFD306">
			<tr>
				<td align='center'>用户名：</td>
				<td><%=user.getUserName()%></td>
			</tr>
			<tr bgcolor="#FFE153">
				<td align='center'>密码：</td>
				<td><%=user.getPsw()%></td>
			</tr>
			<tr bgcolor="#FFED97">
				<td align='center'>权限：</td>
				<td>
					<%
						if (user.getRequest() == 1) {
					%> <%="管理员"%>,&emsp;&emsp;&emsp;<a href="manage.jsp">管理用户</a> <%
 	} else {
 %> <%="普通用户"%> <%
 	}
 %>
				</td>
			</tr>
		</table>
		<br>
		<h3>
			<a href="modifyUser.jsp">修改信息</a>
		</h3>
	</center>
</body>
</html>
