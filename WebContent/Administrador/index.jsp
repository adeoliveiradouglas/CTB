<!-- Página principal do Administrador -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />

<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />
<style>
	th.sticky-header {
	  position: sticky;
	  top: 5rem;
	  z-index: 10;
	  background-color: white;
	}
	btn-link {
		border: none;
		outline: none;
		background: none;
		cursor: pointer;
		color: #0000EE;
		padding: 0;
		text-decoration: underline;
		font-family: inherit;
		font-size: inherit;
	}
</style>
</head>
<body class="aw-layout-page">
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>

	<c:if test="${sessionScope.usuariosnovos.size() > 0}">
		<div style="background-color: #1e94d2; color: white" align="center">
			<h3>Novos usuários que precisam de autorização</h3>
		</div>
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th class="text-center">Nome</th>
					<th class="text-center">Matrícula</th>
					<th class="text-center">Cargo</th>
					<th class="text-center">Setor</th>
					<th class="text-center">Login</th>
					<th class="col-md-1"></th>
					<th class="col-md-1"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="usuario" items="${sessionScope.usuariosnovos}" varStatus="posicao">
					<tr>
						<%-- <td class="text-center"><%=u.getId() %></td> --%>
						<td class="text-center">${usuario.nome }</td>
						<td class="text-center">${usuario.matricula}</td>
						<td class="text-center">
							${usuario.cargo.get(0).nome } 
							${usuario.cargo.get(1).nome}
						</td>
						<td class="text-center">${usuario.setor.sigla }</td>
						<td class="text-center">${usuario.email }</td>
						<td class="text-center">
							<form action="sistema?logica=AutorizarNovoUsuario" method="post">
								<button class="btn-link" value="${posicao.index}" name="id">Autorizar</button>
							</form>
						</td>
						<td class="text-center">
							<form action="sistema?logica=RemoveUsuario&tabela=usuariosnovos"
								method="post">
								<button class="btn-link" value="${usuario.id}" name="id">Recusar</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<!-- Mostra opções de todos os usuários do sistema -->
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Todos os usuários</h3>
	</div>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center">Nome</th>
				<th class="text-center">Matrícula</th>
				<th class="text-center">Cargo</th>
				<th class="text-center">Setor</th>
				<th class="text-center">Login</th>
				<th class="col-md-1"></th><th class="col-md-1"></th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="usuario" items="${sessionScope.usuarios}" varStatus="posicao">
				<tr>
					<td class="text-center">${usuario.nome }</td>
					<td class="text-center">${usuario.matricula}</td>
					<td class="text-center">${usuario.cargo.get(0).nome} ${usuario.cargo.get(1).nome}</td>
					<td class="text-center">${usuario.setor.sigla }</td>
					<td class="text-center">${usuario.email }</td>
					<td class="text-center">
						<form action="sistema?logica=TelaEditarUsuario" method="post">
							<button class="btn-link" value="${posicao.index}" name="posicao">Editar</button>
						</form>
					</td>
					<td class="text-center">
						<form action="sistema?logica=RemoveUsuario&tabela=usuario"
							method="post">
							<button class="btn-link" value="${usuario.id}" name="id">Deletar</button>
						</form>
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
	</body>
</html>