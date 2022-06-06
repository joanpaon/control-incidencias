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
package org.japo.java.bll.commands.permiso.usuario;

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import org.japo.java.dll.DLLPermisoUsuario;
import org.japo.java.dll.DLLProceso;
import org.japo.java.dll.DLLUsuario;
import org.japo.java.entities.PermisoUsuario;
import org.japo.java.entities.Usuario;
import org.japo.java.entities.Proceso;
import org.japo.java.libraries.UtilesPermisoUsuario;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandPermisoUsuarioInsercion extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "permiso/usuario/permiso-usuario-insercion";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validar Acceso
            if (validarAccesoAdmin(request.getSession(false))) {
                // Capas de Datos
                DLLPermisoUsuario dalPermiso = new DLLPermisoUsuario(config);
                DLLProceso dalProceso = new DLLProceso(config);
                DLLUsuario dalUsuario = new DLLUsuario(config);

                // Obtener Operación
                String op = request.getParameter("op");

                // Formulario Captura Datos
                if (op == null || op.equals("captura")) {
                    // BD > Lista de Procesos
                    List<Proceso> procesos = dalProceso.listar();

                    // BD > Lista de Usuarios
                    List<Usuario> usuarios = dalUsuario.listar();

                    // Inyecta Datos > JSP
                    request.setAttribute("procesos", procesos);
                    request.setAttribute("usuarios", usuarios);
                } else if (op.equals("proceso")) {
                    // Request > Parámetros
                    int usuario = UtilesPermisoUsuario.obtenerUsuarioRequest(request);
                    int proceso = UtilesPermisoUsuario.obtenerProcesoRequest(request);
                    String info = UtilesPermisoUsuario.obtenerInfoRequest(request);

                    // Parámetros > Entidad
                    PermisoUsuario permiso = new PermisoUsuario(0, usuario, "", proceso, "", info);

                    // Entidad > Inserción BD - true | false
                    boolean checkOK = dalPermiso.insertar(permiso);

                    // Validar Operación
                    if (checkOK) {
                        out = "controller?cmd=permiso-usuario-listado";
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
