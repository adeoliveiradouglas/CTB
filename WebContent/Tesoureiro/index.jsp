<!-- Página principal do Tesoureiro -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<title>Sistema de Gestão de Contratos da CTB</title>

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
		<h3>Contratos com pagamentos em aberto</h3>
	</div>
	<c:choose>
		<c:when test="${sessionScope.contratosSemPagamento.size() > 0}">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th class="text-center">
							<a href="sistema?logica=TelaPrincipalTesoureiro&ordContrato=numero">Número</a>
						</th>
						<th class="text-center">
							<a href="sistema?logica=TelaPrincipalTesoureiro&ordContrato=nomeEmpresaContratada">Empresa</a>
						</th>
						<th class="text-center">
							<a href="sistema?logica=TelaPrincipalTesoureiro&ordContrato=fiscal_id">Fiscal</a>
						</th>
						<th class="text-center">
							<a href="sistema?logica=TelaPrincipalTesoureiro&ordContrato=fiscal_id">Valor</a>
						</th>
						<th class="text-center">Vencimento</th>
						<!-- <th class="text-center">%</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="contrato" items="${sessionScope.contratosSemPagamento}" varStatus="posicao">
						<tr>
							<td class="text-center">
								<form action="sistema?logica=VerContratoParaPagamento" method="post">
									<div style="display: none">
										<input name="n" value="${posicao.index}">
									</div>
									<button type="submit" class="btn-link">${contrato.numero}</button>
								</form>
							</td>
							<td class="text-center">${contrato.nomeEmpresaContratada}</td>
							<td class="text-center">${contrato.fiscal.nome}</td>
							<td class="text-center">${contrato.valorTotalAsString}</td>
							<td class="text-center">${contrato.dataVencimentoContratoAsString}</td>
							<%-- <th class="text-center" >${c.DataVencimentoContrato}</th> --%>
						</tr>
					</c:forEach> <!-- fim do if do for que mostra os contratos recentes -->
				</tbody>
			</table>
		</c:when>
		
		<c:otherwise>
			<p>Não há contratos</p>
		</c:otherwise>
	</c:choose>
	
	
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>