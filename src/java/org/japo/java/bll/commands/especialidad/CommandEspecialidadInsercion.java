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
package org.japo.java.bll.commands.especialidad;

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import org.japo.java.dll.DLLEspecialidad;
import org.japo.java.entities.Especialidad;
import org.japo.java.libraries.UtilesEspecialidad;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandEspecialidadInsercion extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "especialidad/especialidad-insercion";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validar Acceso
            if (validarAccesoAdmin(request.getSession(false))) {
                // Capas de Datos
                DLLEspecialidad dllEspecialidad = new DLLEspecialidad(config);

                // Obtener Operación
                String op = request.getParameter("op");

                // Invoca Formulario de Captura de Datos
                if (op == null || op.equals("captura")) {
                    // ---
                } else if (op.equals("especialidad")) {
                    // Request > Parámetros
                    String nombre = UtilesEspecialidad.obtenerNombreRequest(request);
                    String info = UtilesEspecialidad.obtenerInfoRequest(request);

                    // Parámetros > Entidad
                    Especialidad especialidad = new Especialidad(0, nombre, info);

                    // Entidad > Inserción BD - true | false
                    boolean checkOK = dllEspecialidad.insertar(especialidad);

                    // Validar Operación
                    if (checkOK) {
                        out = "controller?cmd=especialidad-listado";
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
