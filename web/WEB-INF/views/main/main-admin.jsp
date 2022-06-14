<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>
        
        <link rel="stylesheet" href="public/css/main/main-admin.css" />
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
