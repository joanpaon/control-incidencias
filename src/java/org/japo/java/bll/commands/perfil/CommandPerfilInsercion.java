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
package org.japo.java.bll.commands.perfil;

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import org.japo.java.dll.DLLPerfil;
import org.japo.java.entities.Perfil;
import org.japo.java.libraries.UtilesPerfil;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandPerfilInsercion extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "perfil/perfil-insercion";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validar Acceso
            if (validarAccesoDevel(request.getSession(false))) {
                // Capas de Datos
                DLLPerfil dalPerfil = new DLLPerfil(config);

                // Obtener Operación
                String op = request.getParameter("op");

                // Formulario Captura Datos
                if (op == null || op.equals("captura")) {
                    // ---
                } else if (op.equals("proceso")) {
                    // Request > Parámetros
                    // El ID es importante porque establece realmente el perfil
                    // Hay que ir con cuidado para evitar colisiones
                    // Nivel desarrollador
                    int id = UtilesPerfil.obtenerIdRequest(request);
                    String nombre = UtilesPerfil.obtenerNombreRequest(request);
                    String info = UtilesPerfil.obtenerInfoRequest(request);

                    // Parámetros > Entidad
                    Perfil perfil = new Perfil(id, nombre, info);

                    // Entidad > Inserción BD - true | false
                    boolean checkOK = dalPerfil.insertar(perfil);

                    // Validar Operación
                    if (checkOK) {
                        out = "controller?cmd=perfil-listado";
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
