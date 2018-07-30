<!-- Página principal do Gestor Geral -->
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
	<%@page import="entity.Contrato"%>
	<%
		String id = (String) request.getParameter("id");
	%>
	<form action="sistema?logica=CadastrarProcesso" method="post">
		<div class="aw-simple-panel__box">
			<div class="form-group  has-feedback" style="display: none">
				<input type="text" class="form-control input-lg" name="idContrato"
					value="<%=id%>" required>
			</div>

			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg"
					placeholder="Número SEI" name="numero" required> <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg"
					placeholder="Nota fiscal" name="notaFiscal" required> <span
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
						class="form-control input-lg" name="data" required> <span
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