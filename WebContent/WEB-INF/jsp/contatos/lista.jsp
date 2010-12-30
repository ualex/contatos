<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sistema de gerenciamento de contatos</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/styles/main.css" />" />
<script type="text/javascript" src="<c:url value="/scripts/jquery-1.4.4.min.js"/>"> </script>
<script type="text/javascript" src="<c:url value="/scripts/jquery.autocomplete.min.js"/>"> </script>
<script type="text/javascript">
$(document).ready(function(){ 
	$("#busca").autocomplete('<c:url value="/contatos/busca.json" />',
			 {
				dataType: "json", // pra falar que vamos tratar um json
				parse: function(contatos) { 
				// para tratar o json
				// a função map vai iterar por toda a lista,
				// e transformar os dados usando a função passada
					return $.map(contatos, function(contato) {
						return {
						data: contato, // todos os dados do produto
				value: contato.nome, // o valor lógico do produto
				result: contato.nome // o que vai aparecer ao selecionar
				};
				});
				},
				formatItem: function(contato) { // o que vai aparecer na lista de autocomplete
				return contato.nome + "(" + contato.email + ")";
				}
				});
});
</script>
<style type="text/css">
	.link {
		text-decoration: underline;
		border: none;
		background: none;
		color:blue;
		cursor: pointer;
	}
</style>
</head>
<body>
<c:if test="${!usuarioWeb.logado}"> Você não está logado. <a href="<c:url value="/usuarios/login"/>">Logar</a>|<a href="<c:url value="/usuarios/novo"/>">Cadastre-se</a></c:if>
<c:if test="${usuarioWeb.logado}"> OLA ${usuarioWeb.nome}. <a href="<c:url value="/usuarios/logout"/>">Sair</a> </c:if>
<c:if test="${usuarioWeb.logado}"> <a href="<c:url value="/contatos/novo"/>">Novo Contato</a></c:if>
<form action="<c:url value="/contatos/busca"/>" ><input id="busca" name="nome" /><input type="submit" value="buscar" /></form>
	<table>
		<thead>
			<tr>
				<th>Nome</th>				
				<th>Email</th>
				<th>Editar</th>
				<th>Remover</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${contatoList}" var="contato">
				<tr>
					<td>${contato.nome}</td>
					<td>${contato.email}</td>
					<td><a href="<c:url value="/contatos/${contato.id}" />">Alterar</a></td>
					<td><form action="<c:url value="/contatos/${contato.id}"/>" method="POST">
						<button class="link" name="_method" value="DELETE">Remover</button>
					</form></td>
				</tr>
			</c:forEach>
		</tbody>		
	</table>
</body>
</html>