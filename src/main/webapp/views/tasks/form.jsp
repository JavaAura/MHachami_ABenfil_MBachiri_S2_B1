<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Form</title>
</head>
<body>
<h1><c:if test="${not empty task}">Edit Task</c:if><c:if test="${empty task}">Create Task</c:if></h1>
<form action="tasks" method="post">
    <input type="hidden" name="id" value="${task.id}">
    <label for="name">Title:</label>
    <input type="text" id="name" name="title" value="${task.title}"><br>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" value="${task.description}"><br>
    <label>
        Priority:
        <input type="radio" name="priority" value="LOW">
        <input type="radio" name="priority" value="MEDIUM">
        <input type="radio" name="priority" value="HIGH">
    </label>
    <label>
        Status:
        <input type="radio" name="status" value="TO_DO">
        <input type="radio" name="status" value="DOING">
        <input type="radio" name="status" value="DONE">
    </label>
    <label for="creations_date">Creation Date:</label>
    <input type="date" name="creation_date" id="creations_date">
    <label for="deadline">Deadline:</label>
    <input type="date" name="deadline" id="deadline">
<%--    <c:if test='${not empty task}'>Update</c:if><c:if test='${empty task}'>Create</c:if> --%>
    <input type="submit" name="action" value="create">
</form>
<a href="tasks?action=list">Back to Task List</a>
</body>
</html>
