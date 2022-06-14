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
package org.japo.java.entities;

import java.io.Serializable;
import org.japo.java.libraries.UtilesParamPagina;

/**
 *
 * @author Joanpaon
 */
public final class ParamPagina implements Serializable {

    // Campos
    private long rowCount;
    private long rowIndex;
    private int rowsPage;
    private long rowIndexIni;
    private long rowIndexAnt;
    private long rowIndexSig;
    private long rowIndexFin;
    private String command;

    public ParamPagina() {
        rowCount = UtilesParamPagina.DEF_ROW_COUNT;
        rowIndex = UtilesParamPagina.DEF_ROW_INDEX;
        rowsPage = UtilesParamPagina.DEF_ROWS_PAGE;
        rowIndexIni = UtilesParamPagina.DEF_ROW_INDEX_INI;
        rowIndexAnt = UtilesParamPagina.DEF_ROW_INDEX_ANT;
        rowIndexSig = UtilesParamPagina.DEF_ROW_INDEX_SIG;
        rowIndexFin = UtilesParamPagina.DEF_ROW_INDEX_FIN;
        command = UtilesParamPagina.DEF_COMMAND;
    }

    public ParamPagina(long rowCount, long rowIndex, int rowsPage,
            long rowIndexIni, long rowIndexAnt,
            long rowIndexSig, long rowIndexFin, String command) {
        if (UtilesParamPagina.validarRowCount(rowCount)) {
            this.rowCount = rowCount;
        } else {
            this.rowCount = UtilesParamPagina.DEF_ROW_COUNT;
        }

        if (UtilesParamPagina.validarRowIndex(rowIndex)) {
            this.rowIndex = rowIndex;
        } else {
            this.rowIndex = UtilesParamPagina.DEF_ROW_INDEX;
        }

        if (UtilesParamPagina.validarRowsPage(rowsPage)) {
            this.rowsPage = rowsPage;
        } else {
            this.rowsPage = UtilesParamPagina.DEF_ROWS_PAGE;
        }

        if (UtilesParamPagina.validarRowIndexIni(rowIndexIni)) {
            this.rowIndexIni = rowIndexIni;
        } else {
            this.rowIndexIni = UtilesParamPagina.DEF_ROW_INDEX_INI;
        }

        if (UtilesParamPagina.validarRowIndexIni(rowIndexAnt)) {
            this.rowIndexAnt = rowIndexAnt;
        } else {
            this.rowIndexAnt = UtilesParamPagina.DEF_ROW_INDEX_ANT;
        }

        if (UtilesParamPagina.validarRowIndexIni(rowIndexSig)) {
            this.rowIndexSig = rowIndexSig;
        } else {
            this.rowIndexSig = UtilesParamPagina.DEF_ROW_INDEX_SIG;
        }

        if (UtilesParamPagina.validarRowIndexIni(rowIndexFin)) {
            this.rowIndexFin = rowIndexFin;
        } else {
            this.rowIndexFin = UtilesParamPagina.DEF_ROW_INDEX_FIN;
        }

        if (UtilesParamPagina.validarCommand(command)) {
            this.command = command;
        } else {
            this.command = UtilesParamPagina.DEF_COMMAND;
        }
    }

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }

    public long getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(long rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getRowsPage() {
        return rowsPage;
    }

    public void setRowsPage(int rowsPage) {
        this.rowsPage = rowsPage;
    }

    public long getRowIndexIni() {
        return rowIndexIni;
    }

    public void setRowIndexIni(long rowIndexIni) {
        this.rowIndexIni = rowIndexIni;
    }

    public long getRowIndexAnt() {
        return rowIndexAnt;
    }

    public void setRowIndexAnt(long rowIndexAnt) {
        this.rowIndexAnt = rowIndexAnt;
    }

    public long getRowIndexSig() {
        return rowIndexSig;
    }

    public void setRowIndexSig(long rowIndexSig) {
        this.rowIndexSig = rowIndexSig;
    }

    public long getRowIndexFin() {
        return rowIndexFin;
    }

    public void setRowIndexFin(long rowIndexFin) {
        this.rowIndexFin = rowIndexFin;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
