<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <link rel="stylesheet" href="public/css/main/main-admin.css" />
        <link rel="stylesheet" href="public/css/partials/header.css" />
        <link rel="stylesheet" href="public/css/partials/footer.css" />
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <h2>Página Principal del Administrador</h2>

                <div class="controles">
                    <a class="btn" href="?cmd=permiso-usuario-listado">PermisosU</a>
                    <a class="btn" href="?cmd=usuario-listado">Usuarios</a>
                    <a class="btn" href="?cmd=usuario-galeria">Galería</a>
                </div>
                
                <div class="controles">
                    <a class="btn" href="?cmd=dependencia-listado">Dependencias</a>
                    <a class="btn" href="?cmd=especialidad-listado">Especialidades</a>
                    <a class="btn" href="?cmd=incidencia-listado">Incidencias</a>
                    <a class="btn" href="?cmd=notificacion-listado">Notificaciones</a>
                </div>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/main/main-admin.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
