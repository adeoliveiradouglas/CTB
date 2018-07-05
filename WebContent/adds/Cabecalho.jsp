<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta charset="ISO-8859-1" />
<title>SGC</title>
<link rel="stylesheet" type="text/css" href="/layout/stylesheets/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="/layout/stylesheets/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="/layout/stylesheets/application.css" />
</head>

<nav class="navbar  navbar-fixed-top  navbar-default  js-sticky-reference" id="main-navbar">
	<div class="navbar-header">
		<a class="navbar-brand" href="sistema?logica=TelaPrincipal"> <!--   	 <img src="http://www.ctb.ba.gov.br/themes/admindireta/images/logo-ctb.png"/>  -->
			Sistema de Gestão de Contratos
		</a>
	</div>

	<div class="container-fluid">
		

		<ul class="nav navbar-nav navbar-right">
			<li><a href="sistema?logica=Logout"> Sair</a></li>
		</ul>

	</div>
</nav>
<%@ page import="entity.Usuario"%>
 <% 
 	Usuario u = (Usuario) session.getAttribute("usuario"); 
 %>
	<div class="page-header">
		<div class="container-fluid">
			<h3>
				Seja bem vindo, <i><span
					style="color: blue"><%= u.getNome()%></span></i>
			</h3>
			
		</div>
	</div>
</html>
