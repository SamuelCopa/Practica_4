

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Tarea"%>
<%
    Tarea tarea = (Tarea) request.getAttribute("tarea");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${tarea.id==0}" var="res" scope="request">
                Registrar
            </c:if>
            <c:if test="${tarea.id>0}" var="res" scope="request">
                Editar
            </c:if>
            tarea
        </h1>
        <form action="MainController" method="post">
            <table>
                <input type="hidden" name="id" value="${tarea.id}">
                <tr>
                    <td>Tarea</td>
                    <td><input type="text" name="tarea" value="${tarea.tarea}" size="35"></td>
                <tr>
                    <td>Prioridad</td>
                    <td>
                        <select name="prioridad">
                            <option value="1"
                                    <c:if test="${tarea.prioridad == '1'}" var="res" scope="request">
                                        selected
                                    </c:if>
                                    >1. Alta</option>

                            <option value="2"
                                    <c:if test="${tarea.prioridad == '2'}" var="res" scope="request">
                                        selected
                                    </c:if>
                                    >2. Media</option>   

                            <option value="3"
                                    <c:if test="${tarea.prioridad == '3'}" var="res" scope="request">
                                        selected
                                    </c:if>
                                    >3. Baja</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Completado</td>
                    <td>
                        <select name="completado">
                            <option value="1"
                                    <c:if test="${tarea.completado == '1'}" var="res" scope="request">
                                        selected
                                    </c:if>
                                    >1. Concluido</option>

                            <option value="2"
                                    <c:if test="${tarea.completado == '2'}" var="res" scope="request">
                                        selected
                                    </c:if>
                                    >2. Pendiente</option>   
                         </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Registrar"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><a href="MainController">Volver</a></td>
                </tr>
            </table>
        </form>
     </body>
</html>
