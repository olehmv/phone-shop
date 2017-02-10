<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<style>
#filter>.form-group>.col-sm-12>span {
	display: block;
}
</style>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/admin/category">Category</a></li>
					<li><a href="/admin/producer">Producer</a></li>
					<li class="active"><a href="/admin/item">Item</a><span
						class="sr-only">(current)</span></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
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
			<div class="col-md-7 col-xs-12">
					<div class="row">
			
				<form:form class="form-horizontal" action="/admin/item"
					method="POST" modelAttribute="item" enctype="multipart/form-data">
					<custom:hiddenInputs excludeParams="name, price,image,producer" />
					<div class="form-group">
						<label for="name" class="col-sm-offset-2 col-sm-10"><form:errors
								path="name" /></label>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Name</label>
						<div class="col-sm-10">
							<form:input type="text" class="form-control" path="name"
								id="name" />
						</div>
					</div>
					<div class="form-group">
						<label for="price" class="col-sm-offset-2 col-sm-10"><form:errors
								path="price" /></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Price</label>
						<div class="col-sm-7">
							<form:input id="price" path="price" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="image" class="col-sm-offset-2 col-sm-10"><form:errors
								path="image" /></label>
					</div>
					<div class="col-sm-6 col-xs-6">
						<label>Item Image</label>:
						<form:input type="file" path="image" id="image"
							accept="image/jpeg,image/png,image/gif" />
					</div>
					<div class="form-group">
						<label for="price" class="col-sm-offset-2 col-sm-10"><form:errors
								path="producer" /></label>
					</div>
					<div class="form-group">
						<label for="country" class="col-sm-2 control-label">Producer</label>
						<div class="col-sm-10">
							<form:select class="form-control" path="producer" id="producer">
								<option value="0">Select Producer</option>
								<form:options items="${producers}" itemLabel="name"
									itemValue="id" />
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Create</button>
						</div>
					</div>
				</form:form>
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
				<h3>Update</h3>
			</div>
			<div class="col-md-2 col-xs-2">
				<h3>Delete</h3>
			</div>
			</div>
			<div class="row">
		<c:forEach items="${page.content}" var="item">
				<div class="col-md-2 col-xs-2">${item.producer.name}</div>
				<div class="col-md-2 col-xs-2">${item.name}</div>
				<div class="col-md-2 col-xs-2">${item.price}</div>
				<div class="col-md-2 col-xs-2">
					<a class="btn btn-warning"
						href="/admin/item/update/${item.id}<custom:allParams/>">update</a>
				</div>
				<div class="col-md-2 col-xs-2">
					<a class="btn btn-danger"
						href="/admin/item/delete/${item.id}<custom:allParams/>">delete</a>
				</div>
							<div class="row">
				<div class="col-md-1 col-xs-1">
					<img alt="${item.image}" class="img-rounded" width="100%"
						src="/images/item/${item.image}">
				</div>
				</div>
				<hr size="30" color="green">
		</c:forEach>
		</div>
		</div>
		
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
						<custom:sort innerHtml="Price asc" paramValue="price"/>
						<custom:sort innerHtml="Price desc" paramValue="price,desc"/>
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