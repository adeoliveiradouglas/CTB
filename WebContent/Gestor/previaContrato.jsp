<!-- Ver previa de contrato do gestor -->
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<title>Sistema de Gest�o de Contratos</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

</head>
<body class="aw-layout-page">
<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h1>Pr�via do contrato</h1>
	</div>
	<%@ page import="entity.Contrato,
					 entity.Processo,
					 java.util.ArrayList,
					 utilidades.FormatarCampo" %>
		
	
	<%		 
	FormatarCampo format = new FormatarCampo();

	String adicionaProcesso = request.getParameter("adicionaProcesso"),
		   formatoData = "dd/MM/yyyy";
	Contrato contrato = (Contrato) request.getSession().getAttribute("contratoVisualizar");	
	
	@SuppressWarnings("unchecked")
	ArrayList<Processo> previaProcessos = (ArrayList<Processo>) request.getSession().getAttribute("previaProcessos");
	%>
	
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3><%=contrato.getNomeEmpresaContratada()%> - CNPJ: <%=contrato.getCnpjEmpresaContratada()%></h3>
	</div>
	
	<table class="table table-bordered table-striped">
		<tbody>
			<tr>
				<td class="text-center col-md-1">N�mero: <%=contrato.getNumero() %></td>
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
				<td class="text-center col-md-1">Data de assinatura: <%=contrato.getDataAssinatura().toString(formatoData) %></td>
				<td class="text-center col-md-1">Valor inicial: R$ <%=contrato.getValorInicialAsString() %></td>
				<td class="text-center col-md-1">Valor dos aditivos: R$ <%=contrato.getValorAditivoAsString() %></td>
				<td class="text-center col-md-1">Valor total: R$ <%=contrato.getValorTotalAsString()%></td>
			</tr>
			<tr>
				<td class="text-center col-md-1">Ass. ordem de servi�o: <%=contrato.getDataOrdemServico().toString(formatoData) %></td>
				<td class="text-center col-md-1">Ass. garantia: <%=contrato.getDataGarantia().toString(formatoData) %></td>
				<td class="text-center col-md-1">Vencimento do contrato: <%=contrato.getDataVencimentoContrato().toString(formatoData) %></td>
				<td class="text-center col-md-1">Vencimento da garantia: <%=contrato.getDataVencimentoGarantia().toString(formatoData) %></td>
			</tr>
		</tbody>
	</table>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Processos</h3>
	</div>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-1">N� processo</th>
				<th class="text-center col-md-1">Med</th>
				<th class="text-center col-md-1">M�s refer.</th>
				<th class="text-center col-md-1">Ano refer.</th>
				<th class="text-center col-md-1">Nota fiscal</th>
				<th class="text-center col-md-1">Data</th>
				<th class="text-center col-md-1">Valor</th>
				<th class="text-center col-md-1">Aditivo</th>
				<th class="text-center col-md-2">Objeto</th>
				<th class="text-center col-md-1"></th>
			</tr>
		</thead>
		<tbody>
		<%for(Processo p: previaProcessos){
			String pagamento = "";
			
			if(p.getDataPagamento() != null){
				pagamento = p.getDataPagamento().toString(formatoData);
			}%>
			<tr>
				<td class="text-center">
					<form action="sistema?logica=VerProcesso" method="post">
						<div style="display: none">
							<input name="origem" value="contratoVisualizar">
						</div>
						<div style="display: none">
							<input name="i" value="<%=previaProcessos.indexOf(p)%>">
						</div>
						<button type="submit" name="your_name" class="btn-link"><%=p.getNumeroSei()%></button>
					</form>
				</td>
				<td class="text-center"><%=previaProcessos.indexOf(p)+1 %></td>
				<td class="text-center"><%=p.getMes()%></td>
				<td class="text-center"><%=p.getAno() %></td>
				<td class="text-center"><%=p.getNotaFiscal() %></td>
				<td class="text-center"><%=p.getDataProcesso().toString(formatoData)  %></td>
				<td class="text-center"><%=format.decimalToString(p.getValor()) %></td>
				<td class="text-center"><%=format.decimalToString(p.getAditivo()) %></td>
				<td class="text-center"><%=p.getTipoAditivo() %></td>
				<td class="text-center">
					<form action="sistema?logica=EditarPrevia" method="post">
						<div style="display: none">
							<input name="i" value="<%=previaProcessos.indexOf(p)%>">
						</div>
						<button type="submit" name="your_name" class="btn-link">Remover</button>
					</form>
				</td>
			</tr>
		<%}%> <!-- fim do if do for que mostra os contratos recentes -->
		</tbody>
	</table>
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>