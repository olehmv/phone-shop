<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>

<style>
ul {
	margin: 0;
	padding: 0;
	width: 100%;
	list-style: none;
	position: relative;
	float: left;
	border-bottom: 1px solid #00254a;
	border-radius: 3px;
	list-style: none;
}

nav ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	background-color: #333;
}

nav li {
	float: left;
}

nav li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

nav li a:hover {
	color: #804040;
	text-decoration: none;
}
</style>
<nav>
	<ul>
		<li class=""><a href="/login">Log In</a></li>
		<li class=""><a href="/registration">Registration</a></li>
		<li class=""><a href="/logout">Sing Out</a></li>
		<form:form class="form-horizontal" action="/" method="GET"
			modelAttribute="filter" id="filter">
			<custom:hiddenInputs excludeParams="search, minPrice, maxPrice" />
			<div class="col-sm-3">
				<form:input type="text" class="form-control" path="search"
					placeholder="Search" />
			</div>
			<div class="col-sm-1 col-xs-3">
				<form:input type="text" class="form-control" path="minPrice"
					placeholder="Min price " />
			</div>
			<div class="col-sm-1 col-xs-3">
				<form:input type="text" class="form-control" path="maxPrice"
					placeholder="Max price " />
			</div>
			<div class="col-sm-1">
				<button type="submit" class="btn btn-primary">Search</button>
			</div>
			<div class="col-sm-1">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button"
						data-toggle="dropdown">
						Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name" />
						<custom:sort innerHtml="Name desc" paramValue="name,desc" />
						<custom:sort innerHtml="Price asc" paramValue="price"/>
						<custom:sort innerHtml="Price desc" paramValue="price,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-sm-1">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
			</div>
		</form:form>
		<li class=""><a href="/admin/category">Admin</a></li>

	</ul>
</nav>