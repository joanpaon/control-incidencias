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

    // Parámetros Navegación
    long rowIndex = (long) request.getAttribute("row-index");
    long rowIndexIni = (long) request.getAttribute("row-index-ini");
    long rowIndexAnt = (long) request.getAttribute("row-index-ant");
    long rowIndexSig = (long) request.getAttribute("row-index-sig");
    long rowIndexFin = (long) request.getAttribute("row-index-fin");
    int rowsPage = (int) request.getAttribute("rows-page");
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
        <link rel="stylesheet" href="public/css/permiso/permiso-listado.css" /> 
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
                    <h2>Listado de Permisos de Perfil</h2>
                    <% if (usuario.getPerfil() >= UtilesPerfil.DEVEL_CODE) { %>
                    <a class="btn btn-principal" href="?cmd=main-devel" title="Principal">Principal</a>
                    <% } else if (usuario.getPerfil() >= UtilesPerfil.ADMIN_CODE) { %>
                    <a class="btn btn-principal" href="?cmd=main-admin" title="Principal">Principal</a>
                    <% } else { %>
                    <a class="btn btn-principal" href="?cmd=main-basic" title="Principal">Principal</a>
                    <% }%>
                    <a class="btn btn-insertar" href="?cmd=permiso-perfil-insercion&op=captura" title="Nuevo">Nuevo</a>
                </header>

                <nav class="paginacion">
                    <a href="?cmd=permiso-listado&row-index=<%=rowIndexIni%>&rows-page=<%=rowsPage%>" class="btn btn-ini" title="Principio">&lt;&lt;</a>
                    <a href="?cmd=permiso-listado&row-index=<%=rowIndexAnt%>&rows-page=<%=rowsPage%>" class="btn btn-prv" title="Anterior">&lt;</a>
                    <a href="?cmd=permiso-listado&row-index=<%=rowIndexSig%>&rows-page=<%=rowsPage%>" class="btn btn-nxt" title="Siguiente">&gt;</a>
                    <a href="?cmd=permiso-listado&row-index=<%=rowIndexFin%>&rows-page=<%=rowsPage%>" class="btn btn-end" title="Final">&gt;&gt;</a>
                    |
                    <form class="rows-page" action="">
                        <input type="hidden" name="cmd" value="permiso-listado">
                        <input type="hidden" name="row-index" value="<%= rowIndex%>">
                        <label for="rows-page">Filas</label>
                        <select id="rows-page" name="rows-page" onchange="document.querySelector('.rows-page').submit()">
                            <% if (rowsPage == 80) { %>
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="40">40</option>
                            <option value="80" selected>80</option>
                            <% } else if (rowsPage == 40) { %>
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="40" selected>40</option>
                            <option value="80">80</option>
                            <% } else if (rowsPage == 20) { %>
                            <option value="10">10</option>
                            <option value="20" selected>20</option>
                            <option value="40">40</option>
                            <option value="80">80</option>
                            <% } else { %>
                            <option value="10" selected>10</option>
                            <option value="20">20</option>
                            <option value="40">40</option>
                            <option value="80">80</option>
                            <% }%>
                        </select>
                    </form>
                </nav>

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
                                <a class="btn btn-modificar" href="?cmd=permiso-perfil-modificacion&id=<%= p.getId()%>" title="Modificación">M</a>
                                <a class="btn btn-borrar" href="?cmd=permiso-perfil-borrado&id=<%= p.getId()%>" title="Eliminación">B</a>
                            </td>
                        </tr>

                        <% }%>
                    </tbody>
                </table>

                <nav class="paginacion">
                    <a href="?cmd=permiso-listado&row-index=<%=rowIndexIni%>&rows-page=<%=rowsPage%>" class="btn btn-ini" title="Principio">&lt;&lt;</a>
                    <a href="?cmd=permiso-listado&row-index=<%=rowIndexAnt%>&rows-page=<%=rowsPage%>" class="btn btn-prv" title="Anterior">&lt;</a>
                    <a href="?cmd=permiso-listado&row-index=<%=rowIndexSig%>&rows-page=<%=rowsPage%>" class="btn btn-nxt" title="Siguiente">&gt;</a>
                    <a href="?cmd=permiso-listado&row-index=<%=rowIndexFin%>&rows-page=<%=rowsPage%>" class="btn btn-end" title="Final">&gt;&gt;</a>
                    |
                    <form class="rows-page" action="">
                        <input type="hidden" name="cmd" value="permiso-listado">
                        <input type="hidden" name="row-index" value="<%= rowIndex%>">
                        <label for="rows-page">Filas</label>
                        <select id="rows-page" name="rows-page" onchange="document.querySelector('.rows-page').submit()">
                            <% if (rowsPage == 80) { %>
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="40">40</option>
                            <option value="80" selected>80</option>
                            <% } else if (rowsPage == 40) { %>
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="40" selected>40</option>
                            <option value="80">80</option>
                            <% } else if (rowsPage == 20) { %>
                            <option value="10">10</option>
                            <option value="20" selected>20</option>
                            <option value="40">40</option>
                            <option value="80">80</option>
                            <% } else { %>
                            <option value="10" selected>10</option>
                            <option value="20">20</option>
                            <option value="40">40</option>
                            <option value="80">80</option>
                            <% }%>
                        </select>
                    </form>
                </nav>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/permiso/permiso-listado.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
