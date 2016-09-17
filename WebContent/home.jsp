<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<link href='https://fonts.googleapis.com/css?family=Allura'
	rel='stylesheet' type='text/css'>
<script src="js/OrdersWindow.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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

</head>
<body>

	<%-- calling the servlet to get the list of orders --%>

	<jsp:include page="/get-order" />

	<%-- The modal window displayed when an order entry is selected --%>

<div class="modalContainer" id="modal">
<div class="modalContent">

<table>

<tr>
<th>Name</th>
<th>Phone</th>
<th>Email</th>
<th>Due Date</th>
<th>Product</th>
<th>Price</th>
<th>Comments</th>
</tr>

<c:forEach var="orders" items="${ orders }">
<tr>
<td>${ orders.firstName } ${ orders.lastName }</td>
<td>${ orders.phoneNumber }</td>
<td>${ orders.email }</td>
<td>${ orders.dueDate }</td>
<td>${ orders.product }</td>
<td>${ orders.price }</td>
<td>${ orders.comments }</td>
</tr>
</c:forEach>

</table>
</div>
</div>
	<div class="headerContainer">
		<div class="navbar navbar-default padding">
			<div class="header">
				<div class="navbar-header padding">
					<a href="" class="allura logo">PattyCakes & Cookies</a>
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle Navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse tabDiv">
					<ul class="nav navbar-nav">
						<li class="headerTabs tabWidth">
							<a href="home.jsp">Home</a>
						</li>
						<li class="headerTabs tabWidth">
							<a href="business.jsp">Business</a>
						</li>
						<li class="headerTabs tabWidth">
							<a href="order-form.jsp">New Order</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div id="bodyContainer">
		<h1 id="mainHeader" class="allura tableHeader">Current Orders</h1>
		<ul class="tab">
			<li>
				<a href="#" class="tablinks" onclick="openTab(event, 'orderTable')">Table</a>
			</li>
			<li>
				<a href="#" class="tablinks" onclick="openTab(event, 'calendar')">Calendar</a>
			</li>
		</ul>
		<table id="orderTable" class="tabcontent">
			<tr class="tableHeaders">
				<th class="headerItems">Name</th>
				<th class="headerItems">Date</th>
				<th class="headerItems">Item(s)</th>
				<th class="headerItems">Comments</th>
			</tr>

			<c:forEach var="orders" items="${ orders }">
				<tr>
					<td class="name">
						<a id="customerName" href="#">${ orders.firstName } ${ orders.lastName }</a>
					</td>
					<td>${ orders.dueDate }</td>
					<td>${ orders.product }</td>
					<td>${ orders.comments }</td>
				</tr>
			</c:forEach>

		</table>

		<div id="calendar" class="tabcontent">
			<iframe
				src="https://calendar.google.com/calendar/embed?src=pslagle2012%40gmail.com&ctz=America/New_York"
				style="border: 0" frameborder="0" scrolling="no"></iframe>
		</div>
	</div>

	<script>
		var modal = document.getElementById('modal');
		var order = document.getElementById('customerName');

		order.onclick = function() {
			modal.style.display = 'table';
		}

		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = 'none';
			}
		}
	</script>

</body>
</html>