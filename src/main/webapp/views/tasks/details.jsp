<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:layout title="Tasks | Details">
    <style>
        body {
            background: #eee;
        }
        .card {
            box-shadow: 0 20px 27px 0 rgb(0 0 0 / 5%);
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid rgba(0, 0, 0, .125);
            border-radius: 1rem;
        }
        a {
            color: #5465ff;
            text-decoration: none;
        }
    </style>
    <div class="container-fluid">
        <div class="container">
            <!-- Title -->
            <div class="d-flex justify-content-between align-items-center py-3">
                <h2 class="h5 mb-0"><a href="#" class="text-muted">Task Details</a> #${task.id}</h2>
            </div>

            <!-- Main content -->
            <div class="row">
                <div class="col-lg-8">
                    <!-- Task Details -->
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5>${task.title}</h5>
                            <p>${task.description}</p>
                            <p><strong>Priority:</strong> ${task.priority}</p>
                            <p><strong>Status:</strong> ${task.status}</p>
                            <p><strong>Created on:</strong> ${task.creationDate}</p>
                            <p><strong>Deadline:</strong> ${task.deadline}</p>
                        </div>
                    </div>

                    <!-- Assigned Members -->
                    <div class="card mb-4">
                        <div class="card-body">
                            <h3 class="h6">Assigned Members</h3>
                            <table class="table table-borderless">
                                <thead>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Role</th>
                                    <th>Role</th>
                                    <th>Email</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="member" items="${task.assignedMembers}">
                                    <tr>
                                        <td>${member.firstName}</td>
                                        <td>${member.secondName}</td>
                                        <td>${member.userRole}</td>
                                        <td>${member.email}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h3 class="h6">Additional Information</h3>
                            <p>You can change the assigned members, <a style="color: dodgerblue; text-decoration: underline;" href="tasks?action=assign&id=${task.id}">go assign members</a> .</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="search-box">
                        <a href="tasks?action=list" class="btn btn-primary">Back Home.</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</layout:layout>
