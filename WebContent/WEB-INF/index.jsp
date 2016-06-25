<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<meta charset="UTF-8">
<title>TimeZone App</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="./WebContent/styles/styles.css"
	type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="mainBody">
		<center>
			<h1>Time Zone Selector</h1>
		</center>
		<hr>
			
		<form class="form-horizontal" method="POST"action="/TimeZoneApplication/showTimeZone">
			<div class="form-group">
				<div class="col-xs-offset-3 col-xs-3">
					<label for="zones">Select Zones:</label>
					 <select class="form-control" name="zones" id="zones" multiple>
						<c:forEach items="${availableZones}" var="timeZones">
							<option value="${timeZones.key}">${timeZones.key}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<br>
			<div class="form-group">
				<div class="col-xs-offset-3 col-xs-3">
					<input type="submit" class="btn btn-primary" value="Submit" name="showZone">
					<input type="reset" class="btn btn-default" value="Reset">
				</div>
			</div>
		</form>
	</div>
	<div class="container">
			<form class="form-horizontal" method="POST" action="/TimeZoneApplication/">
			<div class="form-group">
			<div class="col-xs-offset-3 col-xs-3">
				<label for="zoneName">Zone Name</label> <input type="text"
					class="form-control" id="zoneName" name="zoneName">
					</div>
			</div>
			<div class="form-group">
			<div class="col-xs-offset-3 col-xs-3">
				<label for="offset">Time Offset</label> <input type="text"
					class="form-control" id="offset" name="offset">
			</div></div>
			<div class="col-xs-offset-3 col-xs-3">
			<input type="submit" class="btn btn-primary" name="setZone"
				value="Set your own Timezone">
				</div>
		</form>
		</div>
</body>

</html>