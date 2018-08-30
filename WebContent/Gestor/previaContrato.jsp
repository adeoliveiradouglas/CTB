<!-- Ver previa de contrato do gestor -->
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
	<div style="background-color: #1e94d2; color: white" align="center">
		<h1>Prévia do contrato</h1>
	</div>
	<%@ page import="entity.Contrato,
					 entity.Processo,
					 java.util.ArrayList,
					 utilidades.FormatarCampo" %>
		
	
	<%		 
	String adicionaProcesso = request.getParameter("adicionaProcesso");
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
				<td class="text-center col-md-1">Data de assinatura: <%=contrato.getDataAssinaturaAsString() %></td>
				<td class="text-center col-md-1">Valor inicial: R$ <%=contrato.getValorInicialAsString() %></td>
				<td class="text-center col-md-1">Valor dos aditivos: R$ <%=contrato.getValorAditivoAsString() %></td>
				<td class="text-center col-md-1">Valor total: R$ <%=contrato.getValorTotalAsString()%></td>
			</tr>
			<tr>
				<td class="text-center col-md-1">Ass. ordem de serviço: <%=contrato.getDataOrdemServicoAsString() %></td>
				<td class="text-center col-md-1">Ass. garantia: <%=contrato.getDataGarantiaAsString() %></td>
				<td class="text-center col-md-1">Vencimento do contrato: <%=contrato.getDataVencimentoContratoAsString() %></td>
				<td class="text-center col-md-1">Vencimento da garantia: <%=contrato.getDataVencimentoGarantiaAsString() %></td>
			</tr>
		</tbody>
	</table>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Processos</h3>
	</div>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-1">N° processo</th>
				<th class="text-center col-md-1">Med</th>
				<th class="text-center col-md-1">Mês refer.</th>
				<th class="text-center col-md-1">Ano refer.</th>
				<th class="text-center col-md-1">Nota fiscal</th>
				<th class="text-center col-md-1">Data</th>
				<th class="text-center col-md-1">Valor</th>
				<th class="text-center col-md-1">Aditivo</th>
				<th class="text-center col-md-2">Objeto</th>
				<th class="text-center col-md-1"></th><th class="text-center col-md-1"></th>
			</tr>
		</thead>
		<tbody>
		<%for(Processo p: previaProcessos){
			String pagamento = "";
			
			if(p.getDataPagamento() != null){
				pagamento = p.getDataPagamentoAsString();
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
						<button type="submit" class="btn-link"><%=p.getNumeroSei()%></button>
					</form>
				</td>
				<td class="text-center"><%=previaProcessos.indexOf(p)+1 %></td>
				<td class="text-center"><%=p.getMes()%></td>
				<td class="text-center"><%=p.getAno() %></td>
				<td class="text-center"><%=p.getNotaFiscal() %></td>
				<td class="text-center"><%=p.getDataProcessoAsString()  %></td>
				<td class="text-center"><%=p.getValorAsString() %></td>
				<td class="text-center"><%=p.getAditivoAsString() %></td>
				<td class="text-center"><%=p.getTipoAditivo() %></td>
				<td class="text-center">
					<form action="sistema?logica=EditarPrevia" method="post">
						<div style="display: none">
							<input name="i" value="<%=previaProcessos.indexOf(p)%>">
						</div>
						<div style="display: none">
							<input name="acao" value="telaEditar">
						</div>
						<button type="submit" class="btn-link">Editar</button>
					</form>
				</td>
				<td class="text-center">
					<form action="sistema?logica=EditarPrevia" method="post">
						<div style="display: none">
							<input name="i" value="<%=previaProcessos.indexOf(p)%>">
						</div>
						<div style="display: none">
							<input name="acao" value="remover">
						</div>
						<button type="submit" class="btn-link">Remover</button>
					</form>
				</td>
			</tr>
		<%}%> <!-- fim do if do for que mostra os contratos recentes -->
		</tbody>
	</table>
	<form action="sistema?logica=EditarPrevia" method="post">
		<div style="display: none">
			<input name="acao" value="aprovar">
		</div>
		<div class="form-group  has-feedback">
			Nova data de vencimento (deixe vazia se não for necessário alterar): 
			<input type="date"
				class="form-control input-lg" name="novaDataVencimento"> <span
				class="form-control-feedback" aria-hidden="true"> </span>
		</div>
		<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Aprovar</button>
	</form>
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>