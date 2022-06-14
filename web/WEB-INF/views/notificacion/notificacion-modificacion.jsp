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
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/notificacion/notificacion-modificacion.css" /> 
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
                                    <option value="<%= u.getId()%>" selected><%= u.getAlias() + " (" + u.getPerfilInfo() + ")"%></option>
                                    <% } else {%>
                                    <option value="<%= u.getId()%>"><%= u.getAlias() + " (" + u.getPerfilInfo() + ")"%></option>
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
