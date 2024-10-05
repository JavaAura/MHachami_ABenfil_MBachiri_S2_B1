<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:layout title="Tasks | List">
    <c:if test="${empty tasks}">
        <p>NO RECORDS OF TASKS.</p>
    </c:if>
    <div class="container">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-8">
                            <h2>Task <b>Details</b></h2>
                        </div>
                        <div class="col-sm-4">
                            <div class="search-box">
                                <a href="tasks?action=add" class="btn btn-success">Create New Task</a>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title <i class="fa fa-sort"></i></th>
                        <th>Description</th>
                        <th>Priority <i class="fa fa-sort"></i></th>
                        <th>Status <i class="fa fa-sort"></i></th>
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
                                <a href="tasks?action=edit&id=${task.id}" class="edit" title="Edit" data-toggle="tooltip">
                                    <i class="material-icons">&#xE254;</i>
                                </a>
                                <form method="post" action="tasks?action=delete&id=${task.id}" style="display:inline;">
                                    <button type="submit" class="delete" title="Delete" data-toggle="tooltip"
                                            onclick="return confirm('Are you sure you want to delete this task?')">
                                        <i class="material-icons">&#xE872;</i>
                                    </button>
                                </form>
                                <a href="tasks?action=assign&id=${task.id}">Assign</a>
                                <a href="tasks?action=details&id=${task.id}">Details</a>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix">
                    <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="#"><i class="fa fa-angle-double-left"></i></a></li>
                        <li class="page-item"><a href="#" class="page-link">1</a></li>
                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                        <li class="page-item active"><a href="#" class="page-link">3</a></li>
                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                        <li class="page-item"><a href="#" class="page-link"><i class="fa fa-angle-double-right"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</layout:layout>


