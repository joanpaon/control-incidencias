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
public final class CommandPermisoPerfilModificacion extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "permiso/perfil/permiso-perfil-modificacion";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validador de Acceso
            CommandUsuarioValidation validator = new CommandUsuarioValidation(
                    config, request.getSession(false));

            if (validator.validarAccesoAdmin(request.getSession(false))) {
                // Capas de Datos
                DLLPermisoPerfil dalPermiso = new DLLPermisoPerfil(config);
                DLLProceso dalProceso = new DLLProceso(config);
                DLLPerfil dalPerfil = new DLLPerfil(config);

                // request > ID Operación
                String op = request.getParameter("op");

                // Captura de Datos
                if (op == null || op.equals("captura")) {
                    // Request + ID PermisoPerfil + BD > PermisoPerfil
                    PermisoPerfil permiso = UtilesPermisoPerfil.consultarPermisoPerfilIdRequest(config, request);

                    // BD > Lista de Procesos
                    List<Proceso> procesos = dalProceso.listar();

                    // BD > Lista de Perfiles            
                    List<Perfil> perfiles = dalPerfil.listar();

                    // Inyectar Datos > JSP
                    request.setAttribute("permiso", permiso);
                    request.setAttribute("procesos", procesos);
                    request.setAttribute("perfiles", perfiles);
                } else if (op.equals("proceso")) {
                    // Request > Parámetros
                    int id = UtilesPermisoPerfil.obtenerIdRequest(request);
                    int perfil = UtilesPermisoPerfil.obtenerPerfilRequest(request);
                    int proceso = UtilesPermisoPerfil.obtenerProcesoRequest(request);
                    String info = UtilesPermisoPerfil.obtenerInfoRequest(request);

                    // Entidad Final
                    PermisoPerfil permiso = new PermisoPerfil(id, perfil, "", proceso, "", info);

                    // Entidad > Modificación Registro BD
                    boolean checkOK = dalPermiso.modificar(permiso);

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
