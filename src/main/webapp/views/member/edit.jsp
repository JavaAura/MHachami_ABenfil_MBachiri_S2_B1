<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@page import="model.MemberModel"%> 
<%
  MemberModel model=new MemberModel();
  if(request.getAttribute("model") != null){
	 model=(MemberModel)request.getAttribute("model");
  }
  
%>


<layout:layout title="Edit Member">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<div class="container">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><h2>Edit <b>Member </b><b>name</b></h2></div>
                    <div class="col-sm-4">
                       
                    </div>
                </div>
            </div>
          
        <% 
            if (model != null && model.getSuccess() != null) { 
        %>
            <div class="alert alert-success">
                <%= model.getSuccess() %>
            </div>
        <% 
            } 
        %>
            <form method="post" action="member?action=update&id=${model.member.id}">
                <input type="hidden" class="form-control" name="id" value="${model.member.id}">

                <div class="mb-3">
                    <label class="form-label">First Name</label>
                    <input type="text" class="form-control" name="first_name" value="${model.member.firstName}">
                </div>
                <div class="mb-3">
                    <label class="form-label">Second Name</label>
                    <input type="text" class="form-control" name="second_name" value="${model.member.secondName}">
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" value="${model.member.email}">
                </div>
                
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            
            
        </div>
    </div>        
</div>   
</layout:layout>
