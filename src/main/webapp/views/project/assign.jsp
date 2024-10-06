<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<layout:layout title="Project Assignment">
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .centered-form {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f7f7f7;
        }
        .card {
            width: 100%;
            max-width: 500px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        .card-body {
            padding: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .btn-primary {
            width: 100%;
            background-color: #007bff;
            border: none;
            padding: 10px;
            color: #fff;
            border-radius: 5px;
        }
        .btn-link {
            color: #007bff;
            text-decoration: none;
            display: block;
            text-align: center;
            margin-top: 10px;
        }
        .alert {
            padding: 10px;
            background-color: #f8d7da;
            border-color: #f5c6cb;
            color: #721c24;
            border-radius: 5px;
        }
    </style>

    <div class="centered-form">
        <div class="card">
            <div class="card-body">
                <h3 class="card-title text-center">Assign Team to Project</h3>
                <hr>

                <!-- Display error message if any -->
                <c:if test="${not empty errorMessage}">
                    <div id="alert" class="alert" role="alert">
                        ${errorMessage}
                    </div>
                </c:if>

                <!-- Project Assignment Form -->
                <form method="post" action="assign">
                    <input type="hidden" value="${project.id}" name="project_id" />

                    <!-- Team Selection -->
                    <div class="form-group">
                        <label for="team-select">Select Team</label>
                        <select class="form-select" id="team-select" name="team_id" required>
                            <option value="" disabled selected>Select Team</option>
                            <c:forEach var="team" items="${teams}">
                                <option value="${team.id}">${team.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Save Button -->
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                    <a href="${pageContext.request.contextPath}/project" class="btn btn-link">Changed My Mind (Back to Projects)</a>
                </form>
            </div>
        </div>
    </div>
</layout:layout>
