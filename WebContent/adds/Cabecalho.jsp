<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<table>
			<tr>
				<td>
					<a href="sistema?logica=VoltarPagina"><img src="/gestaodecontratos/layout/images/voltar.png"></a>
				</td>
				<td>
					<a class="navbar-brand" href="/gestaodecontratos/sistema?logica=TelaPrincipala">
						<img align="left" height="20" src="/gestaodecontratos/layout/images/logo pequeno.png" />
						Sistema de Gestão de Contratos
					</a>
				</td>
			</tr>
		</table>		
	</div>

	<div class="container-fluid">
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${sessionScope.usuario.cargo.get(0).id != sessionScope.usuario.cargo.get(1).id}">
				<li><a href="/gestaodecontratos/sistema?logica=TrocarCargoEmUso&i=0">${sessionScope.usuario.cargo.get(0).nome}</a></li>
				<li><a href="/gestaodecontratos/sistema?logica=TrocarCargoEmUso&i=1">${sessionScope.usuario.cargo.get(1).nome}</a></li>
			</c:if>
			<!-- <li><a href="adds/ajuda.jsp">Ajuda</a></li> -->
			<li><a href="/gestaodecontratos/sistema?logica=Logout"><img height="20" src="/gestaodecontratos/layout/images/logout.png"></a></li>
		</ul>
	</div>
</nav>
<div class="page-header">
	<div class="container-fluid">
		<table>
			<tr>
				<th class="col-md-1" style="color: #1E94D2"><h3><i>Matrícula: ${sessionScope.usuario.matricula} </i></h3></th>
				<th class="text-center col-md-1" style="color: #1E94D2"><h3><i>${sessionScope.usuario.nome} </i></h3></th>
				<th class="text-center col-md-1" style="color: #1E94D2"><h3><i>${sessionScope.cargoParaLogin.nome}</i></h3></th>
				<th class="text-right col-md-1" style="color: #1E94D2"><h3><i>${sessionScope.usuario.setor.sigla } </i></h3></th>
			</tr>
		</table>
	</div>
</div>
</html>
