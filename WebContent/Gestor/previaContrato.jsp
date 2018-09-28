<!-- Ver previa de contrato do gestor -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<title>Sistema de Gestão de Contratos</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

</head>
<body class="aw-layout-page">
<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>${sessionScope.contratoVisualizar.nomeEmpresaContratada} - CNPJ: ${sessionScope.contratoVisualizar.cnpjEmpresaContratada}</h3>
	</div>
	
	<table class="table table-bordered table-striped">
		<tbody>
			<tr>
				<td rowspan="2" class="text-center col-md-1">Número: ${sessionScope.contratoVisualizar.numero}</td>
				<td class="text-center col-md-1">Portaria: ${sessionScope.contratoVisualizar.portaria}</td>
				<td class="text-center col-md-1">Gestor: ${sessionScope.contratoVisualizar.gestor.nome}</td>
				<td class="text-center col-md-1">Fiscal: ${sessionScope.contratoVisualizar.fiscal.nome}</td>
				
			</tr>
			<tr>
				<td class="text-center col-md-1">Recurso: ${sessionScope.contratoVisualizar.recurso.nome}</td>
				<td class="text-center col-md-1">Uso: ${sessionScope.contratoVisualizar.uso.nome}</td>
				<td class="text-center col-md-1">Fonte pagante: ${sessionScope.contratoVisualizar.fontePagante.nome}</td>
			</tr>
			<tr>
				<td class="text-center col-md-1">Data de assinatura: ${sessionScope.contratoVisualizar.dataAssinaturaAsString}</td>
				<td class="text-center col-md-1">Valor inicial: R$ ${sessionScope.contratoVisualizar.valorInicialAsString}</td>
				<td class="text-center col-md-1">Valor dos aditivos: R$ ${sessionScope.contratoVisualizar.valorAditivoAsString}</td>
				<td class="text-center col-md-1">Valor total: R$ ${sessionScope.contratoVisualizar.valorTotalAsString}</td>
			</tr>
			<tr>
				<td class="text-center col-md-1">Ass. ordem de serviço: ${sessionScope.contratoVisualizar.dataOrdemServicoAsString}</td>
				<td class="text-center col-md-1">Ass. garantia: ${sessionScope.contratoVisualizar.dataGarantiaAsString}</td>
				<td class="text-center col-md-1">Vencimento do contrato: ${sessionScope.contratoVisualizar.dataVencimentoContratoAsString}</td>
				<td class="text-center col-md-1">Vencimento da garantia: ${sessionScope.contratoVisualizar.dataVencimentoGarantiaAsString}</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-bordered table-striped">
		<tr>
			<td>Objeto: ${sessionScope.contratoVisualizar.objeto}</td>
		</tr>
	</table>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Processos</h3>
	</div>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-1">N° processo</th>
				<th class="text-center col-md-1">Med</th>
				<th class="text-center col-md-1">Mês refer.</th>
				<th class="text-center col-md-1">Ano refer.</th>
				<th class="text-center col-md-1">Nota fiscal</th>
				<th class="text-center col-md-1">Data</th>
				<th class="text-center col-md-1">Valor</th>
				<th class="text-center col-md-1">Aditivo</th>
				<th class="text-center col-md-2">Objeto</th>
				<th class="text-center col-md-1"></th><th class="text-center col-md-1"></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="processo" items="${sessionScope.previaProcessos}" varStatus="posicao">
			<tr>
				<td class="text-center">
					<form action="sistema?logica=VerProcesso" method="post">
						<div style="display: none">
							<input name="origem" value="contratoVisualizar">
						</div>
						<div style="display: none">
							<input name="i" value="${posicao.index}">
						</div>
						<button type="submit" class="btn-link">${processo.numeroSei}</button>
					</form>
				</td>
				<td class="text-center">${posicao.index + 1}</td>
				<td class="text-center">${processo.mes}</td>
				<td class="text-center">${processo.ano}</td>
				<td class="text-center">${processo.notaFiscal}</td>
				<td class="text-center">${processo.dataProcessoAsString}</td>
				<td class="text-center">${processo.valorAsString}</td>
				<td class="text-center">${processo.aditivoAsString}</td>
				<td class="text-center">${processo.tipoAditivo}</td>
				<td class="text-center">
					<form action="sistema?logica=EditarPrevia" method="post">
						<div style="display: none">
							<input name="i" value="${posicao.index}">
						</div>
						<div style="display: none">
							<input name="acao" value="telaEditar">
						</div>
						<button type="submit" class="btn-link">Editar</button>
					</form>
				</td>
				<td class="text-center">
					<form action="sistema?logica=EditarPrevia" method="post">
						<div style="display: none">
							<input name="i" value="${posicao.index}">
						</div>
						<div style="display: none">
							<input name="acao" value="remover">
						</div>
						<button type="submit" class="btn-link">Remover</button>
					</form>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<form action="sistema?logica=EditarPrevia" method="post">
		<div style="display: none">
			<input name="acao" value="aprovar">
		</div>
		<div class="form-group  has-feedback">
			Nova data de vencimento (deixe vazia se não for necessário alterar): 
			<input type="date"
				class="form-control input-lg" name="novaDataVencimento"> <span
				class="form-control-feedback" aria-hidden="true"> </span>
		</div>
		<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Aprovar</button>
	</form>
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>