<%@page import="org.japo.java.libraries.UtilesPerfil"%>
<%@page import="org.japo.java.entities.Perfil"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="org.japo.java.entities.PermisoUsuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Sesión > Usuario
    Usuario usuario = (Usuario) (session.getAttribute("usuario"));

    // Datos Inyectados
    List<PermisoUsuario> permisos = (ArrayList<PermisoUsuario>) request.getAttribute("permisos");
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
        <link rel="stylesheet" href="public/css/permiso/permiso-listado.css" /> 
        <link rel="stylesheet" href="public/css/partials/header.css" />
        <link rel="stylesheet" href="public/css/partials/footer.css" />
        <link rel="stylesheet" href="public/css/partials/nav.css" />
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Listado de Permisos de Usuario</h2>

                    <a class="btn btn-insertar" href="?cmd=permiso-usuario-insercion&op=captura" title="Nuevo">Nuevo</a>

                    <% if (usuario.getPerfil() >= UtilesPerfil.DEVEL_CODE) { %>
                    <a class="btn btn-listar" href="?cmd=main-devel">Principal</a>
                    <% } else { %>
                    <a class="btn btn-listar" href="?cmd=main-admin">Principal</a>
                    <% }%>
                </header>

                <%@include file="/WEB-INF/views/partials/nav.jspf" %>

                <table>
                    <thead>
                    <th>ID</th>
                    <th>Usuario</th>
                    <th>Proceso</th>
                    <th>Acciones</th>
                    </thead>

                    <tbody>
                        <% for (PermisoUsuario p : permisos) {%>

                        <tr>
                            <td><%= p.getId()%></td>
                            <td><%= p.getUsuarioName()%></td>
                            <td><%= p.getProcesoInfo()%></td>
                            <td>
                                <a class="btn btn-consultar" href="?cmd=permiso-usuario-consulta&id=<%= p.getId()%>" title="Consulta">C</a>
                                <a class="btn btn-modificar" href="?cmd=permiso-usuario-modificacion&id=<%= p.getId()%>" title="Modificación">M</a>
                                <a class="btn btn-borrar" href="?cmd=permiso-usuario-borrado&id=<%= p.getId()%>" title="Eliminación">B</a>
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
        <script src="public/js/permiso/permiso-listado.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
        <script src="public/js/partials/nav.js"></script>
    </body>
</html>
