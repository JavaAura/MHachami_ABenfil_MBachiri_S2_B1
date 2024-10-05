<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:layout title="Tasks | Edit">
    <style>
        body, html {
            height: 100%;
        }
        .centered-form {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            width: 100%;
            max-width: 500px;
        }
    </style>

    <div class="centered-form">
        <div class="card">
            <div class="card-body">
                <h3 class="card-title text-center mb-4">Edit Task</h3>
                <form method="post" action="tasks?action=update&id=${task.id}">
                    <div class="form-group">
                        <label for="inputTitle">Title</label>
                        <input name="title" type="text" class="form-control" id="inputTitle" value="${task.title}" placeholder="Enter task title" required>
                    </div>
                    <div class="form-group">
                        <label for="inputDescription">Description</label>
                        <textarea name="description" class="form-control" id="inputDescription" rows="3" placeholder="Enter task description" required>${task.description}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="inputPriority">Priority</label>
                        <select name="priority" id="inputPriority" class="form-control" required>
                            <option value="" disabled>Select priority...</option>
                            <option value="LOW" ${task.priority == 'LOW' ? 'selected' : ''}>LOW</option>
                            <option value="MEDIUM" ${task.priority == 'MEDIUM' ? 'selected' : ''}>MEDIUM</option>
                            <option value="HIGH" ${task.priority == 'HIGH' ? 'selected' : ''}>HIGH</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="inputStatus">Status</label>
                        <select name="status" id="inputStatus" class="form-control" required>
                            <option value="" disabled>Select status...</option>
                            <option value="TO_DO" ${task.status == 'TO_DO' ? 'selected' : ''}>TO DO</option>
                            <option value="DOING" ${task.status == 'DOING' ? 'selected' : ''}>DOING</option>
                            <option value="DONE" ${task.status == 'DONE' ? 'selected' : ''}>DONE</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="inputDeadline">Deadline</label>
                        <input type="date" name="deadline" class="form-control" id="inputDeadline" value="${task.deadline}" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Save Changes</button>
                    <a href="tasks" class="btn btn-link">Changed My Mind (back home)</a>
                </form>
            </div>
        </div>
    </div>
</layout:layout>
