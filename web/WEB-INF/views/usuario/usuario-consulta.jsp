<%@page import="org.japo.java.entities.Usuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/usuario/usuario-consulta.css" />
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Consulta de Usuarios</h2>
                    <a class="btn btn-listar" href="?cmd=usuario-listado">Listado</a>
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
                                <td><%= usuario.getId()%></td>
                            </tr>
                            <tr>
                                <td>Nombre</td>
                                <td><%= usuario.getAlias()%></td>
                            </tr>
                            <tr>
                                <td>EMail</td>
                                <td><%= usuario.getEmail()%></td>
                            </tr>
                            <tr>
                                <td>Perfil</td>
                                <td><%= usuario.getPerfilInfo()%></td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="imagen">
                        <div class="imagen-margen">
                            <img src="<%= usuario.getAvatar()%>" alt="<%= usuario.getAlias()%>"/> 
                        </div>
                    </div>
                </div>

                <nav class="controles">
                    <a class="btn btn-borrar" href="?cmd=usuario-borrado&id=<%= usuario.getId()%>">Borrar</a>
                    <a class="btn btn-modificar" href="?cmd=usuario-modificacion&id=<%= usuario.getId()%>&op=captura">Modificar</a>
                </nav>

            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/usuario/usuario-consulta.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
