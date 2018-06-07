<%@ page language="java" import="java.util.*,models.Item,models.Category,models.Manufacturer" pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
		<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
			<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
			<html>

			<head>

				<title>Our Shop</title>
				<meta http-equiv="pragma" content="no-cache">
				<meta http-equiv="cache-control" content="no-cache">
				<meta http-equiv="expires" content="0">
				<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
				<meta http-equiv="description" content="This is my page">

				<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
				 crossorigin="anonymous">
				<link rel="stylesheet" href='css/custom.css'>
			</head>

			<body>
				<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
					<a class="navbar-brand" href="/FWPM_JavaEE_Assignment/">Your Shop</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
					 aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarNav">
						<ul class="navbar-nav">
							<li class="nav-item">
								<a class="nav-link" href="/FWPM_JavaEE_Assignment/">Home
									<span class="sr-only">(current)</span>
								</a>
							</li>
							<li class="nav-item active">
								<a class="nav-link" href="/FWPM_JavaEE_Assignment/Products">Products</a>
							</li>
							<!--
						<li class="nav-item">
							<a class="nav-link" href="/FWPM_JavaEE_Assigment/Login">Login</a>
						</li>
						-->
						</ul>
					</div>
					<div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
						<ul class="navbar-nav ml-auto">
							<li class="nav-item">
								<a class="nav-link" href="/FWPM_JavaEE_Assignment/ShoppingCart">Shopping Cart</a>
							</li>
						</ul>
					</div>
				</nav>

				<!-- Products -->
				<div class='container-fluid'>
					<h2>Products</h2>

					<form method="GET" action='/FWPM_JavaEE_Assignment/Products'>
						<div class='row filter'>
							<div class='col-sm-2'>
								Name:
								<input type='text' value="${search}" name='search' />
							</div>
							<div class='col-sm-2'>
								Manufacturer:
								<select name='manufacturer'>
									<option value="-1"></option>
									<c:forEach items="${manufacturers}" var="current">
										<option value="${current.getId()}">${current.getTitle()}</option>
									</c:forEach>
								</select>
							</div>
							<div class='col-sm-2'>
								Category:
								<select name='category'>
									<option value='-1'></option>
									<c:forEach items="${categories}" var="current">
										<option value="${current.getId()}">${current.getTitle()}</option>
									</c:forEach>
								</select>
							</div>
							<div class='col-sm-2'>
								<input class='submit' type='submit' value='Filter'>
							</div>
						</div>
					</form>

					<div class='products-list'>
						<c:forEach items="${items}" var="current">
							<div class='product-item'>
								<div class='row'>
									<div class='col-sm-3'>
										<img src="<c:url value="${basePath}/Image?id=${current.getImageId()}"/>" />
									</div>
									<div class='col-sm-5'>
										<div class='row'>
											<h3>${current.getTitle()}</h3>
										</div>
										<div class='row'>
											<div class='col-md-6'>Manufacturer:</div>
											<div class='col-md-6'>${current.getManufacturer().getTitle()}</div>
										</div>
										<div class='row'>
											<div class='col-md-6'>Processor:</div>
											<div class='col-md-6'>${current.getDescription().getCpu()}</div>
										</div>
										<div class='row'>
											<div class='col-md-6'>OS:</div>
											<div class='col-md-6'>${current.getDescription().getOs()}</div>
										</div>
										<div class='row'>
											<div class='col-md-6'>Screen-Size:</div>
											<div class='col-md-6'>${current.getDescription().getSize()}</div>
										</div>
										<div class='row'>
											<div class='col-md-6'>Dimensions:</div>
											<div class='col-md-6'>${current.getDescription().getDimensions()}</div>
										</div>
										<div class='row'>
											<form method="GET" action='/FWPM_JavaEE_Assignment/ShoppingCart?id=${current.getId()}'>
												<input type='submit' value='Add to shopping cart'>
											</form>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>

				</div>

				<footer class="footer">
					<div class="container">
						<span class="text-muted">The Your Shop Company, Sanderheinrichsleitenweg 20, 97074 Wuerzburg</span>
					</div>
				</footer>

				<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
				 crossorigin="anonymous"></script>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
				 crossorigin="anonymous"></script>
				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
				 crossorigin="anonymous"></script>
			</body>

			</html>