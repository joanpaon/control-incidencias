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
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
public final class UtilesListado {

    private UtilesListado() {
    }

    public static final long obtenerRowIndex(HttpServletRequest request) throws IOException {
        // Referencia
        long rowIndex;

        // URL > ID Objeto
        try {
            rowIndex = Long.parseLong(request.getParameter("row-index"));

            if (!validarRowIndex(rowIndex)) {
                throw new NumberFormatException("Posición de fila incorrecta");
            }
        } catch (NumberFormatException | NullPointerException e) {
            rowIndex = 0;
        }

        // Retorno
        return rowIndex;
    }

    public static final int obtenerRowsPage(HttpServletRequest request) throws IOException {
        // Referencia
        int rowsPage;

        // URL > ID Objeto
        try {
            rowsPage = Integer.parseInt(request.getParameter("rows-page"));

            if (!validarRowsPage(rowsPage)) {
                throw new NumberFormatException("Filas por página incorrecto");
            }
        } catch (NumberFormatException | NullPointerException e) {
            rowsPage = 10;
        }

        // Retorno
        return rowsPage;
    }

    private static boolean validarRowIndex(long rowIndex) {
        return rowIndex >= 0;
    }

    private static boolean validarRowsPage(long rowsPage) {
        // Validar Escalones
        return false
                || rowsPage == 80
                || rowsPage == 40
                || rowsPage == 20
                || rowsPage == 10;
    }

    public static final long obtenerRowIndexIni() {
        return 0;
    }

    public static final long obtenerRowIndexAnt(long rowIndex, int rowsPage) {
        return rowIndex - rowsPage < 0 ? 0 : rowIndex - rowsPage;
    }

    public static long obtenerRowIndexSig(long rowIndex, int rowsPage, long rowCount) {
        return rowIndex + rowsPage > rowCount - 1 ? rowIndex : rowIndex + rowsPage;
    }

    public static long obtenerRowIndexFin(long rowIndex, int rowsPage, long rowCount) {
        long rowIndexFin;
        
        if (rowCount == 0) {
            rowIndexFin = 0;
        } else if (rowCount / rowsPage == 0) {
            rowIndexFin = 0;
        } else if (rowCount % rowsPage == 0) {
            rowIndexFin = (rowCount / rowsPage - 1) * rowsPage;
        } else {
            rowIndexFin = rowCount / rowsPage * rowsPage;
        }
        
        return rowIndexFin;
    }
}
