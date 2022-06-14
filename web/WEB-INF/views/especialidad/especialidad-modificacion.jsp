<%@page import="org.japo.java.libraries.UtilesEspecialidad"%>
<%@page import="org.japo.java.entities.Especialidad"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    Especialidad especialidad = (Especialidad) request.getAttribute("especialidad");
    List<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/especialidad/especialidad-modificacion.css" /> 
    </head>

    <body>
        <!-- Web Content -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Modificación de Especialidades</h2>
                    <a class="btn btn-listar" href="?cmd=especialidad-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=especialidad-modificacion&op=proceso&id=<%= especialidad.getId()%>">
                    <div class="form-content">
                        <div class="field-container">
                            <div class="field-set">
                                <label for="nombre">Nombre</label>
                                <input id="nombre" 
                                       type="text" 
                                       name="nombre" 
                                       pattern="<%= UtilesEspecialidad.REG_NOMBRE%>" 
                                       required 
                                       value="<%= especialidad.getNombre()%>"/>
                            </div>

                            <div class="field-set">
                                <label for="info">Info</label>
                                <input id="info" 
                                       type="text" 
                                       name="info" 
                                       pattern="<%= UtilesEspecialidad.REG_INFO%>" 
                                       required 
                                       value="<%= especialidad.getInfo()%>"/>
                            </div>

                            <div class="field-set">
                                <label for="responsable">Responsable</label>
                                <select id="responsable" name="responsable" required>
                                    <% for (Usuario u : usuarios) {%>
                                    <% if (u.getId() == especialidad.getResponsable()) {%>
                                    <option value="<%= u.getId()%>" selected><%= u.getAlias() + " (" + u.getPerfilInfo() + ")"%></option>
                                    <% } else {%>
                                    <option value="<%= u.getId()%>"><%= u.getAlias() + " (" + u.getPerfilInfo() + ")"%></option>
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
        <script src="public/js/especialidad/especialidad-modificacion.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
