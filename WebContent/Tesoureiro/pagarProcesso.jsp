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
					 entity.Contrato,
					 utilidades.FormatarCampo,
					 java.util.ArrayList" %>
	<%
	Contrato contrato = (Contrato) request.getSession().getAttribute("contratoVisualizar");
	Processo processo = (Processo) request.getSession().getAttribute("processoVisualizar");
	
	String pagamento = "";
	
	if(processo.getDataPagamento() != null){
		pagamento = processo.getDataPagamentoAsString();
	}
	%>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3><%=contrato.getNomeEmpresaContratada()%> - Processo n°: <%=processo.getNumeroSei() %></h3>
	</div>
	
<div class="aw-layout-content">
	
	<p>Nota fiscal = <%=processo.getNotaFiscal() %></p>
	<p>Mês de referência = <%=processo.getMes() %>/<%=processo.getAno() %></p>
	<p>Data do processo = <%=processo.getDataProcessoAsString() %></p>
	<p>Valor = <%=processo.getValorAsString() %></p>
	<p>Aditivo = <%=processo.getAditivoAsString() %></p>
	<p>Objeto = <%=processo.getTipoAditivo() %></p>
	<p>Data de pagamento = <%=pagamento %></p>
	<p>Responsável pelo pagamento = <%=processo.getTesoureiro().getNome() %>
</div>

<form action="sistema?logica=EditarPrevia" method="post">
	<div class="aw-simple-panel__box">
		<div class="form-group  has-feedback">
			Insira data de pagamento: <input type="date"
			class="form-control input-lg" name="dataPagamento"> <span
			class="form-control-feedback" aria-hidden="true"> </span>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Pagar</button>
		</div>
	</div>
</form>

<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>