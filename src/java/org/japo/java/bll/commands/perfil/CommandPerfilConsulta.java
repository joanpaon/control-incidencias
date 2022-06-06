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
package org.japo.java.bll.commands.perfil;

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import org.japo.java.dll.DLLPerfil;
import org.japo.java.entities.Perfil;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandPerfilConsulta extends Command {

    @Override
    public void process() throws ServletException, IOException {
        // JSP
        String out = "perfil/perfil-consulta";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validar Acceso
            if (validarAccesoDevel(request.getSession(false))) {
                // Capas de Datos
                DLLPerfil dalPerfil = new DLLPerfil(config);

                // Request > ID Perfil
                int id = Integer.parseInt(request.getParameter("id"));

                // ID Perfil > Perfil
                Perfil perfil = dalPerfil.consultar(id);

                // Inyecta Datos > JSP
                request.setAttribute("perfil", perfil);
            } else {
                out = "message/acceso-denegado";
            }
        } else {
            out = "messages/sesion-invalida";
        }

        // Redirección
        forward(out);
    }
}
