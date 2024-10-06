<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<layout:layout title="Project List">
	<style>
		.delete-btn {
		    background-color: transparent;
		    border: none;
		    color: red;
		    display: inline-block; /* Ensure inline-block for button */
		    padding: 0;
		    margin: 0;
		    cursor: pointer;
		    font-size: 24px; /* Adjust size if necessary */
		}
		
		.delete-btn i {
		    vertical-align: middle; /* Align the icon with other inline elements */
		}
		
		.view, .edit, .delete-form {
		    display: inline-block;
		    margin-right: 10px; /* Adjust margin between icons */
		}

	</style>

    <div class="container">
    					<c:if test="${not empty errorMessage}">
						    <div id="alert" class="alert alert-danger" style="color: red; margin-top: 15px;" role="alert">
							  ${ errorMessage }
							</div>
    					</c:if>
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
					    <div class="col-sm-6">
					        <h2><a href="${pageContext.request.contextPath}/project/">Project</a> <b>List</b></h2>
					    </div>
					    
					    <div class="col-sm-3">
					        <div class="search-box">
					            <form action="${pageContext.request.contextPath}/project/search">
					                <i class="material-icons">&#xE8B6;</i>
					                <c:choose>
					                    <c:when test="${ title != null }">
					                        <input type="text" value="${ title }" class="form-control" placeholder="Search&hellip;" name="q">
					                    </c:when>
					                    <c:otherwise>
					                        <input type="text" class="form-control" placeholder="Search&hellip;" name="q">
					                    </c:otherwise>
					                </c:choose>
					            </form>
					        </div>
					    </div>
					    <div class="col-sm-3">
					        <a href="${pageContext.request.contextPath}/project/create" class="btn btn-success btn-block"
					           style="background-color: #28a745; border: none; color: white; padding: 5px 1px; border-radius: 5px; text-align: center; text-decoration: none;">
					            Create New Project
					        </a>
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
								
								<form action="${pageContext.request.contextPath}/project/delete" method="post" class="d-inline-block delete-form">   
	                                <a href="${pageContext.request.contextPath}/project/show?project_id=${project.id}" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
									<a href="${pageContext.request.contextPath}/project/edit?project_id=${project.id}" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
								    <input type="hidden" name="project_id" value="${project.id}"/>                             
								    <button title="Delete" class="delete-btn" data-toggle="tooltip">
								        <i class="material-icons">&#xE872;</i>
								    </button>
								</form>


                            </td>
                        </tr>
               	</c:forEach>
                    </tbody>
                </table>
                <c:if test="${empty projects}">
							<p class="text-danger text-center">Opps, There is no data to show.</p>
						</c:if>
                <div class="clearfix">
				    <div class="hint-text">Showing <b>${projects.size()}</b> out of <b>${totalProjects}</b> entries</div>
				    <ul class="pagination">
				        <c:if test="${page > 1}">
				            <li class="page-item">
				                <a href="${pageContext.request.contextPath}/project?page=${page - 1}" class="page-link">
				                    <i class="fa fa-angle-double-left"></i>
				                </a>
				            </li>
				        </c:if>
				        
				        <c:forEach begin="1" end="${pageNumbers}" var="pageNum">
				            <li class="page-item ${pageNum == page ? 'active' : ''}">
				                <a href="${pageContext.request.contextPath}/project?page=${pageNum}" class="page-link">${pageNum}</a>
				            </li>
				        </c:forEach>
				        
				        <c:if test="${page < pageNumbers}">
				            <li class="page-item">
				                <a href="${pageContext.request.contextPath}/project?page=${page + 1}" class="page-link">
				                    <i class="fa fa-angle-double-right"></i>
				                </a>
				            </li>
				        </c:if>
				    </ul>
				</div>
                              
            </div>
        </div>        
    </div>   
</layout:layout>