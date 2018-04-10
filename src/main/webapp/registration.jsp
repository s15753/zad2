<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="registration"method="get">
	<label>Nazwa uzytkownka:<input type="text" id="username" name="username"/></label><br/>
	<label>Haslo:<input type="text" id="password" name="password"/></label><br/>
	<label>Potwierdz haslo:<input type="text" id="passwordCheck" name="passwordCheck"/></label><br/>
	<label>Email:<input type="text" id="email" name="email"/></label><br/>
	<input type="submit" value="Zarejestruj"/>
</form>
</body>
</html>