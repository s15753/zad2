<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change user privilige</title>
</head>
<body>
    <form action="changePrivilege" method="get">
        <label>Nazwa uzytkownika:<input type="text" id="username" name="username"></label>
        <input type="submit" value="Zmien uprawnienia na PREMIUM">
    </form>
    <form action="showUsers" mehod="get">
        <input type="submit" value="Pokaz uzytkownikow">
    </form>
    <c:if test="${not empty message}">
        <table style="border:1px solid black">
                ${message}
        </table>
    </c:if>
</body>
</html>
