<%@page import="org.japo.java.entities.Especialidad"%>
<%@page import="org.japo.java.entities.Dependencia"%>
<%@page import="org.japo.java.libraries.UtilesIncidencia"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    List<Dependencia> dependencias = (ArrayList<Dependencia>) request.getAttribute("dependencias");
    List<Especialidad> especialidades = (ArrayList<Especialidad>) request.getAttribute("especialidades");
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
        <meta name="description" content="JAPOLabs Java Framework" />

        <!-- Configuration -->
        <meta name="keywords" content="" />
        <meta name="robots" content="noindex, nofollow" />

        <!-- Viewport Setup for mobile devices -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Favicon -->
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />

        <!-- Style Sheet Links -->
        <link rel="stylesheet" href="public/css/incidencia/incidencia-insercion.css" /> 
        <link rel="stylesheet" href="public/css/partials/header.css" />
        <link rel="stylesheet" href="public/css/partials/footer.css" />
    </head>

    <body>
        <!-- Contenido Web -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>

                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Inserción de Incidencias</h2>
                    <a class="btn" href="?cmd=proceso-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=incidencia-insercion&op=proceso">
                    <div class="form-content">
                        <div class="field-container">
                            <div class="field-set">
                                <label for="titulo">Título</label>
                                <input id="titulo" 
                                       type="text" 
                                       name="titulo" 
                                       pattern="<%= UtilesIncidencia.REG_TITULO %>" 
                                       required />
                            </div>

                            <div class="field-set">
                                <label for="info">Información</label>
                                <input id="info" 
                                       type="text" 
                                       name="info" 
                                       pattern="<%= UtilesIncidencia.REG_INFO%>" 
                                       required />
                            </div>

                            <div class="field-set">
                                <label for="dependencia">Dependencia</label>
                                <select id="dependencia" name="dependencia" required>
                                    <option disabled selected value></option>
                                    <% for (Dependencia d : dependencias) {%>
                                    <option value="<%= d.getId()%>"><%= d.getNombre()%></option>
                                    <% }%>
                                </select>
                            </div>                                       

                            <div class="field-set">
                                <label for="especialidad">Especialidad</label>
                                <select id="especialidad" name="especialidad" required>
                                    <option disabled selected value></option>
                                    <% for (Especialidad e : especialidades) {%>
                                    <option value="<%= e.getId()%>"><%= e.getNombre()%></option>
                                    <% }%>
                                </select>
                            </div>                                       
                        </div>
                    </div>

                    <div class="controles">
                        <button class="btn" type="submit">Enviar</button>
                        <button class="btn" type="reset">Reiniciar</button>
                    </div>
                </form>

            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/incidencia/incidencia-insercion.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>

</html>
