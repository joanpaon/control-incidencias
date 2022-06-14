<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>
        
        <link rel="stylesheet" href="public/css/partials/button-login.css" />
        
        <link rel="stylesheet" href="public/css/message/message.css" />
    </head>

    <body>
        <!-- Contenido Web -->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <h2>Operación desconocida</h2>

                <p>No se ha reconocido la operación especificada</p>

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