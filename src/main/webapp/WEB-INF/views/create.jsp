<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytags" uri="http://productInformation.com/jsp/tlds/mytags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>

<sf:form method="POST" commandName="product">
	<sf:errors path="description" /><br/>
</sf:form>
<%--<div class="container">
	<h1>This is secured!</h1>
	<p>
		Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
	</p>
	<c:url var="logoutUrl" value="/logout"/>
	<form class="form-inline" action="${logoutUrl}" method="post">
		<input type="submit" value="Log out" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</div>--%>
<form action="/productInformation/saveOrUpdate" method="post" modelAttribute="users">
	<input type="text" name="username"/><br/>
	<input type="password" name="password"/><br/>
	<input type="text" name="email"/><br/>
	<input type="submit" value="register"/>
</form>
</body>
</html>
