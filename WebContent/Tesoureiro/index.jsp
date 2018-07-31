<!-- Página principal do Tesoureiro -->
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
<style>
	btn-link {
		border: none;
		outline: none;
		background: none;
		cursor: pointer;
		color: #0000EE;
		padding: 0;
		text-decoration: underline;
		font-family: inherit;
		font-size: inherit;
	}
</style>
</head>
<body class="aw-layout-page">
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	
	<%@ page import="entity.Contrato,
					 java.util.ArrayList,
					 utilidades.FormatarCampo" %>
							 
	
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Contratos com pagamentos em aberto</h3>
	</div>
	
	<%
	FormatarCampo format = new FormatarCampo();
	
	@SuppressWarnings("unchecked")
	ArrayList<Contrato> contratos = (ArrayList<Contrato>) request.getSession().getAttribute("contratosSemPagamento");
	if(contratos.size() > 0){
	%>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-1">
					<a href="sistema?logica=TelaPrincipalGestor&ordContrato=numero">Número</a>
				</th>
				<th class="text-center col-md-2">
					<a href="sistema?logica=TelaPrincipalGestor&ordContrato=nomeEmpresaContratada">Empresa</a>
				</th>
				<th class="text-center col-md-2">
					<a href="sistema?logica=TelaPrincipalGestor&ordContrato=fiscal_id">Fiscal</a>
				</th>
				<th class="text-center col-md-1">
					<a href="sistema?logica=TelaPrincipalGestor&ordContrato=fiscal_id">Valor</a>
				</th>
				<th class="text-center col-md-1">Vencimento</th>
				<!-- <th class="text-center col-md-1">%</th> -->
			</tr>
		</thead>
		<tbody>
			<%
			for (Contrato c: contratos){
			%>
			<tr>
				<td class="text-center">
					<form action="sistema?logica=VerContratoParaPagamento" method="post">
						<div style="display: none">
							<input name="origem" value="contratosSemPagamento">
						</div>
						<div style="display: none">
							<input name="n" value="<%=contratos.indexOf(c)%>">
						</div>
						<button type="submit" class="btn-link"><%=c.getNumero()%></button>
					</form>
				</td>
				<td class="text-center"><%=c.getNomeEmpresaContratada() %></td>
				<td class="text-center"><%=c.getFiscal().getNome() %></td>
				<td class="text-center"><%=format.decimalToString(c.getValorTotal()) %></td>
				<td class="text-center"><%=c.getDataVencimentoContrato() %></td>
				<%-- <th class="text-center" ><%=c.getDataVencimentoContrato() %></th> --%>
			</tr>
			<%}%> <!-- fim do if do for que mostra os contratos recentes -->
		</tbody>
	</table>
	
	<br />
	<%}else{%> <!-- fim do if da tabela -->
	<p>Não há contratos.</p>
	<%} %>
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>