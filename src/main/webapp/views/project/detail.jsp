<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<layout:layout title="Project Detail">



<div id="main-content" class="blog-page">
        <div class="container">
            <div class="row clearfix">
                <div class="col-lg-8 col-md-12 left-box">
                    <div class="card single_post">
                        <div class="body">
                            <h3>${ project.name }</h3>
                            <p>${ project.description }</p>
                        </div>                                               
                    </div>
                </div>
            </div>

        </div>
    </div>

</layout:layout>