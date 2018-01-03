<%-- 
    Document   : News
    Created on : Dec 5, 2017, 11:20:21 AM
    Author     : Son
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="NewsController" enctype="multipart/form-data" >
            <table border="1" cellpadding="5">
                <tr>
                    <th>ID:</th>
                    <th><input type="text" readonly="readonly" name="id" value="<c:out value='${news.id}'/>"/></th>
                </tr>
                <tr>
                    <th>Title:</th>
                    <td><input type="text" name="title" value="<c:out value='${news.title}'/>"/></td>
                </tr>
                <tr>
                    <th>Content:</th>
                    <td><textarea name="content" rows="6"><c:out value='${news.content}'/></textarea></td>
                </tr>
                <tr>
                    <th>Img:</th>
                    <td>
                        <input type="text" name="img" value="<c:out value='${news.img}'/>"/>
                        <br/>
                        <input type="file" name="file" />
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form>

        <a href="NewsController?action=listNews">Back</a>
    </body>
</html>
