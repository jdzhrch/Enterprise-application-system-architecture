<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>JSTL fmt:bundle 标签</title>
</head>
<body>

<fmt:setLocale value="en" />
<fmt:setBundle basename="internationalize.SSS" var="lang" />

<fmt:message key="count.one" bundle="${lang}"/><br/>
<fmt:message key="count.two" bundle="${lang}"/><br/>
<fmt:message key="count.three" bundle="${lang}"/><br/>
<button onclick="show()">sdfs</button>
</body>
<script>function show(){
	<% request.setAttribute("lan2","en");%>
	history.go(0);
}
</script>
</html>