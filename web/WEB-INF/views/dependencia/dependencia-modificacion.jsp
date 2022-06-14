<%@page import="org.japo.java.libraries.UtilesDependencia"%>
<%@page import="org.japo.java.entities.Dependencia"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    Dependencia dependencia = (Dependencia) request.getAttribute("dependencia");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/dependencia/dependencia-modificacion.css" /> 
    </head>

    <body>
        <!-- Web Content -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Modificación de Dependencias</h2>
                    <a class="btn btn-listar" href="?cmd=dependencia-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=dependencia-modificacion&op=proceso&id=<%= dependencia.getId()%>">
                    <div class="form-content">
                        <div class="field-container">
                            <div class="field-set">
                                <label for="nombre">Nombre</label>
                                <input id="nombre" 
                                       type="text" 
                                       name="nombre" 
                                       pattern="<%= UtilesDependencia.REG_NOMBRE%>" 
                                       required 
                                       value="<%= dependencia.getNombre()%>"/>
                            </div>

                            <div class="field-set">
                                <label for="info">Info</label>
                                <input id="info" 
                                       type="text" 
                                       name="info" 
                                       pattern="<%= UtilesDependencia.REG_INFO%>" 
                                       required 
                                       value="<%= dependencia.getInfo()%>"/>
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
        <script src="public/js/dependencia/dependencia-modificacion.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
