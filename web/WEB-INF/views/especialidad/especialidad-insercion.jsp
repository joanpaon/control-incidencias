<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.japo.java.libraries.UtilesEspecialidad"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos inyectados
    List<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/especialidad/especialidad-insercion.css" /> 
    </head>

    <body>
        <!-- Contenido Web -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Inserción de Especialidades</h2>
                    <a class="btn btn-listar" href="?cmd=especialidad-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=especialidad-insercion&op=proceso">
                    <div class="form-content">
                        <div class="field-container">
                            <div class="field-set">
                                <label for="nombre">Nombre</label>
                                <input id="nombre" 
                                       type="text" 
                                       name="nombre" 
                                       pattern="<%= UtilesEspecialidad.REG_NOMBRE%>" 
                                       required />
                            </div>

                            <div class="field-set">
                                <label for="info">Información</label>
                                <input id="info" 
                                       type="text" 
                                       name="info" 
                                       pattern="<%= UtilesEspecialidad.REG_INFO%>" 
                                       required />
                            </div>

                            <div class="field-set">
                                <label for="responsable">Responsable</label>
                                <select id="responsable" name="responsable" required>
                                    <% for (Usuario u : usuarios) {%>
                                    <option value="<%= u.getId()%>"><%= u.getAlias() + " (" + u.getPerfilInfo() + ")"%></option>
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
        <script src="public/js/especialidad/especialidad-insercion.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
