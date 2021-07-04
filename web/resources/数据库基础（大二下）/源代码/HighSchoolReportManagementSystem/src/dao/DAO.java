package dao;

import bean.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/zhaoshui03mis?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	private static final String ACCOUNT = "root";
	private static final String PASSWORD = "123456";

	public Connection getConn() {// 获取数据库连接
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ACCOUNT, PASSWORD);
			return conn;
		} catch (Exception ex) {
			System.out.println("不能获取数据库连接！");
			ex.printStackTrace();
			return null;
		}
	}

	public List<Major> getAllMajors() throws Exception {
		String SQL = "SELECT * FROM zhaos_Majors";
		Connection c = getConn();
		Statement s = null;
		List<Major> majors = new ArrayList<Major>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Major major = new Major();
				major.setMno(rs.getInt("Mno"));
				major.setMname(rs.getString("Mname"));
				majors.add(major);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			System.out.println("获取major时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return majors;
	}

	public List<Course> getAllCourses() throws Exception {
		String SQL = "SELECT * FROM zhaos_courses";
		Connection c = getConn();
		Statement s = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Course course = new Course();
				course.setCno(rs.getInt("Cno"));
				course.setCname(rs.getString("Cname"));
				course.setCredit(Double.parseDouble(rs.getString("Credit")));
				course.setCheckChoice(rs.getString("CheckChoice"));
				course.setLearnTime(Integer.parseInt(rs.getString("LearnTime")));
				course.setCterm(rs.getString("Cterm"));
				courses.add(course);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			System.out.println("获取major时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return courses;
	}
	
	public List<Course> getCoursesByClass(int CLno) throws Exception {
		String SQL = "SELECT * FROM zhaos_courses,zhaos_setcourses WHERE "
				+ "zhaos_courses.Cno = zhaos_setcourses.Cno AND "
				+ "zhaos_setcourses.CLno ="+CLno;
		Connection c = getConn();
		Statement s = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Course course = new Course();
				course.setCno(rs.getInt("Cno"));
				course.setCname(rs.getString("Cname"));
				course.setCredit(Double.parseDouble(rs.getString("Credit")));
				course.setCheckChoice(rs.getString("CheckChoice"));
				course.setLearnTime(Integer.parseInt(rs.getString("LearnTime")));
				course.setCterm(rs.getString("Cterm"));
				courses.add(course);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			System.out.println("获取course时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return courses;
	}
	
	public List<Course> getCoursesNoTeach(int Tno) throws Exception {
		String SQL = "SELECT * FROM zhaos_courses WHERE " + 
				"Cno NOT IN " + 
				"(SELECT Cno FROM zhaos_teach WHERE Tno = "+Tno+")";
		Connection c = getConn();
		Statement s = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Course course = new Course();
				course.setCno(rs.getInt("Cno"));
				course.setCname(rs.getString("Cname"));
				course.setCredit(Double.parseDouble(rs.getString("Credit")));
				course.setCheckChoice(rs.getString("CheckChoice"));
				course.setLearnTime(Integer.parseInt(rs.getString("LearnTime")));
				course.setCterm(rs.getString("Cterm"));
				courses.add(course);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			System.out.println("获取course时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return courses;
	}
	
	public List<Course> getCoursesNoSet(int CLno) throws Exception {
		String SQL = "SELECT * FROM zhaos_courses WHERE " + 
				"Cno NOT IN " + 
				"(SELECT Cno FROM zhaos_setCourses WHERE CLno = "+CLno+")";
		Connection c = getConn();
		Statement s = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Course course = new Course();
				course.setCno(rs.getInt("Cno"));
				course.setCname(rs.getString("Cname"));
				course.setCredit(Double.parseDouble(rs.getString("Credit")));
				course.setCheckChoice(rs.getString("CheckChoice"));
				course.setLearnTime(Integer.parseInt(rs.getString("LearnTime")));
				course.setCterm(rs.getString("Cterm"));
				courses.add(course);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			System.out.println("获取course时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return courses;
	}
	
	public List<Course> getCoursesByClassName(String CLname) throws Exception {
		String SQL = "SELECT * FROM zhaos_courses,zhaos_setcourses,zhaos_classes WHERE "
				+ "zhaos_courses.Cno = zhaos_setcourses.Cno AND zhaos_classes.CLno = zhaos_setcourses.CLno AND "
				+ "zhaos_classes.CLname = '"+CLname + "'";
		Connection c = getConn();
		Statement s = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Course course = new Course();
				course.setCno(rs.getInt("Cno"));
				course.setCname(rs.getString("Cname"));
				course.setCredit(Double.parseDouble(rs.getString("Credit")));
				course.setCheckChoice(rs.getString("CheckChoice"));
				course.setLearnTime(Integer.parseInt(rs.getString("LearnTime")));
				course.setCterm(rs.getString("Cterm"));
				courses.add(course);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取course时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return courses;
	}
	
	public boolean insertMajor(Major major) {
		String SQL = "INSERT INTO zhaos_majors(Mname) VALUES('" + major.getMname() + "')";
		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("新增专业信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}

	public boolean insertClass(Classes cl) {
		String SQL = "INSERT INTO zhaos_classes(Mno,CLname) VALUES(" + cl.getMno() + ",'" + cl.getCLname() + "')";
		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("新增班级信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}

	public boolean insertCourse(Course course) {
		String SQL = "INSERT INTO zhaos_courses(Cname,learnTime,credit,cterm,checkchoice) "
				+ "VALUES('" + course.getCname() + "','"
				+ course.getLearnTime() + "','" + course.getCredit() + "','" 
				+ course.getCterm() + "','"
				+ course.getCheckChoice() + "')";
		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("新增course信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}

	public boolean insertTeach(int Tno,int Cno) {
		String SQL = "INSERT INTO zhaos_teach(Tno,Cno) "+ "VALUES('"+Tno+"','"+Cno+"')";
		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("新增teach信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}
	
	public boolean insertSetCourse(int CLno,int Cno) {
		String SQL = "INSERT INTO zhaos_setCourses(CLno,Cno) "+ "VALUES('"+CLno+"','"+Cno+"')";
		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("新增setcourse信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}
	
	public boolean insertTeacher(Teacher teacher) {
		String SQL = "INSERT INTO zhaos_teachers(Tname,Tsex,Tage,Ttype,Telephone) "
				+ "VALUES('" + teacher.getTname() + "','"
				+ teacher.getTsex() + "','" + teacher.getTage() + "','" 
				+ teacher.getTtype() + "','"
				+ teacher.getTelephone() + "')";
		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("新增teacher信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}
	
	public boolean modifyTeacher(Teacher teacher) {
		String SQL = "UPDATE zhaos_teachers SET Tname = '"+teacher.getTname()
		+"',Tage = "+teacher.getTage()+",Tsex = '"+teacher.getTsex()
		+"',Ttype='"+teacher.getTtype()+"',Telephone = '"+teacher.getTelephone()
		+"' WHERE Tno = " + teacher.getTno();

		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("更新teacher信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}
	
	public boolean modifyStudent(Student student) {
		String SQL = "UPDATE zhaos_students SET Sname = '"+student.getSname()
		+"',Sage = "+student.getSage()+",Ssex = '"+student.getSsex()
		+"',CreditGot='"+student.getCreditGot()+"',Sposition = '"+student.getSposition()
		+"' WHERE Sno = " + student.getSno();

		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("更新student信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}
	
	public boolean modifyCourse(Course course) {
		String SQL = "UPDATE zhaos_courses SET Cname = '"+course.getCname()
		+"',LearnTime = "+course.getLearnTime()+",Cterm = '"+course.getCterm()
		+"',Credit='"+course.getCredit()+"',CheckChoice = '"+course.getCheckChoice()
		+"' WHERE Cno = " + course.getCno();

		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("更新course信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}
	
	public boolean insertStudent(Student student) {
		String SQL = "INSERT INTO zhaos_students(Sname,Ssex,Sage,CreditGot,Sposition,CLno) "
				+ "VALUES('" + student.getSname() + "','"
				+ student.getSsex() + "','" + student.getSage() + "','" 
				+ student.getCreditGot() + "','"
				+ student.getSposition() + "','"
				+ student.getCLno()+"')";
		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("新增student信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}
	
	public boolean insertReport(Report r) {
		String SQL = "INSERT INTO zhaos_reports(Sno,Cno,Grade) VALUES('" + r.getSno() + "','" + r.getCno() + "','"
				+ r.getGrade() + "')";
		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.execute(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("录入学生成绩时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}

	public List<Classes> getClassesByMajor(int Mno) throws Exception {
		String SQL = "SELECT * FROM zhaos_Classes WHERE Mno = '" + Mno + "'";
		Connection c = getConn();
		Statement s = null;
		List<Classes> classes = new ArrayList<Classes>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Classes AClass = new Classes();
				AClass.setCLno(rs.getInt("CLno"));
				AClass.setCLname(rs.getString("CLname"));
				AClass.setMno(rs.getInt("Mno"));
				classes.add(AClass);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			System.out.println("获取class时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
		return classes;
	}

	public List<Classes> getClassesByCourse(int Cno) throws Exception {
		String SQL = "SELECT * FROM zhaos_Classes,zhaos_setcourses WHERE "
				+ "zhaos_Classes.CLno = zhaos_setcourses.CLno AND Cno = '" + Cno + "'";
		Connection c = getConn();
		Statement s = null;
		List<Classes> classes = new ArrayList<Classes>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Classes AClass = new Classes();
				AClass.setCLno(rs.getInt("CLno"));
				AClass.setCLname(rs.getString("CLname"));
				AClass.setMno(rs.getInt("Mno"));
				classes.add(AClass);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取class时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
		return classes;
	}
	
	public List<Student> getStudentsByClass(int CLno) throws Exception {
		String SQL = "SELECT * FROM zhaos_Students WHERE CLno = '" + CLno + "'";
		Connection c = getConn();
		Statement s = null;
		List<Student> students = new ArrayList<Student>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Student student = new Student();
				student.setCLno(rs.getInt("CLno"));
				student.setSno(rs.getInt("Sno"));
				student.setSname(rs.getString("Sname"));
				student.setSsex(rs.getString("Ssex"));
				student.setSage(Integer.parseInt(rs.getString("Sage")));
				student.setCreditGot(Double.parseDouble(rs.getString("CreditGot")));
				student.setSposition(rs.getString("Sposition"));
				students.add(student);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			System.out.println("获取student时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return students;
	}

	public List<Student> getStudentsNoReports(int CLno,int Cno) throws Exception {
		String SQL = "SELECT * FROM zhaos_students "
				+ "WHERE Sno NOT IN "
				+ "(SELECT Sno FROM zhaos_reports "
				+ "WHERE Cno = "+Cno+") "
				+ "AND CLno = "+CLno;
		Connection c = getConn();
		Statement s = null;
		List<Student> students = new ArrayList<Student>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Student student = new Student();
				student.setCLno(rs.getInt("CLno"));
				student.setSno(rs.getInt("Sno"));
				student.setSname(rs.getString("Sname"));
				student.setSsex(rs.getString("Ssex"));
				student.setSage(Integer.parseInt(rs.getString("Sage")));
				student.setCreditGot(Double.parseDouble(rs.getString("CreditGot")));
				student.setSposition(rs.getString("Sposition"));
				students.add(student);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			System.out.println("获取student时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return students;
	}
	
	public List<Course> getCoursesByTeacher(int Tno) throws Exception {
		String SQL = "SELECT * FROM zhaos_teach,zhaos_courses WHERE zhaos_teach.Tno = " + Tno
				+ " AND zhaos_courses.Cno = zhaos_teach.Cno";
		Connection c = getConn();
		Statement s = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Course course = new Course();
				course.setCno(rs.getInt("Cno"));
				course.setCname(rs.getString("Cname"));
				course.setCredit(Double.parseDouble(rs.getString("Credit")));
				course.setCheckChoice(rs.getString("CheckChoice"));
				course.setLearnTime(Integer.parseInt(rs.getString("LearnTime")));
				course.setCterm(rs.getString("Cterm"));
				courses.add(course);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			System.out.println("获取course时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
		return courses;
	}

	public List<Course> getCoursesByTeacherName(String name) throws Exception {
		String SQL = "SELECT * FROM zhaos_teachers,zhaos_teach,zhaos_courses WHERE zhaos_teachers.Tname = '" + name
				+ "' AND zhaos_courses.Cno = zhaos_teach.Cno AND zhaos_teachers.Tno = zhaos_teach.Tno";
		Connection c = getConn();
		Statement s = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Course course = new Course();
				course.setCno(rs.getInt("Cno"));
				course.setCname(rs.getString("Cname"));
				course.setCredit(Double.parseDouble(rs.getString("Credit")));
				course.setCheckChoice(rs.getString("CheckChoice"));
				course.setLearnTime(Integer.parseInt(rs.getString("LearnTime")));
				course.setCterm(rs.getString("Cterm"));
				courses.add(course);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取course时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
		return courses;
	}
	
	public List<Teacher> getTeachers() throws Exception {
		String SQL = "SELECT * FROM zhaos_Teachers";
		Connection c = getConn();
		Statement s = null;
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setTno(rs.getInt("Tno"));
				teacher.setTname(rs.getString("Tname"));
				teacher.setTsex(rs.getString("Tsex"));
				teacher.setTage(Integer.parseInt(rs.getString("Tage")));
				teacher.setTtype(rs.getString("Ttype"));
				teacher.setTelephone(rs.getString("Telephone"));
				teachers.add(teacher);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			System.out.println("获取teacher时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
		return teachers;
	}
	
	public List<SCR> getReportsByStudent(int Sno) throws Exception {
		String SQL = "SELECT zhaos_students.Sno, zhaos_students.Sname, " + 
				" zhaos_courses.Cno,zhaos_courses.Cname,zhaos_courses.Credit, " + 
				" zhaos_reports.Grade,zhaos_courses.Cterm " + 
				"FROM zhaos_students,zhaos_courses,zhaos_reports " + 
				"WHERE zhaos_reports.Cno = zhaos_courses.Cno AND " + 
				"zhaos_reports.Sno = zhaos_students.Sno AND " + 
				"zhaos_Students.Sno = '"+Sno+"'";
		Connection c = getConn();
		Statement s = null;
		List<SCR> reports = new ArrayList<SCR>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				SCR report = new SCR();
				report.setCname(rs.getString("Cname"));
				report.setSname(rs.getString("Sname"));
				report.setCterm(rs.getString("Cterm"));
				report.setSno(rs.getInt("Sno"));
				report.setGrade(Integer.parseInt(rs.getString("Grade")));
				reports.add(report);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			System.out.println("获取report时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return reports;
	}
	
	public List<SCR> getReportsByStudentName(String name) throws Exception {
		String SQL = "SELECT zhaos_students.Sno, zhaos_students.Sname, " + 
				" zhaos_courses.Cno,zhaos_courses.Cname,zhaos_courses.Credit, " + 
				" zhaos_reports.Grade,zhaos_courses.Cterm " + 
				"FROM zhaos_students,zhaos_courses,zhaos_reports " + 
				"WHERE zhaos_reports.Cno = zhaos_courses.Cno AND " + 
				"zhaos_reports.Sno = zhaos_students.Sno AND " + 
				"zhaos_Students.Sname = '"+name+"'";
		Connection c = getConn();
		Statement s = null;
		List<SCR> reports = new ArrayList<SCR>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				SCR report = new SCR();
				report.setCname(rs.getString("Cname"));
				report.setSname(rs.getString("Sname"));
				report.setCterm(rs.getString("Cterm"));
				report.setSno(rs.getInt("Sno"));
				report.setGrade(Integer.parseInt(rs.getString("Grade")));
				reports.add(report);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取report时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return reports;
	}
	
	public List<SCR> getReportsByYear(String time) throws Exception {
		String SQL = "CALL queryGradeByYear('"+time+"%')";
		Connection c = getConn();
		Statement s = null;
		List<SCR> reports = new ArrayList<SCR>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				SCR report = new SCR();
				report.setCname(rs.getString("Cname"));
				report.setSname(rs.getString("Sname"));
				report.setCterm(rs.getString("Cterm"));
				report.setSno(rs.getInt("Sno"));
				report.setGrade(Integer.parseInt(rs.getString("Grade")));
				reports.add(report);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取report时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return reports;
	}
	
	public List<SCR> getReportsByGrade(String course) throws Exception {
		String SQL = "SELECT zhaos_students.Sno,zhaos_students.Sname," + 
				"	zhaos_courses.Cname, zhaos_courses.Cterm," + 
				"	zhaos_reports.Grade " + 
				"FROM zhaos_students,zhaos_courses,zhaos_reports " + 
				"WHERE zhaos_students.Sno = zhaos_reports.Sno AND " + 
				"   zhaos_reports.Cno = zhaos_courses.Cno AND " + 
				"	zhaos_courses.Cname = '"+course+"'  " + 
				"    ORDER BY zhaos_reports.Grade";
		Connection c = getConn();
		Statement s = null;
		List<SCR> reports = new ArrayList<SCR>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				SCR report = new SCR();
				report.setCname(rs.getString("Cname"));
				report.setSname(rs.getString("Sname"));
				report.setCterm(rs.getString("Cterm"));
				report.setSno(rs.getInt("Sno"));
				report.setGrade(Integer.parseInt(rs.getString("Grade")));
				reports.add(report);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取report时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return reports;
	}
	
	public List<CR> getAverageGrade() throws Exception {
		String SQL = "SELECT zhaos_courses.Cname, zhaos_reports.Cno,AVG(zhaos_reports.Grade) Grade " + 
				"FROM zhaos_students,zhaos_courses,zhaos_reports " + 
				"WHERE zhaos_reports.Cno = zhaos_courses.Cno " + 
				"GROUP BY zhaos_reports.Cno";
		Connection c = getConn();
		Statement s = null;
		List<CR> reports = new ArrayList<CR>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				CR report = new CR();
				report.setCno(Integer.parseInt(rs.getString("Cno")));
				report.setCname(rs.getString("Cname"));
				report.setGrade(rs.getInt("Grade"));
				reports.add(report);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取report时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return reports;
	}
	
	public List<SC> getStuCouByName(String name) throws Exception {
		String SQL = "SELECT zhaos_students.Sno, zhaos_students.Sname,zhaos_classes.CLname, " + 
				" zhaos_courses.Cno,zhaos_courses.Cname,zhaos_courses.Credit,zhaos_courses.Cterm " + 
				"FROM zhaos_students,zhaos_courses,zhaos_classes,zhaos_setcourses " + 
				"WHERE zhaos_setcourses.Cno = zhaos_courses.Cno AND " + 
				"zhaos_setcourses.CLno = zhaos_classes.CLno AND  " + 
				"zhaos_setcourses.CLno = zhaos_students.CLno AND " + 
				"zhaos_Students.Sname = '"+name+"'";
		Connection c = getConn();
		Statement s = null;
		List<SC> students = new ArrayList<SC>();
		try {
			s = c.createStatement();
			ResultSet rs = s.executeQuery(SQL);
			while (rs.next()) {
				SC student = new SC();
				student.setCLname(rs.getString("CLname"));
				student.setSname(rs.getString("Sname"));
				student.setCname(rs.getString("Cname"));
				student.setCterm(rs.getString("Cterm"));
				student.setCredit(Double.parseDouble(rs.getString("Credit")));
				students.add(student);
			}
			c.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取student时发生错误！");
			return null;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");//
				ex.printStackTrace();
			}
		}
		return students;
	}
	
	public boolean deleteTeacher(int Tno) {
		String SQL = "DELETE FROM zhaos_teachers WHERE Tno = " + Tno;
		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("删除teacher信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}
	
	public boolean deleteStudent(int Sno) {
		String SQL = "DELETE FROM zhaos_students WHERE Sno = " + Sno;
		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			System.out.println("删除student信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}
	
	public boolean deleteCourse(int Cno) {
		String SQL = "DELETE FROM zhaos_courses WHERE Cno = " + Cno;
		Connection c = getConn();
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(SQL);
			c.close();
			s.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("删除course信息时出错");
			return false;
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				System.out.println("Close Error!!!!!!");
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		List<Major> majors = new DAO().getAllMajors();
		for (int i = 0; i < majors.size(); i++) {
			Major major = majors.get(i);
			System.out.println(major.getMno());
		}
	}
}
