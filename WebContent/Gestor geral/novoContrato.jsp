<!-- Página do Gestor Geral -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
	<form action="sistema?logica=CadastrarContrato" method="post">
		<div class="aw-simple-panel__box">
			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg" placeholder="Número" name="numero" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg" placeholder="Empresa" name="nomeEmpresa" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg" placeholder="CNPJ da empresa" name="cnpjEmpresa" maxlength="18" onkeyup="MascaraCnpj(this)" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg" placeholder="Objeto" name="objeto" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				<input type="number" class="form-control input-lg" placeholder="Portaria" name="portaria" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				<input type="text" class="form-control input-lg" placeholder="Valor inicial" id="valor" name="valor" maxlength="14" onkeyup="MascaraMoeda(this)" required/>
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			
			<div class="form-group custom-select has-feedback">
				<select name="gestor" id="gestor">
					<option style="display: none">Selecione o gestor:</option>

					<c:forEach var="g" items="${sessionScope.gestores}" varStatus="posicao">
							<option value="${posicao.index}">
								${g.nome} - ${g.setor.sigla}
							</option>
					</c:forEach>

				</select>
				<!-- select gestor -->
			</div><!-- fim div select gestor -->			
			
			<div class="form-group custom-select has-feedback">
				<select name="fiscal" id="fiscal">
					<option style="display: none">Selecione o fiscal:</option>

					<c:forEach var="fiscal" items="${sessionScope.gestores}" varStatus="posicao">
						<option value="${posicao.index}">
							${fiscal.nome} - ${fiscal.setor.sigla}
						</option>
					</c:forEach>

				</select>
				<!-- select fiscal -->
			</div><!-- fim div select fiscal -->
			
			<div class="form-group custom-select has-feedback">
				<select name="recurso" id="recurso">
					<option style="display: none">Recurso:</option>
					
					<c:forEach var="recurso" items="${sessionScope.recurso}" varStatus="posicao">
						<option value="${posicao.index}">
							${recurso.nome}
						</option>
					</c:forEach>

				</select> <!-- select recurso -->
			</div>
			
			<div  class="form-group custom-select has-feedback">
			  <select name="uso" id="uso">
			  	<option style="display: none">Uso:</option>	
			  	
			  		<c:forEach var="uso" items="${sessionScope.uso}" varStatus="posicao">
						<option value="${posicao.index}">
							${uso.nome}
						</option>
					</c:forEach>
					
			  </select> <!-- select uso -->
			</div> <!-- fim div select uso -->
			
			<div  class="form-group custom-select has-feedback">
			  <select name="fontepagante" id="fontepagante">
			  	<option style="display: none">Fonte pagante:</option>	
			  		
			  		<c:forEach var="fp" items="${sessionScope.fontepagante}" varStatus="posicao">
						<option value="${posicao.index}">
							${fp.nome}
						</option>
					</c:forEach>
					
			  </select> <!-- select fonte pagante -->
			</div> <!-- fim div select fonte pagante  -->
			
			<div class="form-group  has-feedback">
				Data de assinatura:
				<input type="date" class="form-control input-lg" name="dataAssinatura" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				Data da ordem de serviço:
				<input type="date" class="form-control input-lg" name="dataOs" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				Data da garantia:
				<input type="date" class="form-control input-lg" name="dataGarantia" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				Data de vencimento do contrato:
				<input type="date" class="form-control input-lg" name="dataVencimento" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
			<div class="form-group  has-feedback">
				Data de vencimento da garantia:
				<input type="date" class="form-control input-lg" name="dataVencimentoGarantia" required> 
				<span class="form-control-feedback"	aria-hidden="true"> </span>
			</div>
						
			<div class="form-group">
				<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Concluir</button>
			</div>
		</div>
	</form>
	
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
	
	<script type="text/javascript" src="js/campoCnpj.js"></script>
	<script type="text/javascript" src="js/campoValor.js"></script>
</body>
</html>