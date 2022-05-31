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
package org.japo.java.bll.commands.proceso;

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import org.japo.java.bll.commands.usuario.CommandUsuarioValidation;
import org.japo.java.dll.DLLProceso;
import org.japo.java.entities.Proceso;
import org.japo.java.libraries.UtilesProceso;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandProcesoInsercion extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "proceso/proceso-insercion";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validador de Acceso
            CommandUsuarioValidation validator = new CommandUsuarioValidation(
                    config, request.getSession(false));

            if (validator.validarAccesoDevel(request.getSession(false))) {
                // Capas de Datos
                DLLProceso dllProceso = new DLLProceso(config);

                // Obtener Operación
                String op = request.getParameter("op");

                // Invoca Formulario de Captura de Datos
                if (op == null || op.equals("captura")) {
                    // ---
                } else if (op.equals("proceso")) {
                    // Request > Parámetros
                    String nombre = request.getParameter("nombre").trim();
                    String info = request.getParameter("info").trim();

                    // Parámetros > Entidad
                    Proceso proceso = new Proceso(UtilesProceso.DEF_ID, nombre, info);

                    // Entidad > Inserción BD - true | false
                    boolean checkOK = dllProceso.insertar(proceso);

                    // Validar Operación
                    if (checkOK) {
                        out = "controller?cmd=proceso-listado";
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
