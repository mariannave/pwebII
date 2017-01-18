<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Pedidos</title>
</head>
<body>
	<div class="container">
		<h1>PEDIDOS</h1>
		<table class="table table-hover">
			<th>Item</th>
			<th>Quantidade de itens</th>
			<th>Nova quantidade</th>
			<th></th>
			<c:forEach var="produto" items="${itens}">
				<tr>
					<form class="form-control" method="POST">
						<td>${produto.item.descricaoCurta}</td>
						<td>${produto.numItens}</td>
						<td><input type="number" value="${produto.numItens}" placeholder=" " name="novaQtde"></td>
						<td><input type="hidden"  value="${produto.item.itemID}" name="id">
							<button class="btn">Atualizar quantidade</button>
						</td> 
					</form>
				</tr>
			</c:forEach>
		</table>
		<button class="btn"><a href="catalogo">Voltar para o catálogo</a></button>
		<button class="btn"><a href="resumo">Resumo da compra</a></button>
	</div>
</body>
</html>