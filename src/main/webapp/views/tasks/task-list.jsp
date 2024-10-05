<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>

<layout:layout title="Tasks | List">
    <div class="container">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="d-flex justify-content-between">
                        <div class="">
                            <h2>Task <b>Details</b></h2>
                        </div>
                        <div class="">
                            <a href="tasks?action=add" class="btn btn-success">Create New Task</a>
                        </div>
                    </div>
                </div>

                <!-- Display message if no tasks are available -->
                <c:if test="${empty tasks}">
                    <p>No records of tasks available.</p>
                </c:if>

                <!-- Task Table -->
                <table class="table table-striped table-hover table-bordered">
                    <thead>
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
                    </thead>
                    <tbody>
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
                                <div class="d-flex justify-content-around">
                                    <a href="tasks?action=edit&id=${task.id}" class="edit" title="Edit"><i
                                            class="material-icons">&#xE254;</i></a>
                                    <form method="post" action="tasks?action=delete&id=${task.id}"
                                          style="display:inline;">
                                        <button type="submit" class="delete btn btn-danger" title="Delete"
                                                onclick="return confirm('Are you sure you want to delete this task?')">
                                            <i class="material-icons">&#xE872;</i>
                                        </button>
                                    </form>
                                    <a href="tasks?action=assign&id=${task.id}" class="assign text-light btn btn-primary">Assign</a>
                                    <a href="tasks?action=details&id=${task.id}" class="details text-light btn btn-secondary">Details</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <!-- Pagination -->
                <div class="clearfix">
                    <c:if test="${totalTasks > 0}">
                        <div class="hint-text">Showing <b>${tasks.size()}</b> out of <b>${totalTasks}</b> entries</div>
                    </c:if>
                    <ul class="pagination">
                        <!-- Previous Page Link -->
                        <li class="page-item <c:if test='${currentPage == 1}'>disabled</c:if>">
                            <a href="tasks?action=list&page=${currentPage - 1}" class="page-link"><i
                                    class="fa fa-angle-double-left"></i></a>
                        </li>

                        <!-- Page Numbers -->
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                                <a href="tasks?action=list&page=${i}" class="page-link">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Next Page Link -->
                        <li class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>">
                            <a href="tasks?action=list&page=${currentPage + 1}" class="page-link"><i
                                    class="fa fa-angle-double-right"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</layout:layout>
