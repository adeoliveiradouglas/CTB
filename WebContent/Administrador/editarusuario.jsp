<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<title>Sistema de Gestão de Contratos da CTB</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

</head>
<body class="aw-layout-page">
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	
	<%@ page import="entity.Usuario" %>
	
	<%
		Usuario u = (Usuario) request.getSession().getAttribute("usuarioeditar");
	%>	
			 
	<form action="sistema?logica=EditarUsuario" method="POST">
			<div class="aw-simple-panel">
				<div class="aw-simple-panel__box">
					<div class="form-group  has-feedback">
						<input type="number" class="form-control input-lg" placeholder="Sua matrícula" name="matricula" value=<%=u.getMatricula() %> required> 
						<span class="form-control-feedback"	aria-hidden="true"> </span>
					</div>

					<div class="form-group  has-feedback">
						<input class="form-control input-lg" placeholder="Seu nome completo" name="nome" value=<%=u.getNome() %> required> 
						<span class="form-control-feedback" aria-hidden="true"> </span>
					</div>

					<div class="form-group  has-feedback">
						<input type="email" class="form-control input-lg" placeholder="Seu e-mail corporativo" name="email" value=<%=u.getEmail() %> required>
						<span class="form-control-feedback" aria-hidden="true"></span>
					</div>
					
					<br />
					<!-- Mostrar as opções de cargos -->
					<div  class="form-group custom-select has-feedback">
					  <select name="cargo" id="cargoselect">
					  	<option style="display: none">Selecione seu cargo:</option>
					  	<%@ page import="dao.CargoDAO,
					  					 entity.Cargo,
					  					 java.util.ArrayList" %>
					  	
					  	<%
					  		CargoDAO cdao = new CargoDAO();
					  		ArrayList<Cargo> cargos = cdao.getAll();
					  		
					  		for (Cargo c: cargos){
					  	%>
					  	<option value="<%=c.getNome()%>">
					  		<%=c.getNome() %> - <%=c.getDescricao() %>					  	
					  	</option>
					  	<%
					  		}
					  	%>
					  </select> <!-- select cargos -->
					</div> <!-- fim div select cargos -->
					
					<!-- Mostrar as opções de setores -->
					<div class="form-group custom-select has-feedback">
					  <select name="setor">
					  	<option style="display: none">Selecione seu setor:</option>
					  	<%@ page import="dao.SetorDAO,
					  					 entity.Setor,
					  					 java.util.ArrayList" %>
					  	
					  	<%
					  		SetorDAO sdao = new SetorDAO("gestaodecontratos", "servidorApp", "suporte2017");
					  		ArrayList<Setor> setores = sdao.getAll();
					  		
					  		for (Setor s: setores){
					  	%>
					  	<option value="<%=s.getSigla()%>">
					  		<%=s.getSigla() %> - <%=s.getNome() %>					  						  	
					  	</option>
					  	<%
					  		}
					  	%>
					  </select> <!-- select setores -->
					</div> <!-- fim div select setores -->
					
				</div>
				
				<br/>
				<div class="form-group" id="botaocadastrar">
					<button type="submit"
						class="btn btn-primary btn-lg aw-btn-full-width">Confimar</button>
				</div>
				<div class="aw-simple-panel__footer"><br/></div>
			</div>
		</form>
		
</body>
</html>