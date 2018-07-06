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
	
	<form action="sistema?logica=NovoContrato" method="post">
		<div class="form-group  has-feedback">
			<input type="number" class="form-control input-lg" placeholder="Sua matrícula" name="matricula" required> 
			<span class="form-control-feedback"	aria-hidden="true"> </span>
		</div>
	
	
		<div class="form-group">
			<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Concluir</button>
		</div>
	</form>
		
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>