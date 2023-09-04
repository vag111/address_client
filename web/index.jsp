<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

	<title>Главная страница</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">
	<div class="container" id="container-ul-li">
		<ul class="list-group list-group-flush" id="list-ul-li">
			<li class="list-group-item"><a href="/address_client/address"><h4>Адреса</h4></a>
			<li class="list-group-item"><a href="/address_client/city"><h4>Города</h4></a>
			<li class="list-group-item"><a href="/address_client/region"><h4>Регионы</h4></a>
			<li class="list-group-item"><a href="/address_client/country"><h4>Страны</h4></a>
		</ul>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>