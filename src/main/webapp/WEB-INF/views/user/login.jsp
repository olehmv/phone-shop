<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-sm-6 col-xs-6">
		<form:form class="form-horizontal" action="/login" method="POST">


			<div class="form-group">
				<label for="email" class="col-sm-offset-2 col-sm-10">Log in:</label>
				<div class="col-sm-offset-2 col-sm-10">

					<input class="form-control" id="login" name="login"
						placeholder="Enter login">
				</div>
			</div>
			<div class="form-group">
				<label for="pwd" class="col-sm-offset-2 col-sm-10">Password:</label>
				<div class="col-sm-offset-2 col-sm-10">

					<input type="password" class="form-control" id="pwd"
						name="password" placeholder="Enter password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label><input type="checkbox"> Remember me</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Register</button>
				</div>
			</div>
		</form:form>
	</div>
</div>