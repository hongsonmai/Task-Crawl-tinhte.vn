<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tinhte.vn</title>
    </head>
    <body>  
        <table border="1" cellpadding="5">

            <h2 align="center">List of News</h2>
            <a href="NewsController?action=insert">ADD</a>
            <tr>
                <th>Image</th>
                <th>Title</th>
                <th>Content</th>
                <th colspan="2">Actions</th>
            </tr>
            <c:forEach var="news" items="${news}">
                <tr>
                    <td><img src="<c:out value="${news.img}"/>" width="100" height="100"></td>
                    <td><a href="<c:out value="${news.link}"/>"><c:out value="${news.title}" /></a></td>
                    <td><c:out value="${news.content}" /></td>                  
                    <td><a href="NewsController?action=editNews&id=<c:out value='${news.id}' />">Edit</a></td>
                    <td><a href="NewsController?action=delete&id=<c:out value='${news.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
        </table> 
    </body>
</html>