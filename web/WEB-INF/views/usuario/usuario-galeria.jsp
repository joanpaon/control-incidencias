<%@page import="org.japo.java.libraries.UtilesPerfil"%>
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

        <link rel="stylesheet" href="public/css/usuario/usuario-galeria.css" />
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Galería de Usuarios</h2>

                    <a class="btn btn-insertar" href="?cmd=usuario-insercion&op=captura" title="Nuevo">Nuevo</a>

                    <% if (usuario.getPerfil() >= UtilesPerfil.DEVEL_CODE) { %>
                    <a class="btn btn-principal" href="?cmd=main-devel" title="Principal">Principal</a>
                    <% } else { %>
                    <a class="btn btn-principal" href="?cmd=main-admin" title="Principal">Principal</a>
                    <% }%>
                </header>

                <div class="galeria">
                    <% for (Usuario u : usuarios) {%>

                    <a href="?cmd=usuario-consulta&id=<%= u.getId()%>" class="ficha">
                        <img src="<%= u.getAvatar()%>" alt="avatar"/>
                        <p class="id"><%= u.getId()%></p>
                        <p class="nombre"><%= u.getAlias()%></p>
                        <p class="email"><%= u.getEmail()%></p>
                        <p class="perfil"><%= u.getPerfilInfo()%></p>
                    </a>

                    <% }%>
                </div>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/usuario/usuario-galeria.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
