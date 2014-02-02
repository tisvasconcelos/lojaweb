<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<form action="/contact" method="post">
	<input name="name" />
	<button type="submit">Enviar</button>
</form>	

<spring:message code="name.empty" />

<c:forEach items="${errors}" var="e">
	<c:out value="${e}"/> <br/>
	<c:out value="${e.field}"/> - <c:out value="${e.defaultMessage}"/> <br/>
</c:forEach>
</body>
</html>