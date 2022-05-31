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
package org.japo.java.bll.commands.permiso.usuario;

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import org.japo.java.bll.commands.usuario.CommandUsuarioValidation;
import org.japo.java.dll.DLLPermisoUsuario;
import org.japo.java.entities.PermisoUsuario;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandPermisoUsuarioBorrado extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "permiso/usuario/permiso-usuario-borrado";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validador de Acceso
            CommandUsuarioValidation validator = new CommandUsuarioValidation(
                    config, request.getSession(false));

            if (validator.validarAccesoAdmin(request.getSession(false))) {
                // Capas de Datos
                DLLPermisoUsuario dalPermiso = new DLLPermisoUsuario(config);

                // URL > ID Objeto
                int id = Integer.parseInt(request.getParameter("id"));

                // request > ID Operación
                String op = request.getParameter("op");

                // ID Entidad + BD > JSP Modificación
                if (op == null || op.equals("captura")) {
                    // ID Entidad + BD > Entidad
                    PermisoUsuario permiso = dalPermiso.consultar(id);

                    // Enlaza Datos > JSP
                    request.setAttribute("permiso", permiso);
                } else if (op.equals("proceso")) {
                    // ID > Registro Borrado - true | false
                    boolean checkOK = dalPermiso.borrar(id);

                    // Validar Operación
                    if (checkOK) {
                        out = "controller?cmd=permiso-usuario-listado";
                    } else {
                        out = "message/operacion-cancelada";
                    }
                } else {
                    out = "message/operacion-desconocida";
                }
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
