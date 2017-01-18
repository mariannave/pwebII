<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Resumo da compra</title>
</head>
<body>
	<div class="container">
		<h1 class="page-header">Resumo da compra</h1>
		<div class="row">	
			<div class="col-xs-5 well well-sm">
			<h2 class="page-header">Carrinho</h2>
				<table class="table table-hover">
					<th>Item</th>
					<th>Quantidade de itens</th>
					<th>Valor total</th>
					<tr>
					<c:forEach var="produto" items="${itens}">
							<form class="form-control" method="POST">
								<td>${produto.item.descricaoCurta}</td>
								<td>${produto.numItens}</td>
							</form>
					</c:forEach>
						<td></td>
						<td></td>
						<td>VALOR TOTAL AQUI</td>
					</tr>
				</table>
			</div>	
			<div class="col-xs-1">
			<p></p>
			</div>
			<div class="col-xs-5 well well-sm">
				<h2 class="page-header">Dados</h2>
				<form class="form-group">
				<div class="form-group row">
				  <label for="example-text-input" class="col-xs-2 col-form-label">Nome</label>
				  <div class="col-xs-10">
				    <input class="form-control" type="text"  id="nome">
				  </div>
				</div>
				<div class="form-group row">
				  <label for="example-text-input" class="col-xs-2 col-form-label">Endereço</label>
				  <div class="col-xs-10">
				    <input class="form-control" type="text"  id="endereco">
				  </div>
				</div>
				<div class="form-group row">
				  <label for="example-tel-input" class="col-xs-2 col-form-label">Telefone</label>
				  <div class="col-xs-10">
				    <input class="form-control" type="tel"  id="telefone">
				  </div>
				</div>
				<fieldset class="form-group">
				    <legend>Forma de envio</legend>
				    <div class="form-check">
				      <label class="form-check-label">
				        <input type="radio" class="form-check-input" name="opcaoEnvio" id="1" value="option1"> Sedex 10
				      </label>
				    </div>
				    <div class="form-check">
				    <label class="form-check-label">
				        <input type="radio" class="form-check-input" name="opcaoEnvio" id="2" value="option2"> Convencional
				      </label>
				    </div>
				  </fieldset>				
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<button class="btn"><a href="${pageContext.request.contextPath}/pedido">Voltar para o carrinho</a></button>
			</div>			
			<div class="col-xs-6">
				<button class="btn"><a href="resumo">Finalizar compra</a></button>
			</div>			
		</div>		
	</div>
</body>
</html>