<!-- Ver contrato do gestor -->
<!DOCTYPE html>
<%@page import="utilidades.FormatarCampo"%>
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
					 entity.Usuario,
					 java.util.ArrayList,
					 utilidades.FormatarCampo" %>
		
	
	<%		 
	//a essa altura, a lista de contratos já foi carregada e está na sessão. Essa página recebe qual lista deve acessar
	//e qual a posição do contrato nessa lista.
	String origem = request.getParameter("origem"); //lista que deve acessar 
	int n = Integer.parseInt(request.getParameter("n")); //posição na lista
	FormatarCampo format = new FormatarCampo();
	
	//carrega a lista para essa página
	@SuppressWarnings("unchecked")
	ArrayList<Contrato> contratosLista = (ArrayList<Contrato>) request.getSession().getAttribute(origem);
	Usuario logado = (Usuario) request.getSession().getAttribute("usuario");
	//Pega o objeto contrato da lista 
	Contrato contrato = contratosLista.get(n);
	String adicionaProcesso = "";
	
	try{
		adicionaProcesso = (String) request.getParameter("adicionaProcesso");
	}catch(Exception e){}%>
	
	<%try{if(adicionaProcesso.equals("true")){ %>
	<div align="center">
		<a href="sistema?logica=TelaNovoProcesso&id=<%=contrato.getId() %>">
			<font size="5">Novo processo</font>
		</a>
	</div>
	<%}}catch(Exception e){}%>
	
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3><%=contrato.getNomeEmpresaContratada()%> - CNPJ: <%=contrato.getCnpjEmpresaContratada()%></h3>
	</div>
	
	<table class="table table-bordered table-striped">
		<tbody>
			<tr>
				<td class="text-center col-md-1">Número: <%=contrato.getNumero() %></td>
				<td class="text-center col-md-1">Portaria: <%=contrato.getPortaria()%></td>
				<td class="text-center col-md-1">Gestor: <%=contrato.getGestor().getNome()%></td>
				<td class="text-center col-md-1">Fiscal: <%=contrato.getFiscal().getNome()%></td>
				
			</tr>
			<tr>
				<td class="text-center col-md-1">Objeto: <%=contrato.getObjeto()%></td>
				<td class="text-center col-md-1">Recurso: <%=contrato.getRecurso().getNome()%></td>
				<td class="text-center col-md-1">Uso: <%=contrato.getUso().getNome()%></td>
				<td class="text-center col-md-1">Fonte pagante: <%=contrato.getFontePagante().getNome()%></td>
			</tr>
			<tr>
				<td class="text-center col-md-1">Data de assinatura: <%=contrato.getDataAssinatura()%></td>
				<td class="text-center col-md-1">Valor inicial: R$ <%=format.decimalToString(contrato.getValorInicial())%></td>
				<td class="text-center col-md-1">Valor dos aditivos: R$ <%=format.decimalToString(contrato.getValorAditivos()) %></td>
				<td class="text-center col-md-1">Valor total: R$ <%=format.decimalToString(contrato.getValorTotal())%></td>
			</tr>
			<tr>
				<td class="text-center col-md-1">Ass. ordem de serviço: <%=contrato.getDataOrdemServico()%></td>
				<td class="text-center col-md-1">Ass. garantia: <%=contrato.getDataGarantia()%></td>
				<td class="text-center col-md-1">Vencimento do contrato: <%=contrato.getDataVencimentoContrato()%></td>
				<td class="text-center col-md-1">Vencimento da garantia: <%=contrato.getDataVencimentoGarantia()%></td>
			</tr>
		</tbody>
	</table>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Processos</h3>
	</div>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-1">Ano refer.</th>
				<th class="text-center col-md-1">Med</th>
				<th class="text-center col-md-1">Mês refer.</th>
				<th class="text-center col-md-1">Nota fiscal</th>
				<th class="text-center col-md-1">N° processo</th>
				<th class="text-center col-md-1">Data</th>
				<th class="text-center col-md-1">Valor</th>
				<th class="text-center col-md-1">Aditivo</th>
				<th class="text-center col-md-2">Objeto</th>
				<th class="text-center col-md-1">Pagamento</th>
			</tr>
		</thead>
		<tbody>
			<%for(Processo p: contrato.getProcessos()){%>
			<tr>
				<td class="text-center"><%=p.getAno() %></td>
				<td class="text-center"><%=contrato.getProcessos().indexOf(p)+1 %></td>
				<td class="text-center"><%=p.getMes()%></td>
				<td class="text-center"><%=p.getNotaFiscal() %></td>
				<td class="text-center"><%=p.getNumeroSei() %></td>
				<td class="text-center"><%=p.getDataProcesso() %></td>
				<td class="text-center"><%=format.decimalToString(p.getValor()) %></td>
				<td class="text-center"><%=format.decimalToString(p.getAditivo()) %></td>
				<td class="text-center"><%=p.getTipoAditivo() %></td>
				<td class="text-center"><%=p.getDataPagamento() %></td>
			</tr>
			<%}%> <!-- fim do if do for que mostra os contratos recentes -->
		</tbody>
	</table>
	
<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>