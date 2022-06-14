<%@page import="org.japo.java.entities.PermisoPerfil"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    PermisoPerfil permiso = (PermisoPerfil) request.getAttribute("permiso");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>
        
        <link rel="stylesheet" href="public/css/permiso/permiso-consulta.css" /> 
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Consulta de Permisos de Perfil</h2>
                    <a class="btn btn-listar" href="?cmd=permiso-perfil-listado">Listado</a>
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
                                <td><%= permiso.getId()%></td>
                            </tr>
                            
                            <tr>
                                <td>Perfil</td>
                                <td><%= permiso.getPerfilInfo()%></td>
                            </tr>
                            
                            <tr>
                                <td>Proceso</td>
                                <td><%= permiso.getProcesoInfo()%></td>
                            </tr>
                            
                            <tr>
                                <td>Info</td>
                                <td><%= permiso.getInfo()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <nav class="controles">
                    <a class="btn btn-borrar" href="?cmd=permiso-borrado&id=<%= permiso.getId()%>">Borrar</a>
                    <a class="btn btn-modificar" href="?cmd=permiso-modificacion&id=<%= permiso.getId()%>&op=captura">Modificar</a>
                </nav>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/permiso/permiso-consulta.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
