<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	th.sticky-header {
	  position: sticky;
	  top: 5rem;
	  z-index: 10;
	  background-color: white;
	}
</style>	
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>${sessionScope.contratoVisualizar.nomeEmpresaContratada} - CNPJ: ${sessionScope.contratoVisualizar.cnpjEmpresaContratada}</h3>
	</div>
	
	<table class="table table-bordered table-striped">
		<tbody>
			<tr>
				<td rowspan="2" class="text-center">N�mero: ${sessionScope.contratoVisualizar.numero}</td>
				<td class="text-center">Portaria: ${sessionScope.contratoVisualizar.portaria}</td>
				<td class="text-center">Gestor: ${sessionScope.contratoVisualizar.gestor.nome}</td>
				<td class="text-center">Fiscal: ${sessionScope.contratoVisualizar.fiscal.nome}</td>
				
			</tr>
			<tr>
				<td class="text-center">Recurso: ${sessionScope.contratoVisualizar.recurso.nome}</td>
				<td class="text-center">Uso: ${sessionScope.contratoVisualizar.uso.nome}</td>
				<td class="text-center">Fonte pagante: ${sessionScope.contratoVisualizar.fontePagante.nome}</td>
			</tr>
			<tr>
				<td class="text-center">Data de assinatura: ${sessionScope.contratoVisualizar.dataAssinaturaAsString}</td>
				<td class="text-center">Valor inicial: R$ ${sessionScope.contratoVisualizar.valorInicialAsString}</td>
				<td class="text-center">Valor dos aditivos: R$ ${sessionScope.contratoVisualizar.valorAditivoAsString}</td>
				<td class="text-center">Valor total: R$ ${sessionScope.contratoVisualizar.valorTotalAsString}</td>
			</tr>
			<tr>
				<td class="text-center">Ass. ordem de servi�o: ${sessionScope.contratoVisualizar.dataOrdemServicoAsString}</td>
				<td class="text-center">Ass. garantia: ${sessionScope.contratoVisualizar.dataGarantiaAsString}</td>
				<td class="text-center">Vencimento do contrato: ${sessionScope.contratoVisualizar.dataVencimentoContratoAsString}</td>
				<td class="text-center">Vencimento da garantia: ${sessionScope.contratoVisualizar.dataVencimentoGarantiaAsString}</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-bordered table-striped">
		<tr>
			<td>Objeto: ${sessionScope.contratoVisualizar.objeto}</td>
		</tr>
	</table>
	<%-- <div class="teste">
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th class="text-center">Item</th>
					<th class="text-center">N� processo</th>
					<th class="text-center">Refer�ncia</th>
					<th class="text-center">Nota fiscal</th>
					<th class="text-center">Valor</th>
					<th class="text-center">Saldo</th>
					<th class="text-center">Aditivo</th>
					<th class="text-center">Observa��o</th>
					<th class="text-center">Pagamento</th>
				</tr>
			</thead>
		
			<tbody>
				<tr>
					<td class="text-center">
						<form action="sistema?logica=VerDados" method="post">
							<div style="display: none">
								<input name="origem" value="contratoVisualizar">
							</div>
							<div style="display: none">
								<input name="i" value="0">
							</div>
							<button type="submit" name="your_name" class="btn-link">${sessionScope.contratoVisualizar.dados.get(0).item}</button>
						</form>
					</td>
					<td class="text-center">${sessionScope.contratoVisualizar.dados.get(0).numeroSei}</td>
					<td class="text-center">${sessionScope.contratoVisualizar.dados.get(0).mes}/${sessionScope.contratoVisualizar.dados.get(0).ano}</td>
					<td class="text-center">${sessionScope.contratoVisualizar.dados.get(0).notaFiscal}</td>
					<td class="text-center">${sessionScope.contratoVisualizar.dados.get(0).valorAsString}</td>
					<td class="text-center">${sessionScope.contratoVisualizar.dados.get(0).saldoAsString}</td>
					<td class="text-center">${sessionScope.contratoVisualizar.dados.get(0).aditivoAsString}</td>
					<td class="text-center">${sessionScope.contratoVisualizar.dados.get(0).tipoAditivo}</td>
					<td class="text-center">${sessionScope.contratoVisualizar.dados.get(0).dataPagamentoAsString}</td>
				</tr>
			</tbody>
		</table>
	</div> --%>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Planilha</h3>
	</div>
	
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center sticky-header">Item</th>
				<th class="text-center sticky-header">N� processo</th>
				<th class="text-center sticky-header">Refer�ncia</th>
				<th class="text-center sticky-header">Nota fiscal</th>
				<th class="text-center sticky-header">Valor</th>
				<th class="text-center sticky-header">Saldo</th>
				<th class="text-center sticky-header">Aditivo</th>
				<th class="text-center sticky-header">Observa��o</th>
				<th class="text-center sticky-header">Pagamento</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dados" items="${sessionScope.contratoVisualizar.dados}" varStatus="posicao">
				<tr>
					<td class="text-center">
						<form action="sistema?logica=VerDados" method="post">
							<div style="display: none">
								<input name="origem" value="contratoVisualizar">
							</div>
							<div style="display: none">
								<input name="i" value="${posicao.index}">
							</div>
							<button type="submit" name="your_name" class="btn-link">${dados.item}</button>
						</form>
					</td>
					<td class="text-center">${dados.numeroSei}</td>
					<td class="text-center">${dados.mes}/${dados.ano}</td>
					<td class="text-center">${dados.notaFiscal}</td>
					<td class="text-center">${dados.valorAsString}</td>
					<td class="text-center">${dados.saldoAsString}</td>
					<td class="text-center">${dados.aditivoAsString}</td>
					<td class="text-center">${dados.tipoAditivo}</td>
					<td class="text-center">${dados.dataPagamentoAsString}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>