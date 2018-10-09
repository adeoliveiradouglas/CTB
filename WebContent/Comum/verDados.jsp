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
			Processo n°: ${sessionScope.processoVisualizar.numeroSei}</h3>
	</div>

	<div class="aw-layout-content">

		<p>Nota fiscal = ${sessionScope.processoVisualizar.notaFiscal}</p>
		<p>Mês de referência =
			${sessionScope.processoVisualizar.mes}/${sessionScope.processoVisualizar.ano}</p>
		<p>Data do processo =
			${sessionScope.processoVisualizar.dataProcessoAsString}</p>
		<p>Valor = ${sessionScope.processoVisualizar.valorAsString}</p>
		<p>Aditivo = ${sessionScope.processoVisualizar.aditivoAsString}</p>
		<p>Objeto = ${sessionScope.processoVisualizar.tipoAditivo}</p>
		<p>Data de pagamento =
			${sessionScope.processoVisualizar.dataPagamentoAsString}</p>
		<p>Responsável pelo pagamento =
			${sessionScope.processoVisualizar.tesoureiro.nome}
	</div>

	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>