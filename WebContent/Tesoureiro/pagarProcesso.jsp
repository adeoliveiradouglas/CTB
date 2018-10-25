<!-- Ver contrato do gestor -->
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
		<h3>${sessionScope.contratoVisualizar.nomeEmpresaContratada} - Processo n°: ${sessionScope.contratoVisualizar.dados.get(i).numeroSei}</h3>
	</div>
	
<div class="aw-layout-content">
	<p>Nota fiscal = ${sessionScope.contratoVisualizar.dados.get(i).notaFiscal}</p>
	<p>Mês de referência = ${sessionScope.contratoVisualizar.dados.get(i).mes}/${sessionScope.contratoVisualizar.dados.get(i).ano}</p>
	<p>Data do processo = ${sessionScope.contratoVisualizar.dados.get(i).dataAsString}</p>
	<p>Valor = ${sessionScope.contratoVisualizar.dados.get(i).valorAsString}</p>
	<p>Aditivo = ${sessionScope.contratoVisualizar.dados.get(i).aditivoAsString}</p>
	<p>Objeto = ${sessionScope.contratoVisualizar.dados.get(i).tipoAditivo}</p>
	<p>Data de pagamento = ${sessionScope.contratoVisualizar.dados.get(i).dataPagamentoAsString }</p>
	<p>Responsável pelo pagamento = ${sessionScope.contratoVisualizar.dados.get(i).tesoureiro.nome}</p>
	
</div>

<form action="sistema?logica=PagarProcesso" method="post">
	<div class="aw-simple-panel__box">
		<div class="form-group  has-feedback">
			Insira data de pagamento: <input type="date"
			class="form-control input-lg" name="dataPagamento"> <span
			class="form-control-feedback" aria-hidden="true"> </span>
		</div>
		<div style="display: none">
			<input name="id" value="${sessionScope.contratoVisualizar.dados.get(i).id}">
		</div>
		<div style="display: none">
			<input name="acao" value="pagar">
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Pagar</button>
		</div>
	</div>
</form>

<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>