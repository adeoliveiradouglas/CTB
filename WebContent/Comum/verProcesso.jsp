<!-- Ver contrato do gestor -->
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
	<%@ page import="entity.Processo,
					 utilidades.FormatarCampo,
					 java.util.ArrayList" %>
		
	
	<%		
	FormatarCampo fc = new FormatarCampo();
	Processo processo = (Processo) request.getSession().getAttribute("processoVisualizar");
	%>
	
	<p>N° processo = <%=processo.getNumeroSei() %></p>
	<p>Nota fiscal = <%=processo.getNotaFiscal() %></p>
	<p><%=processo.getMes() %>/<%=processo.getAno() %></p>
	<p>Data do processo = <%=processo.getDataProcesso() %></p>
	<p>Valor = <%=fc.decimalToString(processo.getValor()) %></p>
	<p>Aditivo = <%=fc.decimalToString(processo.getAditivo()) %></p>
	<p>Objeto = <%=processo.getTipoAditivo() %></p>
	<p>Data de pagamento = <%=processo.getDataPagamento() %></p>
	<p>Responsável pelo pagamento = <%=processo.getTesoureiro().getNome() %>
<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>