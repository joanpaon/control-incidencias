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
package org.japo.java.bll.commands.incidencia;

import org.japo.java.bll.commands.Command;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import org.japo.java.dll.DLLIncidencia;
import org.japo.java.entities.Incidencia;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesListado;
import org.japo.java.libraries.UtilesPerfil;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class CommandIncidenciaListado extends Command {

    @Override
    public void process() throws ServletException, IOException {
        // Salida
        String out = "incidencia/incidencia-listado";

        // Validar Sesión
        if (validarSesion(request)) {
            // Sesión > Usuario
            Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");

            // Capas de Datos
            DLLIncidencia dllIncidencia = new DLLIncidencia(config);

            // BD > Número de Registros
            long rowCount;
            if (usuario.getPerfil() >= UtilesPerfil.ADMIN_CODE) {
                // BD > Cuenta de TODAS las Incidencias
                rowCount = dllIncidencia.contar();
            } else {
                // BD > Lista de las Incidencias del usuario actual
                rowCount = dllIncidencia.contar(usuario.getId());
            }

            // Request > Índice de pagina            
            long rowIndex = UtilesListado.obtenerRowIndex(request);

            // Request > Líneas por Pagina            
            int rowsPage = UtilesListado.obtenerRowsPage(request);

            // Indice Navegación - Inicio
            long rowIndexIni = UtilesListado.obtenerRowIndexIni();

            // Indice Navegación - Anterior
            long rowIndexAnt = UtilesListado.obtenerRowIndexAnt(rowIndex, rowsPage);

            // Indice Navegación - Siguiente
            long rowIndexSig = UtilesListado.obtenerRowIndexSig(rowIndex, rowsPage, rowCount);

            // Indice Navegación - Final
            long rowIndexFin = UtilesListado.obtenerRowIndexFin(rowIndex, rowsPage, rowCount);

            // BD > Lista de Registros
            List<Incidencia> incidencias;
            if (usuario.getPerfil() >= UtilesPerfil.ADMIN_CODE) {
                // BD > Lista Paginada de TODAS las Incidencias
                incidencias = dllIncidencia.paginar(rowIndex, rowCount);
            } else {
                // BD > Lista Paginada de las Incidencias del usuario actual
                incidencias = dllIncidencia.paginar(rowIndex, rowCount, usuario.getId());
            }

            // Inyecta Datos > JSP
            request.setAttribute("incidencias", incidencias);

            // Inyecta Parámetros Listado > JSP
            request.setAttribute("row-index", rowIndex);
            request.setAttribute("row-index-ini", rowIndexIni);
            request.setAttribute("row-index-ant", rowIndexAnt);
            request.setAttribute("row-index-sig", rowIndexSig);
            request.setAttribute("row-index-fin", rowIndexFin);
            request.setAttribute("rows-page", rowsPage);
            request.setAttribute("command", "incidencia-listado");
        } else {
            out = "message/sesion-invalida";
        }

        // Redirección
        forward(out);
    }
}
