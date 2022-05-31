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
        <link rel="stylesheet" href="public/css/notificacion/notificacion-insercion.css" /> 
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
