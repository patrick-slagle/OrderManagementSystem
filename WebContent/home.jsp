
<%-- The main home page --%>

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
        <link rel="stylesheet" type="text/css" href="css/header.css">
        <link rel="stylesheet" type="text/css" href="css/home.css">
        <link rel="stylesheet" type="text/css" href="css/order-details.css">
        <link href='https://fonts.googleapis.com/css?family=Allura'
              rel='stylesheet' type='text/css'>
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <script src="js/jquery-3.1.0.min.js"></script>
        <script src="js/modal.js"></script>
        <script src="js/tabs.js"></script>
        <script src="js/calendar.js" type="text/javascript"></script>


        <%-- HTML5 Shim and Respond.js IE8 support of HTML5
        elements and media queries --%>

        <%-- WARNING: Respond.js doesn't work if you view the
        page via file:// --%>

        <script src="https://oss.maxcdn.com/libs/html5shiv/
        3.7.0/html5shiv.js"></script>
        <script
            src="https://oss.maxcdn.com/libs/respond.js/
        1.4.2/respond.min.js"></script>
    </head>
    <body>

        <%-- calling the servlet to get the list of orders --%>

        <jsp:include page="/get-orders.do" />


        <%-- calling the servlet to populate the calendar --%>

        <jsp:include page="calendar-events.do" />

        <%-- header --%>

        <jsp:include page="header.jsp" />

        <%-- Where the modal window will be created --%>

        <div class="modalContainer" id="modal">
            <span class="closeModal">&times;</span>
            <div class="modalContent"></div>
        </div>

        <%-- main body --%>

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

                <%-- displaying the table using the previously set attributes and JSTL --%>

                <c:forEach var="order" items="${ orders }">
                    <c:if test="${ year == order.year && month == order.month && day <= order.day 
                                   || year == order.year && month <= order.month 
                                   || year < order.year }">
                          <tr>
                              <td class="name">${ order.firstName } ${ order.lastName }</td>
                              <td>${ order.dueDate }</td>
                              <td>${ order.product }</td>
                              <td>${ order.comments }</td>
                          </tr>
                    </c:if>
                </c:forEach>

            </table>

            <%-- calendar view with Google Calendars --%>

            <div id="calendar" class="tabcontent">
                <iframe src="https://calendar.google.com/calendar/embed?src=pslagle12%40gmail.com&ctz=America/New_York" style="border: 0" width="800" height="600" frameborder="0" scrolling="no"></iframe>
            </div>
        </div>

    </body>
</html>