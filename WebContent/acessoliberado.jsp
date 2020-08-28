<jsp:useBean id="calculo" class="beans.BeansJsp" type="beans.BeansJsp" scope="page"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="resources/css/acessoliberado.css" type="text/css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
<div class="conteirner-acesso-liberado">
	<jsp:setProperty property="*" name="calculo"/>
	<h1>Seja Bem Vindo!!!</h1>

	<a href="usuarioCadastro?acao=listarCadastro">
		<img alt="Cadastrar Usuário" src="resources/imagens/Users-Add-User-icon.png">
	</a>
	</div>
</body>
</html>