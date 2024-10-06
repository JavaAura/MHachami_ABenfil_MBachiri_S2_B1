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

<layout:layout title="Member List">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>

        button,
        input,
        select,
        textarea {
            font: inherit;
        }

        a {
            color: inherit;
        }

        /* End basic CSS override */

        * {
            scrollbar-width: 0;
        }

        *::-webkit-scrollbar {
            background-color: transparent;
            width: 12px;
        }

        *::-webkit-scrollbar-thumb {
            border-radius: 99px;
            background-color: #ddd;
            border: 4px solid #fff;
        }

      

        .button {
            padding: 12px 20px;
            border-radius: 8px;
            background-color: transparent;
            border: 0;
            font-weight: 600;
            cursor: pointer;
            transition: 0.15s ease;
        }

        .button.is-ghost:hover,
        .button.is-ghost:focus {
            background-color: #dfdad7;
        }

        .button.is-primary {
            background-color: #750550;
            color: #fff;
        }

        .button.is-primary:hover,
        .button.is-primary:focus {
            background-color: #4a0433;
        }

        .icon-button {
            padding: 0;
            border: 0;
            background-color: transparent;
            width: 40px;
            height: 40px;
            display: flex;
            align-items: center;
            justify-content: center;
            line-height: 1;
            cursor: pointer;
            border-radius: 8px;
            transition: 0.15s ease;
        }

        .icon-button svg {
            width: 24px;
            height: 24px;
        }

        .icon-button:hover,
        .icon-button:focus {
            background-color: #dfdad7;
        }

    </style>

    <div class="container">
        <div class="table-responsive">
           
            <div class="container">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h2>Member <b>List</b></h2>
                                </div>
                                <div class="col-sm-3 text-end">
                                    <a href="member?action=add" class="btn btn-info">Add new</a>
                                </div>
                                <div class="col-sm-3">
                                    <div class="search-box">
                                        <i class="material-icons">&#xE8B6;</i>
                                        <input type="text" class="form-control" placeholder="Search&hellip;">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>First Name <i class="fa fa-sort"></i></th>
                                    <th>Second Name</th>
                                    <th>Email</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${model.members}" var="member">
                                    <tr>
                                        <td>${member.id}</td>
                                        <td>${member.firstName}</td>
                                        <td>${member.secondName}</td>
                                        <td>${member.email}</td>
                                        <td>
                                            <a href="member?action=edit&id=${member.id}"class="edit" title="View" data-toggle="tooltip">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                                              </svg>
                                            </a>
                                            <button type="button" onclick="openDeleteModal(${member.id}, '${member.firstName}')"> 
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                                                <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47M8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5"/>
                                              </svg>
                                              </button>
                                              <a href="" class="delete" title="seemore" data-toggle="tooltip"><i class="material-icons"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
                                                <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8M1.173 8a13 13 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5s3.879 1.168 5.168 2.457A13 13 0 0 1 14.828 8q-.086.13-.195.288c-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5s-3.879-1.168-5.168-2.457A13 13 0 0 1 1.172 8z"/>
                                                <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5M4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0"/>
                                              </svg>
                                            </a>
                                        </td>
                                    </tr>

                              
    
                                
                                </c:forEach>
                            </tbody>
                        </table>
                        <c:if test="${empty model.members}">
							<p class="text-danger text-center">Opps, There is no data to show.</p>
						</c:if>
                        
                        
                        </div>
    
                      
                          
                    </div>
                </div>        
            </div>  
        </div>        
    </div>  
    <div class="modal">
        <article class="modal-container">
            <header class="modal-container-header">
                <h1 class="modal-container-title">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" aria-hidden="true">
                        <path fill="none" d="M0 0h24v24H0z" />
                        <path fill="currentColor" d="M14 9V4H5v16h6.056c.328.417.724.785 1.18 1.085l1.39.915H3.993A.993.993 0 0 1 3 21.008V2.992C3 2.455 3.449 2 4.002 2h10.995L21 8v1h-7zm-2 2h9v5.949c0 .99-.501 1.916-1.336 2.465L16.5 21.498l-3.164-2.084A2.953 2.953 0 0 1 12 16.95V11zm2 5.949c0 .316.162.614.436.795l2.064 1.36 2.064-1.36a.954.954 0 0 0 .436-.795V13h-5v3.949z" />
                    </svg>
                   
                </h1>
                <button class="icon-button">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                        <path fill="none" d="M0 0h24v24H0z" />
                        <path fill="currentColor" d="M12 10.586l4.95-4.95 1.414 1.414-4.95 4.95 4.95 4.95-1.414 1.414-4.95-4.95-4.95 4.95-1.414-1.414 4.95-4.95-4.95-4.95L7.05 5.636z" />
                    </svg>
                </button>
            </header>
            <section class="modal-container-body rtf">
    
            </section>
            <footer class="modal-container-footer">
                <button type="button" class="button is-ghost" data-bs-dismiss="modal">Close</button>
                <form id="deleteForm" action="" method="post">
                    <input type="hidden" id="">
                    <button class="button is-primary" type="submit">Confirm</button>
                </form>
            </footer>
        </article>
    </div>

    <script>
        function openDeleteModal(id,name){
            const modal = document.querySelector(".modal");
            modal.style.display = "block";
        
            const memberName = document.querySelector(".modal-container-title");
            memberName.innerHTML = " Delete member " +name;
        
            const deleteForm = document.getElementById("deleteForm");
            deleteForm.action = "member?action=delete&id="+id; 

        }
    </script>


</layout:layout>

