<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta charset="ISO-8859-1" />
<title>SGC</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />
<link rel="shortcut icon" type="image/png" href="/gestaodecontratos/layout/images/logo pequeno.png" />
</head>

<nav class="navbar  navbar-fixed-top  navbar-default  js-sticky-reference" id="main-navbar">
	<div class="navbar-header">
		<a class="navbar-brand" href="/gestaodecontratos/sistema?logica=TelaPrincipal">
			<img align="left" height="20" src="/gestaodecontratos/layout/images/logo pequeno.png" />
			Sistema de Gestão de Contratos
		</a>
	</div>

	<div class="container-fluid">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="/gestaodecontratos/sistema?logica=TrocarCargoEmUso&i=1">${sessionScope.usuario.cargo.get(1).nome}</a></li>
			<li><a href="/gestaodecontratos/sistema?logica=TrocarCargoEmUso&i=0">${sessionScope.usuario.cargo.get(0).nome}</a></li>
			<li><a href="adds/ajuda.jsp">Ajuda</a></li>
			<li><a href="/gestaodecontratos/sistema?logica=Logout">Sair</a></li>
		</ul>
	</div>
</nav>
	<div class="page-header">
		<div class="container-fluid">
			<table>
				<tr>
					<th class="col-md-1" style="color: blue"><h3><i>Matrícula: ${sessionScope.usuario.matricula} </i></h3></th>
					<th class="text-center col-md-1" style="color: blue"><h3><i>${sessionScope.usuario.nome} </i></h3></th>
					<th class="text-center col-md-1" style="color: blue"><h3><i>${sessionScope.cargoParaLogin.nome}</i></h3></th>
					<th class="text-right col-md-1" style="color: blue"><h3><i>${sessionScope.usuario.setor.sigla } </i></h3></th>
				</tr>
			</table>
		</div>
	</div>
</html>
