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
import java.util.Date;
import org.japo.java.dll.DLLNotificacion;
import org.japo.java.entities.Notificacion;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesIncidencia;
import org.japo.java.libraries.UtilesNotificacion;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandNotificacionInsercion extends Command {

    @Override
    public void process() throws ServletException, IOException {
        // Salida
        String out = "notificacion/notificacion-insercion";

        // Validar Sesión
        if (validarSesion(request)) {

            // Obtener Operación
            String op = request.getParameter("op");

            if (op == null || op.equals("captura")) {
                // Datos a Inyectar
                int incidencia = UtilesIncidencia.obtenerIdRequest(request);

                // Inyeccion de Datos
                request.setAttribute("incidencia", incidencia);
            } else if (op.equals("proceso")) {
                // Request > Sesión > Usuario
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

                // Capas de Datos
                DLLNotificacion dllNotificacion = new DLLNotificacion(config);

                // Request > Parámetros
                Date fecha = UtilesNotificacion.obtenerFechaRequest(request);
                int incidencia = UtilesNotificacion.obtenerIncidenciaRequest(request);
                String info = UtilesNotificacion.obtenerInfoRequest(request);

                // Parámetros > Incidencia
                Notificacion notificacion = new Notificacion(
                        UtilesNotificacion.DEF_ID, fecha,
                        usuario.getId(), "", "",
                        incidencia, "", 
                        info);

                // Entidad > Inserción BD - true | false
                boolean checkOK = dllNotificacion.insertar(notificacion);

                // Validar Operación
                if (checkOK) {
                    out = "controller?cmd=incidencia-listado";
                } else {
                    out = "message/operacion-cancelada";
                }
            }
        } else {
            out = "message/sesion-invalida";
        }

        // Redirección
        forward(out);
    }
}
