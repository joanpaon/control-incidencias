<%@page import="org.japo.java.libraries.UtilesUsuario"%>
<%@page import="org.japo.java.libraries.UtilesPerfil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
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
        <link rel="stylesheet" href="public/css/usuario/usuario-insercion.css" /> 
        <link rel="stylesheet" href="public/css/partials/header.css" />
        <link rel="stylesheet" href="public/css/partials/footer.css" />
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <header>
                    <h2>Inserción de Usuarios</h2>
                    <a class="btn btn-listar" href="?cmd=usuario-listado">Listado</a>
                </header>

                <form method="post" 
                      accept-charset="Windows-1252" 
                      enctype="multipart/form-data"
                      action="?cmd=usuario-insercion&op=proceso">
                    <input type="hidden" name="perfil" value="<%= UtilesPerfil.BASIC_CODE%>" />

                    <div class="form-content">
                        <div class="field-container">
                            <div class="field-set">
                                <label for="user">Usuario</label>
                                <input id="user" 
                                       type="text" 
                                       name="user" 
                                       pattern="<%= UtilesUsuario.REG_ALIAS%>" 
                                       required />
                            </div>

                            <div class="field-set">
                                <label for="mail">EMail</label>
                                <input id="mail" 
                                       type="email" 
                                       name="mail" 
                                       required />
                            </div>

                            <div class="field-set">
                                <label for="pass">Contraseña</label>
                                <input id="pass" 
                                       type="text" 
                                       name="pass" 
                                       pattern="<%= UtilesUsuario.REG_PASS%>" 
                                       required />
                            </div>

                            <div class="field-set">
                                <label for="avatar">Avatar</label>
                                <div type="text" class="avatar-name">Predeterminado</div>
                                <input id="avatar" 
                                       type="file" 
                                       name="avatar" 
                                       accept="image/png,image/jpeg" 
                                       style="display: none" />
                                <button type="button" 
                                        class="btn btn-img" 
                                        onclick="document.getElementById('avatar').click()">👀</button>
                            </div>
                        </div>

                        <div class="avatar-frame">
                            <div class="avatar">
                                <img class="actual" src="<%= UtilesUsuario.DEF_AVATAR%>" alt="Avatar"/>
                                <img class="backup" src="<%= UtilesUsuario.DEF_AVATAR%>" alt="Avatar" style="display: none;" />
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
        <script src="public/js/usuario/usuario-insercion.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
