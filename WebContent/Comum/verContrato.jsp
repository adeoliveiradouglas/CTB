<!-- Ver contrato do gestor -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<title>Sistema de Gestão de Contratos</title>

</head>
<body>
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	<c:if test="${relatorioGerado == true}">
		<div style="background-color: #5cb85c; color: white" align="center">
		Veja o relatório do contrato <a href="/gestaodecontratos/Comum/relatorios/relatorioContrato${sessionScope.contratoVisualizar.id}.pdf" target="_blank">aqui</a>.
		</div>
	</c:if>
	
	<table class="table" style="border: none">
	<tbody>	
		
		<tr>
			<td>
				<form action="sistema?logica=VerResumoContrato" method="post">
					<button class="btn btn-primary btn-lg aw-btn-full-width" type="submit">
						Ver resumo do contrato
					</button>
				</form>
			</td>	
			<td>
				<form action="sistema?logica=ExportarPDF" method="post">
					<button class="btn btn-primary btn-lg aw-btn-full-width" type="submit">
						Exportar para PDF
					</button>
				</form>
			</td>
					
		</tr>
	</tbody>
	</table>	
	
	<jsp:include page="../Comum/avisoDeVencimento.jsp"></jsp:include>
	<jsp:include page="../Comum/planilha.jsp"></jsp:include>
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>