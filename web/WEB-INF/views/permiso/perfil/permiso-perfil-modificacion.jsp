<%@page import="org.japo.java.libraries.UtilesPermisoPerfil"%>
<%@page import="org.japo.java.entities.PermisoPerfil"%>
<%@page import="org.japo.java.entities.Proceso"%>
<%@page import="org.japo.java.entities.Perfil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    PermisoPerfil permiso = (PermisoPerfil) request.getAttribute("permiso");
    List<Proceso> procesos = (ArrayList<Proceso>) request.getAttribute("procesos");
    List<Perfil> perfiles = (ArrayList<Perfil>) request.getAttribute("perfiles");
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
        <link rel="stylesheet" href="public/css/permiso/permiso-modificacion.css" /> 
        <link rel="stylesheet" href="public/css/partials/header.css" />
        <link rel="stylesheet" href="public/css/partials/footer.css" />
    </head>

    <body>
        <!-- Web Content -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>

                <img class="watermark" src="public/img/logo01.png" alt="Logo" />

                <header>
                    <h2>Modificación de Permisos de Perfil</h2>
                    <a class="btn btn-listar" href="?cmd=permiso-perfil-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=permiso-perfil-modificacion&op=proceso&id=<%= permiso.getId()%>">
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
                                <label for="perfil">Perfil</label>
                                <select id="perfil" name="perfil" required>
                                    <option disabled selected value></option>
                                    <% for (Perfil p : perfiles) {%>
                                    <% if (p.getId() == permiso.getPerfil()) {%>
                                    <option value="<%= p.getId()%>" selected><%= p.getNombre()%></option>
                                    <% } else {%>
                                    <option value="<%= p.getId()%>"><%= p.getNombre()%></option>
                                    <% }%>
                                    <% }%>
                                </select>
                            </div>

                            <div class="field-set">
                                <label for="info">Información</label>
                                <input id="info" 
                                       type="text" 
                                       name="info" 
                                       pattern="<%= UtilesPermisoPerfil.REG_INFO%>" 
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
