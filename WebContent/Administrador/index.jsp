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
	<jsp:include page="../cabecalho/Cabecalho.jsp"></jsp:include>
	
	<%@ page import="dao.UsuarioNovoDAO,
					 dao.UsuarioDAO,
			         entity.Usuario,
					 java.util.ArrayList" %>	
 
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Novos usuários que precisam de autorização</h3>
	</div>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-2">Nome</th>
				<th class="text-center col-md-1">Matrícula</th>
				<th class="text-center col-md-1">Cargo</th>
				<th class="text-center col-md-1">Setor</th>
				<th class="text-center col-md-2">Login</th>
				<th class="col-md-1"></th><th class="col-md-1"></th>
			</tr>

		</thead>
		<tbody>
			<%
				UsuarioNovoDAO undao = new UsuarioNovoDAO();
				ArrayList<Usuario> usuariosnovos = undao.getAll();
					  		
				for (Usuario u: usuariosnovos){
			%>
			<tr>
				<td class="text-center"><%=u.getNome() %></td>
				<td class="text-center"><%=u.getMatricula() %></td>
				<td class="text-center"><%=u.getCargo() %></td>
				<td class="text-center"><%=u.getSetor() %></td>
				<td class="text-center"><%=u.getEmail() %></td>
				<td class="text-center">
					<form action="sistema?logica=AutorizarNovoUsuario" method="post">
						<button value="<%=u.getEmail()%>" name="email">Autorizar</button>
					</form>
				</td>
				<td class="text-center">
					<form action="sistema?logica=RecusarNovoUsuario" method="post">
						<button value="<%=u.getMatricula()%>" name="matricula">Recusar</button>
					</form>
				</td>
			</tr>
			<%
				} //fim do for
			%>
			
		</tbody>
	</table>
	
	
	<!-- Mostra opções de todos os usuários do sistema -->
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Todos os usuários</h3>
	</div>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-2">Nome</th>
				<th class="text-center col-md-1">Matrícula</th>
				<th class="text-center col-md-1">Cargo</th>
				<th class="text-center col-md-1">Setor</th>
				<th class="text-center col-md-2">Login</th>
				<th class="col-md-1"></th>
			</tr>

		</thead>
		<tbody>
			<%
				UsuarioDAO udao = new UsuarioDAO();
				ArrayList<Usuario> usuarios = udao.getAll();
					  		
				for (Usuario u: usuarios){
			%>
			<tr>
				<td class="text-center"><%=u.getNome() %></td>
				<td class="text-center"><%=u.getMatricula() %></td>
				<td class="text-center"><%=u.getCargo() %></td>
				<td class="text-center"><%=u.getSetor() %></td>
				<td class="text-center"><%=u.getEmail() %></td>
				<td class="text-center">
					<form action="sistema?logica=TelaEditarUsuario" method="post">
						<button value="<%=u.getEmail()%>" name="email">Editar</button>
					</form>
				</td>
			</tr>
			<%
				}
			%>
			
		</tbody>
	</table>
	
	</body>
</html>