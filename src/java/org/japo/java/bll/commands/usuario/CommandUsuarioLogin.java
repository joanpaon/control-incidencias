/* 
 * Copyright 2022 JAPO Labs - japolabs@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.bll.commands.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.japo.java.bll.commands.Command;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesUsuario;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandUsuarioLogin extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "usuario/usuario-login";

        // Procesar Operación
        if (validarSesion(request)) {
            // Salida > Principal del perfil
            out = UtilesUsuario.obtenerComandoVistaPrincipal(request);
        } else {
            // Obtener Operación
            String op = request.getParameter("op");

            if (op == null || op.equals("captura")) {
                // Vista Predeterminada
            } else if (op.equals("proceso")) {
                // Entrada + BD > Usuario
                Usuario usuario = UtilesUsuario.obtenerUsuarioEmailRequest(config, request);

                // Validar Usuario
                if (usuario == null) {
                    // Credenciales Inválidas > Acceso Denegado
                    out = "message/acceso-denegado";
                } else {
                    // Regenerar Sesión Actual
                    HttpSession sesion = UtilesUsuario.reiniciarSesion(config, request);

                    // Usuario > Sesión
                    sesion.setAttribute("usuario", usuario);

                    // Salida > Principal del perfil
                    out = UtilesUsuario.obtenerComandoVistaPrincipal(request);
                }
            } else {
                // Vista > Operación Desconocida
                out = "message/operacion-desconocida";
            }
        }

        // Redirección
        forward(out);
    }
}
