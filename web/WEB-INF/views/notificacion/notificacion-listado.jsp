<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.japo.java.libraries.UtilesPerfil"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="org.japo.java.entities.Notificacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Session > Usuario
    Usuario usuario = (Usuario) (session.getAttribute("usuario"));

    // Datos Inyectados
    List<Notificacion> notificaciones = (ArrayList<Notificacion>) request.getAttribute("notificaciones");
    
    // Formaterador Datos Temporales
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>
        
        <link rel="stylesheet" href="public/css/partials/nav.css" />
        
        <link rel="stylesheet" href="public/css/notificacion/notificacion-listado.css" /> 
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Listado de Notificaciones</h2>

                    <!--<a class="btn btn-insertar" href="?cmd=notificacion-insercion&op=captura" title="Nuevo">Nuevo</a>-->

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
                    <th>Fecha</th>
                    <th>Autor</th>
                    <th>Incidencia</th>
                    <th>Acciones</th>
                    </thead>

                    <tbody>
                        <% for (Notificacion n : notificaciones) {%>

                        <tr>
                            <td><%= n.getId()%></td>
                            <td><%= sdf.format(n.getFecha()) %></td>
                            <td><%= n.getAutorNombre() %></td>
                            <td><%= n.getIncidenciaTitulo() %></td>
                            <td>
                                <a class="btn btn-consultar" href="?cmd=notificacion-consulta&id=<%= n.getId()%>" title="Consulta">C</a>
                                <a class="btn btn-modificar" href="?cmd=notificacion-modificacion&id=<%= n.getId()%>" title="Modificación">M</a>
                                <a class="btn btn-borrar" href="?cmd=notificacion-borrado&id=<%= n.getId()%>" title="Eliminación">B</a>
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
        <script src="public/js/notificacion/notificacion-listado.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
        <script src="public/js/partials/nav.js"></script>
    </body>
</html>
