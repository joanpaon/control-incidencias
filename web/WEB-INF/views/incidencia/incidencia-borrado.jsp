<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.japo.java.libraries.UtilesIncidencia"%>
<%@page import="org.japo.java.entities.Incidencia"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    Incidencia incidencia = (Incidencia) request.getAttribute("incidencia");

    // Formateador Datos Temporales
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/incidencia/incidencia-borrado.css" /> 
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Borrado de Incidencias</h2>
                    <a class="btn btn-listar" href="?cmd=incidencia-listado">Listado</a>
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
                                <td><%= incidencia.getId()%></td>
                            </tr>
                            
                            <tr>
                                <td>Titulo</td>
                                <td><%= incidencia.getTitulo()%></td>
                            </tr>
                            
                            <tr>
                                <td>Info</td>
                                <td><%= incidencia.getInfo()%></td>
                            </tr>
                            
                            <tr>
                                <td>Estado</td>
                                <% if (incidencia.getEstado() == UtilesIncidencia.INCIDENCIA_CERRADA) { %>
                                <td>Incidencia Cerrada</td>
                                <% } else { %>
                                <td>Incidencia Abierta</td>
                                <% }%>
                            </tr>
                            
                            <tr>
                                <td>Fecha</td>
                                <td><%= sdf.format(incidencia.getFecha())%></td>
                            </tr>
                            
                            <tr>
                                <td>Autor</td>
                                <td><%= ""
                                        + incidencia.getAutor() + " - "
                                        + incidencia.getAutorNombre() + " ("
                                        + incidencia.getAutorPerfil() + ")"%>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>Dependencia</td>
                                <td><%= ""
                                        + incidencia.getDependencia() + " - "
                                        + incidencia.getDependenciaNombre()%>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>Especialidad</td>
                                <td><%= ""
                                        + incidencia.getEspecialidad() + " - "
                                        + incidencia.getEspecialidadNombre()%>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <nav class="controles">
                    <a class="btn btn-borrar" href="?cmd=incidencia-borrado&op=proceso&id=<%= incidencia.getId()%>">Borrar</a>
                </nav>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/incidencia/incidencia-borrado.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
