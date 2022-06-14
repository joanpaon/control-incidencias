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
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/incidencia/incidencia-insercion.css" /> 
    </head>

    <body>
        <!-- Contenido Web -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Inserción de Incidencias</h2>
                    <a class="btn" href="?cmd=incidencia-listado">Listado</a>
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
                                       pattern="<%= UtilesIncidencia.REG_TITULO%>" 
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
