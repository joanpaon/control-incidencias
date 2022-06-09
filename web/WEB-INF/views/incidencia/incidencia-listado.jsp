<%@page import="org.japo.java.entities.Perfil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.japo.java.entities.Incidencia"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="org.japo.java.libraries.UtilesIncidencia"%>
<%@page import="org.japo.java.libraries.UtilesPerfil"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Usuario
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    // Datos Inyectados
    List<Incidencia> incidencias = (ArrayList<Incidencia>) request.getAttribute("incidencias");

    // Formateador de Datos Temporales
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- These lines go in the first 1024 bytes -->
        <meta charset="utf-8" />
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
        <link href="public/img/logo.png" rel="icon" type="image/png" />

        <!-- Style Sheet Links -->
        <link rel="stylesheet" href="public/css/incidencia/incidencia-listado.css" /> 
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
                    <h2>Listado de Incidencias</h2>

                    <a class="btn btn-insertar" href="?cmd=incidencia-insercion&op=captura" title="Nueva Incidencia">Nueva</a>

                    <% if (usuario.getPerfil() >= UtilesPerfil.DEVEL_CODE) { %>
                    <a class="btn btn-listar" href="?cmd=main-devel">Principal</a>
                    <% } else if (usuario.getPerfil() >= UtilesPerfil.ADMIN_CODE) { %>
                    <a class="btn btn-listar" href="?cmd=main-admin">Principal</a>
                    <% }%>
                </header>

                <%@include file="/WEB-INF/views/partials/nav.jspf" %>

                <table>
                    <% if (incidencias.size() > 0) {%>
                    <thead>
                    <th>ID</th>
                    <th>Titulo</th>
                    <th>Creación</th>
                    <th>Estado</th>
                    <th>Dependencia</th>
                    <th>Especialidad</th>
                    <th>Acciones</th>
                    </thead>

                    <tbody>
                        <% for (Incidencia i : incidencias) {%>

                        <tr>
                            <td class="id"><%= i.getId()%></td>
                            <td><%= i.getTitulo()%></td>
                            <td><%= sdf.format(i.getFecha())%></td>
                            <td><%= i.getEstado() == UtilesIncidencia.INCIDENCIA_ABIERTA ? "Abierta" : "Cerrada"%></td>
                            <td><%= i.getDependenciaNombre()%></td>
                            <td><%= i.getEspecialidadNombre()%></td>
                            <td class="controles">
                                <a class="btn btn-consultar" href="?cmd=incidencia-consulta&id=<%= i.getId()%>" title="Consulta">C</a>
                                <% if (usuario.getPerfil() >= UtilesPerfil.ADMIN_CODE) {%>
                                <a class="btn btn-modificar" href="?cmd=incidencia-modificacion&id=<%= i.getId()%>" title="Modificación">M</a>
                                <a class="btn btn-borrar" href="?cmd=incidencia-borrado&id=<%= i.getId()%>" title="Eliminación">B</a>
                                <% } %>
                                <% if (i.getEstado() == UtilesIncidencia.INCIDENCIA_ABIERTA) {%>
                                <a class="btn" href="?cmd=incidencia-cierre&id=<%= i.getId()%>" title="Finalizar">F</a>
                                <% } %>
                            </td>
                        </tr>

                        <% }%>
                    </tbody>
                    <% } else {%>
                    <tbody>
                        <tr>
                            <td class="mensaje"><h3>No hay incidencias que mostrar</h3></td>
                        </tr>
                    </tbody>
                    <% }%>
                </table>

                <%@include file="/WEB-INF/views/partials/nav.jspf" %>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/incidencia/incidencia-listado.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
        <script src="public/js/partials/nav.js"></script>
    </body>
</html>
