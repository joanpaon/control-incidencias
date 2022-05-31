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
import java.util.Date;
import org.japo.java.dll.DLLIncidencia;
import org.japo.java.dll.DLLNotificacion;
import org.japo.java.entities.Incidencia;
import org.japo.java.entities.Notificacion;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesIncidencia;
import org.japo.java.libraries.UtilesNotificacion;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandIncidenciaCierre extends Command {

    @Override
    public void process() throws ServletException, IOException {
        // Salida
        String out;

        // Validar Sesión
        if (validarSesion(request)) {
            // Sesión > Usuario
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            
            // Capas de Datos
            DLLIncidencia dllIncidencia = new DLLIncidencia(config);
            DLLNotificacion dllNotificacion = new DLLNotificacion(config);

            // Request > ID Incidencia
            int id = UtilesIncidencia.obtenerIdRequest(request);

            // Incidencia a Cerrar
            Incidencia incidencia = dllIncidencia.consultar(id);
            
            // Marcar Incidencia como cerrada
            incidencia.setCodigo(UtilesIncidencia.INCIDENCIA_CERRADA);
            
            // Notificación de Cierre
            Date fecha = new Date();
            String info = "Incidencia Cerrada por " + usuario.getUser();
            Notificacion notificacion = new Notificacion(
                    UtilesNotificacion.DEF_ID, fecha, 
                    usuario.getId(), usuario.getUser(), usuario.getPerfilInfo(),
                    id, info);
            
            // Actualizar BD
            dllIncidencia.modificar(incidencia);
            dllNotificacion.insertar(notificacion);
            
            // Redireccion
            out = "controller?cmd=incidencia-consulta&id=" + id;
        } else {
            out = "message/sesion-invalida";
        }

        // Redirección
        forward(out);
    }
}
