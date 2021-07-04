<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.*"%>
<%@ page import="bean.*"%>
<html>
<head>
<title></title>
</head>
<body>
	<center>
	<div style="background:#FFFF99">
		<h1>数据插入模块：</h1>
		<h2><a href="addCourse.jsp">新增课程信息</a> <a href="courses.jsp">查看课程信息</a></h2>
		<h2><a href="addTeacher.jsp">新增教师信息</a> <a href="teachers.jsp">查看教师信息</a></h2>
		<h2><a href="major.jsp">查看专业情况</a></h2>
	</div>
	<div style="background:#CCCCCC">
		<h1>业务查询模块：</h1>
		<div style="background:#CCCC00;float:left;width:50%;height:40%">
			<h2><a href="query1.jsp">学生成绩按每学年进行成绩统计</a></h2>
			<h2><a href="query2.jsp">学生成绩名次排定</a></h2>
			<h2><a href="query3.jsp">每门课程平均成绩统计</a></h2>
			<h2><a href="query4.jsp">学生所学课程及学分统计</a></h2>
		</div>
		<div style="background:#CCCCFF;width:100%;height:40%">
			<h2>输入每个学生成绩时，自动生成该学生已修总学分(已使用触发器：添加成绩时，学生的已修学分增加相应的学分)</h2>
			<h2><a href="query6.jsp">学生成绩查询</a></h2>
			<h2><a href="query7.jsp">教师任课查询</a></h2>
			<h2><a href="query8.jsp">班级课程开设查询</a></h2>
		</div>
	</div>
	</center>
</body>
</html>