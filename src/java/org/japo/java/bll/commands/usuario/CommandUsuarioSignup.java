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

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import org.japo.java.dll.DLLUsuario;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesUsuario;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandUsuarioSignup extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "usuario/usuario-registro";

        // Capas de Negocio
        DLLUsuario dalUsuario = new DLLUsuario(config);

        // Obtener Operación
        String op = request.getParameter("op");

        // Procesar Operación
        if (op == null || op.equals("captura")) {
            // ---
        } else if (op.equals("proceso")) {
            // Request > Parámetros
            String user = UtilesUsuario.obtenerUserRequest(request);
            String pass = UtilesUsuario.obtenerPassRequest(request);
            String avatar = UtilesUsuario.obtenerAvatarRequest(config, request);
            int perfil = UtilesUsuario.obtenerPerfilRequest(request);

            // Parámetros > Entidad
            Usuario usuario = new Usuario(0, user, pass, avatar, perfil, "");

            // Entidad > Inserción BD - true | false
            boolean operacionOK = dalUsuario.insertar(usuario);

            // Validar Inserción BD
            if (operacionOK) {
                out = "controller?cmd=usuario-login";
            } else {
                out = "message/credencial-erronea";
            }
        } else {
            // URL de Acceso Errónea
            out = "message/operacion-desconocida";
        }

        // Redirección
        forward(out);
    }
}
