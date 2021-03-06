<!-- 
	Parte 1 da recupera��o de senha 
	Inserir email
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:th="http://www.thymeleaf.org"
	  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta charset="ISO-8859-1">
<title>SGC - Recuperar senha</title>
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />
</head>

<body class="aw-layout-simple-page">
	<div class="aw-layout-simple-page__container">
		<div align="center">
			<a href="sistema?logica=TelaLogin">
				<img src="/gestaodecontratos/layout/images/logo.png" />
			</a>			
		</div>
		<br />
		<form action="sistema?logica=GerarToken" method="POST"> 
			<div class="aw-simple-panel">
				<div class="aw-simple-panel__box">
					Por favor, informe seu email de acesso:
					<div class="form-group  has-feedback">
						<input type="email" class="form-control  input-lg" placeholder="Seu e-mail" name="email" required/> 
						<span class="glyphicon  glyphicon-envelope  form-control-feedback" aria-hidden="true"></span>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Enviar c�digo</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>