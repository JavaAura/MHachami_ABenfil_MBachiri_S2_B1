<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<layout:layout title="Project Detail">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h2 class="text-center">${project.name}</h2>
                        <p class="text-center">${project.description}</p>
                        <div class="row">
                            <div class="col-sm-6">
                                <h4 class="text-center">Project Timeline</h4>
                                <ul class="list-unstyled text-center">
                                    <li><strong>Start Date:</strong> ${project.startDate}</li>
                                    <li><strong>End Date:</strong> ${project.endDate}</li>
                                    <li><strong>Status:</strong> <span class="label label-${statusUtil.getBadgeColor(project.status)}">${project.status}</span></li>
                                </ul>
                            </div>
                            <div class="col-sm-6">
                                <h4 class="text-center">Project Statistics</h4>
                                <ul class="list-unstyled text-center">
                                    <li><strong>Members:</strong> ${memberCount}</li>
                                    <li><strong>Tasks:</strong> ${taskCount}</li>
                                    <li><strong>Project ID:</strong> ${project.id}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-body">
                        <h4 class="text-center">Project Actions</h4>
                        <div class="text-center">
                            <a href="<c:url value='/project/edit?project_id=${project.id}'/>" class="btn btn-primary">Edit Project</a>
                            <a href="<c:url value='/project/${project.id}/members'/>" class="btn btn-default">Manage Members</a>
                            <a href="<c:url value='/project'/>" class="btn btn-info">View Projects</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</layout:layout>