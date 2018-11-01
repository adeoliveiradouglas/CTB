<!-- Página principal do Gestor -->
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
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Contratos sob minha gestão</h3>
	</div>

	<c:choose>
		<c:when test="${sessionScope.contratos.size() > 0}">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th class="text-center"><a
							href="sistema?logica=TelaPrincipalGestor&ordContrato=numero">Número</a>
						</th>
						<th class="text-center"><a
							href="sistema?logica=TelaPrincipalGestor&ordContrato=nomeEmpresaContratada">Empresa</a>
						</th>
						<th class="text-center"><a
							href="sistema?logica=TelaPrincipalGestor&ordContrato=fiscal_id">Fiscal</a>
						</th>
						<th class="text-center"><a
							href="sistema?logica=TelaPrincipalGestor&ordContrato=valor">Valor</a>
						</th>
						<th class="text-center">Vencimento</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${sessionScope.contratos}"
						varStatus="posicao">
						<tr>
							<td class="text-center">
								<form action="sistema?logica=VerContrato" method="post">
									<div style="display: none">
										<input name="adicionaProcesso" value="true">
									</div>
									<div style="display: none">
										<input name="origem" value="contratos">
									</div>
									<div style="display: none">
										<input name="n" value="${posicao.index}">
									</div>
									<button type="submit" name="your_name" class="btn-link">${c.numero}</button>
								</form>
							</td>
							<td class="text-center">${c.nomeEmpresaContratada}</td>
							<td class="text-center">${c.fiscal.nome}</td>
							<td class="text-center">${c.valorTotalAsString}</td>
							<td class="text-center">${c.dataVencimentoContratoAsString}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p>Você não é gestor de nenhum contrato.</p>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${sessionScope.contratosFiscal.size() > 0}">
		<div style="background-color: #1e94d2; color: white" align="center">
			<h3>Contratos que sou fiscal</h3>
		</div>


		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th class="text-center">Número</th>
					<th class="text-center">Empresa</th>
					<th class="text-center">Gestor</th>
					<th class="text-center">Valor</th>
					<th class="text-center">Vencimento</th>
					<!-- <th class="text-center">%</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${sessionScope.contratosFiscal}" varStatus="posicao">
					<tr>
						<td class="text-center">
							<form action="sistema?logica=VerContrato" method="post">
								<div style="display: none">
									<input name="origem" value="contratosFiscal">
								</div>
								<div style="display: none">
									<input name="n" value="${posicao.index}">
								</div>
								<button type="submit" name="your_name" class="btn-link">${c.numero}</button>
							</form>
						</td>
						<td class="text-center">${c.nomeEmpresaContratada}</td>
						<td class="text-center">${c.fiscal.nome}</td>
						<td class="text-center">${c.valorTotalAsString}</td>
						<td class="text-center">${c.dataVencimentoContratoAsString}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br />
	</c:if>
	<!-- fim do if da tabela -->
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>