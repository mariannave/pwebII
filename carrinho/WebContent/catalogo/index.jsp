<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Catalogo</title>
</head>

<body>
	<div class="container">
		<h1>Catálogo</h1>
		<table class="table table-hover">
			<th>Descrição</th>
			<th>Valor</th>
			<th>Comprar</th>
			<c:forEach var="produto" items="${produtos}">
				<tr>
					<form method="POST" action="${pageContext.request.contextPath}/pedido">
						<td>${produto.descricaoCurta}</td>
						<td>R$ ${produto.preco}</td>
						<td><input type="hidden"  value="${produto.itemID}" name="id">
							<button class="btn">Adicionar</button>
						</td> 
					</form>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>