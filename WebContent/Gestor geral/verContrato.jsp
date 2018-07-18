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
	<%@ page import="entity.Contrato,
					 entity.Processo,
					 java.util.ArrayList" %>
			
	<%		 
	@SuppressWarnings("unchecked")
	//Pega o objeto contrato da lista de contratos recentes ou de todos os contratos dependendo de quem fez a chamada 
	Contrato contrato = ((ArrayList<Contrato>) request.getSession().getAttribute(request.getParameter("origem"))).get(Integer.parseInt(request.getParameter("n")));
	%>
	
	<p>Número: <%=contrato.getNumero() %></p>
	<p>Empresa: <%=contrato.getNomeEmpresaContratada()%> <%=contrato.getCnpjEmpresaContratada()%></p>
	<p>Objeto: <%=contrato.getObjeto()%></p>
	<p>Portaria: <%=contrato.getPortaria()%></p>
	<p>Data de assinatura: <%=contrato.getDataAssinatura()%></p>
	<p>Data da ordem de serviço: <%=contrato.getDataOrdemServico()%></p>
	<p>Data da garantia: <%=contrato.getDataGarantia()%></p>
	<p>Data de vencimento do contrato: <%=contrato.getDataVencimentoContrato()%></p>
	<p>Data de vencimento da garantia: <%=contrato.getDataVencimentoGarantia()%></p>
	<p>Gestor: <%=contrato.getGestor().getNome()%></p>
	<p>Fiscal: <%=contrato.getFiscal().getNome()%></p>
	<p>Recurso: <%=contrato.getRecurso().getNome()%></p>
	<p>Uso: <%=contrato.getUso().getNome()%></p>
	<p>Fonte pagante: <%=contrato.getFontePagante().getNome()%></p>
	<p>Valor inicial: R$ <%=contrato.getValorInicial()%></p>
	<p>Valor total: R$ <%=contrato.getValorTotal()%></p>
	
	<%for(Processo p: contrato.getProcessos()){ %>
		<%=p.getNumeroSei() %>
	<%}%>
<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>