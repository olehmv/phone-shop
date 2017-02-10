<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<a href="/admin">admin</a>
<a href="/registration">registration</a>
<a href="/login">login</a>
<a href="">Hello
	${user.getUsername()}</a>
<li class=""><a href="/shop/buyer">Shop</a></li>
<div class="row">
	<div class="col-md-3 col-xs-12">
		<form:form class="form-horizontal" action="/admin/item" method="GET"
			modelAttribute="filter" id="filter">
			<custom:hiddenInputs
				excludeParams="search, minPrice, maxPrice, producerIds" />
			<div class="form-group">
				<div class="col-sm-12">
					<form:input type="text" class="form-control" path="search"
						placeholder="Search" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-6 col-xs-6">
					<form:input type="text" class="form-control" path="minPrice"
						placeholder="Min price" />
				</div>
				<div class="col-sm-6 col-xs-6">
					<form:input type="text" class="form-control" path="maxPrice"
						placeholder="Max price" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<form:checkboxes items="${producers}" path="producerIds"
						itemLabel="name" itemValue="id" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<button type="submit" class="btn btn-primary">Search</button>
				</div>
			</div>
		</form:form>
	</div>
</div>
<div class="row">
	<div class="col-md-2 col-xs-2">
		<h3>Producer</h3>
	</div>
	<div class="col-md-2 col-xs-2">
		<h3>Item name</h3>
	</div>
	<div class="col-md-2 col-xs-2">
		<h3>Item price</h3>
	</div>
	<div class="col-md-2 col-xs-2">
		<h3>Buy</h3>
	</div>
</div>
<div class="row">
<c:forEach items="${page.content}" var="item">
	<div class="row">
		<div class="col-md-2 col-xs-2">${item.producer.name}</div>
		<div class="col-md-2 col-xs-2">${item.name}</div>
		<div class="col-md-2 col-xs-2">${item.price}</div>
		<div class="col-md-2 col-xs-2">
			<a class="btn btn-danger"
				href="/admin/item/delete/${item.id}<custom:allParams/>">Buy</a>
		</div>
	</div>
	<hr size="30" color="green">
</c:forEach>
</div>
<div class="row">
<div class="col-md-2 col-xs-12">
	<div class="row">
		<div class="col-md-6 col-xs-6 text-center">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Sort <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<custom:sort innerHtml="Name asc" paramValue="name" />
					<custom:sort innerHtml="Name desc" paramValue="name,desc" />
					<custom:sort innerHtml="Price asc" paramValue="price" />
					<custom:sort innerHtml="Price asc" paramValue="price,desc" />
				</ul>
			</div>
		</div>
		<div class="col-md-6 col-xs-6 text-center">
			<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
		</div>
	</div>
</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>"
			container="<ul class='pagination'></ul>" />
	</div>
</div>