<!-- Página principal do Gestor Geral -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />
</head>
<body class="aw-layout-page">
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>

	<div align="center">
		<a href="sistema?logica=TelaNovoContrato"> <font size="5">Novo
				contrato</font>
		</a>
	</div>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Contratos mais recentes</h3>
	</div>

	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-1">Número</th>
				<th class="text-center col-md-2">Empresa</th>
				<th class="text-center col-md-2">Gestor</th>
				<th class="text-center col-md-1">Valor</th>
				<th class="text-center col-md-1">Vencimento</th>
				<!-- <th class="text-center col-md-1">%</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${sessionScope.contratosRecentes}"
				varStatus="posicao">
				<tr>
					<td class="text-center"><a
						href="sistema?logica=VerContrato&n=${posicao.index}&origem=contratosRecentes">
							<!-- Envia o id da lista onde o contrato está (n) e diz que a origem da chamada é da página principal (origem)-->
							${c.numero}
					</a></td>
					<td class="text-center">${c.nomeEmpresaContratada}</td>
					<td class="text-center">${c.gestor.nome}</td>
					<td class="text-center">${c.valorTotalAsString}</td>
					<td class="text-center">${c.dataVencimentoContratoAsString}</td>
				</tr>
			</c:forEach>
			<!-- fim do if do for que mostra os contratos recentes -->
		</tbody>
	</table>

	<div align="center">
		<a href="sistema?logica=VerTodosContratos">Clique aqui para ver
			todos os contratos</a>
	</div>
	<br />
	<!-- fim do if da tabela -->

	<c:if test="${sessionScope.contratos90.size() > 0}">
		<div style="background-color: #1e94d2; color: white" align="center">
			<h3>Contratos com vencimento dentro de 90 dias</h3>
		</div>
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th class="text-center col-md-1">Número</th>
					<th class="text-center col-md-2">Empresa</th>
					<th class="text-center col-md-2">Gestor</th>
					<th class="text-center col-md-1">Valor</th>
					<th class="text-center col-md-1">Vencimento</th>
					<!-- <th class="text-center col-md-1">%</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${sessionScope.contratos90}" varStatus="posicao">
					<tr>
						<td class="text-center">
							<form action="sistema?logica=VerContrato" method="post">
								<button value="${c.numero}" name="numeroContrato">${c.numero}</button>
							</form>
						</td>
						<td class="text-center">${c.nomeEmpresaContratada}</td>
						<td class="text-center">${c.gestor.nome}</td>
						<td class="text-center">${c.valorTotal}</td>
						<td class="text-center">${c.dataVencimentoContratoAsString}</td>
					</tr>
				</c:forEach>

				<!-- fim do if do for que mostra os contratos com vencimentos -->
			</tbody>
		</table>
		<div align="center">
			<a href="sistema?logica=ContratosVencimento">Clique aqui para ver todos os contratos</a>
		</div>
	</c:if>

	<br />
	<!-- fim do if da tabela -->
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>