<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Document</title>
</head>
<body>
  <div class="container">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><h2>Add <b>New </b><b>Member</b></h2></div>
                    <div class="col-sm-4">
                       
                    </div>
                </div>
            </div>
            <form  method="post" action="member?action=create">
                <div class="mb-3">
                  <label  class="form-label">First Name</label>
                  <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"  name="first_name"  value="${ model.member.first_name}">
                </div>
                <div class="mb-3">
                    <label  class="form-label">Second Name</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="second_name"  value="${ model.member.second_name}">
                </div>
                <div class="mb-3">
                    <label  class="form-label">Email </label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email"  value="${ model.member.email}"> 
                </div>
                
                <div class="mb-3">
                  <label for="exampleInputPassword1" class="form-label">Role</label>
                  <select name="role" class="form-select" aria-label="Default select example">
                    <option selected disabled>Open this select menu</option>
                    <option value="ProjectManager">ProjectManager</option>
                    <option value="Developer">Developer</option>
                    <option value="Designer">Designer</option>
                  </select>
                </div>
                
                <button type="submit" class="btn btn-primary">Submit</button>
              </form>
            
        </div>
    </div>        
</div>     
</body>
</html>