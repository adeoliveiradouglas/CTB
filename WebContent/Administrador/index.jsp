<!-- Página principal do Administrador -->
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
<meta charset="ISO-8859-1" />

<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />
<style>
	btn-link {
		border: none;
		outline: none;
		background: none;
		cursor: pointer;
		color: #0000EE;
		padding: 0;
		text-decoration: underline;
		font-family: inherit;
		font-size: inherit;
	}
</style>
</head>
<body class="aw-layout-page">
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	
	<%@ page import="entity.Usuario,
					 entity.Cargo,
					 java.util.ArrayList" %>	
 
 	<%
 	@SuppressWarnings("unchecked") /*GAMBIARRA PARA TIRAR WARNING DA LINHA ABAIXO*/
 	ArrayList<Usuario> lun = (ArrayList<Usuario>) request.getSession().getAttribute("usuariosnovos");
 	
 	if(lun.size() > 0){ //if para mostrar sessão de novos usuários
	%>
	
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Novos usuários que precisam de autorização</h3>
	</div>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-2">
					<a href="sistema?logica=TelaPrincipalAdministrador&ordUser=nome">Nome</a>
				</th>
				<th class="text-center col-md-1">
					<a href="sistema?logica=TelaPrincipalAdministrador&ordUser=matricula">Matrícula</a>
				</th>
				<th class="text-center col-md-1">
					<a href="sistema?logica=TelaPrincipalAdministrador&ordUser=cargo_id">Cargo</a>
				</th>
				<th class="text-center col-md-1">
					<a href="sistema?logica=TelaPrincipalAdministrador&ordUser=setor_codigo">Setor</a>
				</th>
				<th class="text-center col-md-2">
					<a href="sistema?logica=TelaPrincipalAdministrador&ordUser=login">Login</a>
				</th>
				<th class="col-md-1"></th><th class="col-md-1"></th>
			</tr>
		</thead>
		<tbody>
			<%for (Usuario u: lun){%>
			<tr>
				<%-- <td class="text-center"><%=u.getId() %></td> --%>
				<td class="text-center"><%=u.getNome() %></td>
				<td class="text-center"><%=u.getMatricula() %></td>
				<td class="text-center"><%=u.getCargo().get(0).getNome() %></td>
				<td class="text-center"><%=u.getSetor().getSigla() %></td>
				<td class="text-center"><%=u.getEmail() %></td>
				<td class="text-center">
					<form action="sistema?logica=AutorizarNovoUsuario" method="post">
						<button class="btn-link" value="<%=lun.indexOf(u)%>" name="id">Autorizar</button>
					</form>
				</td>
				<td class="text-center">
					<form action="sistema?logica=RemoveUsuario&tabela=usuariosnovos" method="post">
						<button class="btn-link" value="<%=u.getId()%>" name="id">Recusar</button>
					</form>
				</td>
			</tr>
			<%} //fim do for%>			
		</tbody>
	</table>
	<%} //fim do if de mostrar novos usuários%> 
	
	<!-- Mostra opções de todos os usuários do sistema -->
	<div style="background-color: #1e94d2; color: white" align="center">
		<h3>Todos os usuários</h3>
	</div>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="text-center col-md-2">
					<a href="sistema?logica=TelaPrincipalAdministrador&ordUser=nome">Nome</a>
				</th>
				<th class="text-center col-md-1">
					<a href="sistema?logica=TelaPrincipalAdministrador&ordUser=matricula">Matrícula</a>
				</th>
				<th class="text-center col-md-1">
					<a href="sistema?logica=TelaPrincipalAdministrador&ordUser=cargo_id">Cargo</a>
				</th>
				<th class="text-center col-md-1">
					<a href="sistema?logica=TelaPrincipalAdministrador&ordUser=setor_codigo">Setor</a>
				</th>
				<th class="text-center col-md-2">
					<a href="sistema?logica=TelaPrincipalAdministrador&ordUser=login">Login</a>
				</th>
				<th class="col-md-1"></th><th class="col-md-1"></th>
			</tr>
		</thead>
		<tbody>
			<%
			@SuppressWarnings("unchecked") 
			ArrayList<Usuario> lu = (ArrayList<Usuario>) request.getSession().getAttribute("usuarios");
			for (Usuario u: lu){
			%>
			<tr>
				<td class="text-center"><%=u.getNome() %></td>
				<td class="text-center"><%=u.getMatricula() %></td>
				<td class="text-center"><%=u.getCargo().get(0).getNome() %> <%=u.getCargo().get(1).getNome() %></td>
				<td class="text-center"><%=u.getSetor().getSigla() %></td>
				<td class="text-center"><%=u.getEmail() %></td>
				<td class="text-center">
					<form action="sistema?logica=TelaEditarUsuario" method="post">
						<button class="btn-link" value="<%=lu.indexOf(u)%>" name="id">Editar</button>
					</form>
				</td>
				<td class="text-center">
					<form action="sistema?logica=RemoveUsuario&tabela=usuario" method="post">
						<button class="btn-link" value="<%=u.getId()%>" name="id">Deletar</button>
					</form>
				</td>
			</tr>
			<%
				}
			%>
			
		</tbody>
	</table>
	
	<jsp:include page="../adds/Rodape.jsp"></jsp:include>
	</body>
</html>