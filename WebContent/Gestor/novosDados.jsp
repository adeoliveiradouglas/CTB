<!-- Página principal do Gestor Geral -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:th="http://www.thymeleaf.org"
	  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<title>SGC</title>

<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

</head>
<body class="aw-layout-page">
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	
	<form action="sistema?logica=CadastrarDados" method="post">
		<div class="aw-simple-panel__box">
			<div class="form-group  has-feedback" style="display: none">
				<input type="text" class="form-control input-lg" name="idContrato"
					value="${param.id}" required>
			</div>

			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg"
					placeholder="Número SEI" name="numero"> <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg"
					placeholder="Nota fiscal" name="notaFiscal"> <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg" placeholder="Valor"
					id="valor" name="valor" maxlength="14" onkeyup="MascaraMoeda(this)"
					required /> <span class="form-control-feedback" aria-hidden="true">
				</span>
			</div>

			<div class="form-group custom-select has-feedback">
				<select name="mes" id="mes">
					<option style="display: none">Mês de referência:</option>
					<option value="01">Janeiro</option>
					<option value="02">Fevereiro</option>
					<option value="03">Março</option>
					<option value="04">Abril</option>
					<option value="05">Maio</option>
					<option value="06">Junho</option>
					<option value="07">Julho</option>
					<option value="08">Agosto</option>
					<option value="09">Setembro</option>
					<option value="10">Outubro</option>
					<option value="11">Novembro</option>
					<option value="12">Dezembro</option>
				</select>
				<!-- select mês de referência -->
			</div>
			<!-- fim div select cargos -->

			<div class="form-group  has-feedback">
				<input type="number" class="form-control input-lg" placeholder="Ano"
					name="ano" maxlength="4" required /> <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				Data do processo: <input type="date" class="form-control input-lg"
					name="data" required> <span class="form-control-feedback"
					aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback" align="center">
				<input type="checkbox" name="aditivoChkBox" id="aditivoChkBox"
					onClick="aditivo()"> Houve aditivo?<br>
			</div>
			<div id="infoAditivo" style="display: none">
				<div class="form-group  has-feedback">
					<input type="text" class="form-control input-lg"
						placeholder="Valor do aditivo" name="valorAditivo" maxlength="14"
						onkeyup="MascaraMoeda(this)" /> <span
						class="form-control-feedback" aria-hidden="true"> </span>
				</div>
				<div class="form-group  has-feedback">
					<input type="text" class="form-control input-lg"
						placeholder="Tipo de aditivo" name="tipoAditivo"> <span
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