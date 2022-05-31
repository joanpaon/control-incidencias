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
package org.japo.java.bll.commands.permiso.perfil;

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import org.japo.java.bll.commands.usuario.CommandUsuarioValidation;
import org.japo.java.dll.DLLPermisoPerfil;
import org.japo.java.entities.PermisoPerfil;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandPermisoPerfilConsulta extends Command {

    @Override
    public void process() throws ServletException, IOException {
        // Salida
        String out = "permiso/perfil/permiso-perfil-consulta";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validador de Acceso
            CommandUsuarioValidation validator = new CommandUsuarioValidation(
                    config, request.getSession(false));

            if (validator.validarAccesoDevel(request.getSession(false))) {
                // Capas de Datos
                DLLPermisoPerfil dalPermiso = new DLLPermisoPerfil(config);

                // Request > ID Permiso Perfil
                int id = Integer.parseInt(request.getParameter("id"));

                // ID Permiso Perfil + BD > Permiso Perfil
                PermisoPerfil permiso = dalPermiso.consultar(id);

                // Enlaza Datos > JSP
                request.setAttribute("permiso", permiso);
            } else {
                out = "message/acceso-denegado";
            }
        } else {
            out = "message/sesion-invalida";
        }

        // Redirección
        forward(out);
    }
}
