

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Tarea"%>
<%
    List<Tarea> lista = (List<Tarea>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>P_4 Tareas</title>
    </head>
    <body>
        <h1>Lista de tareas</h1>        
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table border="1" cellspacing="0">
            <tr>
                <th>Id</th>
                <th>Tarea</th>
                <th>Prioridad</th>
                <th>Completado</th>
                <th colspan="2">Opcion</th>
            </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td >${item.id}</td>
                    <td>${item.tarea}</td> 
                    <c:if test="${item.prioridad==1}" var="res" >
                        <td>Alta</td>
                    </c:if>
                    <c:if test="${item.prioridad==2}" var="res" >
                        <td>Media</td>
                    </c:if>
                    <c:if test="${item.prioridad==3}" var="res" >
                        <td>Baja</td>
                    </c:if>
                   <c:if test="${item.completado==1}" var="res" >
                        <td><input type="checkbox" checked></td>
                        </c:if>
                        <c:if test="${item.completado==2}" var="res">
                        <td><input type="checkbox" ></td>
                        </c:if>                       
                    <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>             
                    <td><a href="MainController?op=eliminar&id=${item.id}" onclick="return(confirm('Esta seguro de eliminar ?'))">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
