<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

	<form action="sistema?logica=EditarUsuario" method="POST">
		<div class="aw-simple-panel__box">
			<div style="display: none">
				<input type="number" name="id" value="${usuarioeditar.id}" required>
				<input name="senha" value="${usuarioeditar.senha}" required> 
			</div>
			
			<div class="form-group  has-feedback">
				<input type="number" class="form-control input-lg"
					placeholder="Sua matrícula" name="matricula"
					value="${usuarioeditar.matricula}" required> <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg"
					value="${usuarioeditar.nome}" name="nome"> <span class="form-control-feedback"
					aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				<input type="email" class="form-control input-lg"
					placeholder="Seu e-mail corporativo" name="email"
					value="${usuarioeditar.email}" required> <span
					class="form-control-feedback" aria-hidden="true"></span>
			</div>

			<br />
			
			<!-- Mostrar as opções de cargos -->
			<div class="form-group custom-select has-feedback">
				<select name="cargoNovo0">
					<option style="display: none"
						value="${posicaoCargo0}">
						${usuarioeditar.cargo.get(0).nome} - ${usuarioeditar.cargo.get(0).descricao}
					</option>

					<c:forEach var="c" items="${sessionScope.todososcargos}" varStatus="posicao">
						<option value="${posicao.index}">
							${c.nome} -
							${c.descricao}
						</option>
					</c:forEach>
				</select>
				<!-- select cargos -->
			</div>
			<div class="form-group custom-select has-feedback">
				<select name="cargoNovo1">
					<option style="display: none"
						value="${posicaoCargo1}">
						${usuarioeditar.cargo.get(1).nome} - ${usuarioeditar.cargo.get(1).descricao}
					</option>
					
					<c:forEach var="c" items="${sessionScope.todososcargos}" varStatus="posicao">
						<option value="${posicao.index}">
							${c.nome} -
							${c.descricao}
						</option>
					</c:forEach>

					<option value="-1">
						Não tem segundo cargo
					</option>
				</select>
				<!-- select cargos -->
			</div>
			<!-- fim div select cargos -->

			<!-- Mostrar as opções de setores -->
			<div class="form-group custom-select has-feedback">
				<select name="setorNovo">
					<option style="display: none" value="${posicaoSetor}">
						${usuarioeditar.setor.sigla} - 
						${usuarioeditar.setor.nome}
					</option>
	
					<c:forEach var="s" items="${sessionScope.todosossetores}" varStatus="posicao">
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
				class="btn btn-primary btn-lg aw-btn-full-width">Confimar</button>
		</div>
		<div class="aw-simple-panel__footer">
			<br />
		</div>
	</form>
</body>
</html>