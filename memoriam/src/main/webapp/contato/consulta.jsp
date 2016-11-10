<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Memoriam</title>
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/memoriam.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
</head>
<body>
			<c:if test="${not empty msgs}">
				<div align="left">
				<div style="color:red">
					<ul style ="padding-left:0px;">
						<c:forEach var="msg" items="${msgs}">
						<li style="list-style-type: none;">${msg}</li>
						</c:forEach>
					</ul>
				</div>
				</div>
			</c:if>	
	<div class="container">
		<div class="jumbotron">
			<h2>Memori<i class="glyphicon glyphicon-phone"></i>m</h2>
			<form action="${pageContext.request.contextPath}/controller.do?op=delctt" method="POST">
			<table class="table">
				<thead align="left">
					<th></th>
					<th style="width:30%">Nome</th>
					<th>Telefone</th>
					<th>Operadora</th>	
				</thead>
				
				
				<c:forEach var="contato" items="${contatos}">
				<tr align="left">
					<td><input type="checkbox" name="delete" value="${contato.id}" class="selectOp"/></td>
					<td><a href="controller.do?op=edtctt&id=${contato.id}">${contato.nome }</a></td>
					<td>${contato.fone}</td>
					<td>${contato.operadora.nome}</td>
				</tr>
				</c:forEach>
			</table>
				<button id="botaoDeletar" class="form-control btn btn-danger hidden">Deletar</button>
			</form>
			<a href="contato/cadastro.jsp" class="form-control btn btn-primary">Novo</a>
		</div>
	</div>
	<script>
		$(document).ready(function() {
				var enabled = false;
				var checkboxes = $(".selectOp");
				$(".selectOp").on("click", function(e) {
					
					if ($(this).is(":checked")) {
						enabled = true;
					} else {
						enabled = false;
					}
					
					if (enabled) {
						$("#botaoDeletar").removeClass("hidden");
					} else {
						
						for (var i = 0; i < checkboxes.length; i++) {
							if (checkboxes[i].checked) {
								return;
							}
						}
						$("#botaoDeletar").addClass("hidden");
					}
				})
			});
	</script>
</body>
</html>