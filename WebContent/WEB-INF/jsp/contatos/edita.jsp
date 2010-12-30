<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sistema de gerenciamento de contatos</title>
</head>
<body>
<a href="<c:url value='/contatos'/>">Voltar</a>
<form action="<c:url value='/contatos/${contato.id}'/>" method="post">
<fieldset>
	<legend>Editando Contato</legend>
	<label for="nome">Nome: </label>
	<input type="text" name="contato.nome" value="${contato.nome}" />
	<label for="nome">Email: </label>
	<input type="email" name="contato.email" value="${contato.email}" />
	<button type="submit" name="_method" value="PUT">Gravar</button>	
</fieldset>
</form>
</body>
</html>