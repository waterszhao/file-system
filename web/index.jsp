<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>hello</title>

  <style>
    body{
      background: linear-gradient(115deg, #3fdabd 0%, #d1da46 100%);
      font-family:"DejaVu Sans Mono","楷体",serif;
    }
    h3{
      font-size: 23px;
    }
    a{
      line-height: 30px;
      font-size: 30px;
      text-decoration: none;
      color: #927e7e;
    }
    a:hover {
      color: #edabf3;
    }
    ul{
      list-style-type: none;
      line-height: 50px;
    }

  </style>

</head>
<body>
<div style="width: 100%;height: 30%">
  <div style="width: 65%;float:left;">
    &nbsp;
    <div style="padding-left: 20%;padding-top: 10%">
      <h3>个人（课程）项目：</h3>
      <ul>
        <li><a href="${pageContext.request.contextPath}/fileList?path=resources">个人项目</a> </li>
      </ul>
    </div>

  </div>

  <div style="width: 35%;float:left;">
    <div style="text-align: center;">
      <h1>Hello Waters!</h1>
      <h2>Hello Strangers!</h2>
    </div>
    <div>
      <h3>个人主页：</h3>
      <ul>
        <li><a href="https://github.com/waterszhao?tab=repositories">github</a> </li>
        <li><a href="https://gitee.com/waterszhao">gitee</a></li>
      </ul>
    </div>
    <div>
      <h3>我的管理系统:<br/>(github.com/waterszhao/ssm-framework)</h3>
      <ul>
        <li><a href="http://waterszhao.top/book/allBook" style="font-weight: bold">在线预览</a></li>
      </ul>
    </div>
  </div>
</div>

</body>
</html>