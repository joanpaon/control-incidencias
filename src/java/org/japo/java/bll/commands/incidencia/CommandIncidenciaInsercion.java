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
import org.japo.java.entities.Dependencia;
import org.japo.java.entities.Especialidad;
import org.japo.java.entities.Incidencia;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesIncidencia;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandIncidenciaInsercion extends Command {

    @Override
    public void process() throws ServletException, IOException {
        // Salida
        String out = "incidencia/incidencia-insercion";

        // Validar Sesión
        if (validarSesion(request)) {

            // Obtener Operación
            String op = request.getParameter("op");

            if (op == null || op.equals("captura")) {
                // Capas de Datos
                DLLDependencia dllDependencia = new DLLDependencia(config);
                DLLEspecialidad dllEspecialidad = new DLLEspecialidad(config);

                // Datos a Inyectar
                List<Dependencia> dependencias = dllDependencia.listar();
                List<Especialidad> especialidades = dllEspecialidad.listar();

                // Enlaza Datos > JSP
                request.setAttribute("dependencias", dependencias);
                request.setAttribute("especialidades", especialidades);
            } else if (op.equals("proceso")) {
                // Sesion > Usuario
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

                // Capas de Datos
                DLLIncidencia dllincidencia = new DLLIncidencia(config);

                // Request > Parámetros
                String nombre = UtilesIncidencia.obtenerNombreRequest(request);
                String info = UtilesIncidencia.obtenerInfoRequest(request);
                int estado = UtilesIncidencia.INCIDENCIA_ABIERTA;
                Date creacion = new Date();
                int autor = usuario.getId();
                int dependencia = UtilesIncidencia.obtenerDependenciaRequest(request);
                int especialidad = UtilesIncidencia.obtenerEspecialidadRequest(request);

                // Parámetros > Incidencia
                Incidencia incidencia = new Incidencia(UtilesIncidencia.DEF_ID,
                        nombre, info, estado, creacion,
                        autor, "", "",
                        dependencia, "", especialidad, "");

                // Entidad > Inserción BD - true | false
                boolean checkOK = dllincidencia.insertar(incidencia);

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
