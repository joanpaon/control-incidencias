<%@page import="org.japo.java.libraries.UtilesPerfil"%>
<%@page import="org.japo.java.entities.Perfil"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    Perfil perfil = (Perfil) request.getAttribute("perfil");
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
        <link rel="stylesheet" href="public/css/perfil/perfil-modificacion.css" /> 
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
                    <h2>Modificación de Perfiles</h2>
                    <a class="btn btn-listar" href="?cmd=perfil-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=perfil-modificacion&op=proceso&id=<%= perfil.getId()%>">
                    <div class="form-content">
                        <div class="field-container">
                            <div class="field-set">
                                <label for="nombre">Nombre</label>
                                <input id="nombre" 
                                       type="text" 
                                       name="nombre" 
                                       pattern="<%= UtilesPerfil.REG_NOMBRE%>" 
                                       required 
                                       value="<%= perfil.getNombre()%>"/>
                            </div>

                            <div class="field-set">
                                <label for="pass">Información</label>
                                <input id="info" 
                                       type="text" 
                                       name="info" 
                                       pattern="<%= UtilesPerfil.REG_INFO%>" 
                                       required 
                                       value="<%= perfil.getInfo()%>"/>
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
        <script src="public/js/perfil/perfil-modificacion.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
