<jsp:useBean id="calculo" class="beans.BeansJsp" type="beans.BeansJsp"
	scope="page" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="resources/css/login.css" type="text/css" rel="stylesheet"/>
<title>Insert title here</title>
</head>
	<body>

		<div class="painel-principal">
			<div class="painel-login">
				<form action="loginServelet" class="font-class">
						<label>Login </label>
						<input type="text" id="login" name="login">
					<br />
						<label>Senha </label>
						<input type="password" id="senha"	name="senha">
					<br />
						<button type="submit" value="Logar">Logar</button>
				</form>
			</div>
		</div>
	</body>
</html>