<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" /> -->

<title>Sistema de Gestão de Contratos da CTB</title>

<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

<style>
	select{
		width:100%;
	}
</style>

</head>

<body class="aw-layout-simple-page">
	<div class="aw-layout-simple-page__container">
		<div align="center">
			<img
				src="http://www.ctb.ba.gov.br/themes/admindireta/images/logo-ctb.png" />
		</div>
		<br />
		<form action="sistema?logica=CadastrarUsuario" th:object="${userdetails}" method="POST">
			<div class="aw-simple-panel">
				<div class="aw-simple-panel__box">
					<div class="form-group  has-feedback">
						<input type="number" class="form-control input-lg" placeholder="Sua matrícula" name="matricula" required> 
						<span class="form-control-feedback"	aria-hidden="true"> </span>
					</div>

					<div class="form-group  has-feedback">
						<input type="text" class="form-control input-lg" placeholder="Seu nome completo" name="nome" required> 
						<span class="form-control-feedback" aria-hidden="true"> </span>
					</div>

					<div class="form-group  has-feedback">
						<input type="email" class="form-control input-lg" placeholder="Seu e-mail corporativo" name="email" required>
						<span class="form-control-feedback" aria-hidden="true"></span>
					</div>

					<div class="form-group has-feedback">
						<input type="password" class="form-control input-lg" placeholder="Crie sua senha" name="senha" id="senha">
						<span class="form-control-feedback" aria-hidden="true"></span>
						<div id="mensagemsenha">
							<label>Senha deve ter mais que 6 caracteres</label>
						</div>
					</div>
					
					<div class="form-group has-feedback" id="confirmacaosenhadiv">
						<input type="password" class="form-control input-lg" placeholder="Confirme sua senha" name="confirmacaosenha" id="confirmacaosenha">
						<span class="form-control-feedback" aria-hidden="true"></span>
						<div id="mensagemconfirmacaosenha">
							<label>Senhas não conferem</label>
						</div>
					</div>
					
					<br />
					<!-- Mostrar as opções de cargos -->
					<div  class="form-group custom-select has-feedback">
					  <select name="cargo" id="cargoselect">
					  	<option style="display: none">Selecione seu cargo:</option>
					  	<%@ page import="dao.CargoDAO,
					  					 entity.Cargo,
					  					 java.util.ArrayList" %>
					  	
					  	<%
					  		CargoDAO cdao = new CargoDAO("gestaodecontratos", "servidorApp", "suporte2017");
					  		ArrayList<Cargo> cargos = cdao.getAll();
					  		
					  		for (Cargo c: cargos){
					  	%>
					  	<option value="<%=c.getNome()%>">
					  		<%=c.getNome() %> - <%=c.getDescricao() %>					  	
					  	</option>
					  	<%
					  		}
					  	%>
					  </select> <!-- select cargos -->
					</div> <!-- fim div select cargos -->
					
					<!-- Mostrar as opções de setores -->
					<div class="form-group custom-select has-feedback">
					  <select name="setor">
					  	<option style="display: none">Selecione seu setor:</option>
					  	<%@ page import="dao.SetorDAO,
					  					 entity.Setor,
					  					 java.util.ArrayList" %>
					  	
					  	<%
					  		SetorDAO sdao = new SetorDAO("gestaodecontratos", "servidorApp", "suporte2017");
					  		ArrayList<Setor> setores = sdao.getAll();
					  		
					  		for (Setor s: setores){
					  	%>
					  	<option value="<%=s.getSigla()%>">
					  		<%=s.getSigla() %> - <%=s.getNome() %>					  						  	
					  	</option>
					  	<%
					  		}
					  	%>
					  </select> <!-- select setores -->
					</div> <!-- fim div select setores -->
					
				</div>
				
				<br/>
				<div class="form-group" id="botaocadastrar">
					<button type="submit"
						class="btn btn-primary btn-lg aw-btn-full-width">Cadastrar</button>
				</div>
				<div class="aw-simple-panel__footer"><br/></div>
			</div>
		</form>
		
	<script type="text/javascript" src="script.js"></script>
	</div>
</body>
</html>