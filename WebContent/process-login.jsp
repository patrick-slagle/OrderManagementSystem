<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- set DatabaseManager attribute --%>

<%
	com.patrickslagle.model.DatabaseManager dbm = new DatabaseManager();
	pageContext.setAttribute("dbm", dbm, PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8" />
</head>
<body>

	<%-- process the username and password --%>

	<c:if test="${dbm.validLogin(username, password)}">
		<c:include page="home.jsp" />
	</c:if>
	<c:include page="redirect.jsp" />

</body>
</html>