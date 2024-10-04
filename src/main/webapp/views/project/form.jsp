<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:layout title="Project Form">

	<div>
		<c:if test="${not empty errorMessage}">
	        <div style="color: red; margin-bottom: 15px;">
	            Error: ${errorMessage}
	        </div>
    	</c:if>
		<form method="post" action="${ isUpdate ? 'update' : 'store' }">
		  <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="inputEmail4">Project Name</label>
		      <input type="text" name="name" value="${isUpdate ? project.name : ''}" class="form-control" id="inputEmail4" placeholder="Project Name">
		    </div>
		    <div class="form-group col-md-6">
		      <label for="inputPassword4">Description</label>
		      <input type="text" name="description" value="${isUpdate ? project.description : ''}"  class="form-control" id="inputPassword4" placeholder="Description">
		    </div>
		  </div>
		 
		  <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="inputCity">Start Date</label>
		      <input type="date" class="form-control" name="start_date" value="${isUpdate ? project.startDate : ''}"  id="inputCity">
		    </div>
		    
		    <div class="form-group col-md-6">
		      <label for="inputCity">End Date</label>
		      <input type="date" name="end_date" class="form-control" value="${isUpdate ? project.endDate : ''}"  id="inputCity">
		    </div>
		  </div>
		  <c:if test="${isUpdate}">
			<input type="hidden" value="${ project.id }" name="project_id"/>
		    <div class="form-row">
		        <div class="form-group col-md-6">
		            <select class="form-select" aria-label="Default select example" name="status">
		                <option value="" disabled ${project.status == '' ? 'selected' : ''}>Select Status</option>
		                <option value="InPreparation" ${project.status == 'InPreparation' ? 'selected' : ''}>In Preparation</option>
		                <option value="Paused" ${project.status == 'Paused' ? 'selected' : ''}>Paused</option>
		                <option value="Completed" ${project.status == 'Completed' ? 'selected' : ''}>Completed</option>
		                <option value="Canceled" ${project.status == 'Canceled' ? 'selected' : ''}>Canceled</option>
		            </select>
		        </div>
		    </div>
		</c:if>

		  <button type="submit" class="btn btn-primary">Create New Project</button>
		</form>
	</div>
</layout:layout>