<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="zh">
<head>
<title>JSTL fmt:bundle 标签</title>
</head>
<body>

<fmt:setLocale value="${lan2 }" />
<fmt:setBundle basename="internationalize.HomepageResourceBundle" var="lang" />

<fmt:message key="Home" bundle="${lang}"/><br/>
<fmt:message key="Books" bundle="${lang}"/><br/>
<fmt:message key="Chatroom" bundle="${lang}"/><br/>
<button onclick="show()">sdfs</button>
</body>
<script>function show(){
	<% session.setAttribute("lan2","fr");%>
	history.go(0);
}
</script>
</html>