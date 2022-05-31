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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class UtilesEstatico {

    // Tamaño Máximo Fichero Recurso ( Defecto: Ilimitado )
    private static final long MAX_SIZE = -1;

    private UtilesEstatico() {
    }

    public static final void procesar(
            ServletConfig config,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        // Request > Recurso
        File fichero = obtenerRecurso(config, request);

        // Recurso > Salida
        servirRecurso(fichero, response);
    }

    private static File obtenerRecurso(ServletConfig config, HttpServletRequest request) {
        // Config > Views Path
        String base = config.getServletContext().getInitParameter("ruta-estatica");

        // Request > Ruta Absoluta ( Compilada )
        String ruta = request.getPathTranslated().replace("\\", "/");

        // Request > Ruta Peticion ( Public )
        String rutaPeticion = request.getPathInfo();

        // Ruta Peticion > Ruta Servicio
        String rutaServicio = base + rutaPeticion;

        // Ruta Absoluta ( Peticion ) > Ruta Absoluta ( Servicio ) 
        ruta = ruta.replace(rutaPeticion, rutaServicio);

        // Retorno: Ruta Absoluta Servicio > Fichero
        return new File(ruta);
    }

    private static File obtenerRecursoSeguro(ServletConfig config, HttpServletRequest request) {
        // Config > Views Path
        String base = config.getServletContext().getInitParameter("ruta-estatica");

        // Obtener Sesión
        HttpSession sesion = request.getSession();

        // ID de Sesion
        String id = sesion.getId();

        // Request > Ruta Local Equivalente)
        String ruta = request.getPathTranslated().replace("\\", "/");

        if (ruta.contains(id)) {
            // Request > Ruta Peticion ( Public )
            String rutaPeticion = request.getPathInfo();

            // Ruta Peticion > Ruta Servicio
            String rutaServicio = rutaPeticion.replace("/" + id, base);

            // Ruta Absoluta ( Peticion ) > Ruta Absoluta ( Servicio ) 
            ruta = ruta.replace(rutaPeticion, rutaServicio);
        } else {
            ruta = null;
        }

        // Retorno: Ruta Absoluta Servicio > Fichero
        return new File(ruta);
    }

    private static void servirRecurso(
            File fichero,
            HttpServletResponse response)
            throws IOException {
        // Analizar Recurso
        if (MAX_SIZE <= -1 || fichero.length() <= MAX_SIZE) {
            // Buffer Temporal
            byte[] buffer = new byte[(int) fichero.length()];

            // Origen > Destino
            try ( FileInputStream origen = new FileInputStream(fichero);  ServletOutputStream destino = response.getOutputStream()) {
                // Origen > Buffer
                origen.read(buffer);

                // Buffer > Destino
                destino.write(buffer);
            }
        }
    }
}
