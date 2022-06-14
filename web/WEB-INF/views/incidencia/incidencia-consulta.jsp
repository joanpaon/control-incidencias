<%@page import="java.util.Date"%>
<%@page import="org.japo.java.libraries.UtilesPerfil"%>
<%@page import="org.japo.java.entities.Incidencia"%>
<%@page import="org.japo.java.entities.Notificacion"%>
<%@page import="org.japo.java.libraries.UtilesIncidencia"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Usuario
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    // Datos Inyectados
    Incidencia incidencia = (Incidencia) request.getAttribute("incidencia");
    List<Notificacion> notificaciones = (ArrayList<Notificacion>) request.getAttribute("notificaciones");

    // Formateador de Fecha
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/incidencia/incidencia-consulta.css" /> 
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Consulta de Incidencias</h2>

                    <a class="btn btn-listar" href="?cmd=incidencia-listado">Listado</a>
                </header>

                <table class="incidencia">
                    <thead>
                    <th>ID</th>
                    <th>Info</th>
                    <th>Creacion</th>
                    <th>Estado</th>
                    <th>Dependencia</th>
                    <th>Especialidad</th>
                    </thead>

                    <tbody>
                        <tr>
                            <td><%= incidencia.getId()%></td>
                            <td><%= incidencia.getTitulo()%></td>
                            <td><%= sdf1.format(incidencia.getFecha())%></td>
                            <td><%= incidencia.getEstado() == UtilesIncidencia.INCIDENCIA_ABIERTA ? "Abierta" : "Cerrada"%></td>
                            <td><%= incidencia.getDependenciaNombre()%></td>
                            <td><%= incidencia.getEspecialidadNombre()%></td>
                        </tr>

                    </tbody>
                </table>

                <div class="notificaciones">
                    <% for (Notificacion n : notificaciones) { %>
                    <% if (n.getAutor() == usuario.getId()) {%>
                    <div class="propio">
                        <div class="nombre">
                            Yo
                            ( <%= usuario.getPerfilInfo()%> )
                        </div>
                        <div class="info"><%= n.getInfo()%></div>
                        <div class="fecha"><%= sdf2.format(n.getFecha())%></div>
                    </div>
                    <% } else {%>
                    <div class="ajenos">
                        <div class="nombre">
                            <%= n.getAutorNombre()%>
                            ( <%= n.getAutorPerfil()%> )
                        </div>
                        <div class="info"><%= n.getInfo()%></div>
                        <div class="fecha"><%= sdf2.format(n.getFecha())%></div>
                    </div>
                    <% }%>
                    <% }%>
                </div>

                <% if (incidencia.getEstado() == UtilesIncidencia.INCIDENCIA_ABIERTA) {%>
                <nav class="controles">
                    <a class="btn" href="?cmd=incidencia-cierre&id=<%= incidencia.getId()%>">Finalizar</a>
                    <a class="btn" href="?cmd=notificacion-insercion&id=<%= incidencia.getId()%>&op=captura">Notificar</a>
                </nav>
                <% }%>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/incidencia/incidencia-consulta.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
