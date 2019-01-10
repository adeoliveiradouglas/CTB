<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>${sessionScope.contratoVisualizar.nomeEmpresaContratada} - CNPJ: ${sessionScope.contratoVisualizar.cnpjEmpresaContratada}</h3>
	</div>
	
	<table class="table table-bordered table-striped">
		<tbody>
			<tr>
				<td rowspan="2">Número: ${sessionScope.contratoVisualizar.numero}</td>
				<td>Portaria: ${sessionScope.contratoVisualizar.portaria}</td>
				<td>Gestor: ${sessionScope.contratoVisualizar.gestor.nome}</td>
				<td>Fiscal: ${sessionScope.contratoVisualizar.fiscal.nome}</td>
				
			</tr>
			<tr>
				<td>Recurso: ${sessionScope.contratoVisualizar.recurso.nome}</td>
				<td>Uso: ${sessionScope.contratoVisualizar.uso.nome}</td>
				<td>Fonte pagante: ${sessionScope.contratoVisualizar.fontePagante.nome}</td>
			</tr>
			<tr>
				<td>Data de assinatura: ${sessionScope.contratoVisualizar.dataAssinaturaAsString}</td>
				<td>Valor inicial: R$ ${sessionScope.contratoVisualizar.valorInicialAsString}</td>
				<td>Valor dos aditivos: R$ ${sessionScope.contratoVisualizar.valorAditivoAsString}</td>
				<td>Valor total: R$ ${sessionScope.contratoVisualizar.valorTotalAsString}</td>
			</tr>
			<tr>
				<td>Ass. ordem de serviço: ${sessionScope.contratoVisualizar.dataOrdemServicoAsString}</td>
				<td>Ass. garantia: ${sessionScope.contratoVisualizar.dataGarantiaAsString}</td>
				<td>Vencimento do contrato: ${sessionScope.contratoVisualizar.dataVencimentoContratoAsString}</td>
				<td>Vencimento da garantia: ${sessionScope.contratoVisualizar.dataVencimentoGarantiaAsString}</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-bordered table-striped">
		<tr>
			<td>Objeto: ${sessionScope.contratoVisualizar.objeto}</td>
		</tr>
	</table>
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Planilha</h3>
	</div>
	
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center sticky-header">Item</th>
				<th class="text-center sticky-header">Nº processo</th>
				<th class="text-center sticky-header">Referência</th>
				<th class="text-center sticky-header">Nota fiscal</th>
				<th class="text-center sticky-header">Valor</th>
				<th class="text-center sticky-header">Saldo</th>
				<th class="text-center sticky-header">Aditivo</th>
				<th class="text-center sticky-header">Observação</th>
				<th class="text-center sticky-header">Pagamento</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dados" items="${sessionScope.contratoVisualizar.dados}" varStatus="posicao">
				<tr>
					<td>
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
					<td>${dados.numeroSei}</td>
					<td>${dados.mes}/${dados.ano}</td>
					<td>${dados.notaFiscal}</td>
					<td>${dados.valorAsString}</td>
					<td>${dados.saldoAsString}</td>
					<td>${dados.aditivoAsString}</td>
					<td>${dados.tipoAditivo}</td>
					<td>${dados.dataPagamentoAsString}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div align="center">
		<a href="sistema?logica=VerResumoContrato">
			<font size="5">Ver resumo do contrato</font>
		</a>
	</div>
	<br /><br />