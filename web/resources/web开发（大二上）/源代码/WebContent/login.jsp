<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
	request.setCharacterEncoding("utf-8");
  String message = request.getParameter("msg");
  if (message == null) {
    message = "请输入用户名和密码";
  }

%>
<html>
  <head>
    <title>用户信息表单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
 
    <form action="validate.jsp" method="post">
      <center>
       	<a href="index.jsp">返回首页</a><br><br>
      <table align="center" border="0" bordercolor="red" cellpadding="5"
			cellspacing="0" width="600" bgcolor="#FFD306">
        <tr>
          <td colspan="2" align ='center'>
            <font color="red"><%=message %></font>
          </td>
        </tr>
        <tr bgcolor = "#FFE153">
          <td align ='center'>用户名：</td>
          <td>
            <input type="text" name="userName"/>
          </td>
        </tr>
        <tr bgcolor = "#FFED97">
          <td align ='center'>密码：</td>
          <td >
            <input type="password" name="psw1"/>
          </td>
        </tr>
        <input type='hidden' name='source' value='login' >
        <tr bgcolor="#FFF4C1">
          <td colspan="2" align="center">
            <input type="submit" value="登录"/>
          </td>
        </tr>
      </table>
      </center>
    </form>
  </body>
</html>