<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<style>
	td{
		text-align: center;
	}
</style>
<body>
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>${sessionScope.contratoVisualizar.nomeEmpresaContratada} - CNPJ: ${sessionScope.contratoVisualizar.cnpjEmpresaContratada}</h3>
	</div>
	
	<table class="table table-striped">
		<tbody>	
			<tr>
				<td><h2>Porcentagem concluída: ${porcentagemConcluida}% </h2></td>
				<td> </td>
				<td><h2>Porcentagem a concluir: ${porcentagemAConcluir}% </h2></td>
			</tr>
			<tr>
				<td>Total pago: R$${valorTotalPago}</td>
				<td>Valor à pagar: R$${valorAPagar}</td>
				<td>Valor do contrato: R$${sessionScope.contratoVisualizar.valorTotalAsString}</td>
			</tr>
		</tbody>
	</table>	
	
	<c:forEach var="rAno" items="${anos}">
		<table class="table table-bordered table-striped">
			<tbody>	
				<tr><td colspan="3">${rAno.ano}</td></tr>			
				<tr>
					<td>Total: R$${rAno.totalAsString}</td>
					<td>Saldo: R$${rAno.saldoAsString}</td>
					<td>Aditivo: R$${rAno.aditivoAsString}</td>
				</tr>
			</tbody>
		</table>
	</c:forEach>
		
	
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>