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

	<%@ page import="entity.Usuario,
					 entity.Cargo,
			  		 entity.Setor,
  					 java.util.ArrayList"%>
	<%Usuario u = (Usuario) request.getSession().getAttribute("usuarioeditar");
	String nome = u.getNome();
	%>

	<form action="sistema?logica=EditarUsuario" method="POST">
		<div class="aw-simple-panel__box">
			<div class="form-group  has-feedback">
				<input type="number" class="form-control input-lg"
					placeholder="Sua matrícula" name="matricula"
					value=<%=u.getMatricula() %> required> <span
					class="form-control-feedback" aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg"
					placeholder="Seu nome completo" name="nome" value=<%=nome %>
					required> <span class="form-control-feedback"
					aria-hidden="true"> </span>
			</div>

			<div class="form-group  has-feedback">
				<input type="email" class="form-control input-lg"
					placeholder="Seu e-mail corporativo" name="email"
					value=<%=u.getEmail() %> required> <span
					class="form-control-feedback" aria-hidden="true"></span>
			</div>

			<br />
			
			<!-- Mostrar as opções de cargos -->
			<%
				@SuppressWarnings("unchecked")
				ArrayList<Cargo> cargos = ((ArrayList<Cargo>) request.getSession().getAttribute("todososcargos"));

				for (Cargo cargoDoUsuario : u.getCargo()) {
			%>
			<div class="form-group custom-select has-feedback">
				<select name="cargoNovo<%=u.getCargo().indexOf(cargoDoUsuario)%>">
					<option style="display: none"
						value="<%=cargos.indexOf(u.getCargo())%>">
						<%=cargoDoUsuario.getNome()%> -
						<%=cargoDoUsuario.getDescricao()%>
					</option>

					<%
						for (Cargo c : cargos) {
					%>
					<option value="<%=cargos.indexOf(c)%>">
						<%=c.getNome()%> -
						<%=c.getDescricao()%>
					</option>
					<%
						}
					%>

				</select>
				<!-- select cargos -->
			</div>
			<%
				}
			%>
			<!-- fim div select cargos -->

			<!-- Mostrar as opções de setores -->
			<div class="form-group custom-select has-feedback">
				<select name="setorNovo">
					<%
						@SuppressWarnings("unchecked")
						ArrayList<Setor> setores = ((ArrayList<Setor>) request.getSession().getAttribute("todosossetores"));
					%>
					<option style="display: none"
						value="<%=setores.indexOf(u.getSetor())%>">
						<%=u.getSetor().getSigla()%> -
						<%=u.getSetor().getNome()%>
					</option>

					<%
						for (Setor s : setores) {
					%>
					<option value="<%=setores.indexOf(s)%>">
						<%=s.getSigla()%> -
						<%=s.getNome()%>
					</option>
					<%
						}
					%>
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