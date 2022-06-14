<%@page import="org.japo.java.entities.Dependencia"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    Dependencia dependencia = (Dependencia) request.getAttribute("dependencia");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/dependencia/dependencia-consulta.css" /> 
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Consulta de Dependencias</h2>
                    <a class="btn btn-listar" href="?cmd=dependencia-listado">Listado</a>
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
                                <td><%= dependencia.getId()%></td>
                            </tr>
                            <tr>
                                <td>Nombre</td>
                                <td><%= dependencia.getNombre()%></td>
                            </tr>
                            <tr>
                                <td>Info</td>
                                <td><%= dependencia.getInfo()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <nav class="controles">
                    <a class="btn btn-borrar" href="?cmd=dependencia-borrado&id=<%= dependencia.getId()%>">Borrar</a>
                    <a class="btn btn-modificar" href="?cmd=dependencia-modificacion&id=<%= dependencia.getId()%>&op=captura">Modificar</a>
                </nav>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/dependencia/dependencia-consulta.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
