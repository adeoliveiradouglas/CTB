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

</head>
<body class="aw-layout-page">
	<jsp:include page="../adds/Cabecalho.jsp"></jsp:include>
	
	<%@ page import="entity.Usuario,
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
				@SuppressWarnings("unchecked") /*GAMBIARRA PARA TIRAR WARNING DA LINHA ABAIXO*/
				ArrayList<Usuario> lun = (ArrayList<Usuario>) request.getSession().getAttribute("usuariosnovos");
			
				for (Usuario u: lun){
			%>
			<tr>
				<%-- <td class="text-center"><%=u.getId() %></td> --%>
				<td class="text-center"><%=u.getNome() %></td>
				<td class="text-center"><%=u.getMatricula() %></td>
				<td class="text-center"><%=u.getCargo().getNome() %></td>
				<td class="text-center"><%=u.getSetor().getSigla() %></td>
				<td class="text-center"><%=u.getEmail() %></td>
				<td class="text-center">
					<form action="sistema?logica=AutorizarNovoUsuario" method="post">
						<button value="<%=u.getId()%>" name="id">Autorizar</button>
					</form>
				</td>
				<td class="text-center">
					<form action="sistema?logica=RecusarNovoUsuario" method="post">
						<button value="<%=u.getId()%>" name="id">Recusar</button>
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
			@SuppressWarnings("unchecked") /*GAMBIARRA PARA TIRAR WARNING DA LINHA ABAIXO*/
			ArrayList<Usuario> lu = (ArrayList<Usuario>) request.getSession().getAttribute("usuarios");
		
			for (Usuario u: lu){
			%>
			<tr>
				<td class="text-center"><%=u.getNome() %></td>
				<td class="text-center"><%=u.getMatricula() %></td>
				<td class="text-center"><%=u.getCargo().getNome() %></td>
				<td class="text-center"><%=u.getSetor().getSigla() %></td>
				<td class="text-center"><%=u.getEmail() %></td>
				<td class="text-center">
					<form action="sistema?logica=TelaEditarUsuario" method="post">
						<button value="<%=u.getId()%>" name="id">Editar</button>
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