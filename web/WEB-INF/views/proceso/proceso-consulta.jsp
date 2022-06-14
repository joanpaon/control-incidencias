<%@page import="org.japo.java.entities.Proceso"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    Proceso proceso = (Proceso) request.getAttribute("proceso");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>
        
        <link rel="stylesheet" href="public/css/proceso/proceso-consulta.css" /> 
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Consulta de Procesos</h2>
                    <a class="btn btn-listar" href="?cmd=proceso-listado">Listado</a>
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
                                <td><%= proceso.getId()%></td>
                            </tr>
                            
                            <tr>
                                <td>Nombre</td>
                                <td><%= proceso.getNombre()%></td>
                            </tr>
                            
                            <tr>
                                <td>Info</td>
                                <td><%= proceso.getInfo()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <nav class="controles">
                    <a class="btn btn-borrar" href="?cmd=proceso-borrado&id=<%= proceso.getId()%>">Borrar</a>
                    <a class="btn btn-modificar" href="?cmd=proceso-modificacion&id=<%= proceso.getId()%>&op=captura">Modificar</a>
                </nav>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/proceso/proceso-consulta.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
