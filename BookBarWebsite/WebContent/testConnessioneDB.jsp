<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Document</title>
</head>
<body>
	<h1>
		Test Connessione al DataBase
	</h1>
	<h3>Utenti presenti: ${utentiPresenti}</h3>
	
	<c:forEach items="${utenti}" var="utente">			
	<p>
	${utente.username} ${utente.password}
	</p>
	</c:forEach>

	<input type="text" name="username" id="username"><br/>
	<input type="password" name="password" id="password">
	<button type="submit">Entra</button>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("button[type='submit']").click((e)=>{
			let dati={
					username: '',
					password: ''
			}
			dati.username = $("#username")[0].value;
			dati.password = $("#password")[0].value;
		});
	</script>
</body>
</html>