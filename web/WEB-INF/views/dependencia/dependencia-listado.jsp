<%@page import="org.japo.java.libraries.UtilesPerfil"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="org.japo.java.entities.Dependencia"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Session > Usuario
    Usuario usuario = (Usuario) (session.getAttribute("usuario"));

    // Datos Inyectados
    List<Dependencia> dependencias = (ArrayList<Dependencia>) request.getAttribute("dependencias");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>
        
        <link rel="stylesheet" href="public/css/partials/nav.css" />
        
        <link rel="stylesheet" href="public/css/dependencia/dependencia-listado.css" /> 
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Listado de Dependencias</h2>

                    <a class="btn btn-insertar" href="?cmd=dependencia-insercion&op=captura" title="Nuevo">Nuevo</a>

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
                    <th>Nombre</th>
                    <th>Info</th>
                    <th>Acciones</th>
                    </thead>

                    <tbody>
                        <% for (Dependencia d : dependencias) {%>

                        <tr>
                            <td><%= d.getId()%></td>
                            <td><%= d.getNombre()%></td>
                            <td><%= d.getInfo()%></td>
                            <td>
                                <a class="btn btn-consultar" href="?cmd=dependencia-consulta&id=<%= d.getId()%>" title="Consulta">C</a>
                                <a class="btn btn-modificar" href="?cmd=dependencia-modificacion&id=<%= d.getId()%>" title="Modificación">M</a>
                                <a class="btn btn-borrar" href="?cmd=dependencia-borrado&id=<%= d.getId()%>" title="Eliminación">B</a>
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
        <script src="public/js/dependencia/dependencia-listado.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
        <script src="public/js/partials/nav.js"></script>
    </body>
</html>
