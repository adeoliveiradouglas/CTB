<!-- Ver contrato do gestor -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<title>Sistema de Gestão de Contratos</title>

<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

</head>
<body class="aw-layout-page">

	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>${sessionScope.contratoVisualizar.nomeEmpresaContratada}-
			Processo n°: ${sessionScope.dadosVisualizar.numeroSei}</h3>
	</div>

	<div class="aw-layout-content">
		<p>Item = ${sessionScope.dadosVisualizar.item}</p>
		<p>Nota fiscal = ${sessionScope.dadosVisualizar.notaFiscal}</p>
		<p>Mês de referência =
			${sessionScope.dadosVisualizar.mes}/${sessionScope.dadosVisualizar.ano}</p>
		<p>Data do registro =
			${sessionScope.dadosVisualizar.dataProcessoAsString}</p>
		<p>Valor = ${sessionScope.dadosVisualizar.valorAsString}</p>
		<p>Aditivo = ${sessionScope.dadosVisualizar.aditivoAsString}</p>
		<p>Saldo = ${sessionScope.dadosVisualizar.saldoAsString}</p>
		<p>Objeto = ${sessionScope.dadosVisualizar.tipoAditivo}</p>
		<p>Data de pagamento =
			${sessionScope.dadosVisualizar.dataPagamentoAsString}</p>
		<p>Responsável pelo pagamento =
			${sessionScope.dadosVisualizar.tesoureiro.nome}
	</div>

	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>