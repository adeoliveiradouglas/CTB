<!-- Ver contrato do gestor -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<title>Sistema de Gestão de Contratos</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

</head>
<body class="aw-layout-page">
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	
	<div align="center">
		<a href="sistema?logica=TelaNovosDados&id=${sessionScope.contratoVisualizar.id}">
			<font size="5">Novos dados</font>
		</a>
	</div>
	
	<jsp:include page="../Comum/avisoDeVencimento.jsp"></jsp:include>
	<jsp:include page="../Comum/planilha.jsp"></jsp:include>
	
	
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>