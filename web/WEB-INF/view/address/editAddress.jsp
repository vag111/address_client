<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.Address"%>
<%@ page import="main.java.domain.City"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<title>Редактирование адреса</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Address Edit</title>
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
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <h3>Редактировать адрес</h3>
    <div class="container" id="container-form">
        <form method="POST" action="">
            <div class="row mb-3">
                <div class="col-sm-10">
                    <input type="text" class="form-control" readonly
                           style="border-radius: 0px;
						   width: 550px;
						   position: relative;
						   margin-left: 10px;"
                           value="${addressEdit[0].getId()}" />
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-sm-10">
                    <input type="text" name="person" class="form-control" placeholder="Клиент"
                           style="border-radius: 0px;
						   width: 550px;
						   position: relative;
						   margin-left: 10px;"
                           value="${addressEdit[0].getPerson()}" />
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
						   margin-left: 10px;"
                           value="${addressEdit[0].getStreet()}" />
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-sm-10">
                    <input type="text" name="building" class="form-control" placeholder="Номер строения, дома"
                           style="border-radius: 0px;
						   width: 550px;
						   position: relative;
						   margin-left: 10px;"
                           value="${addressEdit[0].getBuilding()}" />
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-sm-10">
                    <input type="text" name="office" class="form-control" placeholder="Номер офиса"
                           style="border-radius: 0px;
						   width: 550px;
						   position: relative;
						   margin-left: 10px;"
                           value="${addressEdit[0].getOffice()}" />
                </div>
            </div>

            <div class="col text-center">
                <button type="submit" class="btn btn-primary" id="btn-1">ОК</button>
                <a href='<c:url value="/address" />' role="button" id="btn-2" class="btn btn-secondary">Отменить/Возврат</a>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>
