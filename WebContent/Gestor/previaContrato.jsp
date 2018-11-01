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
		<h3>Prévia da importação</h3>
		<h5>Verifique todos os dados e informações pois você não poderá alterá-los depois. Quando estiver pronto, vá até o final da página e clique em "Aprovar".</h5>
	</div>
	
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>${sessionScope.contratoVisualizar.nomeEmpresaContratada} - CNPJ: ${sessionScope.contratoVisualizar.cnpjEmpresaContratada}</h3>
	</div>
	
	<table class="table table-bordered table-striped">
		<tbody>
			<tr>
				<td rowspan="2" class="text-center">Número: ${sessionScope.contratoVisualizar.numero}</td>
				<td class="text-center">Portaria: ${sessionScope.contratoVisualizar.portaria}</td>
				<td class="text-center">Gestor: ${sessionScope.contratoVisualizar.gestor.nome}</td>
				<td class="text-center">Fiscal: ${sessionScope.contratoVisualizar.fiscal.nome}</td>
				
			</tr>
			<tr>
				<td class="text-center">Recurso: ${sessionScope.contratoVisualizar.recurso.nome}</td>
				<td class="text-center">Uso: ${sessionScope.contratoVisualizar.uso.nome}</td>
				<td class="text-center">Fonte pagante: ${sessionScope.contratoVisualizar.fontePagante.nome}</td>
			</tr>
			<tr>
				<td class="text-center">Data de assinatura: ${sessionScope.contratoVisualizar.dataAssinaturaAsString}</td>
				<td class="text-center">Valor inicial: R$ ${sessionScope.contratoVisualizar.valorInicialAsString}</td>
				<td class="text-center">Valor dos aditivos: R$ ${sessionScope.contratoVisualizar.valorAditivoAsString}</td>
				<td class="text-center">Valor total: R$ ${sessionScope.contratoVisualizar.valorTotalAsString}</td>
			</tr>
			<tr>
				<td class="text-center">Ass. ordem de serviço: ${sessionScope.contratoVisualizar.dataOrdemServicoAsString}</td>
				<td class="text-center">Ass. garantia: ${sessionScope.contratoVisualizar.dataGarantiaAsString}</td>
				<td class="text-center">Vencimento do contrato: ${sessionScope.contratoVisualizar.dataVencimentoContratoAsString}</td>
				<td class="text-center">Vencimento da garantia: ${sessionScope.contratoVisualizar.dataVencimentoGarantiaAsString}</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-bordered table-striped">
		<tr>
			<td>Objeto: ${sessionScope.contratoVisualizar.objeto}</td>
		</tr>
	</table>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Planilha</h3>
	</div>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center"></th>
				<th class="text-center">Item</th>
				<th class="text-center">N° processo</th>
				<th class="text-center">Referência</th>
				<th class="text-center">Nota fiscal</th>
				<th class="text-center">Valor</th>
				<th class="text-center">Saldo</th>
				<th class="text-center">Aditivo</th>
				<th class="text-center">Objeto</th>
				<th class="text-center"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dados" items="${sessionScope.contratoVisualizar.processos}" varStatus="posicao">
			<tr>
				<td class="text-center">
					<form action="sistema?logica=EditarPrevia" >
						<div style="display: none">
							<input name="acao" value="abrirTelaEditar">
							<input name="i" value="${posicao.index}">
						</div>
						<button>Editar</button>
					</form>
				</td>
				<td class="text-center">
					<form action="sistema?logica=VerDados" method="post">
						<div style="display: none">
							<input name="i" value="${posicao.index}">
						</div>
						<button type="submit" class="btn-link">${posicao.index + 1}</button>
					</form>
				</td>
				<td class="text-center">${dados.numeroSei}</td>
				<td class="text-center">${dados.mes}/${dados.ano}</td>
				<td class="text-center">${dados.notaFiscal}</td>
				<td class="text-center">${dados.valorAsString}</td>
				<td class="text-center">${dados.saldoAsString}</td>
				<td class="text-center">${dados.aditivoAsString}</td>
				<td class="text-center">${dados.tipoAditivo}</td>
				<td class="text-center">
					<form action="sistema?logica=EditarPrevia">
						<div style="display: none">
							<input name="acao" value="remover">
							<input name="i" value="${posicao.index}">
						</div>
						<button>Remover</button>
					</form>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>