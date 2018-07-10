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
	
	<div align="center">
		<a href="Gestor geral/novoContrato.jsp">
			<h1>
				Novo contrato
			</h1>			
		</a>
	</div>
	
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
				<th class="text-center col-md-1">Data</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th class="text-center col-md-1">Número</th>
				<th class="text-center col-md-2">Empresa</th>
				<th class="text-center col-md-2">Gestor</th>
				<th class="text-center col-md-1">Valor</th>
				<th class="text-center col-md-1">Data vencimento</th>
			</tr>		
		</tbody>
	</table>
	
	<div align="center">
		<a href="sistema?logica=TodosContratos" >Clique aqui para ver todos os contratos</a>
	</div>
	<br />
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Contratos com vencimento dentro de 90 dias</h3>
	</div>
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>