<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="tasks?action=add">Create new Task</a>
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
                            <td>${task.priority}</td>
                            <td>${task.status}</td>
                            <td>${task.creationDate}</td>
                            <td>${task.deadline}</td>
            <td>
                <a href="tasks?action=edit&id=${task.id}">Edit</a>
                <form method="post" action="tasks?action=delete&id=${task.id}"><button type="submit" onsubmit="this.confirm('Are you sure you want to delete this task?')" >Delete</button></form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
