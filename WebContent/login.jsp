<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:th="http://www.thymeleaf.org"
	  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />

<title>Sistema de Gest�o de Contratos</title>

<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />

</head>

<body class="aw-layout-simple-page">
	<div class="aw-layout-simple-page__container">

		<div align="center">
			<img
				src="http://www.ctb.ba.gov.br/themes/admindireta/images/logo-ctb.png" />
			<!--<p>Por favor, fa�a o login.</p>-->
		</div>

		<br />
		<form action="sistema?logica=Login" th:object="${userdetails}" method="POST">
			<div class="aw-simple-panel">
				<div class="aw-simple-panel__box">
					<div class="form-group  has-feedback">
						<input type="email" class="form-control  input-lg" placeholder="Seu e-mail" name="email" required/> 
						<span class="glyphicon  glyphicon-envelope  form-control-feedback" aria-hidden="true"></span>
					</div>

					<div class="form-group  has-feedback">
						<input type="password" class="form-control  input-lg" placeholder="Sua senha" name="senha" required/> 
						<span class="glyphicon  glyphicon-lock  form-control-feedback" aria-hidden="true"></span>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Entrar</button>
					</div>

					<div class="form-group clearfix">
						<div class="checkbox  pull-left  aw-checkbox-no-margin">
							<input type="checkbox" id="lembrar" /> 
							<label for="lembrar">Lembre de mim</label>
						</div>

						<div class="pull-right">
							<a href="esqueceuasenha.jsp">Esqueci minha senha</a>
						</div>
					</div>
				</div>

				<div class="aw-simple-panel__footer">
					Novo por aqui? <a href="cadastrousuario.jsp">Cadastre-se</a>.					
				</div>
			</div>
		</form>
	</div>
	
	<jsp:include page="adds/Rodape.jsp"></jsp:include>
</body>
</html>