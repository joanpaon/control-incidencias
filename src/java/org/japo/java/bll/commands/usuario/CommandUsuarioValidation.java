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
package org.japo.java.bll.commands.usuario;

import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;
import org.japo.java.dll.DLLPermisoPerfil;
import org.japo.java.dll.DLLPermisoUsuario;
import org.japo.java.dll.DLLProceso;
import org.japo.java.entities.PermisoPerfil;
import org.japo.java.entities.PermisoUsuario;
import org.japo.java.entities.Proceso;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesPerfil;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandUsuarioValidation {

    // Capas de Datos
    private final DLLPermisoPerfil dalPermisoPerfil;
    private final DLLPermisoUsuario dalPermisoUsuario;
    private final DLLProceso dalProceso;

    public CommandUsuarioValidation(ServletConfig config, HttpSession sesion) {
        dalPermisoPerfil = new DLLPermisoPerfil(config);
        dalPermisoUsuario = new DLLPermisoUsuario(config);
        dalProceso = new DLLProceso(config);
    }

    public final boolean validarAccesoComando(HttpSession sesion, String comando) {
        // Semáforo
        boolean checkOK;

        try {
            // Sesión > Usuario
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");

            // Usuario > Perfil
            int perfil = usuario.getPerfil();

            // Validar Perfil
            if (perfil >= UtilesPerfil.ADMIN_CODE) {
                checkOK = true;
            } else {
                // Perfil + BD > Lista de Permisos del Perfil
                List<PermisoPerfil> permPerfil = dalPermisoPerfil.listar(perfil);

                // Nombre Comando > Entidad Proceso
                Proceso proceso = dalProceso.consultar(comando);

                // Semaforo: true | false
                checkOK = validarProcesoPermisoPerfil(permPerfil, proceso);

                if (!checkOK) {
                    // Usuario + BD > Lista de Permisos del Usuario
                    List<PermisoUsuario> permUsuario = dalPermisoUsuario.listar(usuario.getId());

                    // Semaforo: true | false
                    checkOK = validarProcesoPermisoUsuario(permUsuario, proceso);
                }
            }
        } catch (Exception e) {
            checkOK = false;
        }

        // Retorno: true | false
        return checkOK;
    }

    public final boolean validarAccesoServicio(String servicio) {
        return true;
    }

    private boolean validarProcesoPermisoPerfil(List<PermisoPerfil> permisos, Proceso proceso) {
        return buscarProcesoPermisosPerfil(permisos, proceso) > -1;
    }

    private int buscarProcesoPermisosPerfil(List<PermisoPerfil> permisos, Proceso proceso) {
        // Posicion del proceso en la lista de permisos
        int posicion = -1;

        // Bucle de Búsqueda
        for (int i = 0; i < permisos.size(); i++) {
            if (permisos.get(i).getProceso() == proceso.getId()) {
                posicion = i;
                i = permisos.size();
            }
        }

        // Retorno: Posición
        return posicion;
    }

    private boolean validarProcesoPermisoUsuario(List<PermisoUsuario> permisos, Proceso proceso) {
        return buscarProcesoPermisosUsuario(permisos, proceso) > -1;
    }

    private int buscarProcesoPermisosUsuario(List<PermisoUsuario> permisos, Proceso proceso) {
        // Posicion del proceso en la lista de permisos
        int posicion = -1;

        // Bucle de Búsqueda
        for (int i = 0; i < permisos.size(); i++) {
            if (permisos.get(i).getProceso() == proceso.getId()) {
                posicion = i;
                i = permisos.size();
            }
        }

        // Retorno: Posición
        return posicion;
    }
}
