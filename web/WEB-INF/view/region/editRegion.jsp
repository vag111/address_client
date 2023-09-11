<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.Region"%>
<%@ page import="main.java.domain.Country"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<title>Редактирование региона</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Region Edit</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">
    <h3>Список регионов</h3>
    <div class="container">
        <table class="table table-hover">
            <thead class="table-primary">
            <tr>
                <th scope="col">Код</th>
                <th scope="col">Страна</th>
                <th scope="col">Регион</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="region" items="${regions}">
                <tr>
                    <td>${region.getId()}</td>
                    <td>${region.getCountry()}</td>
                    <td>${region.getRegion()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <h3>Редактировать регион</h3>
    <div class="container" id="container-form">
        <form method="POST" action="">
            <div class="row mb-3">
                <div class="col-sm-10">
                    <input type="text" class="form-control" readonly
                           style="border-radius: 0px;
						   width: 550px;
						   position: relative;
						   margin-left: 10px;"
                           value="${regionEdit[0].getId()}" />
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-sm-10">
                    <select name="country" class="form-control"
                            style="border-radius: 0px;
							width: 550px;
							position: relative;
							margin-left: 10px;">
                        <option>Страна</option>
                        <c:forEach var="country" items="${countries}">
                            <option value="${country}">
                                <c:out value="${country.getCountryShort()}"></c:out>
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-sm-10">
                    <input type="text" name="region" class="form-control" placeholder="Регион"
                           style="border-radius: 0px;
						   width: 550px;
						   position: relative;
						   margin-left: 10px;"
                           value="${regionEdit[0].getRegion()}" />
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