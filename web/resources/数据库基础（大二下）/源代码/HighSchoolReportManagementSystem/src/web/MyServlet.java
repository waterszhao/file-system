package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.*;
import dao.*;

public class MyServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String source = request.getParameter("source");

		if (source.equals("addMajor")) {
			String Mname = request.getParameter("Mname");
			if (Mname.equals("")) {
				request.setAttribute("msg", "专业名不能为空！");
				request.getRequestDispatcher("/addMajor.jsp").forward(request, response);
			} else {
				DAO dao = new DAO();
				Major major = new Major();
				major.setMname(Mname);
				dao.insertMajor(major);
				request.getRequestDispatcher("/major.jsp").forward(request, response);
			}
		}
		if (source.equals("addClass")) {
			String CLname = request.getParameter("CLname");
			String Mno = request.getParameter("Mno");
			String Mname = request.getParameter("Mname");
			if (CLname.equals("")) {
				request.setAttribute("msg", "班级名不能为空！");
				request.getRequestDispatcher("/addClass.jsp").forward(request, response);
			} else {
				DAO dao = new DAO();
				Classes cl = new Classes();
				cl.setCLname(CLname);
				cl.setMno(Integer.parseInt(Mno));
				dao.insertClass(cl);
				request.setAttribute("Mname", Mname);
				request.setAttribute("Mno", Mno);
				request.getRequestDispatcher("/class.jsp").forward(request, response);
			}
		}
		if (source.equals("addCourse")) {
			String Cname = request.getParameter("Cname");
			String learnTime = request.getParameter("learnTime");
			String credit = request.getParameter("credit");
			String checkChoice = request.getParameter("checkChoice");
			String Cterm = request.getParameter("Cterm");

			if (Cname.equals("")) {
				request.setAttribute("msg", "课程名不能为空！");
				request.getRequestDispatcher("/addCourse.jsp").forward(request, response);
			} else {
				DAO dao = new DAO();
				Course course = new Course();
				course.setCname(Cname);
				course.setCredit(Double.parseDouble(credit));
				course.setCheckChoice(checkChoice);
				course.setLearnTime(Integer.parseInt(learnTime));
				course.setCterm(Cterm);

				dao.insertCourse(course);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}

		if (source.equals("addTeacher")) {
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String type = request.getParameter("type");
			String tel = request.getParameter("telephone");

			if (name.equals("")) {
				request.setAttribute("msg", "姓名不能为空！");
				request.getRequestDispatcher("/addTeacher.jsp").forward(request, response);
			} else {
				DAO dao = new DAO();
				Teacher teacher = new Teacher();
				teacher.setTname(name);
				teacher.setTage(Integer.parseInt(age));
				teacher.setTsex(sex);
				teacher.setTtype(type);
				teacher.setTelephone(tel);

				dao.insertTeacher(teacher);
				request.getRequestDispatcher("/teachers.jsp").forward(request, response);
			}
		}
		if (source.equals("modifyTeacher")) {
			String Tno = request.getParameter("Tno");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String type = request.getParameter("type");
			String tel = request.getParameter("telephone");
			Teacher teacher = new Teacher();
			teacher.setTno(Integer.parseInt(Tno));
			teacher.setTname(name);
			teacher.setTage(Integer.parseInt(age));
			teacher.setTsex(sex);
			teacher.setTtype(type);
			teacher.setTelephone(tel);
			
			if (name.equals("")) {
				request.setAttribute("msg", "姓名不能为空！");
				request.setAttribute("Tno", Tno);
				request.setAttribute("Tname", name);
				request.setAttribute("Tsex", sex);
				request.setAttribute("Tage", age);
				request.setAttribute("Ttype", type);
				request.setAttribute("Telephone", tel);
				request.getRequestDispatcher("/modifyTeacher.jsp").forward(request, response);
			} else {
				DAO dao = new DAO();
				dao.modifyTeacher(teacher);
				request.setAttribute("Tno", Tno);
				request.setAttribute("Tname", name);
				request.getRequestDispatcher("/teachers.jsp").forward(request, response);
			}
		}

		
		if (source.equals("modifyStudent")) {
			String Tno = request.getParameter("Sno");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String credit = request.getParameter("credit");
			String position = request.getParameter("position");
			Student student = new Student();
			student.setSno(Integer.parseInt(Tno));
			student.setSname(name);
			student.setSage(Integer.parseInt(age));
			student.setSsex(sex);
			student.setCreditGot(Double.parseDouble(credit));
			student.setSposition(position);
			DAO dao = new DAO();
			dao.modifyStudent(student);
			
			request.setAttribute("CLname", request.getParameter("CLname"));
			request.setAttribute("CLno", request.getParameter("CLno"));
			request.getRequestDispatcher("/checkstudents.jsp").forward(request, response);
		}
		
		if (source.equals("modifyCourse")) {
			String Cname = request.getParameter("Cname");
			String learnTime = request.getParameter("LearnTime");
			String credit = request.getParameter("Credit");
			String checkChoice = request.getParameter("CheckChoice");
			String Cterm = request.getParameter("Cterm");
			
			Course course = new Course();
			course.setCno(Integer.parseInt(request.getParameter("Cno")));
			course.setCname(Cname);
			course.setCredit(Double.parseDouble(credit));
			course.setCheckChoice(checkChoice);
			course.setLearnTime(Integer.parseInt(learnTime));
			course.setCterm(Cterm);
			
			DAO dao = new DAO();
			dao.modifyCourse(course);
			
			request.getRequestDispatcher("/courses.jsp").forward(request, response);
		}
		
		
		if (source.equals("deleteTeacher")) {
			String Tno = request.getParameter("Tno");
			DAO dao = new DAO();
			dao.deleteTeacher(Integer.parseInt(Tno));
			request.getRequestDispatcher("/teachers.jsp").forward(request, response);
		}
		
		if (source.equals("deleteStudent")) {
			String Sno = request.getParameter("Sno");
			DAO dao = new DAO();
			dao.deleteStudent(Integer.parseInt(Sno));
			
			request.setAttribute("CLname", request.getParameter("CLname"));
			request.setAttribute("CLno", request.getParameter("CLno"));
			request.getRequestDispatcher("/checkstudents.jsp").forward(request, response);
		}
		
		if (source.equals("deleteCourse")) {
			String Cno = request.getParameter("Cno");
			DAO dao = new DAO();
			dao.deleteCourse(Integer.parseInt(Cno));
			request.getRequestDispatcher("/courses.jsp").forward(request, response);
		}
		
		if (source.equals("addteach")) {
			String Tno = request.getParameter("Tno");
			String Tname = request.getParameter("Tname");
			String Cno = request.getParameter("Cno");
			DAO dao = new DAO();
			dao.insertTeach(Integer.parseInt(Tno), Integer.parseInt(Cno));
			request.setAttribute("Tname", Tname);
			request.setAttribute("Tno", Tno);
			request.getRequestDispatcher("/checkteacher.jsp").forward(request, response);
		}

		if (source.equals("addsetcourse")) {
			String CLno = request.getParameter("CLno");
			String CLname = request.getParameter("CLname");
			String Cno = request.getParameter("Cno");
			DAO dao = new DAO();

			dao.insertSetCourse(Integer.parseInt(CLno), Integer.parseInt(Cno));
			request.setAttribute("CLname", CLname);
			request.setAttribute("CLno", CLno);
			request.getRequestDispatcher("/checksetcourse.jsp").forward(request, response);
		}
		if (source.equals("addStudent")) {
			String CLno = request.getParameter("CLno");
			String CLname = request.getParameter("CLname");

			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String creditGot = request.getParameter("credit");
			String position = request.getParameter("position");
			if (name.equals("")) {
				request.setAttribute("msg", "姓名不能为空！");
				request.getRequestDispatcher("/addstudent.jsp").forward(request, response);
			} else {
				DAO dao = new DAO();
				Student student = new Student();
				student.setSname(name);
				student.setSage(Integer.parseInt(age));
				student.setSsex(sex);
				student.setCreditGot(Double.parseDouble(creditGot));
				student.setSposition(position);
				student.setCLno(Integer.parseInt(CLno));

				dao.insertStudent(student);
				request.setAttribute("CLname", CLname);
				request.setAttribute("CLno", CLno);
				request.getRequestDispatcher("/checkstudents.jsp").forward(request, response);
			}
		}
		if (source.equals("addreport")) {
			String grade = request.getParameter("grade");

			if (grade.equals("")) {
				request.setAttribute("msg", "成绩不能为空！");
				request.getRequestDispatcher("/addreport.jsp").forward(request, response);
			} else {

				String Cno = request.getParameter("Cno");
				String Sno = request.getParameter("Sno");
				String CLno = request.getParameter("CLno");
				String CLname = request.getParameter("CLname");
				String Cname = request.getParameter("Cname");
				
				Report report = new Report();
				report.setCno(Integer.parseInt(Cno));
				report.setSno(Integer.parseInt(Sno));
				report.setGrade(Integer.parseInt(grade));
				DAO dao = new DAO();
				dao.insertReport(report);
				request.setAttribute("CLname", CLname);
				request.setAttribute("CLno", CLno);
				request.setAttribute("Cname", Cname);
				request.setAttribute("Cno", Cno);
				request.getRequestDispatcher("/choosestudent.jsp").forward(request, response);
			}
		}
	}
}
