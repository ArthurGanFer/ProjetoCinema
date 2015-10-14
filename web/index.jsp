<%-- 
    Document   : index
    Created on : 08/10/2015, 08:11:31
    Author     : 1147106
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cinema</title>
    </head>
    <body>
        <h1>Cinema</h1>
        <hr>
        <c:forEach var="i" begin="0" end="59">
            <c:if test="${i % 10 == 0}">
                <br>
            </c:if>
                <a href="FrontController?cadeira=${i}&command=selecionar">
                    <img id="c${i}" alt="cadeira" src="img/cadeira${cadeiras.get(i)}.png" style="width:60px"/>
                </a>
        </c:forEach>
        
    </body>
</html>
