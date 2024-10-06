<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:layout title="Tasks | Assign Member">
    <style>
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
        .form-select {
            width: 100%;
            margin-bottom: 20px;
        }
        button.btn {
            width: 100%;
        }
    </style>
    <div class="form-container">
        <h1>Assign members to "${task.title}"</h1>
        <form method="post" action="tasks?action=assign&task_id=${task.id}">
            <div style="width: 100%;">
                <select name="members_id[]" class="form-select" multiple aria-label="multiple select example">
                    <option selected disabled>CTRL Select members, to assign to this task</option>
                    <c:forEach var="member" items="${members}">
                        <option value="${member.id}">${member.firstName} ${member.secondName} | ${member.email}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Assign members</button>
        </form>
        <a href="tasks" class="btn btn-link">Changed My Mind (back home)</a>
    </div>
</layout:layout>
