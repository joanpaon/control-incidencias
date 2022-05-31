<%@page import="org.japo.java.entities.PermisoPerfil"%>
<%@page import="org.japo.java.entities.Perfil"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    PermisoPerfil permiso = (PermisoPerfil) request.getAttribute("permiso");
%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <!-- These lines go in the first 1024 bytes -->
        <meta charset="utf-8" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <title>JAPOLabs Java Framework</title>

        <!-- References -->
        <meta name="author" content="2021 - José A. Pacheco Ondoño - japolabs@gmail.com" />
        <meta name="description" content="JAPOLabs Java Framework" />

        <!-- Configuration -->
        <meta name="keywords" content="" />
        <meta name="robots" content="noindex, nofollow" />

        <!-- Viewport Setup for mobile devices -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Favicon -->
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />

        <!-- Style Sheet Links -->
        <link rel="stylesheet" href="public/css/permiso/permiso-borrado.css" /> 
        <link rel="stylesheet" href="public/css/partials/header.css" />
        <link rel="stylesheet" href="public/css/partials/footer.css" />
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>

                <img class="watermark" src="public/img/logo01.png" alt="Logo" />

                <header>
                    <h2>Borrado de Permisos de Perfil</h2>
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
                                <td>Perfil</td>
                                <td><%= permiso.getPerfilInfo()%></td>
                            </tr>
                            <tr>
                                <td>Proceso</td>
                                <td><%= permiso.getProcesoInfo()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <nav class="controles">
                    <a class="btn btn-borrar" href="?cmd=permiso-perfil-borrado&op=proceso&id=<%= permiso.getId()%>">Borrar</a>
                </nav>

            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/permiso/permiso-borrado.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
