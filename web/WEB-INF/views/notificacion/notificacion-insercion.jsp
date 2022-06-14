<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.japo.java.libraries.UtilesNotificacion"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    int incidencia = (Integer) request.getAttribute("incidencia");

    // Formateador Temporal
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>
        
        <link rel="stylesheet" href="public/css/notificacion/notificacion-insercion.css" /> 
    </head>

    <body>
        <!-- Contenido Web -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Inserción de Notificaciones</h2>
                    <a class="btn" href="?cmd=proceso-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=notificacion-insercion&op=proceso">

                    <input name="incidencia" type="hidden" value="<%= incidencia%>" />

                    <div class="form-content">
                        <div class="field-container">
                            <div class="field-set">
                                <label for="fecha">Fecha</label>
                                <input id="fecha" 
                                       type="datetime-local" 
                                       name="fecha"
                                       value="<%= sdf.format(new Date())%>"
                                       required />
                            </div>

                            <div class="field-set">
                                <label for="info">Información</label>
                                <input id="info" 
                                       type="text" 
                                       name="info" 
                                       pattern="<%= UtilesNotificacion.REG_INFO%>" 
                                       required />
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
        <script src="public/js/notificacion/notificacion-insercion.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
