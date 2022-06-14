<%@page import="org.japo.java.libraries.UtilesPermisoUsuario"%>
<%@page import="org.japo.java.entities.PermisoUsuario"%>
<%@page import="org.japo.java.entities.Proceso"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    PermisoUsuario permiso = (PermisoUsuario) request.getAttribute("permiso");
    List<Proceso> procesos = (ArrayList<Proceso>) request.getAttribute("procesos");
    List<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/permiso/permiso-modificacion.css" /> 
    </head>

    <body>
        <!-- Web Content -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Modificación de Permisos de Usuario</h2>
                    <a class="btn btn-listar" href="?cmd=permiso-usuario-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=permiso-usuario-modificacion&op=proceso&id=<%= permiso.getId()%>">
                    <div class="form-content">
                        <div class="field-container">
                            <div class="field-set">
                                <label for="proceso">Proceso</label>
                                <select id="proceso" name="proceso" required>
                                    <% for (Proceso p : procesos) {%>
                                    <% if (p.getId() == permiso.getProceso()) {%>
                                    <option value="<%= p.getId()%>" selected><%= p.getInfo()%></option>
                                    <% } else {%>
                                    <option value="<%= p.getId()%>"><%= p.getInfo()%></option>
                                    <% }%>
                                    <% }%>
                                </select>
                            </div>

                            <div class="field-set">
                                <label for="usuario">Usuario</label>
                                <select id="usuario" name="usuario" required>
                                    <option disabled selected value></option>
                                    <% for (Usuario u : usuarios) {%>
                                    <% if (u.getId() == permiso.getUsuario()) {%>
                                    <option value="<%= u.getId()%>" selected><%= u.getAlias()%></option>
                                    <% } else {%>
                                    <option value="<%= u.getId()%>"><%= u.getAlias()%></option>
                                    <% }%>
                                    <% }%>
                                </select>
                            </div>

                            <div class="field-set">
                                <label for="info">Información</label>
                                <input id="info" 
                                       type="text" 
                                       name="info" 
                                       pattern="<%= UtilesPermisoUsuario.REG_INFO%>" 
                                       required 
                                       value="<%= permiso.getInfo()%>" />
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
        <script src="public/js/permiso/permiso-modificacion.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
