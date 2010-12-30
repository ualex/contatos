<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sistema de gerenciamento de contatos</title>

<script type="text/javascript" src="<c:url value="/"/>/scripts/jquery-1.4.4.min.js"> </script>
<script type="text/javascript" src="<c:url value="/"/>/scripts/jquery.validate.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ $("#contatosForm").validate(); });
</script>		
</head>
<body>
<a href="<c:url value='/contatos'/>">Voltar</a>
<ul>
	<c:forEach items="${errors}" var="error">
		<li>${error.category} - ${error.message}</li>
	</c:forEach>
</ul>
<form id="contatosForm" action="<c:url value="/contatos"/>" method="POST">
<fieldset>
	<legend>Adicionar Contato</legend>
	<label for="nome">Nome: </label>
	<input type="text" name="contato.nome" value="${contato.nome}" class="required" />
	<label for="nome">Email: </label>
	<input type="email" name="contato.email" value="${contato.email}" class="required"  />
	<button type="submit">Gravar</button>	
</fieldset>
</form>
</body>
</html>