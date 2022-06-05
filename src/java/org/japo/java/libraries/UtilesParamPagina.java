/*
 * Copyright 2022 Joanpaon.
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
import javax.servlet.http.HttpServletRequest;
import org.japo.java.entities.ParamPagina;

/**
 *
 * @author Joanpaon
 */
public class UtilesParamPagina {

    public static final int ROWS_PAGE01 = 10;
    public static final int ROWS_PAGE02 = 20;
    public static final int ROWS_PAGE03 = 40;
    public static final int ROWS_PAGE04 = 80;

    public static long DEF_ROW_COUNT = 0;
    public static long DEF_ROW_INDEX = 0;
    public static int DEF_ROWS_PAGE = ROWS_PAGE01;
    public static long DEF_ROW_INDEX_INI = 0;
    public static long DEF_ROW_INDEX_ANT = 0;
    public static long DEF_ROW_INDEX_SIG = 0;
    public static long DEF_ROW_INDEX_FIN = 0;
    public static String DEF_COMMAND = "unknown";

    public static String REG_COMMAND = "[a-z\\-]+";

    public static final boolean validarRowCount(long rowCount) {
        return rowCount >= DEF_ROW_COUNT;
    }

    public static final boolean validarRowIndex(long rowIndex) {
        return rowIndex >= DEF_ROW_INDEX;
    }

    public static final boolean validarRowsPage(long rowsPage) {
        return false
                || rowsPage == ROWS_PAGE01
                || rowsPage == ROWS_PAGE02
                || rowsPage == ROWS_PAGE03
                || rowsPage == ROWS_PAGE04;
    }

    public static final boolean validarRowIndexIni(long rowIndexIni) {
        return rowIndexIni >= DEF_ROW_INDEX_INI;
    }

    public static final boolean validarRowIndexAnt(long rowIndexAnt) {
        return rowIndexAnt >= DEF_ROW_INDEX_ANT;
    }

    public static final boolean validarRowIndexSig(long rowIndexSig) {
        return rowIndexSig >= DEF_ROW_INDEX_SIG;
    }

    public static final boolean validarRowIndexFin(long rowIndexFin) {
        return rowIndexFin >= DEF_ROW_INDEX_FIN;
    }

    public static final boolean validarCommand(String command) {
        return UtilesValidacion.validarDato(command, REG_COMMAND);
    }

    public static final ParamPagina generar(
            HttpServletRequest request, long rowCount, String command)
            throws IOException {

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

        // Retorno
        return new ParamPagina(
                rowCount, rowIndex, rowsPage,
                rowIndexIni, rowIndexAnt, rowIndexSig, rowIndexFin,
                command);
    }
}
