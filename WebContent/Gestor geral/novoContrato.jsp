<!-- Página principal do Gestor Geral -->
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
	
	<div class="aw-layout-simple-page">
		<form action="sistema?logica=NovoContrato" method="post">
			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg" placeholder="Número" name="numero" required> 
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
				<input type="number" class="form-control input-lg" placeholder="Portaria" name="portaria" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg" placeholder="Valor" id="valor" name="valor" maxlength="14" onkeyup="MascaraMoeda(this)" required/>
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div  class="form-group custom-select has-feedback">
			  <select name="gestor" id="gestor">
			  	<option style="display: none">Selecione o gestor:</option>
			  	<%@ page import="entity.Usuario,
			  					 entity.Outro,
			  					 java.util.ArrayList" %>
			  	
			  	<%
			  		@SuppressWarnings("unchecked")
			  		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getSession().getAttribute("gestor");
			  		
			  		for (Usuario c: usuarios){
			  	%>
			  	<option value="<%=c.getId() %>">
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
			  		for (Usuario c: usuarios){
			  	%>
			  	<option value="<%=c.getId() %>">
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
			  		@SuppressWarnings("unchecked")
			  		ArrayList<Outro> lRecurso = (ArrayList<Outro>) request.getSession().getAttribute("recurso"); 
			  		
			  		for (Outro o: lRecurso){
			  	%>
			  	<option value="<%=o.getId() %>">
			  		<%=o.getNome() %>			  	
			  	</option>
			  	<%
			  		}
			  	%>
			  </select> <!-- select recurso -->
			</div> <!-- fim div select recurso -->
			
			<div  class="form-group custom-select has-feedback">
			  <select name="uso" id="uso">
			  	<option style="display: none">Uso:</option>	
			  	<%
			  		@SuppressWarnings("unchecked")
			  		ArrayList<Outro> lUso = (ArrayList<Outro>) request.getSession().getAttribute("uso");
			  	
			  		for (Outro o: lUso){
			  	%>
			  	<option value="<%=o.getId() %>">
			  		<%=o.getNome() %>			  	
			  	</option>
			  	<%
			  		}
			  	%>
			  </select> <!-- select uso -->
			</div> <!-- fim div select uso -->
			
			<div  class="form-group custom-select has-feedback">
			  <select name="fontepagante" id="fontepagante">
			  	<option style="display: none">Fonte pagante:</option>	
			  	<%
			  		@SuppressWarnings("unchecked")
			  		ArrayList<Outro> lFonte = (ArrayList<Outro>) request.getSession().getAttribute("fontepagante");
			  	
			  		for (Outro o: lFonte){
			  	%>
			  	<option value="<%=o.getId() %>">
			  		<%=o.getNome() %>			  	
			  	</option>
			  	<%
			  		}
			  	%>
			  </select> <!-- select uso -->
			</div> <!-- fim div select uso -->
			<div class="form-group  has-feedback">
				Data de assinatura:
				<input type="date" class="form-control input-lg" name="dataAssinatura" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				Data da ordem de serviço:
				<input type="date" class="form-control input-lg" name="dataOs" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				Data da garantia:
				<input type="date" class="form-control input-lg" name="dataGarantia" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				Data de vencimento do contrato:
				<input type="date" class="form-control input-lg" name="dataVencimento" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				Data de vencimento da garantia:
				<input type="date" class="form-control input-lg" name="dataVencimentoGarantia" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
						
			<div class="form-group">
				<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Concluir</button>
			</div>
		</form>
	</div>
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
	
	<script type="text/javascript" src="js/campoValor.js"></script>
</body>
</html>