<%@page import="org.japo.java.entities.Especialidad"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    Especialidad especialidad = (Especialidad) request.getAttribute("especialidad");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>
        
        <link rel="stylesheet" href="public/css/especialidad/especialidad-consulta.css" /> 
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Consulta de Especialidades</h2>
                    <a class="btn btn-listar" href="?cmd=especialidad-listado">Listado</a>
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
                                <td><%= especialidad.getId()%></td>
                            </tr>
                            
                            <tr>
                                <td>Nombre</td>
                                <td><%= especialidad.getNombre()%></td>
                            </tr>
                            
                            <tr>
                                <td>Info</td>
                                <td><%= especialidad.getInfo()%></td>
                            </tr>
                            
                            <tr>
                                <td>Responsable</td>
                                <td><%= "" 
                                        + especialidad.getResponsable() + " - " 
                                        + especialidad.getResponsableNombre()%>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <nav class="controles">
                    <a class="btn btn-borrar" href="?cmd=especialidad-borrado&id=<%= especialidad.getId()%>">Borrar</a>
                    <a class="btn btn-modificar" href="?cmd=especialidad-modificacion&id=<%= especialidad.getId()%>&op=captura">Modificar</a>
                </nav>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/especialidad/especialidad-consulta.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
