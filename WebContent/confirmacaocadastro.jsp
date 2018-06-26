<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html 
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<title>Sistema de Gestão de Contratos da CTB</title>

<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

</head>

<body class="aw-layout-simple-page">
	<div class="aw-layout-simple-page__container">

		<div align="center">
			<img
				src="http://www.ctb.ba.gov.br/themes/admindireta/images/logo-ctb.png" />
		</div>
		<br />

		<p align="justify">
			Pronto, ${param.nome}, obrigado por se cadastrar no Sistema de Gestão de Contratos
			da CTB. Agora basta aguardar o administrador liberar o seu acesso
			para começar a usar. Te informaremos por email quando seu acesso for aprovado. 
			<br />
			<br />
			Clique <a href="login.html">aqui</a> para voltar para a página principal.
		</p>

	</div>
</body>