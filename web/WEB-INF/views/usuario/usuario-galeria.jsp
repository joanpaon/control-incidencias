<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    List<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <!-- These lines go in the first 1024 bytes -->
        <meta charset="utf-8" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <title>Galería de Usuarios</title>

        <!-- References -->
        <meta name="author" content="2021 - José A. Pacheco Ondoño - japolabs@gmail.com" />
        <meta name="description" content="Galería de Usuarios" />

        <!-- Configuration -->
        <meta name="keywords" content="" />
        <meta name="robots" content="noindex, nofollow" />

        <!-- Viewport Setup for mobile devices -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Favicon -->
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />

        <!-- Style Sheet Links -->
        <link rel="stylesheet" href="public/css/usuario/usuario-galeria.css" /> 
        <link rel="stylesheet" href="public/css/partials/header.css" />
        <link rel="stylesheet" href="public/css/partials/footer.css" />
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>

                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Galería de Usuarios</h2>
                    <a class="btn btn-principal" href="?cmd=main-admin" title="Principal">Principal</a>
                    <a class="btn btn-insertar" href="?cmd=usuario-insercion&op=captura" title="Nuevo">Nuevo</a>
                </header>

                <div class="galeria">
                    <% for (Usuario u : usuarios) {%>

                    <a href="?cmd=usuario-consulta&id=<%= u.getId()%>" class="ficha">
                        <img src="<%= u.getAvatar()%>" alt="avatar"/>
                        <p class="id"><%= u.getId()%></p>
                        <p class="nombre"><%= u.getUser()%></p>
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
