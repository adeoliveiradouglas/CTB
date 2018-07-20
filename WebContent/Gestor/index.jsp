<!-- Página principal do Gestor -->
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
	
	<%@ page import="entity.Contrato,
					 java.util.ArrayList" %>
							 
	
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Contratos sob minha gestão</h3>
	</div>
	
	<%
	@SuppressWarnings("unchecked")
	ArrayList<Contrato> contratos = (ArrayList<Contrato>) request.getSession().getAttribute("contratos");
	if(contratos.size() > 0){
	%>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-1">Número</th>
				<th class="text-center col-md-2">Empresa</th>
				<th class="text-center col-md-2">Gestor</th>
				<th class="text-center col-md-1">Valor</th>
				<th class="text-center col-md-1">Vencimento</th>
				<!-- <th class="text-center col-md-1">%</th> -->
			</tr>
		</thead>
		<tbody>
			<%
			for (Contrato c: contratos){
			%>
			<tr>
				<th class="text-center">
					<a href="sistema?logica=VerContrato&n=<%=contratos.indexOf(c)%>&origem=contratos">
						<!-- Envia o id da lista onde o contrato está (n) e diz que a origem da chamada é da página principal (origem)-->
						<%=c.getNumero() %>
					</a>
				</th>
				<th class="text-center"><%=c.getNomeEmpresaContratada() %></th>
				<th class="text-center"><%=c.getGestor().getNome() %></th>
				<th class="text-center"><%=c.getValorTotal() %></th>
				<th class="text-center"><%=c.getDataVencimentoContrato() %></th>
				<%-- <th class="text-center" ><%=c.getDataVencimentoContrato() %></th> --%>
			</tr>
			<%}%> <!-- fim do if do for que mostra os contratos recentes -->
		</tbody>
	</table>
	
	<br />
	<%}else{%> <!-- fim do if da tabela -->
	<p>Você não é gestor de nenhum contrato.</p>
	<%} %>
	
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Contratos que sou fiscal</h3>
	</div>
	
	<%
	@SuppressWarnings("unchecked")
	ArrayList<Contrato> contratosFiscal = (ArrayList<Contrato>) request.getSession().getAttribute("contratosFiscal");
					
	if(contratosFiscal.size() > 0){ 
	%>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-1">Número</th>
				<th class="text-center col-md-2">Empresa</th>
				<th class="text-center col-md-2">Gestor</th>
				<th class="text-center col-md-1">Valor</th>
				<th class="text-center col-md-1">Vencimento</th>
				<!-- <th class="text-center col-md-1">%</th> -->
			</tr>
		</thead>
		<tbody>
			<%for (Contrato c: contratosFiscal){%>
			<tr>
				<th class="text-center">
					<a href="sistema?logica=VerContrato&n=<%=contratosFiscal.indexOf(c)%>&origem=contratosFiscal">
						<!-- Envia o id da lista onde o contrato está (n) e diz que a origem da chamada é da página principal (origem)-->
						<%=c.getNumero() %>
					</a>
				</th>
				<th class="text-center"><%=c.getNomeEmpresaContratada() %></th>
				<th class="text-center"><%=c.getGestor().getNome() %></th>
				<th class="text-center"><%=c.getValorTotal() %></th>
				<th class="text-center"><%=c.getDataVencimentoContrato() %></th>
				<%-- <th class="text-center"><%=c.getDataVencimentoContrato() %></th> --%>
			</tr>
			<%}%> <!-- fim do for que mostra os contratos com vencimentos -->
		</tbody>
	</table>
	<div align="center">
		<a href="sistema?logica=ContratosVencimento" >Clique aqui para ver todos os contratos</a>
	</div>
	<br />
	<%}else{%> <!-- fim do if da tabela -->
	<p>Você não é fiscal de nenhum contrato.</p>
	<%} %>
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>