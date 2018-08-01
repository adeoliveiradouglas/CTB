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
				 java.util.ArrayList,
				 utilidades.FormatarCampo" %>
							 
	<%
	@SuppressWarnings("unchecked")
	ArrayList<Contrato> contratos = (ArrayList<Contrato>) request.getSession().getAttribute("contratos");
	FormatarCampo format = new FormatarCampo();
	%>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Todos os contratos</h3>
	</div>
	
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
				<td class="text-center">
					<a href="sistema?logica=VerContrato&n=<%=contratos.indexOf(c)%>&origem=contratos">
						<%=c.getNumero() %>
					</a>
				</td>
				<td class="text-center"><%=c.getNomeEmpresaContratada() %></td>
				<td class="text-center"><%=c.getGestor().getNome() %></td>
				<td class="text-center"><%=format.decimalToString(c.getValorTotal()) %></td>
				<td class="text-center"><%=format.dataToString(c.getDataVencimentoContrato()) %></td>
				<%-- <th class="text-center" ><%=c.getDataVencimentoContrato() %></th> --%>
			</tr>
			<%}%> <!-- fim do if do for que mostra os contratos recentes -->
		</tbody>
	</table>
	
<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>