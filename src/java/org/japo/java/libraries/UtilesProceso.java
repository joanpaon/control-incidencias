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
import org.japo.java.dll.DLLProceso;
import org.japo.java.entities.Proceso;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
public final class UtilesProceso {

    // Valores por Defecto
    public static final int DEF_ID = 0;
    public static final String DEF_NOMBRE = "CommandUnknown";
    public static final String DEF_INFO = "Proceso Desconocido";

    // Expresiones regulares
    public static final String REG_NOMBRE = "[\\wáéíóúüñÁÉÍÓÚÜÑ]{6,20}";
    public static final String REG_INFO = "[\\wáéíóúüñÁÉÍÓÚÜÑ\\- ]{6,50}";

    private UtilesProceso() {
    }

    public static Proceso consultarProcesoIdRequest(
            ServletConfig config,
            HttpServletRequest request)
            throws IOException {
        // Capas de Negocio
        DLLProceso dllProceso = new DLLProceso(config);

        // Request > Id de Proceso
        int id = obtenerIdRequest(request);

        // Retorno: Proceso
        return dllProceso.consultar(id);
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
                throw new IOException("ID de Proceso Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Proceso Incorrecta");
        }

        // Retorno: id
        return id;
    }

    public static final String obtenerNombreRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > Nombre
        String valor = request.getParameter("nombre");

        // Validar User
        if (!validarNombre(valor)) {
            throw new IOException("Nombre de Proceso Incorrecto");
        }

        // Retorno
        return valor;
    }

    public static final String obtenerInfoRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > Info
        String valor = request.getParameter("info");

        // Validar User
        if (!validarInfo(valor)) {
            throw new IOException("Info de Proceso Incorrecta");
        }

        // Retorno
        return valor;
    }

    public static final boolean validarId(int id) {
        return id >= DEF_ID;
    }

    public static final boolean validarNombre(String nombre) {
        return UtilesValidacion.validarDato(nombre, REG_NOMBRE);
    }

    public static boolean validarInfo(String info) {
        return UtilesValidacion.validarDato(info, REG_INFO);
    }
}
