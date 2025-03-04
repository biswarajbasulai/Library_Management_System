<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System</title>
</head>
<body>
    <h1>Library System</h1>
    
    <form action="library" method="get">
        <input type="hidden" name="action" value="search">
        <input type="text" name="query" placeholder="Search books...">
        <button type="submit">Search</button>
    </form>
    
    <a href="library?action=overdue">View Overdue Books</a>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Available</th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
                <td>${book.available ? 'Yes' : 'No'}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
