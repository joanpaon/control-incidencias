<%@page import="org.japo.java.libraries.UtilesPerfil"%>
<%@page import="org.japo.java.entities.PermisoPerfil"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Session > Usuario
    Usuario usuario = (Usuario) (session.getAttribute("usuario"));

    // Datos Inyectados
    List<PermisoPerfil> permisos = (ArrayList<PermisoPerfil>) request.getAttribute("permisos");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>
        
        <link rel="stylesheet" href="public/css/partials/nav.css" />
        
        <link rel="stylesheet" href="public/css/permiso/permiso-listado.css" /> 
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Listado de Permisos de Perfil</h2>

                    <a class="btn btn-insertar" href="?cmd=permiso-perfil-insercion&op=captura" title="Nuevo">Nuevo</a>

                    <% if (usuario.getPerfil() >= UtilesPerfil.DEVEL_CODE) { %>
                    <a class="btn btn-principal" href="?cmd=main-devel" title="Principal">Principal</a>
                    <% }%>
                </header>

                <%@include file="/WEB-INF/views/partials/nav.jspf" %>

                <table>
                    <thead>
                    <th>ID</th>
                    <th>Perfil</th>
                    <th>Proceso</th>
                    <th>Acciones</th>
                    </thead>

                    <tbody>
                        <% for (PermisoPerfil p : permisos) {%>

                        <tr>
                            <td><%= p.getId()%></td>
                            <td><%= p.getPerfilInfo()%></td>
                            <td><%= p.getProcesoInfo()%></td>
                            <td>
                                <a class="btn btn-consultar" href="?cmd=permiso-perfil-consulta&id=<%= p.getId()%>" title="Consulta">C</a>
                                <a class="btn btn-modificar" href="?cmd=permiso-perfil-modificacion&id=<%= p.getId()%>" title="Modificaci??n">M</a>
                                <a class="btn btn-borrar" href="?cmd=permiso-perfil-borrado&id=<%= p.getId()%>" title="Eliminaci??n">B</a>
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
