<%@page import="org.japo.java.libraries.UtilesPermisoPerfil"%>
<%@page import="org.japo.java.entities.Proceso"%>
<%@page import="org.japo.java.entities.Perfil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    List<Proceso> procesos = (ArrayList<Proceso>) request.getAttribute("procesos");
    List<Perfil> perfiles = (ArrayList<Perfil>) request.getAttribute("perfiles");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/permiso/permiso-insercion.css" /> 
    </head>

    <body>
        <!-- Contenido Web -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Inserción de Permisos de Perfil</h2>
                    <a class="btn btn-listar" href="?cmd=permiso-perfil-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=permiso-perfil-insercion&op=proceso">
                    <div class="form-content">
                        <div class="field-container">
                            <div class="field-set">
                                <label for="proceso">Proceso</label>
                                <select id="proceso" name="proceso" required>
                                    <option disabled selected value></option>
                                    <% for (Proceso p : procesos) {%>
                                    <option value="<%= p.getId()%>"><%= p.getInfo()%></option>
                                    <% }%>
                                </select>
                            </div>

                            <div class="field-set">
                                <label for="perfil">Perfil</label>
                                <select id="perfil" name="perfil" required>
                                    <option disabled selected value></option>
                                    <% for (Perfil p : perfiles) {%>
                                    <option value="<%= p.getId()%>"><%= p.getNombre()%></option>
                                    <% }%>
                                </select>
                            </div>

                            <div class="field-set">
                                <label for="info">Información</label>
                                <input id="info" 
                                       type="text" 
                                       name="info" 
                                       pattern="<%= UtilesPermisoPerfil.REG_INFO%>" 
                                       required />
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
        <script src="public/js/permiso/permiso-insercion.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
