<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:layout title="Tasks | List">
    <style>
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
    <div class="form-container">
        <form method="post" action="tasks?action=create">
            <div class="form-group">
                <label for="inputTitle">Title</label>
                <input name="title" type="text" class="form-control" id="inputTitle" placeholder="Enter task title" required>
            </div>
            <div class="form-group">
                <label for="inputDescription">Description</label>
                <textarea name="description" class="form-control" id="inputDescription" rows="3" placeholder="Enter task description" required></textarea>
            </div>
            <div class="form-group">
                <label for="inputPriority">Priority</label>
                <select id="inputPriority" name="priority" class="form-control" required>
                    <option value="" disabled selected>Choose priority...</option>
                    <option value="LOW">LOW</option>
                    <option value="MEDIUM">MEDIUM</option>
                    <option value="HIGH">HIGH</option>
                </select>
            </div>
            <div class="form-group">
                <label for="inputStatus">Status</label>
                <select id="inputStatus" name="status" class="form-control" required>
                    <option value="" disabled selected>Choose status...</option>
                    <option value="TO_DO">TO DO</option>
                    <option value="DOING">DOING</option>
                    <option value="DONE">DONE</option>
                </select>
            </div>
            <div class="form-group">
                <label for="inputCreationDate">Deadline</label>
                <input type="date" name="creation_date" class="form-control" id="inputCreationDate" required>
            </div>
            <div class="form-group">
                <label for="inputDeadline">Deadline</label>
                <input type="date" name="deadline" class="form-control" id="inputDeadline" required>
            </div>
            <button type="submit" class="btn btn-primary">Save Changes</button>
            <a href="tasks" class="btn btn-link">Changed My Mind (back home)</a>
        </form>
    </div>
</layout:layout>
