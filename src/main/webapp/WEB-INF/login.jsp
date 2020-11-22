<%-- 
    Document   : index
    Created on : 8 nov. 2020, 14:45:23
    Author     : steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Simple login</h1>
        
        <c:if test="${errMsg != null}">
            <c:out value="${errMsg}" />
        </c:if>
        <form name="form1" method="post" action="login">
            Login : <input type="text" name="user"/> </br>
            <label> Password : </label> <input type="text" name="pwd" /> </br>
            <input type="submit" name="boutonOk" value="OK" />
        </form>
    </body>
</html>
