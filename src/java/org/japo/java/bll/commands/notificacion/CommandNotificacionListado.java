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
import java.util.List;
import org.japo.java.dll.DLLNotificacion;
import org.japo.java.entities.Notificacion;
import org.japo.java.entities.ParamPagina;
import org.japo.java.libraries.UtilesParamPagina;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandNotificacionListado extends Command {

    @Override
    public void process() throws ServletException, IOException {
        // Salida
        String out = "notificacion/notificacion-listado";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validar Acceso
            if (validarAccesoAdmin(request.getSession(false))) {
                // Capas de Datos
                DLLNotificacion dllNotificacion = new DLLNotificacion(config);

                // BD > Parámetros Listado
                long rowCount = dllNotificacion.contar();

                // Generar Entidad Navegación
                ParamPagina param = UtilesParamPagina.generar(
                        request, rowCount, "notificacion-listado");
                
                // BD > Lista de Notificacions
                List<Notificacion> notificaciones = dllNotificacion.paginar(
                        param.getRowIndex(), param.getRowsPage());

                // Inyecta Datos > JSP
                request.setAttribute("param-pagina", param);
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
