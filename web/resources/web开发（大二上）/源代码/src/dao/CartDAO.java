package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

public class CartDAO {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/web_final?useUnicode=true&characterEncoding=utf8";
	private static final String ACCOUNT = "root";
	private static final String PASSWORD = "123456wo-";

	private Connection getConn() {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ACCOUNT, PASSWORD);
			return conn;
		} catch (Exception ex) {
			System.out.println("不能获取数据库连接！");//
			ex.printStackTrace();
			return null;
		}
	}

	public List<Integer> getCartByUserId(int userId) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "select proId from cart where userId = " + userId;
		List<Integer> pros = new ArrayList<Integer>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlValue);
			while (rs.next()) {
				pros.add(rs.getInt("proId"));
			}
			stmt.close();
			conn.close();
			return pros;
		} catch (Exception ex) {
			System.out.println("数据库操作发生错误！");
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
				return null;
			}
		}
	}
	
	public List<Integer> getCartNumByUserId(int userId) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "select num from cart where userId = " + userId;
		List<Integer> pros = new ArrayList<Integer>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlValue);
			while (rs.next()) {
				pros.add(rs.getInt("num"));
			}
			stmt.close();
			conn.close();
			return pros;
		} catch (Exception ex) {
			System.out.println("数据库操作发生错误！");
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
				return null;
			}
		}
	}

	public void addCart(int proId, int userId) {
		Connection conn = getConn();
		Statement stmt = null;
		String sql = "select * from cart where userId = " + userId + " and proId = " + proId;
		boolean isExist = false;
		int num = 0;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				isExist = true;
				num = rs.getInt("num") + 1;
				break;
			}
		} catch (Exception ex) {
			System.out.println("数据库操作发生错误1！");
			ex.printStackTrace();
		}
		if (!isExist) {
			String sqlValue = "INSERT INTO cart(userId,proId) VALUES ('" + userId + "','" + proId + "')";
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sqlValue);
				stmt.close();
				conn.close();
			} catch (Exception ex) {
				System.out.println("数据库操作发生错误2！");
				ex.printStackTrace();
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException ex) {
					System.out.println("Close Error!!!!!!");//
					ex.printStackTrace();
				}
			}
		} else {
			String sqlValue = "update cart set num ='" + num + "' where userId = " + userId + " and proId = " + proId;
			try {
				stmt.executeUpdate(sqlValue);
				stmt.close();
				conn.close();
			} catch (Exception ex) {
				System.out.println("数据库操作发生错误3！");
				ex.printStackTrace();
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException ex) {
					System.out.println("Close Error!!!!!!");//
					ex.printStackTrace();
				}
			}
		}
	}

	public void removeFromCart(int proId, int userId) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "delete from cart where userId = " + userId + " and proId = " + proId;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlValue);
			stmt.close();
			conn.close();
		} catch (Exception ex) {
			System.out.println("数据库操作发生错误！");
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
	}
	
	public void minusCart(int proId, int userId) {
		Connection conn = getConn();
		Statement stmt = null;
		String sql = "select * from cart where userId = " + userId + " and proId = " + proId;
		int num = 1;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				num = rs.getInt("num");
				break;
			}
		} catch (Exception ex) {
			System.out.println("数据库操作发生错误1！");
			ex.printStackTrace();
		}
		if (num == 1) {
			String sqlValue = "delete from cart where userId = " + userId + " and proId = " + proId;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sqlValue);
				stmt.close();
				conn.close();
			} catch (Exception ex) {
				System.out.println("数据库操作发生错误2！");
				ex.printStackTrace();
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException ex) {
					System.out.println("Close Error!!!!!!");//
					ex.printStackTrace();
				}
			}
		} else {
			String sqlValue = "update cart set num ='" + (num-1) + "' where userId = " + userId + " and proId = " + proId;
			try {
				stmt.executeUpdate(sqlValue);
				stmt.close();
				conn.close();
			} catch (Exception ex) {
				System.out.println("数据库操作发生错误3！");
				ex.printStackTrace();
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException ex) {
					System.out.println("Close Error!!!!!!");//
					ex.printStackTrace();
				}
			}
		}
	}
	public static void main(String []args)
	{
		CartDAO dao = new CartDAO();
	}
}
