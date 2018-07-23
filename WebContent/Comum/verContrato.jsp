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
	<%@ page import="entity.Contrato,
					 entity.Processo,
					 entity.Usuario,
					 java.util.ArrayList" %>
		
	
	<%		 
	//a essa altura, a lista de contratos já foi carregada e está na sessão. Essa página recebe qual lista deve acessar
	//e qual a posição do contrato nessa lista.
	String origem = request.getParameter("origem"); //lista que deve acessar 
	int n = Integer.parseInt(request.getParameter("n")); //posição na lista
	
	//carrega a lista para essa página
	@SuppressWarnings("unchecked")
	ArrayList<Contrato> contratosLista = (ArrayList<Contrato>) request.getSession().getAttribute(origem);
	Usuario logado = (Usuario) request.getSession().getAttribute("usuario");
	//Pega o objeto contrato da lista 
	Contrato contrato = contratosLista.get(n);
	%>
	
	<%if(logado.getCargo().getId() == 3){ %>
	<div align="center">
		<a href="sistema?logica=TelaNovoProcesso&id=<%=contrato.getId() %>">
			<font size="5">Novo processo</font>
		</a>
	</div>
	<%}%>
	
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
	<p>Valor dos aditivos: R$ <%=contrato.getValorAditivos() %></p>
	<p>Valor total: R$ <%=contrato.getValorTotal()%></p>
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
				<td class="text-center"><%=p.getValor() %></td>
				<td class="text-center"><%=p.getAditivo() %></td>
				<td class="text-center"><%=p.getTipoAditivo() %></td>
				<td class="text-center"><%=p.getDataPagamento() %></td>
			</tr>
			<%}%> <!-- fim do if do for que mostra os contratos recentes -->
		</tbody>
	</table>
	
<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>