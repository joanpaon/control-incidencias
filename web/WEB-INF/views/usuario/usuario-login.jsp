<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/WEB-INF/views/partials/common-head.jspf" %>

        <link rel="stylesheet" href="public/css/usuario/usuario-login.css" />
    </head>

    <body>
        <!-- Web Content-->
        <div id="container">
            <%@include file="/WEB-INF/views/partials/header.jspf" %>

            <main>
                <img class="watermark" src="public/img/water.png" alt="watermark" />

                <form action="?cmd=usuario-login&op=proceso" 
                      method="post" 
                      accept-charset="Windows-1252">
                    <div class="fieldset">
                        <label for="email">EMail</label>
                        <input
                            id="email"
                            type="email"
                            name="email"
                            required
                            autocomplete="email"
                            />
                    </div>

                    <div class="fieldset">
                        <label for="pass">Contraseña</label>
                        <input
                            id="pass"
                            type="password"
                            name="pass"
                            required
                            pattern="\w{3,20}"
                            autocomplete="current-password"
                            />
                    </div>

                    <div class="buttonset">
                        <button class="btn" type="submit">Aceptar ✅</button> 
                        <button class="btn" type="reset">Reiniciar ❌</button>
                    </div>
                </form>
            </main>

            <%@include file="/WEB-INF/views/partials/footer.jspf" %>
        </div>

        <!-- Application Scripts -->
        <script src="public/js/usuario/usuario-login.js"></script>
        <script src="public/js/partials/header.js"></script>
        <script src="public/js/partials/footer.js"></script>
    </body>
</html>
