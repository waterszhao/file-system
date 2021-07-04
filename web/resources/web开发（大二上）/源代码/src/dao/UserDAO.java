package dao;

import java.util.*;

import bean.*;

import java.sql.*;
public class UserDAO {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/web_final?useUnicode=true&characterEncoding=utf8";
	private static final String ACCOUNT = "root";
	private static final String PASSWORD = "123456wo-";

	public List<UserInfoBean> getAllUsers() {
		try {
			List<UserInfoBean> users = new ArrayList<UserInfoBean>();
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM user";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserInfoBean user = new UserInfoBean();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("name"));
				user.setPsw(rs.getString("psw"));
				user.setRequest(rs.getInt("request"));
				users.add(user);
			}
			stmt.close();
			conn.close();
			return users;
		} catch (Exception ex) {
			ex.printStackTrace();System.out.println("数据库操作发生错误！");
			return null;
		}
	}
	
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
	
	public UserInfoBean getUserByName(String name) {
		if(name == "") return null;
		Connection conn = getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlValue = "SELECT * FROM user WHERE name =" + name;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlValue);
			UserInfoBean user = new UserInfoBean();
			while (rs.next()) {
				user.setUserName(rs.getString("name"));
				 user.setPsw(rs.getString("psw"));
				 user.setRequest(rs.getInt("request"));
				 user.setId(rs.getInt("id"));
			}
			stmt.close();
			conn.close();
			return user;
		} catch (Exception ex) {
			System.out.println("数据库操作发生错误1！");
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
	public boolean validateUserName(String name) {
		if(name == "") return false;
		Connection conn = getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT name FROM user where name = "+name;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			boolean result = false;
			while (rs.next()) {
				result = true;
				break;
			}
			stmt.close();
			conn.close();
			return result;
		} catch (Exception ex) {
			System.out.println("数据库操作发生错误！");
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
	
	public void addUser(UserInfoBean User) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "INSERT INTO user(name,psw) VALUES ('"
				+ User.getUserName()
				+ "','"
				+ User.getPsw()
				+ "')";
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
	
	public UserInfoBean modifyUser(UserInfoBean User,String name) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "UPDATE user SET name ='"+User.getUserName()+"',psw='"+User.getPsw()+"'WHERE name = "+name;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlValue);
			stmt.close();
			conn.close();
			return User;
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
	
	public void deleteUser(int id) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "delete from user where id ="+id;
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
	public List<UserInfoBean> getUsersLimitSize(int begin,int pagesize) {
		try {
			List<UserInfoBean> users = new ArrayList<UserInfoBean>();
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM user limit "+begin+","+pagesize;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserInfoBean user = new UserInfoBean();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("name"));
				user.setPsw(rs.getString("psw"));
				user.setRequest(rs.getInt("request"));
				users.add(user);
			}
			stmt.close();
			conn.close();
			return users;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public List<UserInfoBean> searchUsersLimitSize(int begin,int pagesize,String search) {
		try {
			List<UserInfoBean> users = new ArrayList<UserInfoBean>();
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM user where name like '%"+search+"%' limit "+begin+","+pagesize;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserInfoBean user = new UserInfoBean();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("name"));
				user.setPsw(rs.getString("psw"));
				user.setRequest(rs.getInt("request"));
				users.add(user);
			}
			stmt.close();
			conn.close();
			return users;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public int getNumOfUsers() {
		try {
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM user";
			ResultSet rs = stmt.executeQuery(sql);
			rs.last();
			int num = rs.getRow();
			stmt.close();
			conn.close();
			return num;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	public int getNumOfUsersLimitSearch(String search) {
		try {
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM user where name like '%"+search+"%'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.last();
			int num = rs.getRow();
			stmt.close();
			conn.close();
			return num;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}