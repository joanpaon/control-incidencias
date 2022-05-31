<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="es">

    <head>
        <!-- These lines go in the first 1024 bytes -->
        <meta charset="utf-8" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <title>JAPOLabs Java Framework</title>

        <!-- References -->
        <meta name="author" content="2022 - José A. Pacheco Ondoño - japolabs@gmail.com" />
        <meta name="description" content="JAPOLabs Java Framework" />

        <!-- Configuration -->
        <meta name="keywords" content="" />
        <meta name="robots" content="noindex, nofollow" />

        <!-- Viewport Setup for mobile devices -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Favicon -->
        <link href="public/img/logo.png" rel="icon" type="image/x-icon" />

        <!-- Style Sheet Links -->
        <link rel="stylesheet" href="public/css/message/message.css" />
        <link rel="stylesheet" href="public/css/partials/header.css" />
        <link rel="stylesheet" href="public/css/partials/footer.css" />
        <link rel="stylesheet" href="public/css/partials/button-login.css" />
    </head>

    <body>
        <!-- Contenido Web -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <h2>Operación Cancelada</h2>

                <p>No se ha podido completar la operación</p>

                <div class="controles">
                    <%@include file="/WEB-INF/views/partials/button-login.jspf" %>
                </div>

            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Scripts -->
        <script src="public/js/message/message.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
        <script src="public/js/partials/button-login.js"></script>
    </body> 
</html>