<!-- Página principal do Presidente -->
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

	<%@ page
		import="entity.Contrato,
					 java.util.ArrayList,
					 utilidades.FormatarCampo"%>

	<%
		FormatarCampo format = new FormatarCampo(); //objeto usado para formatacao de valores
		@SuppressWarnings("unchecked")
		ArrayList<Contrato> contratos = (ArrayList<Contrato>) request.getSession()
				.getAttribute("contratosRecentes");
		if (contratos.size() > 0) {
	%>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Contratos mais recentes</h3>
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
				for (Contrato c : contratos) {
			%>
			<tr>
				<td class="text-center"><a
					href="sistema?logica=VerContrato&n=<%=contratos.indexOf(c)%>&origem=contratosRecentes">
						<!-- Envia o id da lista onde o contrato está (n) e diz que a origem da chamada é da página principal (origem)-->
						<%=c.getNumero()%>
				</a></td>
				<td class="text-center"><%=c.getNomeEmpresaContratada()%></td>
				<td class="text-center"><%=c.getGestor().getNome()%></td>
				<td class="text-center"><%=format.decimalToString(c.getValorTotal())%></td>
				<td class="text-center"><%=format.dataToString(c.getDataVencimentoContrato())%></td>
				<%-- <th class="text-center" ><%=c.getDataVencimentoContrato() %></th> --%>
			</tr>
			<%
				}
			%>
			<!-- fim do if do for que mostra os contratos recentes -->
		</tbody>
	</table>

	<div align="center">
		<a href="sistema?logica=VerTodosContratos">Clique aqui para ver
			todos os contratos</a>
	</div>
	<br />
	<%
		}
	%>
	<!-- fim do if da tabela -->

	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>