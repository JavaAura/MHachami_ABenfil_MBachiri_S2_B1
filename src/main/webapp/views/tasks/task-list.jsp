<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="form.jsp">Create New Task</a>
<br>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Priority</th>
        <th>Status</th>
        <th>Creation Date</th>
        <th>Deadline</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="task" items="${tasks}">
        <tr>
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td>${task.description}</td>
                <%--            <td>${task.priority}</td>--%>
                <%--            <td>${task.status}</td>--%>
                <%--            <td>${task.creation_date}</td>--%>
                <%--            <td>${task.deadline}</td>--%>
            <td>
                <a href="tasks?action=edit&id=${task.id}">Edit</a>
                <a href="tasks?action=delete&id=${task.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
