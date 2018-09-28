<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<title>Sistema de Gestão de Contratos da CTB</title>

<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

</head>
<body class="aw-layout-page">
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	<form action="sistema?logica=EditarPrevia" method="post">
		<div class="aw-simple-panel__box">
			<div style="display: none">
				<input type="text" class="form-control input-lg" name="idContrato"
					value="${p.id}" >
			</div>

			<div style="display: none">
				<input name="i"
					value="${i}" >
			</div>
			<div style="display: none">
				<input name="acao"
					value="editar" >
			</div>
			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg"
					value="${processoEditar.numeroSei}" placeholder="Número SEI" name="numero" > <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg"
					value="${processoEditar.notaFiscal}"placeholder="Nota fiscal" name="notaFiscal" > <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg" placeholder="Valor"
					value="${processoEditar.valorAsString}"id="valor" name="valor" maxlength="14" onkeyup="MascaraMoeda(this)"
					 /> <span class="form-control-feedback" aria-hidden="true">
				</span>
			</div>

			<div class="form-group custom-select has-feedback">
				<select name="mes" id="mes">
					<option value="${processoEditar.mes}"style="display: none">${processoEditar.mes}</option>
					<option value="Janeiro">Janeiro</option>
					<option value="Fevereiro">Fevereiro</option>
					<option value="Março">Março</option>
					<option value="Abril">Abril</option>
					<option value="Maio">Maio</option>
					<option value="Junho">Junho</option>
					<option value="Julho">Julho</option>
					<option value="Agosto">Agosto</option>
					<option value="Setembro">Setembro</option>
					<option value="Outubro">Outubro</option>
					<option value="Novembro">Novembro</option>
					<option value="Dezembro">Dezembro</option>
				</select>
				<!-- select mês de referência -->
			</div>
			<!-- fim div select cargos -->

			<div class="form-group  has-feedback">
				<input type="number" class="form-control input-lg" placeholder="Ano"
					value="${processoEditar.ano}"name="ano" maxlength="4"  /> <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				Data do processo: <input type="date" class="form-control input-lg"
					value="${processoEditar.dataProcesso}"name="data" > <span class="form-control-feedback"
					aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback" align="center">
				<input type="checkbox" name="aditivoChkBox" id="aditivoChkBox"
					onClick="aditivo()"> Houve aditivo?<br>
			</div>
			<div id="infoAditivo" style="display: none">
				<div class="form-group  has-feedback">
					<input type="text" class="form-control input-lg"
						value="${processoEditar.aditivoAsString}" placeholder="Valor do aditivo" name="valorAditivo" maxlength="14"
						onkeyup="MascaraMoeda(this)" /> <span
						class="form-control-feedback" aria-hidden="true"> </span>
				</div>
				<div class="form-group  has-feedback">
					<input type="text" class="form-control input-lg"
						value="${processoEditar.tipoAditivo}"placeholder="Tipo de aditivo" name="tipoAditivo"> <span
						class="form-control-feedback" aria-hidden="true"> </span>
				</div>
				<div class="form-group  has-feedback">
					Nova data de vencimento: <input type="date"
						class="form-control input-lg" name="novaDataVencimento"> <span
						class="form-control-feedback" aria-hidden="true"> </span>
				</div>
			</div>
			<div class="form-group">
				<button type="submit"
					class="btn btn-primary btn-lg aw-btn-full-width">Concluir</button>
			</div>
		</div>
	</form>

	<jsp:include page="../adds/Rodape.jsp"></jsp:include>

	<script type="text/javascript" src="js/campoValor.js"></script>
	<script type="text/javascript" src="js/mostrarCampoAditivo.js"></script>
</body>
</html>