<%-- 
    Document   : list
    Created on : 22 nov. 2020, 10:09:21
    Author     : steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List of interns</h1>
        <table>
            <c:forEach items="${internsList}" var="intern">
                <tr>
                    <td>${intern.last_name}</td>
                </tr>

            </c:forEach>
        </table>
    </body>
</html>
