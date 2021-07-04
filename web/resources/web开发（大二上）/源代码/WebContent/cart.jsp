<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
				<a href="manage.jsp">管理用户</a>
				<%
					}
				%>
				&nbsp;&nbsp;&nbsp;
			<a href="cart.jsp">查看购物车</a>
		&nbsp;&nbsp;&nbsp;<a href="storage.jsp">我的仓库</a>&nbsp;&nbsp;&nbsp;<a
				href="user.do?source=logout">退出登录</a>
			<%
				}
			%>
		</div><br>
		<div>
			您的位置：<a href = "index.jsp">首页</a>>>我的购物车
		</div>
    <h1>购物车列表</h1>
    <table border="1" width="80%">
      <tr bgcolor="orange">
        <th>序号</th>
        <th>商品名</th>
        <th>查看</th>
        <th>操作</th>
        <th>购物车数量</th>
        <th>价格</th>
      </tr>
      <%
      ProductDAO prodao = new ProductDAO();
      CartDAO cartdao = new CartDAO();
      List<Product> products = new ArrayList<Product>();
      List<Integer> productsId = cartdao.getCartByUserId(user.getId());
      List<Integer> productsnum = cartdao.getCartNumByUserId(user.getId());
      for(int i = 0;i < productsId.size();i++){
    	  products.add(prodao.getProductById(productsId.get(i)));
      }
      int totalPrice = 0;
      for (int i=0; i<products.size(); i++) {
        Product pro = products.get(i);
      %>
        <tr>
          <td align ='center'><%=i+1 %></td>
          <td align ='center'><%=pro.getName() %></td>
          <td align ='center'><a href="view.jsp?id=<%=pro.getId() %>">查看</a></td> 
          <td align ='center'><a href="cart.do?source=removeFromCart&id=<%=pro.getId() %>">移除出购物车</a>&emsp;&emsp;
          <a href="cart.do?source=addToCart&id=<%=pro.getId() %>">+1</a>
          &emsp;&emsp;<a href="cart.do?source=minusCart&id=<%=pro.getId() %>">-1</a>
          &emsp;&emsp;<%if(pro.getCanBuy() == 1){ %><a href="pay.jsp?id=<%=pro.getId()%>&num=<%=productsnum.get(i)%>">购买</a><%
          }else{ %>商品已下架<%} %></td>    
          
          <td align ='center'><%=productsnum.get(i)%></td>
          <td><%=pro.getPrice()*productsnum.get(i)%></td>
        </tr>
      <%
      	totalPrice += pro.getPrice()*productsnum.get(i);
      }
      %>
      
    </table>
    
    <% if(products.size()==0)out.print("<h1>您的购物车空空如也</h1>"); %>
    </center>
  </body>
</html>
