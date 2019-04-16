<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ include file="header.html" %>  Bringing in nav bar  --%>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../../../favicon.ico">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet">

<title>You have Logged in</title>

<!-- Bootstrap core CSS -->
<link href="http://127.0.0.1:127/pulse/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="http://127.0.0.1:127/css/success.css" rel="stylesheet">

</head>

<body class="text-center">

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="http://localhost:8080/homepage"><img
			src="http://127.0.0.1:127/images/logo_2.png" height="70px" /> SHOP</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor03" aria-controls="navbarColor03"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarColor03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="http://localhost:8080/homepage">Home <span
						class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="/viewTickets">View
						Your Tickets</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Account
						Information</a></li>
				<li class="nav-item"><a class="nav-link"
					href="http://localhost:8080/logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<%-- <br>Image here: <img src= ${picUrl} />--%>
	<div id="gig-list">
		<c:forEach var="list" items="${lists}">
			<div class="gig">
				<div class="start">

					<img src="${list.imageUrl}" />
					<!-- <p>${list.id}</p> -->
					<p class="artist">${list.itemName}</p>
				</div>
				<div class="end">
					<div class="info">
						<%-- <p class="location">${list.arena}</p> --%>
						<div class="datetime">
							<%-- <p class="date">${list.date}</p>
							<p class="time">${list.time}</p> --%>
						</div>
					</div>
					<p class="price">€ ${list.price}</p>
					<!-- <p><a href="<c:url value='/purchase-tickets'><c:param name="id" value="${list.id}"/></c:url>">Purchase</a></p> -->
					<form action="/addItemToCart" method="POST">
						<input type="hidden" name="id" id="id" value=${list.id } />
						<button type="submit" class="purchase-button">Add to Cart</button>
					</form>
					<!-- 	<button type="submit" class="purchase-button">Purchase</button> -->

				</div>
			</div>
		</c:forEach>
	</div>
	<div>
			<form action="/viewCart" method="GET" class="form-inline my-2 my-lg-0">
				<button class="btn btn-secondary my-2 my-sm-0" type="submit">View Cart</button>
			</form>
	</div>
</body>
</html>
