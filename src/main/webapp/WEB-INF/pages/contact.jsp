<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<form action="/lojaweb/contact" method="post">
	<input name="name" />
	<button type="submit">Enviar</button>
</form>	

<c:forEach items="${errors}" var="e">
	<c:out value="${e.field}"/> - <c:out value="${e.defaultMessage}"/> <br /><br />
</c:forEach>

</body>
</html>