<!-- Página principal do Tesoureiro -->
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
<%@page import = "java.util.ArrayList,
				  entity.Processo" %>
				  
	<%
	@SuppressWarnings("unchecked")
	ArrayList <Processo> processosSemPagamento = (ArrayList<Processo>) request.getSession().getAttribute("processoSemPagamento");
	%>
	
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Processos que precisam de pagamento</h3>
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
			<%for(Processo p: processosSemPagamento){%>
			<tr>
				<th class="text-center"><%=p.getAno() %></th>
				<th class="text-center"><%=processosSemPagamento.indexOf(p)+1 %></th>
				<th class="text-center"><%=p.getMes()%></th>
				<th class="text-center"><%=p.getNotaFiscal() %></th>
				<th class="text-center"><%=p.getNumeroSei() %></th>
				<th class="text-center"><%=p.getDataProcesso() %></th>
				<th class="text-center"><%=p.getValor() %></th>
				<th class="text-center"><%=p.getAditivo() %></th>
				<th class="text-center"><%=p.getTipoAditivo() %></th>
				<th class="text-center">
					<a href="sistema?logica=PagarProcesso&n=<%=p.getNumeroSei() %>">
						<!-- Envia o id da lista onde o contrato está (n) e diz que a origem da chamada é da página principal (origem)-->
						Registrar pagamento
					</a>
				</th>
			</tr>
			<%}%> <!-- fim do if do for que mostra os contratos recentes -->
		</tbody>
	</table>
<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>