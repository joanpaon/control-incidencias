<%@page import="org.japo.java.entities.Incidencia"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.japo.java.libraries.UtilesNotificacion"%>
<%@page import="org.japo.java.entities.Notificacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    Notificacion notificacion = (Notificacion) request.getAttribute("notificacion");
    List<Incidencia> incidencias = (ArrayList<Incidencia>) request.getAttribute("incidencias");
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
        <link rel="stylesheet" href="public/css/notificacion/notificacion-modificacion.css" /> 
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
                    <h2>Modificación de Notificaciones</h2>
                    <a class="btn btn-listar" href="?cmd=notificacion-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=notificacion-modificacion&op=proceso&id=<%= notificacion.getId()%>">
                    <div class="form-content">
                        <div class="field-container">
                            <div class="field-set">
                                <label for="fecha">Fecha</label>
                                <input id="fecha" 
                                       type="datetime-local" 
                                       name="fecha"
                                       value="<%= sdf.format(notificacion.getFecha())%>"
                                       required />
                            </div>
                            <div class="field-set">
                                <label for="autor">Autor</label>
                                <select id="autor" name="autor" required>
                                    <% for (Usuario u : usuarios) {%>
                                    <% if (u.getId() == notificacion.getAutor()) {%>
                                    <option value="<%= u.getId()%>" selected><%= u.getUser()%></option>
                                    <% } else {%>
                                    <option value="<%= u.getId()%>"><%= u.getUser()%></option>
                                    <% }%>
                                    <% }%>
                                </select>
                            </div>
                            <div class="field-set">
                                <label for="incidencia">Incidencia</label>
                                <select id="incidencia" name="incidencia" required>
                                    <% for (Incidencia i : incidencias) {%>
                                    <% if (i.getId() == notificacion.getIncidencia()) {%>
                                    <option value="<%= i.getId()%>" selected><%= i.getTitulo()%></option>
                                    <% } else {%>
                                    <option value="<%= i.getId()%>"><%= i.getTitulo()%></option>
                                    <% }%>
                                    <% }%>
                                </select>
                            </div>
                            <div class="field-set">
                                <label for="info">Info</label>
                                <input id="info" 
                                       type="text" 
                                       name="info" 
                                       pattern="<%= UtilesNotificacion.REG_INFO%>" 
                                       required 
                                       value="<%= notificacion.getInfo()%>"/>
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
        <script src="public/js/notificacion/notificacion-modificacion.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
