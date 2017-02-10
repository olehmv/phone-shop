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
	<div class="col-md-2 col-xs-2">
		<h3>Producer</h3>
	</div>
	<div class="col-md-2 col-xs-2">
		<h3>Item</h3>
	</div>
	<div class="col-md-2 col-xs-2">
		<h3>Price</h3>
	</div>
	<div class="col-md-2 col-xs-2">
		<h3></h3>
	</div>
</div>
<div class="row">
	<c:forEach items="${page.content}" var="item">
			<div class=" col-md-2 col-xs-2"><h4>${item.producer.name}</h4></div>
			<div class="col-md-2 col-xs-2"><h4>${item.name}</h4></div>
			<div class="col-md-2 col-xs-2"><h4>${item.price}</h4></div>
		<div class="row">
			<div class="col-md-1 col-xs-1">
				<img alt="${item.image}" class="img-rounded" width="100%"
					src="/images/item/${item.image}">
			</div>
		</div>
		<hr size="30" color="green">
	</c:forEach>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>"
			container="<ul class='pagination'></ul>" />
	</div>
	</div>