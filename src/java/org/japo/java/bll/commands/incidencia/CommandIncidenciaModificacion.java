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
import java.util.List;
import org.japo.java.dll.DLLDependencia;
import org.japo.java.dll.DLLEspecialidad;
import org.japo.java.dll.DLLIncidencia;
import org.japo.java.dll.DLLUsuario;
import org.japo.java.entities.Dependencia;
import org.japo.java.entities.Especialidad;
import org.japo.java.entities.Incidencia;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesIncidencia;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandIncidenciaModificacion extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "incidencia/incidencia-modificacion";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validar Acceso
            if (validarAccesoAdmin(request.getSession(false))) {
                // Capas de Datos
                DLLIncidencia dllIncidencia = new DLLIncidencia(config);
                DLLDependencia dllDependencia = new DLLDependencia(config);
                DLLEspecialidad dllEspecialidad = new DLLEspecialidad(config);
                DLLUsuario dllUsuario = new DLLUsuario(config);

                // request > Operación
                String op = request.getParameter("op");

                // Entidad > JSP
                if (op == null || op.equals("captura")) {
                    // BD > Lista de Datos
                    List<Dependencia> dependencias = dllDependencia.listar();
                    List<Especialidad> especialidades = dllEspecialidad.listar();
                    List<Usuario> usuarios = dllUsuario.listar();

                    // Request + ID Usuario + BD > Usuario
                    Incidencia incidencia = UtilesIncidencia.
                            consultarIncidenciaIdRequest(config, request);

                    // Inyección de Datos
                    request.setAttribute("incidencia", incidencia);
                    request.setAttribute("dependencias", dependencias);
                    request.setAttribute("especialidades", especialidades);
                    request.setAttribute("usuarios", usuarios);
                } else if (op.equals("proceso")) {
                    // Request > Parámetros
                    int id = UtilesIncidencia.obtenerIdRequest(request);
                    String titulo = UtilesIncidencia.obtenerTituloRequest(request);
                    String info = UtilesIncidencia.obtenerInfoRequest(request);
                    int estado = UtilesIncidencia.obtenerEstadoRequest(request);
                    Date fecha = UtilesIncidencia.obtenerFechaRequest(request);
                    int autor = UtilesIncidencia.obtenerAutorRequest(request);
                    int dependencia = UtilesIncidencia.obtenerDependenciaRequest(request);
                    int especialidad = UtilesIncidencia.obtenerEspecialidadRequest(request);

                    // Parámetros > Entidad
                    Incidencia incidencia = new Incidencia(
                            id, 
                            titulo, info, estado, fecha, 
                            autor, "", "", 
                            dependencia, "", 
                            especialidad, "");

                    // Ejecutar Operación
                    boolean checkOK = dllIncidencia.modificar(incidencia);

                    // Validar Operación
                    if (checkOK) {
                        out = "controller?cmd=incidencia-listado";
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
