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
package org.japo.java.bll.commands.permiso.perfil;

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import org.japo.java.bll.commands.usuario.CommandUsuarioValidation;
import org.japo.java.dll.DLLPerfil;
import org.japo.java.dll.DLLPermisoPerfil;
import org.japo.java.dll.DLLProceso;
import org.japo.java.entities.PermisoPerfil;
import org.japo.java.entities.Perfil;
import org.japo.java.entities.Proceso;
import org.japo.java.libraries.UtilesPermisoPerfil;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandPermisoPerfilInsercion extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "permiso/perfil/permiso-perfil-insercion";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validador de Acceso
            CommandUsuarioValidation validator = new CommandUsuarioValidation(
                    config, request.getSession(false));

            if (validator.validarAccesoDevel(request.getSession(false))) {
                // Capas de Datos
                DLLPerfil dalPerfil = new DLLPerfil(config);
                DLLPermisoPerfil dalPermiso = new DLLPermisoPerfil(config);
                DLLProceso dalProceso = new DLLProceso(config);

                // Obtener Operación
                String op = request.getParameter("op");

                // Formulario Captura Datos
                if (op == null || op.equals("captura")) {
                    // BD > Lista de Procesos
                    List<Proceso> procesos = dalProceso.listar();

                    // BD > Lista de Perfiles
                    List<Perfil> perfiles = dalPerfil.listar();

                    // Inyecta Datos > JSP
                    request.setAttribute("procesos", procesos);
                    request.setAttribute("perfiles", perfiles);
                } else if (op.equals("proceso")) {
                    // Request > Parámetros
                    int perfil = UtilesPermisoPerfil.obtenerPerfilRequest(request);
                    int proceso = UtilesPermisoPerfil.obtenerProcesoRequest(request);
                    String info = UtilesPermisoPerfil.obtenerInfoRequest(request);

                    // Parámetros > Entidad
                    PermisoPerfil permiso = new PermisoPerfil(
                            UtilesPermisoPerfil.DEF_ID, 
                            perfil, "", proceso, "", info);

                    // Entidad > Inserción BD - true | false
                    boolean checkOK = dalPermiso.insertar(permiso);

                    // Validar Operación
                    if (checkOK) {
                        out = "controller?cmd=permiso-perfil-listado";
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
