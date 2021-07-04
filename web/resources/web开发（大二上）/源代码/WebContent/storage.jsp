<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
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
			<a href="cart.jsp">查看购物车</a> &nbsp;&nbsp;&nbsp; <a href="storage.jsp">我的仓库</a>&nbsp;&nbsp;&nbsp;
			<a href="user.do?source=logout">退出登录</a>
			<%
				}
			%>
		</div>
		<br>
		<div>
			您的位置：<a href="index.jsp">首页</a>>>我的仓库
		</div>
		<h1>我的仓库</h1>
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>序号</th>
				<th>商品名</th>
				<th>查看</th>
				<th>单价</th>
				<th>购买数量</th>
				<th>购买时间</th>
				<th>操作</th>
			</tr>
			<%
				ProductDAO prodao = new ProductDAO();
				StorageDAO stodao = new StorageDAO();
				List<Product> products = new ArrayList<Product>();
				List<String> productsTime = stodao.getBoughtTime(user.getId());
				List<Integer> productsId = stodao.getStorageByUserId(user.getId());
				List<Integer> productsnum = stodao.getStorageNumByUserId(user.getId());
				List<Integer> storages = stodao.getStorageId(user.getId());
				for (int i = 0; i < productsId.size(); i++) {
					products.add(prodao.getProductById(productsId.get(i)));
				}
				for (int i = 0; i < products.size(); i++) {
					Product pro = products.get(i);
			%>
			<tr>
				<td align='center'><%=i + 1%></td>
				<td align='center'><%=pro.getName()%></td>
				<td align='center'><a href="view.jsp?id=<%=pro.getId()%>">查看</a></td>
				<td align='center'><%=pro.getPrice()%></td>
				<td align='center'><%=productsnum.get(i)%></td>
				<td align='center'><%=productsTime.get(i)%></td>
				<td align='center'><a
					href="product.do?source=deleteStorage&id=<%=storages.get(i)%>">删除记录</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			if (products.size() == 0)
				out.print("<h1>您的仓库空空如也</h1>");
		%>
	</center>
</body>
</html>
