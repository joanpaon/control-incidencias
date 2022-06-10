<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.japo.java.entities.Especialidad"%>
<%@page import="org.japo.java.entities.Dependencia"%>
<%@page import="org.japo.java.libraries.UtilesIncidencia"%>
<%@page import="org.japo.java.entities.Incidencia"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    Incidencia incidencia = (Incidencia) request.getAttribute("incidencia");
    List<Dependencia> dependencias = (ArrayList<Dependencia>) request.getAttribute("dependencias");
    List<Especialidad> especialidades = (ArrayList<Especialidad>) request.getAttribute("especialidades");
    List<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");

    // Formateador Temporal
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
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
        <link rel="stylesheet" href="public/css/incidencia/incidencia-modificacion.css" /> 
        <link rel="stylesheet" href="public/css/partials/header.css" />
        <link rel="stylesheet" href="public/css/partials/footer.css" />
    </head>

    <body>
        <!-- Web Content -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Modificación de Incidencias</h2>
                    <a class="btn btn-listar" href="?cmd=incidencia-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=incidencia-modificacion&op=proceso&id=<%= incidencia.getId()%>">
                    <div class="form-content">
                        <div class="field-container">
                            <div class="field-set">
                                <label for="titulo">Título</label>
                                <input id="titulo" 
                                       type="text" 
                                       name="titulo" 
                                       pattern="<%= UtilesIncidencia.REG_TITULO%>" 
                                       required 
                                       value="<%= incidencia.getTitulo()%>"/>
                            </div>

                            <div class="field-set">
                                <label for="info">Info</label>
                                <input id="info" 
                                       type="text" 
                                       name="info" 
                                       pattern="<%= UtilesIncidencia.REG_INFO%>" 
                                       required 
                                       value="<%= incidencia.getInfo()%>"/>
                            </div>

                            <div class="field-set">
                                <label for="estado">Estado</label>
                                <select id="estado" name="estado" required>
                                    <% if (incidencia.getEstado() == UtilesIncidencia.INCIDENCIA_CERRADA) {%>
                                    <option value="<%= UtilesIncidencia.INCIDENCIA_CERRADA%>" selected><%= "Incidencia Cerrada"%></option>
                                    <option value="<%= UtilesIncidencia.INCIDENCIA_ABIERTA%>"><%= "Incidencia Abierta"%></option>
                                    <% } else {%>
                                    <option value="<%= UtilesIncidencia.INCIDENCIA_CERRADA%>"><%= "Incidencia Cerrada"%></option>
                                    <option value="<%= UtilesIncidencia.INCIDENCIA_ABIERTA%>" selected><%= "Incidencia Abierta"%></option>
                                    <% }%>
                                </select>
                            </div>

                            <div class="field-set">
                                <label for="fecha">Fecha</label>
                                <input id="fecha" 
                                       type="datetime-local" 
                                       name="fecha"
                                       value="<%= sdf.format(incidencia.getFecha())%>"
                                       required />
                            </div>

                            <div class="field-set">
                                <label for="autor">Autor</label>
                                <select id="autor" name="autor" required>
                                    <% for (Usuario u : usuarios) {%>
                                    <% if (u.getId() == incidencia.getAutor()) {%>
                                    <option value="<%= u.getId()%>" selected><%= u.getAlias() + " (" + u.getPerfilInfo() + ")"%></option>
                                    <% } else {%>
                                    <option value="<%= u.getId()%>"><%= u.getAlias() + " (" + u.getPerfilInfo() + ")"%></option>
                                    <% }%>
                                    <% }%>
                                </select>
                            </div>

                            <div class="field-set">
                                <label for="dependencia">Dependencia</label>
                                <select id="dependencia" name="dependencia" required>
                                    <% for (Dependencia d : dependencias) {%>
                                    <% if (d.getId() == incidencia.getDependencia()) {%>
                                    <option value="<%= d.getId()%>" selected><%= d.getNombre()%></option>
                                    <% } else {%>
                                    <option value="<%= d.getId()%>"><%= d.getNombre()%></option>
                                    <% }%>
                                    <% }%>
                                </select>
                            </div>

                            <div class="field-set">
                                <label for="especialidad">Especialidad</label>
                                <select id="especialidad" name="especialidad" required>
                                    <% for (Especialidad e : especialidades) {%>
                                    <% if (e.getId() == incidencia.getEspecialidad()) {%>
                                    <option value="<%= e.getId()%>" selected><%= e.getNombre()%></option>
                                    <% } else {%>
                                    <option value="<%= e.getId()%>"><%= e.getNombre()%></option>
                                    <% }%>
                                    <% }%>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="controles">
                        <button class="btn btn-submit" type="submit">Enviar</button>
                        <button class="btn btn-reset" type="reset">Reiniciar</button>
                    </div>
                </form>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/incidencia/incidencia-modificacion.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
