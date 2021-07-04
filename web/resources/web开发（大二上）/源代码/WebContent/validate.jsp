<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="bean.*"%>
<%@ page import="dao.*"%>

<%
	String userName = request.getParameter("userName");
	String psw = request.getParameter("psw1");
	UserDAO dao = new UserDAO();
	UserInfoBean user = dao.getUserByName(userName);
	boolean isExist = dao.validateUserName(userName);
	boolean isRight = false;
	request.setCharacterEncoding("utf-8") ;
	if (isExist) {
		if (psw.equals(user.getPsw())) {
			isRight = true;
		}
	}
	if (!isExist) {
%>
<jsp:forward page="login.jsp">
	<jsp:param name="msg" value="用户不存在！" />
</jsp:forward>
<%
	}
	if (!isRight) {
%>
<jsp:forward page="login.jsp">
	<jsp:param name="msg" value="密码错误！" />
</jsp:forward>
<%
	}
	if (isExist && isRight) {
		session.setAttribute("user", user);
%>
<jsp:forward page="index.jsp">
	<jsp:param name="msg" value='' />
</jsp:forward>
<%
	}
%>
