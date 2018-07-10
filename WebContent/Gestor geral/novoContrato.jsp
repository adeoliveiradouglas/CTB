<!-- Página principal do Gestor Geral -->
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<title>Sistema de Gestão de Contratos da CTB</title>

<link rel="stylesheet" type="text/css" href="../css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="../css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="../css/application.css" />

</head>
<body class="aw-layout-page">
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	
	<form action="sistema?logica=NovoContrato" method="post">
		<div class="form-group  has-feedback">
			<input type="number" class="form-control input-lg" placeholder="Número" name="numero" required> 
			<span class="form-control-feedback"	aria-hidden="true"> </span>
		</div>
		<div class="form-group  has-feedback">
			<input type="text" class="form-control input-lg" placeholder="Empresa" name="nomeEmpresa" required> 
			<span class="form-control-feedback"	aria-hidden="true"> </span>
		</div>
		<div class="form-group  has-feedback">
			<input type="text" class="form-control input-lg" placeholder="CNPJ da empresa" name="cnpjEmpresa" required> 
			<span class="form-control-feedback"	aria-hidden="true"> </span>
		</div>
		<div class="form-group  has-feedback">
			<input type="text" class="form-control input-lg" placeholder="Objeto" name="objeto" required> 
			<span class="form-control-feedback"	aria-hidden="true"> </span>
		</div>
		<div class="form-group  has-feedback">
			<input type="number" class="form-control input-lg" placeholder="Valor" name="valor" required> 
			<span class="form-control-feedback"	aria-hidden="true"> </span>
		</div>
		<div class="form-group  has-feedback">
			<input type="number" class="form-control input-lg" placeholder="Número" name="numero" required> 
			<span class="form-control-feedback"	aria-hidden="true"> </span>
		</div>
		<div class="form-group  has-feedback">
			<input type="number" class="form-control input-lg" placeholder="Portaria" name="portaria" required> 
			<span class="form-control-feedback"	aria-hidden="true"> </span>
		</div>
		
		<div  class="form-group custom-select has-feedback">
		  <select name="gestor" id="gestor">
		  	<option style="display: none">Selecione o gestor:</option>
		  	<%@ page import="dao.UsuarioDAO,
		  					 dao.OutroDAO,
		  					 entity.Usuario,
		  					 java.util.ArrayList" %>
		  	
		  	<%
		  		UsuarioDAO cdao = new UsuarioDAO();
		  		ArrayList<Usuario> cargos = cdao.getAllGestor();
		  		
		  		for (Usuario c: cargos){
		  	%>
		  	<option value="<%=c.getMatricula() %>">
		  		<%=c.getNome() %>			  	
		  	</option>
		  	<%
		  		}
		  	%>
		  </select> <!-- select gestor -->
		</div> <!-- fim div select gestor -->
		
		<div  class="form-group custom-select has-feedback">
		  <select name="fiscal" id="fiscal">
		  	<option style="display: none">Selecione o fiscal:</option>	
		  	<%
		  		for (Usuario c: cargos){
		  	%>
		  	<option value="<%=c.getMatricula() %>">
		  		<%=c.getNome() %>			  	
		  	</option>
		  	<%
		  		}
		  	%>
		  </select> <!-- select fiscal -->
		</div> <!-- fim div select fiscal -->
		
		<div  class="form-group custom-select has-feedback">
		  <select name="recurso" id="recurso">
		  	<option style="display: none">Recurso:</option>	
		  	<%
		  		ArrayList<Outro> lRecurso
		  		for (Outro c: lRecurso){
		  	%>
		  	<option value="<%=c.getMatricula() %>">
		  		<%=c.getNome() %>			  	
		  	</option>
		  	<%
		  		}
		  	%>
		  </select> <!-- select recurso -->
		</div> <!-- fim div select recurso -->
		
		<div class="form-group">
			<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Concluir</button>
		</div>
	</form>
		
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>