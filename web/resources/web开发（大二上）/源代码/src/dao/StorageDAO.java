package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

public class StorageDAO {
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
	
	public List<Integer> getStorageByUserId(int userId) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "select proId from storage where userId = " + userId;
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
			System.out.println("数据库操作发生错误1！");
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
	
	public List<Integer> getStorageId(int userId) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "select id from storage where userId = " + userId;
		List<Integer> pros = new ArrayList<Integer>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlValue);
			while (rs.next()) {
				pros.add(rs.getInt("id"));
			}
			stmt.close();
			conn.close();
			return pros;
		} catch (Exception ex) {
			System.out.println("数据库操作发生错误1！");
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
	
	public List<Integer> getStorageNumByUserId(int userId) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "select num from storage where userId = " + userId;
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
			System.out.println("数据库操作发生错误2！");
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
	
	public List<String> getBoughtTime(int userId) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "select buyDate from storage where userId = " + userId;
		List<String> pros = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlValue);
			while (rs.next()) {
				pros.add(rs.getString("buyDate"));
			}
			stmt.close();
			conn.close();
			return pros;
		} catch (Exception ex) {
			System.out.println("数据库操作发生错误2！");
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
	
	public boolean addStorage(int proId, int userId,int buyNum) {
		ProductDAO prodao = new ProductDAO();
		boolean isBought = prodao.minusInventory(buyNum, proId);
		if(!isBought) return isBought;
		CartDAO cartdao = new CartDAO();
		cartdao.removeFromCart(proId, userId);	
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "INSERT INTO storage(userId,proId,num) VALUES ('" + userId + "','" + proId + "','" + buyNum + "')";
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sqlValue);
				stmt.close();
				conn.close();
				return isBought;
			} catch (Exception ex) {
				System.out.println("数据库操作发生错误4！");
				ex.printStackTrace();
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
					return false;
				} catch (SQLException ex) {
					System.out.println("Close Error!!!!!!");//
					ex.printStackTrace();
				}
			}
		return false;
	}
	
	public void deleteStorage(int id) {	
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "delete from storage where id = " + id;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sqlValue);
				stmt.close();
				conn.close();
			} catch (Exception ex) {
				System.out.println("数据库操作发生错误4！");
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
	
	public void deleteStorageByUserId(int id) {	
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "delete from storage where userId = " + id;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sqlValue);
				stmt.close();
				conn.close();
			} catch (Exception ex) {
				System.out.println("数据库操作发生错误4！");
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
