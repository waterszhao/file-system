<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="bean.*"%>
<html>
<head>
<title></title>
</head>
<%
	int proId = Integer.parseInt(request.getParameter("id"));
	ProductDAO prodao = new ProductDAO();
	Product product = prodao.getProductById(proId);
%>
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
			您的位置：<a href="index.jsp">首页</a>>>商品信息编辑
		</div>
		<h1>商品信息修改</h1>
		<form action="product.do" method="POST">
			<input type="hidden" name="id" value="<%=product.getId()%>" />
			<table align="center" border="0" bordercolor="red" cellpadding="5"
				cellspacing="0" width="600" bgcolor="#FFD306">
				<tr>
					<td align='center'>商品名</td>
					<td align='center'><input type="text" name="name"
						value="<%=product.getName()%>" /></td>
				</tr>
				<tr bgcolor="#FFE153">
					<td align='center'>产地</td>
					<td align='center'><input type="text" name="origin"
						value="<%=product.getOrigin()%>" /></td>
				</tr>
				<tr bgcolor="#FFED97">
					<td align='center'>价格</td>
					<td align='center'><input type="text" name="price"
						value="<%=product.getPrice()%>" /></td>
				</tr>
				<tr bgcolor="#FFF4C1">
					<td align='center'>库存</td>
					<td align='center'><input type="text" name="inventory"
						value="<%=product.getInventory()%>" /></td>
				</tr>
				<input type='hidden' name='source' value='modifyProduct'>
				<tr bgcolor="#FFFCEC">
					<td colspan="2" align='center'><input type="submit" value="提交" /></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>
