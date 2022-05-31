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
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.japo.java.bll.services.IService;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class UtilesServicio {

    // Nombres de Paquetes
    private static final String SERVICE_PKG = "org.japo.java.bll.services";

    // Prefijos
    private static final String SERVICE_PRE = "Service";

    private UtilesServicio() {
    }

    public static final void procesar(
            ServletConfig config,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // Petición > Nombre de Servicio (Kebab Case)
        String svcName = request.getParameter("svc");

        // Nombre de Servicio > Servicio ( Referenciado por Interfaz )
        IService svc = obtenerServicio(svcName);

        // ServletContext + Petición + Resuesta > Inicializar Servicio
        svc.init(config, request, response);

        // Procesa Servicio
        svc.process();
    }

    private static IService obtenerServicio(String svcName) throws ServletException {
        // Referencia Comando
        IService svc;

        try {
            // Parámetro cmd > Nombre Cualificado de Clase Comando
            String svcClassName = obtenerNombreServicio(svcName);

            // Nombre Clase > Objeto Class
            Class<?> svcClass = Class.forName(svcClassName);

            // Objeto Class > Constructor Clase
            Constructor<?> constructor = svcClass.getConstructor();

            // Constructor Clase > Instancia Clase
            svc = (IService) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException
                | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            // Clase Indefinida | Desconocida
            throw new ServletException(e.getMessage());
        }

        // Retorno Comando
        return svc;
    }

    private static String obtenerNombreServicio(String svcName) throws ServletException {
        // Subpaquete
        String sub;

        // Parámetro > Subpaquete
        if (svcName == null) {
            throw new ServletException("Servicio no especificado");
        } else if (false
                || svcName.equals("sample")
                || svcName.equals("token")
                || svcName.equals("check")
                || svcName.equals("clue")) {
            sub = "helper";
        } else if (svcName.contains("-")) {
            // Eliminar Operacion Final
            sub = svcName.substring(0, svcName.lastIndexOf("-"));

            // Notación Kebab-Case > Notación Package: - > . 
            sub = sub.replace("-", ".");
        } else {
            sub = svcName;
        }

        // Kebab Case > Camel Case
        svcName = UtilesFormato.cambiarKebab2Camel(svcName);

        // Retorno: Nombre Cualificado Clase Servicio
        return String.format("%s.%s.%s%s", SERVICE_PKG, sub, SERVICE_PRE, svcName);
    }
}
