
<%-- user login form --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- we use the core library as well as the LoginManager class --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="lm" uri="/WEB-INF/login.tld"%>

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
		<form id="loginform" method=POST class="wpl-track-me" action="j_security_check">
			<p class="login-username">
				<input type="text" name="j_username" id="user_login" class="input"
					placeholder="Email Address" value="" size="20" />
			</p>
			<p class="login-password">
				<label for="user_pass">Password</label>
				<input type="password" name="j_password" id="user_pass" class="input"
					placeholder="Password" value="" size="20" />
			</p>
			<p class="login-submit">
				<input type="submit" name="submit" id="wp-submit"
					class="button-primary" value="Log in" />
			</p>
		</form>
	</div>
</body>
</html>
