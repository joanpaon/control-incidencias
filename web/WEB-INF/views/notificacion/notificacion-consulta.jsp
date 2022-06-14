<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.japo.java.entities.Notificacion"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    Notificacion notificacion = (Notificacion) request.getAttribute("notificacion");

    // Formateador Datos Temporales
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>
        
        <link rel="stylesheet" href="public/css/notificacion/notificacion-consulta.css" /> 
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Consulta de Notificaciones</h2>
                    <a class="btn btn-listar" href="?cmd=notificacion-listado">Listado</a>
                </header>

                <div class="content">
                    <table>
                        <thead>
                            <tr>
                                <th>Dato</th>
                                <th>Valor</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td>ID</td>
                                <td><%= notificacion.getId()%></td>
                            </tr>
                            
                            <tr>
                                <td>Fecha</td>
                                <td><%= sdf.format(notificacion.getFecha())%></td>
                            </tr>
                            
                            <tr>
                                <td>Autor</td>
                                <td><%= notificacion.getAutorNombre()%></td>
                            </tr>
                            
                            <tr>
                                <td>Incidencia</td>
                                <td><%= notificacion.getIncidenciaTitulo()%></td>
                            </tr>
                            
                            <tr>
                                <td>Info</td>
                                <td><%= notificacion.getInfo()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <nav class="controles">
                    <a class="btn btn-borrar" href="?cmd=notificacion-borrado&id=<%= notificacion.getId()%>">Borrar</a>
                    <a class="btn btn-modificar" href="?cmd=notificacion-modificacion&id=<%= notificacion.getId()%>&op=captura">Modificar</a>
                </nav>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/notificacion/notificacion-consulta.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
