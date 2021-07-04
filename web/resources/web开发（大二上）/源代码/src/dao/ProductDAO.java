package dao;

import java.util.*;

import bean.Product;

import java.sql.*;

public class ProductDAO {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/web_final?useUnicode=true&characterEncoding=utf8";
	private static final String ACCOUNT = "root";
	private static final String PASSWORD = "123456wo-";

	public List<Product> getAllProducts() {
		try {
			List<Product> products = new ArrayList<Product>();
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM product where canBuy = 1";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("pName"));
				product.setOrigin(rs.getString("origin"));
				product.setPrice(rs.getDouble("price"));
				product.setInventory(rs.getInt("inventory"));
				product.setCanBuy(rs.getInt("canBuy"));
				products.add(product);
			}
			stmt.close();
			conn.close();
			return products;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public void addProduct(Product product) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "INSERT INTO product(pName,origin,price,inventory) VALUES ('"
				+ product.getName()
				+ "','"
				+ product.getOrigin()
				+ "','"
				+ product.getPrice()
				+ "','"
				+ product.getInventory()
				+ "')";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlValue);
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
	private Connection getConn() {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ACCOUNT, PASSWORD);
			return conn;
		} catch (Exception ex) {
			System.out.println("数据库链接错误");//
			ex.printStackTrace();
			return null;
		}
	}
	public Product getProductById(int id) {
		Connection conn = getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sqlValue = "SELECT * FROM product WHERE id=" + id;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlValue);
			Product pro = new Product();
			while (rs.next()) {
				pro.setId(rs.getInt("id"));
				  pro.setName(rs.getString("pName"));
				  pro.setOrigin(rs.getString("origin"));
				  pro.setPrice(rs.getDouble("price"));
				  pro.setInventory(rs.getInt("inventory"));
				  pro.setCanBuy(rs.getInt("canBuy"));
				break;
			}
			return pro;
		} catch (Exception ex) {
			System.out.println("数据库操作发生错误！");
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
	public void modifyProduct(Product product) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "update product set pName = '"+product.getName()+"',origin = '"+product.getOrigin()+"',price = '"+product.getPrice()+"',inventory = '"+product.getInventory()+"' where id = "+product.getId();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlValue);
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
	public int getNumOfProducts() {
		try {
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM product where canBuy = 1";
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
	public int getNumOfProductsLimitSearch(String search) {
		try {
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM product where canBuy = 1 and pName like '%"+search+"%'";
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
	public List<Product> getProductsLimitSize(int begin,int pagesize) {
		try {
			List<Product> products = new ArrayList<Product>();
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM product where canBuy = 1 limit "+begin+","+pagesize;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("pName"));
				product.setOrigin(rs.getString("origin"));
				product.setPrice(rs.getDouble("price"));
				product.setInventory(rs.getInt("inventory"));
				product.setCanBuy(rs.getInt("canBuy"));
				products.add(product);
			}
			stmt.close();
			conn.close();
			return products;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public List<Product> searchProductsLimitSize(int begin,int pagesize,String search) {
		try {
			List<Product> products = new ArrayList<Product>();
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM product where canBuy = 1 and pName like '%"+search+"%' limit "+begin+","+pagesize;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("pName"));
				product.setOrigin(rs.getString("origin"));
				product.setPrice(rs.getDouble("price"));
				product.setInventory(rs.getInt("inventory"));
				product.setCanBuy(rs.getInt("canBuy"));
				products.add(product);
			}
			stmt.close();
			conn.close();
			return products;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean minusInventory(int num,int proId) {
		Connection conn = getConn();
		Statement stmt = null;
		int originNum = getInventoryOfProducts(proId);
		if(originNum < num) return false;
		if(originNum == num) {
			String sqlValue = "update product set canBuy = 0 where id = "+proId;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sqlValue);
			}catch (Exception ex) {
				System.out.println("数据库操作发生错误！");
				ex.printStackTrace();
			}
		}
		String sqlValue = "update product set inventory = '"+(originNum - num)+"' where id = "+proId;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlValue);
			stmt.close();
			conn.close();
			return true;
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
		return false;
	}
	
	public int getInventoryOfProducts(int proId) {
		try {
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM product where id = " + proId;
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int num = rs.getInt("Inventory");
			stmt.close();
			conn.close();
			return num;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	public void removeProduct(int proId) {
		Connection conn = getConn();
		Statement stmt = null;
		String sqlValue = "update product set canBuy = 0 where id = " + proId;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlValue);
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
}