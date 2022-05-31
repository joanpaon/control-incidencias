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

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandUsuarioLogout extends Command {

    @Override
    public void process() throws ServletException, IOException {
        // Salida
        String out = "controller?cmd=usuario-login";

        // Validar Sesión
        if (validarSesion(request)) {
            // request > Sesión Actual
            HttpSession sesion = request.getSession(false);

            // Cerrar Sesión Actual
            sesion.invalidate();
        } else {
            // JSP
            out = "messages/sesion-caducada";
        }

        // Redirección JSP
        forward(out);
    }
}
