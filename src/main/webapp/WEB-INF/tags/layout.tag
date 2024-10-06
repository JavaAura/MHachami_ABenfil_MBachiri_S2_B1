<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="title" required="true" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css"> <!-- Link to your CSS -->
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@coreui/coreui@5.1.2/dist/js/coreui.min.js" integrity="sha384-kiD3MgQ2eSqSjSfkoKS7/ipCvMvkfmpWHk3WRppeqnYxCVF0wQ+7gHzkXfJyvHbQ" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css'>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js'></script>
	<script>
	$(document).ready(function(){
		$('[data-toggle="tooltip"]').tooltip();
	});
    <link >
</script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <nav>
        <a href="${pageContext.request.contextPath}/project">Home</a>
        <a class="btn btn-light" href="${pageContext.request.contextPath}/tasks">Tasks</a>
        <a href="${pageContext.request.contextPath}/member">Members</a>
        <a href="${pageContext.request.contextPath}/project">Projects</a>
    </nav>
</header>

<main>
    <jsp:doBody />
</main>

<footer>
    <p>&copy; 2024 My Website</p>
</footer>


    <script src="${pageContext.request.contextPath}/script/alertHandler.js"></script>
</body>
</html>