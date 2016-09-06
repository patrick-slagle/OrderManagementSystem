<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link href='http://fonts.googleapis.com/css?family=Oswald'
	rel='stylesheet' type='text/css'>
</head>

<body>
	<div id="main" class="container">
		<form name="loginform" id="loginform" method=POST action="homepage.do"
			class="wpl-track-me">
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
				<input type="submit" name="wp-submit" id="wp-submit"
					class="button-primary" value="Log in" submit="process-login.jsp" />
				<input type="hidden" name="redirect_to" value="#" />
			</p>
		</form>
	</div>
</body>
</html>
