<%@page import="org.japo.java.libraries.UtilesPerfil"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="org.japo.java.entities.Perfil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Session > Usuario
    Usuario usuario = (Usuario) (session.getAttribute("usuario"));

    // Datos Inyectados
    List<Perfil> perfiles = (ArrayList<Perfil>) request.getAttribute("perfiles");
%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <!-- These lines go in the first 1024 bytes -->
        <meta charset="utf-8" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <title>Control de Incidencias</title>

        <!-- References -->
        <meta name="author" content="2021 - José A. Pacheco Ondoño - japolabs@gmail.com" />
        <meta name="description" content="Control de Incidencias" />

        <!-- Configuration -->
        <meta name="keywords" content="" />
        <meta name="robots" content="noindex, nofollow" />

        <!-- Viewport Setup for mobile devices -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Favicon -->
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />

        <!-- Style Sheet Links -->
        <link rel="stylesheet" href="public/css/perfil/perfil-listado.css" />
        <link rel="stylesheet" href="public/css/partials/header.css" />
        <link rel="stylesheet" href="public/css/partials/footer.css" />
        <link rel="stylesheet" href="public/css/partials/browser.css" />
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>

                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Listado de Perfiles</h2>

                    <a class="btn btn-insertar" href="?cmd=perfil-insercion&op=captura" title="Nuevo">Nuevo</a>

                    <% if (usuario.getPerfil() >= UtilesPerfil.DEVEL_CODE) { %>
                    <a class="btn btn-principal" href="?cmd=main-devel" title="Principal">Principal</a>
                    <% }
                       %>
                </header>

                <%@include file="/WEB-INF/views/partials/browser.jspf" %>

                <table>
                    <thead>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Info</th>
                    <th>Acciones</th>
                    </thead>

                    <tbody>
                        <% for (Perfil p : perfiles) {%>

                        <tr>
                            <td><%= p.getId()%></td>
                            <td><%= p.getNombre()%></td>
                            <td><%= p.getInfo()%></td>
                            <td>
                                <a class="btn btn-consultar" href="?cmd=perfil-consulta&id=<%= p.getId()%>" title="Consulta">C</a>
                                <a class="btn btn-modificar" href="?cmd=perfil-modificacion&id=<%= p.getId()%>" title="Modificación">M</a>
                                <a class="btn btn-borrar" href="?cmd=perfil-borrado&id=<%= p.getId()%>" title="Eliminación">B</a>
                            </td>
                        </tr>

                        <% }%>
                    </tbody>
                </table>

                <%@include file="/WEB-INF/views/partials/browser.jspf" %>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/perfil/perfil-listado.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
        <script src="public/js/partials/browser.js"></script>
    </body>
</html>
