<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>文件列表</title>
    <style>
        body {
            background: linear-gradient(115deg, #cffff2 0%, #f7ffc7 100%);
            font-family: "DejaVu Sans Mono", "楷体", serif;
        }

        h3 {
            font-size: 23px;
        }

        a {
            line-height: 20px;
            font-size: 20px;
            text-decoration: none;
            color: #927e7e;
        }

        a:hover {
            color: #edabf3;
        }

        ul {
            list-style-type: none;
            line-height: 20px;
        }
    </style>
</head>

<body>

${fn:replace(file,"+","%2B")}
<span>
    <h3>当前位置:${path}</h3>
</span>

<ul>
    <li>
        <img src="../../images/folder.png" alt="文件夹"/>&nbsp;
        <c:if test="${path=='resources/'}">
            <a href="${pageContext.request.contextPath}/">.../</a>
        </c:if>
        <c:if test="${path!='resources/'}">
            <a href="${pageContext.request.contextPath}/fileList?path=${lastPath}">.../</a>
        </c:if>
    </li>
    <br/>

    <c:forEach var="file" items="${folderList}">
        <li><img src="/images/folder.png" alt="文件夹"/>&nbsp;<a
                href="${pageContext.request.contextPath}/fileList?path=${path}${file}">${file}/</a></li>
        <br/>
    </c:forEach>
    <c:forEach var="file" items="${fileList}">
        <li>
            <img src="/images/file.png" alt="文件"/>&nbsp;
            <a href="${pageContext.request.contextPath}/fileContent?path=${path}${file}" target="_blank">${file}</a>
            <a href="${pageContext.request.contextPath}/download?filePath=${path}&fileName=${file}"
               target="_blank">下载</a>
        </li>
        <br/>
    </c:forEach>
</ul>

</body>
</html>
