<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<!-- meta tags -->

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- links for fonts, styles, bootstrap -->

<link href='https://fonts.googleapis.com/css?family=Allura'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/business.css">

<!-- javascript links -->

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

<!-- for google charts -->

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('43', {
		packages : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);
	function drawChart() {

		var data = google.visualization.arrayToDataTable([
				[ 'Task', 'Hours per Day' ], [ 'Cakes', 11 ],
				[ 'Cupcakes', 2 ], [ 'Cookies', 2 ], [ 'Other', 2 ], ]);

		var options = {
			title : 'Product Sales',
			'legend' : 'left',
			'is3D' : true,
			backgroundColor : '#CFC3F8',

		};

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));

		chart.draw(data, options);

		$(window).resize(function() {
			drawChart();
		});

	}
</script>
<script>
	google.charts.setOnLoadCallback(drawBasic);

	function drawBasic() {

		var data = new google.visualization.DataTable();
		data.addColumn('number', 'X');
		data.addColumn('number', 'Sales');

		data.addRows([ [ 0, 0 ], [ 1, 10 ], [ 2, 23 ], [ 3, 17 ], [ 4, 18 ],
				[ 5, 9 ], [ 6, 11 ], [ 7, 27 ], [ 8, 33 ], [ 9, 40 ],
				[ 10, 32 ], [ 11, 35 ], [ 12, 30 ], [ 13, 40 ], [ 14, 42 ],
				[ 15, 47 ], [ 16, 44 ], [ 17, 48 ], [ 18, 52 ], [ 19, 54 ],
				[ 20, 42 ], [ 21, 55 ], [ 22, 56 ], [ 23, 57 ], [ 24, 60 ],
				[ 25, 50 ], [ 26, 52 ], [ 27, 51 ], [ 28, 49 ], [ 29, 53 ],
				[ 30, 55 ], [ 31, 60 ], [ 32, 61 ], [ 33, 59 ], [ 34, 62 ],
				[ 35, 65 ], [ 36, 62 ], [ 37, 58 ], [ 38, 55 ], [ 39, 61 ],
				[ 40, 64 ], [ 41, 65 ], [ 42, 63 ], [ 43, 66 ], [ 44, 67 ],
				[ 45, 69 ], [ 46, 69 ], [ 47, 70 ], [ 48, 72 ], [ 49, 68 ],
				[ 50, 66 ], [ 51, 65 ], [ 52, 67 ], [ 53, 70 ], [ 54, 71 ],
				[ 55, 72 ], [ 56, 73 ], [ 57, 75 ], [ 58, 70 ], [ 59, 68 ],
				[ 60, 64 ], [ 61, 60 ], [ 62, 65 ], [ 63, 67 ], [ 64, 68 ],
				[ 65, 69 ], [ 66, 70 ], [ 67, 72 ], [ 68, 75 ], [ 69, 80 ] ]);

		var options = {
			hAxis : {
				title : 'Time'
			},
			vAxis : {
				title : 'Popularity'
			},
			hAxis : {
				gridlines : {
					color : '#1E4D6B'
				}
			},
			vAxis : {
				gridlines : {
					color : '#1E4D6B'
				}
			},

			legend : {
				position : 'top'
			},
			backgroundColor : '#CFC3F8'
		};

		var chart = new google.visualization.LineChart(document
				.getElementById('chart_div'));

		chart.draw(data, options);

		$(window).resize(function() {
			drawBasic();
		});
	}
</script>
</head>
<body>

<%-- calling the servlet to get the list of orders --%>

<jsp:include page="/get-order"/>

	<%-- header --%>

	<jsp:include page="header.jsp" />

	<%-- main body --%>

	<div class="headerDiv">
		<h1>Sales Data</h1>
	</div>

	<%-- we use google charts for data visualization --%>
	
	<div class="charts">
		<div class="pieChartDiv">
			<div id="piechart"></div>
		</div>
		<div class="lineChartDiv">
			<div id="chart_div"></div>
		</div>
	</div>
	<div class="sales">
		<table id="dataTable" align="center">
			<tr>
				<th>Date</th>
				<th>Name</th>
				<th>Price</th>
			</tr>
			<c:forEach var="orders" items="${ orders }">
				<tr>
					<td>${ orders.dueDate }</td>
					<td>${ orders.firstName } ${ orders.lastName }</td>
					<td>${ orders.price }</td>
				</tr>
			</c:forEach>
		</table>
		<table id="total" align="center">
			<tr>
				<th>Total</th>
			</tr>
			<tr>
				<td>${ priceTotal }</td>
			</tr>
		</table>
	</div>
</body>
</html>