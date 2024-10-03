<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<layout:layout title="Project Details">
	<c:if test="${empty projects}">
		<p>The list of arrays is empty.</p>
	</c:if>
	
    <div class="container">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-8"><h2>Project	 <b>Details</b></h2></div>
                        <div class="col-sm-4">
	                        <div class="col-sm-4"><a href="/teamsync/project/create">Create new project</a></div>
                            <div class="search-box">
                            <form action="/teamsync/project/search" >
                            <button class="border-0">
                                <i class="material-icons">&#xE8B6;</i>
                            </button>                            
                                <input type="text" class="form-control" placeholder="Search&hellip;" name="q">
                            </form>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Name <i class="fa fa-sort"></i></th>
                            <th>Description</th>
                            <th>Start Date<i class="fa fa-sort"></i></th>
                            <th>End Date</th>
                            <th>Status <i class="fa fa-sort"></i></th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="project" items="${projects}">
                        <tr>
                            <td>${project.id}</td>
                            <td>${project.name}</td>
                            <td>${project.description}</td>
                            <td>${project.startDate}</td>
                            <td>${project.endDate}</td>
                            <td>${project.status}</td>
                            <td>
                                <a href="/teamsync/project/show?project_id=${project.id }" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
                                <a href="/teamsync/project/edit?project_id=${project.id }" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                <form action="/teamsync/project/delete" method="post">   
                                <input type="hidden" name="project_id" value="${project.id }"/>                             
                                <button title="Delete" class="border-0 d-inline" data-toggle="tooltip"><i class="material-icons" style="cursor: pointer; display:inline;">&#xE872;</i></button>
                                </form>
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