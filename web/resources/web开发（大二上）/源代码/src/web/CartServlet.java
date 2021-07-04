package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.*;
import dao.*;

public class CartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String source = request.getParameter("source");

		if (source.equals("addToCart")) {//添加至购物车
			UserInfoBean user = (UserInfoBean) request.getSession().getAttribute("user");
			if (user == null) {
				request.setAttribute("msg", "Please login first!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				int proId = Integer.parseInt(request.getParameter("id"));
				CartDAO cartdao = new CartDAO();
				cartdao.addCart(proId, user.getId());
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
		}

		if (source.equals("removeFromCart")) {//移除购物车
			UserInfoBean user = (UserInfoBean) request.getSession().getAttribute("user");
			int proId = Integer.parseInt(request.getParameter("id"));
			CartDAO cartdao = new CartDAO();
			cartdao.removeFromCart(proId, user.getId());
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}

		if (source.equals("minusCart")) {//购物车数量-1功能
			UserInfoBean user = (UserInfoBean) request.getSession().getAttribute("user");
			int proId = Integer.parseInt(request.getParameter("id"));
			CartDAO cartdao = new CartDAO();
			cartdao.minusCart(proId, user.getId());
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}

		if (source.equals("buyProduct")) {//确认支付
			UserInfoBean userInfo = (UserInfoBean) request.getSession().getAttribute("user");
			int proId = Integer.parseInt(request.getParameter("id"));
			int buyNum = Integer.parseInt(request.getParameter("num"));
			StorageDAO dao = new StorageDAO();
			boolean isBought = dao.addStorage(proId, userInfo.getId(), buyNum);
			request.setAttribute("bought", isBought);
			request.getRequestDispatcher("/storage.jsp").forward(request, response);
		}
	}

}
