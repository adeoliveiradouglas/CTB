<!-- Página principal do Diretor/Presidente -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<c:forEach var="c" items="${sessionScope.contratos}" varStatus="posicao">
			<tr>
				<td class="text-center">
					<a href="sistema?logica=VerContrato&n=${posicao.index}&origem=contratos">
						${c.numero}
					</a>
				</td>
				<td class="text-center">${c.nomeEmpresaContratada}</td>
				<td class="text-center">${c.gestor.nome}</td>
				<td class="text-center">${c.valorTotalAsString}</td>
				<td class="text-center">${c.dataVencimentoContratoAsString}</td>
				<%-- <th class="text-center" ><%=c.getDataVencimentoContrato() %></th> --%>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>