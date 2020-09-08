<jsp:useBean id="calculo" class="beans.BeansJsp" type="beans.BeansJsp"
	scope="page" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="resources/css/acessoliberado.css" type="text/css"
	rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
	<div class="conteirner-acesso-liberado">
		<jsp:setProperty property="*" name="calculo" />
		<table>
			<tr>
				<th colspan="2">
					<h1>Seja Bem Vindo!!!</h1>
				</th>
			</tr>
			<tr>
				<td >
					<div class="usuario-div">
						<a href="usuarioCadastro?acao=listarCadastro"> <img
							alt="Cadastrar Usuário"
							src="resources/imagens/Users-Add-User-icon.png">
						</a>
					</div>
				</td>
				<td>
					<div class="produto-div">
						<a href="produtoCadastro?acao=listarProduto"><img
							alt="Produtos" src="resources/imagens/produtos.png"> </a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>