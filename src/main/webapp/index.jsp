<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/1/2022
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button type="button"><a href="/student?action=create">Create</a></button>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Birth</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Class</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${students}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.birth}</td>
            <td>${p.address}</td>
            <td>${p.phone}</td>
            <td>${p.email}</td>
            <td>${p.classS}</td>
            <td><button type="button" class="btn btn-warning"><a href="/student?action=edit&id=${p.id}">Edit</a></button></td>
            <td><button type="button" class="btn btn-danger"><a href="/student?action=delete&id=${p.id}">Delete</a></button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
