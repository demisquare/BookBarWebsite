<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Book Bar</title>
<link rel="apple-touch-icon" sizes="57x57"
	href="icon/apple-icon-57x57.png" />
<link rel="apple-touch-icon" sizes="60x60"
	href="icon/apple-icon-60x60.png" />
<link rel="apple-touch-icon" sizes="72x72"
	href="icon/apple-icon-72x72.png" />
<link rel="apple-touch-icon" sizes="76x76"
	href="icon/apple-icon-76x76.png" />
<link rel="apple-touch-icon" sizes="114x114"
	href="icon/apple-icon-114x114.png" />
<link rel="apple-touch-icon" sizes="120x120"
	href="icon/apple-icon-120x120.png" />
<link rel="apple-touch-icon" sizes="144x144"
	href="icon/apple-icon-144x144.png" />
<link rel="apple-touch-icon" sizes="152x152"
	href="icon/apple-icon-152x152.png" />
<link rel="apple-touch-icon" sizes="180x180"
	href="icon/apple-icon-180x180.png" />
<link rel="icon" type="image/png" sizes="192x192"
	href="icon/android-icon-192x192.png" />
<link rel="icon" type="image/png" sizes="32x32"
	href="icon/favicon-32x32.png" />
<link rel="icon" type="image/png" sizes="96x96"
	href="icon/favicon-96x96.png" />
<link rel="icon" type="image/png" sizes="16x16"
	href="icon/favicon-16x16.png" />
<link rel="manifest" href="icon/manifest.json" />
<meta name="msapplication-TileColor" content="#ffffff" />
<meta name="msapplication-TileImage" content="icon/ms-icon-144x144.png" />
<meta name="theme-color" content="#ffffff" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-1/css/all.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" rel="no-referrer" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="http://localhost:8080/BookBarWebsite/"><img
				src="./img/coffee-cup.png" alt="BookBar Logo" class="logo"></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-sm-between" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link"
						href="${pageContext.request.contextPath}/">Home <span
							class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link" href="#">Menu</a></li>
				</ul>
				
				<ul class="navbar-nav">
<c:choose>
    <c:when test="${utente.getRole().equals(\"admin\")}">
        <%@ include file="admin_menu.jsp" %>
    </c:when>

</c:choose>

<c:choose>
    <c:when test="${not empty utente}">
		<li class="nav-item"><a class="nav-link" href="#">Ciao, ${utente.getFirstName()}</a></a></li>
    </c:when>
    <c:otherwise>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
    </c:otherwise>
</c:choose>

	</ul>
			</div>
		</div>
	</nav>

	<div class="container" id="main">