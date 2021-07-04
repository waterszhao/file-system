<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="bean.*"%>
<%@ page import="dao.*"%>
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
			<a href="manage.jsp">管理用户</a>
			<%
				}
			%>
			&nbsp;&nbsp;&nbsp;<a href="cart.jsp">查看购物车</a>
			&nbsp;&nbsp;&nbsp;<a href="storage.jsp">我的仓库</a>&nbsp;&nbsp;&nbsp;<a
				href="user.do?source=logout">退出登录</a>
			<%
				}
			%>
		</div>
		<br>
		<div>
			您的位置：<a href="index.jsp">首页</a>>>商品信息
		</div>
		<h1>商品详细信息</h1>
		<%
			String strId = request.getParameter("id");
			ProductDAO prodao = new ProductDAO();
			int id = Integer.parseInt(strId);
			Product pro = prodao.getProductById(id);
		%>
		<table align="center" border="1" cellpadding="5" cellspacing="0"
			width="600" bgcolor="#FFD306">
			<tr>
				<td align='center'>商品名：</td>
				<td><%=pro.getName()%><br /></td>
			</tr>
			<tr bgcolor="#FFE153">
				<td align='center'>产地：</td>
				<td><%=pro.getOrigin()%><br /></td>
			</tr>
			<tr bgcolor="#FFED97">
				<td align='center'>价格：</td>
				<td><%=pro.getPrice()%><br /></td>
			</tr>
			<tr bgcolor="#FFF4C1">
				<td align='center'>库存：</td>
				<td><%=pro.getInventory()%><br /></td>
			</tr>

		</table>
		<%
			if (pro.getCanBuy() == 1) {
		%>
		<a href="cart.do?source=addToCart&id=<%=pro.getId()%>">添加到购物车</a>
		<%
			} else {
		%>
		该商品已下架或售罄
		<%
			}
		%>
	</center>
</body>
</html>
