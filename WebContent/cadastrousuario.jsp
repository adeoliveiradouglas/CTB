<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />

<title>SGC</title>

<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

<style>
select {
	width: 100%;
}
</style>

</head>

<body class="aw-layout-simple-page">
	<div class="aw-layout-simple-page__container">
		<div align="center">
			<a href="sistema?logica=TelaLogin"> <img
				src="/gestaodecontratos/layout/images/logo.png" />
			</a>
		</div>
		<br />
		<form action="sistema?logica=CadastrarUsuario" method="POST">
			<div class="aw-simple-panel">
				<div class="aw-simple-panel__box">
					<div class="form-group  has-feedback">
						<input type="number" class="form-control input-lg"
							placeholder="Sua matrícula" name="matricula" required> <span
							class="form-control-feedback" aria-hidden="true"> </span>
					</div>

					<div class="form-group  has-feedback">
						<input type="text" class="form-control input-lg"
							placeholder="Seu nome completo" name="nome" required> <span
							class="form-control-feedback" aria-hidden="true"> </span>
					</div>

					<div class="form-group  has-feedback">
						<input type="email" class="form-control input-lg"
							placeholder="Seu e-mail corporativo" name="email" required>
						<span class="form-control-feedback" aria-hidden="true"></span>
					</div>

					<div class="form-group has-feedback">
						<input type="password" class="form-control input-lg"
							placeholder="Crie sua senha" name="senha" id="senha"> <span
							class="form-control-feedback" aria-hidden="true"></span>
						<div id="mensagemsenha">
							<label>Senha deve ter mais que 6 caracteres</label>
						</div>
					</div>

					<div class="form-group has-feedback" id="confirmacaosenhadiv">
						<input type="password" class="form-control input-lg"
							placeholder="Confirme sua senha" name="confirmacaosenha"
							id="confirmacaosenha">
						<div id="mensagemconfirmacaosenha">
							<label>Senhas não conferem</label>
						</div>
					</div>

					<br />
					<!-- Mostrar as opções de cargos -->
					<div class="form-group custom-select has-feedback">
						<select name="cargo1">
							<option style="display: none">Selecione seu cargo:</option>
							<c:forEach var="c" items="${sessionScope.cargos}" varStatus="posicao">
								<option value="${posicao.index}">
									${c.nome} - ${c.descricao}
								</option>
							</c:forEach>							
						</select>
						<!-- select do primeiro cargo -->
						
						<input type="checkbox" id="chkBox" onClick="segundoCargo()">Tenho um segundo cargo
					</div>
					<!-- fim div select do primeiro cargo -->

					<div class="form-group custom-select has-feedback"
						id="cargoselectopcional" style="display: none">
						<select name="cargo2">
							<option style="display: none">Selecione seu segundo cargo:</option>
							<c:forEach var="c" items="${sessionScope.cargos}" varStatus="posicao">
								<option value="${posicao.index}">
									${c.nome} - ${c.descricao}
								</option>
							</c:forEach>
						</select>
						<!-- select do segundo cargo (opcional) -->
					</div>
					<!-- fim div select do segundo cargo (opcional) -->

					<!-- Mostrar as opções de setores -->
					<div class="form-group custom-select has-feedback">
						<select name="setor">
							<option style="display: none">Selecione seu setor:</option>

							<c:forEach var="s" items="${sessionScope.setores}" varStatus="posicao">
								<option value="${posicao.index}">
									${s.sigla} -
									${s.nome}
								</option>
							</c:forEach>
						</select>
						<!-- select setores -->
					</div>
					<!-- fim div select setores -->

				</div>

				<br />
				<div class="form-group" id="botaocadastrar">
					<button type="submit"
						class="btn btn-primary btn-lg aw-btn-full-width">Cadastrar</button>
				</div>
				<div class="aw-simple-panel__footer">
					<br />
				</div>
			</div>
		</form>

		<script type="text/javascript" src="js/validarsenha.js"></script>
		<script type="text/javascript" src="js/mostraropcaodecargo.js"></script>
	</div>
	<p>${param.setores.get(0) }</p>
	<jsp:include page="adds/Rodape.jsp"></jsp:include>
</body>
</html>