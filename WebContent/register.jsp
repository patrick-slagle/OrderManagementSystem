<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=UTF-8 />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href='http://fonts.googleapis.com/css?family=Oswald'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Allura'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="css/register.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/tabs.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5
elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the
page via file:// -->
<script src="https://oss.maxcdn.com/libs/html5shiv/
3.7.0/html5shiv.js"></script>
<script
	src="https://oss.maxcdn.com/libs/respond.js/
1.4.2/respond.min.js"></script>

<title>Register User</title>
</head>
<body>
	<%-- header --%>

	<jsp:include page="header.jsp" />

	<%-- main body --%>

	<div class="headerDiv">
		<h1>User Registration</h1>
	</div>

	<div class="contentDiv">

		<form class="orderForm" method=POST action="create-user.do">
			<input name="first-name" class="twoCenterInput" type="text"
				placeholder="First Name" />
			<input class="twoCenterInput" name="last-name" id="lastName"
				type="text" placeholder="Last Name" />
			<input class="centerInput" name="email" id="email" type="text"
				placeholder="Email" />
			<br>
			<input class="twoCenterInput" name="password" id="password"
				type="text" placeholder="Password" />
			<input class="twoCenterInput" name="re-enter-pw" id="re-enter" type="text"
				placeholder="Re-enter Password" />
			<br>
			<br>
			<input class="centerInput" id="passcode" type="text"
				placeholder="Enter Passcode" />
			<br>
			<input class="submit" type="submit" value="submit" />
		</form>

	</div>
</body>
</html>