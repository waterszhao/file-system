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
			<a href="cart.jsp">查看购物车</a>&nbsp;&nbsp;&nbsp; <a href="storage.jsp">我的仓库</a>&nbsp;&nbsp;&nbsp;
			<a href="user.do?source=logout">退出登录</a>
			<%
				}
			%>
		</div><br>
		<div>
			您的位置：首页
		</div>
		<h1>商品信息一览</h1>
		<form action = "searchProduct.jsp" method = "post">
			<input type = 'text' name = "search" placeholder = "请输入想搜索的商品"/>
			<input type = "submit" value = "搜索">
		</form>
		<table border="1" width="80%">
			<tr bgcolor="orange">
				<th>序号</th>
				<th>商品名</th>
				<th>查看</th>
				<th>操作</th>
				<th>库存</th>
				<th>单价</th>
			</tr>
			<%
				ProductDAO prodao = new ProductDAO();
				int proNum = prodao.getNumOfProducts();
				int totalPage = (proNum - 1) / 10 + 1;
				int pagesize = 10;
				int pages = (request.getParameter("pages") != null) ? Integer.parseInt(request.getParameter("pages")) : 1;
				int begin = 10 * (pages - 1);
				List<Product> products = prodao.getProductsLimitSize(begin, pagesize);
				for (int i = 1; i <= products.size(); i++) {
					Product pro = products.get(i-1);
			%>
			<tr>
				<td align='center'><%=i + begin%></td>
				<td align='center'><%=pro.getName()%></td>
				<td align='center'><a href="view.jsp?id=<%=pro.getId()%>">查看</a></td>
				<td align='center'><a href="cart.do?source=addToCart&id=<%=pro.getId()%>">添加到购物车</a>&emsp;&emsp;
					<%
						if (user != null && user.getRequest() != 0) {
					%> <a
					href="modifyProduct.jsp?id=<%=pro.getId()%>">修改商品信息</a>
					&emsp;&emsp;	<a
					href="product.do?source=removeProduct&id=<%=pro.getId()%>">下架商品</a></td>
				<%
					}
				%>
				<td align='center'><%=pro.getInventory()%></td>
				<td align='center'><%=pro.getPrice()%></td>
			</tr>
			<%
				}
			%>
		</table>
		一共有<%=proNum%>条数据，共<%=totalPage%>页。<br>
		<%
			for (int i = 1; i <= totalPage; i++) {
		%>
		<a href="index.jsp?pages=<%=i%>"><%=i%></a>
		<%
			}
			if (products.size() == 0)
				out.print("<h1>目前暂无商品</h1>");
		%>
	</center>
	<%
		if (user != null && user.getRequest() != 0) {
	%>
	<a href="addProduct.jsp">新增商品信息</a>
	<%
		}
	%>
</body>
</html>
