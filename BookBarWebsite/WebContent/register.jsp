<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Register</title>
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
<meta name="theme-color" content="#ffffff">
<link rel="stylesheet" href="css/signin.css" rel="no-referrer" />
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>

<div class="container">
	<div class="card card-container">
		<a href="/BookBarWebsite/">
		<img id="profile-img" class="profile-img-card" src="./img/coffee-cup.png" /></a>
		<p id="profile-name" class="profile-name-card"></p>
		<form class="form-signin" method="POST" action="register">
			<span id="reauth-email" class="reauth-email"></span>
			<input type="text" name="firstname" id="inputFirstname" class="form-control" placeholder="Your First name" required autofocus>
			<input type="text" name="lastname" id="inputLastname" class="form-control" placeholder="Your Last name" required>
			<input type="email" name="email" id="inputEmail" class="form-control" placeholder="Your Email" required>
			<input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
			<input type="password" name="confirmpassword" id="inputConfirmPassword" class="form-control" placeholder="Confirm Password" required>
				
			<button class="btn btn-lg btn-primary btn-block btn-register"
				type="submit">Register here</button>
		</form>
		<a href="${pageContext.request.contextPath}/login" class = "forgot-password"> Already registered? Sign in. </a>
	</div>
	<!-- /card-container -->
</div>
<!-- /container -->