<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Novo usuario</title>
<script type="text/javascript" src="<c:url value="/"/>/scripts/jquery-1.4.4.min.js"> </script>
<script type="text/javascript" src="<c:url value="/"/>/scripts/jquery.validate.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ $("#usuariossForm").validate(); });
</script>
</head>
<body>
<ul>
	<c:forEach items="${errors}" var="error">
		<li>${error.category} - ${error.message}</li>
	</c:forEach>
</ul>
<form id="usuariosForm" action="<c:url value="/usuarios/login"/>" method="POST">
<fieldset>
	<legend>Logar</legend>

	<label for="login">Login:</label>
	<input id="login" class="required" type="text" name="usuario.login" value="${usuario.login }"/>
	<label for="senha">Senha:</label>
	<input id="senha" class="required" type="password" name="usuario.senha"/>
	<button type="submit">Enviar</button>
</fieldset>
</form>
</body>
</html>