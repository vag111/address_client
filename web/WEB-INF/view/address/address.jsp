<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.Address"%>
<%@ page import="main.java.domain.City"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<title>Адреса</title>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<title>Address</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">
	<h3>Список адресов</h3>
	<div class="container">
		<table class="table table-hover">
			<thead class="table-primary">
			<tr>
				<th scope="col">Код</th>
				<th scope="col">Клиент</th>
				<th scope="col">Город</th>
				<th scope="col">Наименование улицы</th>
				<th scope="col">Номер строения, дома</th>
				<th scope="col">Номер офиса</th>
					<!-- <th scope="col">Редактировать</th>
					<th scope="col">Удалить</th> -->
			</tr>
			</thead>

			<tbody>
			<c:forEach var="address" items="${addresses}">
				<tr>
					<td>${address.getId()}</td>
					<td>${address.getPerson()}</td>
					<td>${address.getCity()}</td>
					<td>${address.getStreet()}</td>
					<td>${address.getBuilding()}</td>
					<td>${address.getOffice()}</td>
						<!-- <td width="20" id="td-edit">
							<a href='<c:url value="/editComputer?id=${city.getId()}" />'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Редактировать" src="images/icon-edit.png"></a>
						</td>
						<td width="20" id="td-delete">
							<a href='<c:url value="/deleteComputer?id=${city.getId()}" />'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Удалить" src="images/icon-delete.png"></a>
						</td> -->
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<h3>Добавить адрес</h3>
	<div class="container" id="container-form">
		<form method="POST" action="">
			<div class="row mb-3">
				<div class="col-sm-10">
					<input type="text" name="person" class="form-control" placeholder="Клиент"
						   style="border-radius: 0px;
						   width: 550px;
						   position: relative;
						   margin-left: 10px;" />
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-sm-10">
					<select name="city" class="form-control"
							style="border-radius: 0px;
							width: 550px;
							position: relative;
							margin-left: 10px;">
						<option>Город</option>
						<c:forEach var="city" items="${cities}">
							<option value="${city}">
								<c:out value="${city.getCity()}"></c:out>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-sm-10">
					<input type="text" name="street" class="form-control" placeholder="Наименование улицы"
						   style="border-radius: 0px;
						   width: 550px;
						   position: relative;
						   margin-left: 10px;" />
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-sm-10">
					<input type="text" name="building" class="form-control" placeholder="Номер строения, дома"
						   style="border-radius: 0px;
						   width: 550px;
						   position: relative;
						   margin-left: 10px;" />
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-sm-10">
					<input type="text" name="office" class="form-control" placeholder="Номер офиса"
						   style="border-radius: 0px;
						   width: 550px;
						   position: relative;
						   margin-left: 10px;" />
				</div>
			</div>

			<div class="col text-center">
				<button type="submit" class="btn btn-primary" id="btn-1">ОК</button>
			</div>
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>