<%@page import="org.japo.java.libraries.UtilesPerfil"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Session > Usuario
    Usuario usuario = (Usuario) (session.getAttribute("usuario"));

    // Datos Inyectados
    List<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>
       
        <link rel="stylesheet" href="public/css/partials/nav.css" />

        <link rel="stylesheet" href="public/css/usuario/usuario-listado.css" />
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Listado de Usuarios</h2>

                    <a class="btn btn-insertar" href="?cmd=usuario-insercion&op=captura" title="Nuevo">Nuevo</a>

                    <% if (usuario.getPerfil() >= UtilesPerfil.DEVEL_CODE) { %>
                    <a class="btn btn-principal" href="?cmd=main-devel" title="Principal">Principal</a>
                    <% } else if (usuario.getPerfil() >= UtilesPerfil.ADMIN_CODE) { %>
                    <a class="btn btn-principal" href="?cmd=main-admin" title="Principal">Principal</a>
                    <% }%>
                </header>

                <%@include file="/WEB-INF/views/partials/nav.jspf" %>

                <table>
                    <thead>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Perfil</th>
                    <th>Acciones</th>
                    </thead>

                    <tbody>
                        <% for (Usuario u : usuarios) {%>

                        <tr>
                            <td><%= u.getId()%></td>
                            <td><%= u.getAlias()%></td>
                            <td><%= u.getEmail()%></td>
                            <td><%= u.getPerfilInfo()%></td>
                            <td>
                                <a class="btn btn-consultar" href="?cmd=usuario-consulta&id=<%= u.getId()%>" title="Consulta">C</a>
                                <a class="btn btn-modificar" href="?cmd=usuario-modificacion&id=<%= u.getId()%>" title="Modificación">M</a>
                                <a class="btn btn-borrar" href="?cmd=usuario-borrado&id=<%= u.getId()%>" title="Eliminación">B</a>
                            </td>
                        </tr>

                        <% }%>
                    </tbody>
                </table>

                <%@include file="/WEB-INF/views/partials/nav.jspf" %>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/usuario/usuario-listado.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
        <script src="public/js/partials/nav.js"></script>
    </body>
</html>
