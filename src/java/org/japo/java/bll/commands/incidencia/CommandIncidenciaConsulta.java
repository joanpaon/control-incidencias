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
package org.japo.java.bll.commands.incidencia;

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import org.japo.java.dll.DLLNotificacion;
import org.japo.java.entities.Incidencia;
import org.japo.java.entities.Notificacion;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesIncidencia;
import org.japo.java.libraries.UtilesPerfil;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandIncidenciaConsulta extends Command {

    @Override
    public void process() throws ServletException, IOException {
        // Salida
        String out = "incidencia/incidencia-consulta";

        // Validar Sesión
        if (validarSesion(request)) {
            // Sesión > Usuario
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

            // Capas de Datos
            DLLNotificacion dllNotificacion = new DLLNotificacion(config);

            // Request + ID incidencia + BD > Incidencia
            Incidencia incidencia = UtilesIncidencia.
                    consultarIncidenciaIdRequest(config, request);

            // Valida Autoría Incidencia | Administrador
            if (incidencia.getAutor() == usuario.getId()
                    || usuario.getPerfil() >= UtilesPerfil.ADMIN_CODE) {
                // Datos a Inyectar
                List<Notificacion> notificaciones = dllNotificacion.
                        listar(incidencia.getId());

                // Enlaza Datos > JSP
                request.setAttribute("incidencia", incidencia);
                request.setAttribute("notificaciones", notificaciones);
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
