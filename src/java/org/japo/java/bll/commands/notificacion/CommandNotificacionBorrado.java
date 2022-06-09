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
package org.japo.java.bll.commands.notificacion;

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import org.japo.java.dll.DLLNotificacion;
import org.japo.java.entities.Notificacion;
import org.japo.java.libraries.UtilesNotificacion;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandNotificacionBorrado extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "notificacion/notificacion-borrado";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validar Acceso
            if (validarAccesoAdmin(request.getSession(false))) {
                // Capas de Datos
                DLLNotificacion dllNotificacion = new DLLNotificacion(config);

                // Request + ID Entidad + BD > Entidad
                Notificacion notificacion = UtilesNotificacion.
                        consultarNotificacionIdRequest(config, request);

                // request > ID Operación
                String op = request.getParameter("op");

                // ID Entidad + BD > JSP Modificación
                if (op == null || op.equals("captura")) {
                    // Enlaza Datos > JSP
                    request.setAttribute("notificacion", notificacion);
                } else if (op.equals("proceso")) {
                    // ID > Registro Borrado - true | false
                    boolean checkOK = dllNotificacion.borrar(notificacion.getId());

                    // Validar Operación
                    if (checkOK) {
                        out = "controller?cmd=notificacion-listado";
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
