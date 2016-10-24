
<%-- Business data page --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>

        <title>PattyCakes Order Management</title>

        <%-- meta tags --%>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%-- links for fonts, styles, bootstrap --%>

        <link href='https://fonts.googleapis.com/css?family=Allura'
              rel='stylesheet' type='text/css'>
        <link rel="stylesheet"
              href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/header.css">
        <link rel="stylesheet" type="text/css" href="css/business.css">
        <link rel="stylesheet" type="text/css" href="css/order-details.css">

        <%-- javascript links --%>

        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <%-- HTML5 Shim and Respond.js IE8 support of HTML5
        elements and media queries --%>

        <%-- WARNING: Respond.js doesn't work if you view the
        page via file:// 

        <script src="https://oss.maxcdn.com/libs/html5shiv/
                3.7.0/html5shiv.js"
        type="text/javascript"></script>
        <script
            src="https://raw.github.com/scottjehl/Respond/master/dest/respond.min.js"
        type="text/javascript"></script>
        --%>
        
        <%-- for google charts --%>

        <script type="text/javascript"
        src="https://www.gstatic.com/charts/loader.js"></script>

        <script src="js/charts.js" type="text/javascript"></script>
        <script src="js/search-bar.js" type="text/javascript"></script>
        <script src="js/tabs.js" type="text/javascript"></script>
        <script src="js/modal.js" type="text/javascript"></script>

    </head>
    <body>

        <%-- header --%>

        <jsp:include page="header.jsp" />

        <%-- Where the modal window will be created --%>

        <div class="modalContainer" id="modal">
            <div class="modalContent"></div>
        </div>

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
            <div class="expSearchBox">
                <div class="expSearchFrom">
                    <input id="field" type="text" placeholder="name, date or price" />
                    <div class="close">
                        <span class="front"></span>
                        <span class="back"></span>
                    </div>
                </div>
            </div>
            <table id="orderTable" align="center">
                <tr>
                    <th>Date</th>
                    <th>Name</th>
                    <th>Price</th>
                </tr>

                <%--displaying the data with JSTL --%>

                <c:forEach var="order" items="${ orders }">
                    <c:if test="${ order.year <= year }">
                        <c:if test="${ order.month <= month }">
                            <tr>
                                <td>${order.dueDate}</td>
                                <td>${ order.firstName } ${order.lastName} </td>
                                <td>${ order.price }</td>
                            </tr>
                        </c:if>
                    </c:if>
                </c:forEach>

            </table><input type="hidden" value="${priceTotal}" />
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