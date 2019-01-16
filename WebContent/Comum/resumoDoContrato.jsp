<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<style>
	th.sticky-header {
	  position: sticky;
	  top: 5rem;
	  z-index: 10;
	  background-color: white;
	}
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
				<th class="text-center">Total pago: R$${valorTotalPago}</th>
				<th class="text-center">Valor à pagar: R$${valorAPagar}</th>
				<th class="text-center">Valor do contrato: R$${sessionScope.contratoVisualizar.valorTotalAsString}</th>
			</tr>
		</tbody>
	</table>	
	
	<c:forEach var="rAno" items="${anos}">
		<br />
		<div style="background-color: #a3a3a3; color: white" align="center">
			<h3>${rAno.marcacao}</h3>
		</div>
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th class="text-center sticky-header">Mês</th>
					<th class="text-center sticky-header">Total</th>
					<th class="text-center sticky-header">Saldo</th>
					<th class="text-center sticky-header">Aditivo</th>
				</tr>
			</thead>
			<tbody>	
				<c:forEach var="rMes" items="${rAno.meses}">
					<tr>
						<td>${rMes.marcacao}</td>
						<td>R$${rMes.totalAsString}</td>
						<td>R$${rMes.saldoAsString}</td>
						<td>R$${rMes.aditivoAsString}</td>
					</tr>
				</c:forEach>
				<tr>
					<td rowspan="2"><b>Ano: ${rAno.marcacao}</b></td>				
					<td><b>Total: R$${rAno.totalAsString}</b></td>
					<td><b>Saldo: R$${rAno.saldoAsString}</b></td>
					<td><b>Aditivo: R$${rAno.aditivoAsString}</b></td>
				</tr>
				<tr>			
					<td><b>Total do contrato: R$${rAno.totalAcumuladoAsString}</b></td>
					<td></td>
					<td><b>Aditivo do contrato: R$${rAno.aditivoAcumuladoAsString}</b></td>
				</tr>
			</tbody>
		</table>
	</c:forEach>
		
	
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
</body>
</html>