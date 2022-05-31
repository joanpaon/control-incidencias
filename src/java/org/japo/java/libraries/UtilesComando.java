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
import org.japo.java.bll.commands.ICommand;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class UtilesComando {

    // Nombres de Paquetes
    private static final String COMMAND_PKG = "org.japo.java.bll.commands";

    // Prefijos
    private static final String COMMAND_PRE = "Command";

    private UtilesComando() {
    }

    public static final void procesar(
            ServletConfig config,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // Petición > Nombre de Comando (Kebab Case)
        String cmdName = request.getParameter("cmd");

        // Nombre de Comando > Comando ( Referenciado por Interfaz )
        ICommand cmd = obtenerComando(cmdName);

        // ServletContext + Peticion + Resuesta > Inicializar Comando
        cmd.init(config, request, response);

        // Procesa Comando
        cmd.process();
    }

    private static ICommand obtenerComando(String cmdName) throws ServletException {
        // Referencia Comando
        ICommand cmd;

        try {
            // Parámetro cmd > Nombre Cualificado de Clase Comando
            String cmdClassName = obtenerNombreComando(cmdName);

            // Nombre Clase > Objeto Class
            Class<?> cmdClass = Class.forName(cmdClassName);

            // Objeto Class > Constructor Clase
            Constructor<?> constructor = cmdClass.getConstructor();

            // Constructor Clase > Instancia Clase
            cmd = (ICommand) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException
                | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            // Clase Indefinida | Desconocida
            throw new ServletException(e.getMessage());
        }

        // Retorno Comando
        return cmd;
    }

    private static String obtenerNombreComando(String cmd) throws ServletException {
        // Subpaquete
        String sub;

        // Parámetro > Subpaquete
        if (cmd == null) {
            throw new ServletException("Comando no especificado");
        } else if (cmd.equals("validation")) {
            // Comandos con UN nombre
            sub = "admin";
        } else if (cmd.contains("-")) {
            // Varios Nombres - Eliminar Item Final
            sub = cmd.substring(0, cmd.lastIndexOf("-"));

            // Notación Kebab-Case > Notación Package: - > . 
            sub = sub.replace("-", ".");
        } else {
            sub = cmd;
        }

        // Kebab Case > Camel Case
        cmd = UtilesFormato.cambiarKebab2Camel(cmd);

        // Retorno: Nombre Cualificado Clase Comando
        return String.format("%s.%s.%s%s", COMMAND_PKG, sub, COMMAND_PRE, cmd);
    }
}
