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
import org.japo.java.libraries.UtilesPerfil;
import org.japo.java.libraries.UtilesPermisoPerfil;
import org.japo.java.libraries.UtilesProceso;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
public final class PermisoPerfil implements Serializable {

    // Campos
    private int id;
    private int perfil;
    private String perfilInfo;
    private int proceso;
    private String procesoInfo;
    private String info;

    public PermisoPerfil() {
        id = UtilesPermisoPerfil.DEF_ID;
        perfil = UtilesPerfil.DEF_ID;
        perfilInfo = UtilesPerfil.DEF_INFO;
        proceso = UtilesProceso.DEF_ID;
        procesoInfo = UtilesProceso.DEF_INFO;
        info = UtilesPermisoPerfil.DEF_INFO;
    }

    public PermisoPerfil(int id,
            int perfil, String perfilInfo,
            int proceso, String procesoInfo,
            String info) {
        if (UtilesPermisoPerfil.validarId(id)) {
            this.id = id;
        } else {
            this.id = UtilesPermisoPerfil.DEF_ID;
        }

        if (UtilesPerfil.validarId(perfil)) {
            this.perfil = perfil;
        } else {
            this.perfil = UtilesPerfil.DEF_ID;
        }

        if (UtilesPerfil.validarInfo(perfilInfo)) {
            this.perfilInfo = perfilInfo;
        } else {
            this.perfilInfo = UtilesPerfil.DEF_INFO;
        }

        if (UtilesProceso.validarId(proceso)) {
            this.proceso = proceso;
        } else {
            this.proceso = UtilesProceso.DEF_ID;
        }

        if (UtilesProceso.validarInfo(procesoInfo)) {
            this.procesoInfo = procesoInfo;
        } else {
            this.procesoInfo = UtilesProceso.DEF_INFO;
        }

        if (UtilesPermisoPerfil.validarInfo(info)) {
            this.info = info;
        } else {
            this.info = UtilesPermisoPerfil.DEF_INFO;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProceso() {
        return proceso;
    }

    public void setProceso(int proceso) {
        this.proceso = proceso;
    }

    public String getProcesoInfo() {
        return procesoInfo;
    }

    public void setProcesoInfo(String procesoInfo) {
        this.procesoInfo = procesoInfo;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public String getPerfilInfo() {
        return perfilInfo;
    }

    public void setPerfilInfo(String perfilInfo) {
        this.perfilInfo = perfilInfo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
