<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.City"%>
<%@ page import="main.java.domain.Region"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<title>Города</title>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<title>City</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">
	<h3>Список городов</h3>
	<div class="container">
		<table class="table table-hover">
			<thead class="table-primary">
			<tr>
				<th scope="col">Код</th>
				<th scope="col">Регион</th>
				<th scope="col">Город</th>
				<th scope="col">Редактировать</th>
				<th scope="col">Удалить</th>
			</tr>
			</thead>

			<tbody>
			<c:forEach var="city" items="${cities}">
				<tr>
					<td>${city.getId()}</td>
					<td>${city.getRegion()}</td>
					<td>${city.getCity()}</td>
					<td style="text-align: center">
						<a href='<c:url value="/editCity?id=${city.getId()}" />'
						   role="button" class="btn btn-outline-secondary border-0" id="edit_btn">
							<img alt="Редактировать" src="images/edit.png" id="edit_img"></a>
					</td>
					<td style="text-align: center">
						<a href='<c:url value="/deleteCity?id=${city.getId()}"/>'
						   role="button" class="btn btn-outline-secondary border-0" id="delete_btn">
							<img alt="Удалить" src="images/delete.png" id="delete_img"></a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<h3>Добавить город</h3>
	<div class="container" id="container-form">
		<form method="POST" action="">
			<div class="row mb-3">
				<div class="col-sm-10">
					<select name="region" class="form-control"
							style="border-radius: 0px;
							width: 550px;
							position: relative;
							margin-left: 10px;">
						<option>Регион</option>
						<c:forEach var="region" items="${regions}">
							<option value="${region}">
								<c:out value="${region.getRegion()}"></c:out>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-sm-10">
					<input type="text" name="city" class="form-control" placeholder="Город"
						   style="border-radius: 0px;
						   width: 550px;
						   position: relative;
						   margin-left: 10px;" />
				</div>
			</div>

			<div class="col text-center">
				<button type="submit" class="btn btn-primary" id="btn-1">ОК</button></p>
			</div>
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>