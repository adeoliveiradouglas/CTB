<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE c:choose PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<c:choose>
	<c:when test="${sessionScope.contratoVisualizar.diasParaVencimento <= 0}">
		<div style="background-color: black; color: white" align="center">
			<h1>Contrato vencido: ${sessionScope.contratoVisualizar.diasParaVencimento} dias</h1>
		</div>
	</c:when>
	
	<c:when test="${sessionScope.contratoVisualizar.diasParaVencimento <= 45}">
		<div style="background-color: red; color: white" align="center">
			<h1>Faltam ${sessionScope.contratoVisualizar.diasParaVencimento} dias para o vencimento do contrato</h1>
		</div>
	</c:when>
	
	<c:when test="${sessionScope.contratoVisualizar.diasParaVencimento <= 90}">
		<div style="background-color: orange; color: white" align="center">
			<h1>Faltam ${sessionScope.contratoVisualizar.diasParaVencimento} dias para o vencimento do contrato</h1>
		</div>
	</c:when>
</c:choose>
</body></html>
