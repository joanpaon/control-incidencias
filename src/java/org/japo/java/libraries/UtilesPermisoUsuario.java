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
package org.japo.java.libraries;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import org.japo.java.dll.DLLPermisoUsuario;
import org.japo.java.entities.PermisoUsuario;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
public final class UtilesPermisoUsuario {

    // Valores por Defecto
    public static final int DEF_ID = 0;
    public static final int DEF_USUARIO = UtilesUsuario.DEF_ID;
    public static final String DEF_USUARIO_NAME = UtilesUsuario.DEF_USER;
    public static final int DEF_PROCESO = UtilesProceso.DEF_ID;
    public static final String DEF_PROCESO_INFO = UtilesProceso.DEF_INFO;
    public static final String DEF_INFO = "Permiso de Usuario NO Definido";

    // Expresiones regulares
    public static final String REG_INFO = "[\\wáéíóúüñÁÉÍÓÚÜÑ\\- ]{6,50}";

    private UtilesPermisoUsuario() {
    }

    public static final PermisoUsuario consultarPermisoUsuarioIdRequest(
            ServletConfig config,
            HttpServletRequest request)
            throws IOException {
        // Capas de Negocio
        DLLPermisoUsuario dllPermiso = new DLLPermisoUsuario(config);

        // Request > Id Permiso Usuario
        int id = obtenerIdRequest(request);

        // Retorno: Permiso Usuario
        return dllPermiso.consultar(id);
    }

    public static final int obtenerIdRequest(
            HttpServletRequest request)
            throws IOException {
        // Referencia
        int id;

        // URL > ID Objeto
        try {
            id = Integer.parseInt(request.getParameter("id"));

            if (!validarId(id)) {
                throw new IOException("ID de Permiso de Usuario Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Permiso de Usuario Incorrecta");
        }

        // Retorno
        return id;
    }

    public static final int obtenerProcesoRequest(
            HttpServletRequest request)
            throws IOException {
        // Referencia
        int proceso;

        // URL > ID Objeto
        try {
            proceso = Integer.parseInt(request.getParameter("proceso"));

            if (!UtilesProceso.validarId(proceso)) {
                throw new IOException("ID de Proceso Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Proceso Incorrecta");
        }

        // Retorno
        return proceso;
    }

    public static final int obtenerUsuarioRequest(
            HttpServletRequest request)
            throws IOException {
        // Referencia
        int usuario;

        // URL > ID Objeto
        try {
            usuario = Integer.parseInt(request.getParameter("usuario"));

            if (!UtilesUsuario.validarId(usuario)) {
                throw new IOException("ID de Usuario Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Usuario Incorrecta");
        }

        // Retorno
        return usuario;
    }

    public static final String obtenerInfoRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > Info
        String info = request.getParameter("info");

        // Validar User
        if (!validarInfo(info)) {
            throw new IOException("Info de Permiso de Usuario Incorrecta");
        }

        // Retorno
        return info;
    }

    public static final boolean validarId(int id) {
        return id >= DEF_ID;
    }

    public static boolean validarInfo(String info) {
        return UtilesValidacion.validarDato(info, REG_INFO);
    }
}
