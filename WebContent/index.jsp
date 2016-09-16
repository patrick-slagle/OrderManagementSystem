
<%-- user login form --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="css/login.css">
<link href='http://fonts.googleapis.com/css?family=Oswald'
	rel='stylesheet' type='text/css'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>

<body>
	<div id="main" class="container">
		<form id="loginform" method=POST 
		class="wpl-track-me" action="validate.do">
			<p class="login-username">
				<input type="text" name="username" id="user_login" class="input"
					placeholder="Email Address" value="" size="20" />
			</p>
			<p class="login-password">
				<label for="user_pass">Password</label>
				<input type="password" name="password" id="user_pass" class="input"
					placeholder="Password" value="" size="20" />
			</p>
			<p class="login-submit">
				<input type="submit" name="submit" id="wp-submit"
					class="button-primary" value="Log in" />
			</p>
		</form>
		<a href="register.jsp"><h1 id="register">New User?</h1></a>
	</div>
</body>
</html>
