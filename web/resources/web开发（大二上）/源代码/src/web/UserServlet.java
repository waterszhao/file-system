package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.*;
import dao.*;

public class UserServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String source = request.getParameter("source");
			
			if(source.equals("register")) {//用户注册
				String name =request.getParameter("username");
				String psw = request.getParameter("psw1");
				UserInfoBean newUser = addUser(name,psw);//内置函数1
				request.getSession().setAttribute("user",newUser);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			

			if(source.equals("deleteUser")) {//删除用户
	    		int userId = Integer.parseInt(request.getParameter("id"));
	    		UserDAO userdao = new UserDAO();
	    		userdao.deleteUser(userId);
	    		CartDAO cartdao = new CartDAO();
	    		List<Integer> products = cartdao.getCartByUserId(userId);
	    		StorageDAO stodao = new StorageDAO();
	    		stodao.deleteStorageByUserId(userId);
	    		for(int i = 0;i < products.size();i++) {
				cartdao.removeFromCart(products.get(i), userId);
				
	    		}
				  request.getRequestDispatcher("/manage.jsp").forward(request, response);
			}
			
			if(source.equals("modifyUser")) {//修改用户信息
				UserInfoBean userInfo = (UserInfoBean)request.getSession().getAttribute("user");
				  String name =request.getParameter("username");
				  String psw = request.getParameter("psw1");
				  UserInfoBean newUser = modifyUser(userInfo,name,psw);//内置函数2
				  request.getSession().setAttribute("user",newUser);
				  request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			
			if(source.equals("logout")) {//退出登录
				request.getSession().removeAttribute("user");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
	}
	
	private UserInfoBean addUser(String name,String psw) {
		  UserInfoBean user = new UserInfoBean();
		  user.setUserName(name);
		  user.setPsw(psw);
		  UserDAO dao = new UserDAO();
		  dao.addUser(user);
		  return dao.getUserByName(name);
	}
	
	private UserInfoBean modifyUser(UserInfoBean userInfo,String name,String psw) {
		  int request1 = userInfo.getRequest();
		  int id = userInfo.getId();
		  UserInfoBean user = new UserInfoBean();
		  user.setUserName(name);
		  user.setPsw(psw);
		  user.setRequest(request1);
		  user.setId(id);
		  UserDAO dao = new UserDAO();
		  return dao.modifyUser(user,userInfo.getUserName());
	}
}
