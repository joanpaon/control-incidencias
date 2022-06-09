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
import java.util.List;
import org.japo.java.dll.DLLIncidencia;
import org.japo.java.dll.DLLNotificacion;
import org.japo.java.dll.DLLUsuario;
import org.japo.java.entities.Incidencia;
import org.japo.java.entities.Notificacion;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesNotificacion;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandNotificacionModificacion extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "notificacion/notificacion-modificacion";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validar Acceso
            if (validarAccesoAdmin(request.getSession(false))) {
                // Capas de Datos
                DLLIncidencia dllIncidencia = new DLLIncidencia(config);
                DLLNotificacion dllNotificacion = new DLLNotificacion(config);
                DLLUsuario dllUsuario = new DLLUsuario(config);

                // request > Operación
                String op = request.getParameter("op");

                // Entidad > JSP
                if (op == null || op.equals("captura")) {
                    // Listas de Datos
                    List<Incidencia> incidencias = dllIncidencia.listar();
                    List<Usuario> usuarios = dllUsuario.listar();
                    
                    // Request + ID Usuario + BD > Usuario
                    Notificacion notificacion = UtilesNotificacion.
                            consultarNotificacionIdRequest(config, request);

                    // Inyección de Datos
                    request.setAttribute("notificacion", notificacion);
                    request.setAttribute("incidencias", incidencias);
                    request.setAttribute("usuarios", usuarios);
                } else if (op.equals("proceso")) {
                    // Request > Parámetros
                    int id = UtilesNotificacion.obtenerIdRequest(request);
                    Date fecha = UtilesNotificacion.obtenerFechaRequest(request);
                    int autor = UtilesNotificacion.obtenerAutorRequest(request);
                    int incidencia = UtilesNotificacion.obtenerIncidenciaRequest(request);
                    String info = UtilesNotificacion.obtenerInfoRequest(request);

                    // Parámetros > Entidad
                    Notificacion notificacion = new Notificacion(
                            id,
                            fecha,
                            autor, "", "",
                            incidencia, "",
                            info);

                    // Ejecutar Operación
                    boolean checkOK = dllNotificacion.modificar(notificacion);

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
