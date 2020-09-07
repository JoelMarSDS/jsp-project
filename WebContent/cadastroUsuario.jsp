<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/cadastro.css" type="text/css" rel="stylesheet" />
<link href="resources/css/produto.css" type="text/css" rel="stylesheet" />
<title>Usuário</title>
</head>
<body>
	<center>
		<h2>Cadastro de Usuário</h2>
		<h3 style="color: orange;">${msg}</h3>
	</center>
	<div class="painel-cadastro-principal">
		<form class="form-cadastro" action="usuarioCadastro" method="post"
			id="formCadastro">
			<table class="painel-info">
				<tr>
					<td>Código:</td>
					<td><input type="text" id="id" name="id" value="${user.id}"></td>
				</tr>
				<tr>
					<td>Login:</td>
					<td><input type="text" id="login" name="login"
						value="${user.login}"></td>
				</tr>
				<tr>
					<td>Senha:</td>

					<td><input type="password" id="senha" name="senha"
						value="${user.senha}"></td>
				</tr>
				<tr>
					<td>Nome:</td>

					<td><input type="text" id="nome" name="nome"
						value="${user.nome}"></td>
				</tr>
				<tr>
					<td>Telefone:</td>

					<td><input type="text" id="telefone" name="telefone"
						value="${user.telefone}"></td>
				</tr>
			</table>
			<input type="submit" value="Salvar"> <input type="submit"
				value="Cancelar"
				onclick="document.getElementById('formCadastro').action = 'usuarioCadastro?acao=reset'">
		</form>
	</div>
	<div class="info-tabela-cadastro">
		<table class="tabela-cadastro-usuario">
			<tr>
				<th>Id</th>
				<th>Usuário</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Ação</th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 10%" align="center"><c:out
							value="${user.id}"></c:out></td>
					<td style="width: 25%"><c:out value="${user.login}"></c:out></td>
					<td style="width: 25%"><c:out value="${user.nome}"></c:out></td>
					<td style="width: 25%"><c:out value="${user.telefone}"></c:out></td>
					<td style="width: 15%" align="center"><a
						href="usuarioCadastro?acao=delete&user=${user.id}"><img
							src="resources/icones/delete-2.png" width="20px"></a> <a
						href="usuarioCadastro?acao=editar&user=${user.id}"><img
							src="resources/icones/edit-1.png" width="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>