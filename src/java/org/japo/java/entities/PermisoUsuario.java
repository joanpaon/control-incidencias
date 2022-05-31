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
import org.japo.java.libraries.UtilesPermisoUsuario;
import org.japo.java.libraries.UtilesProceso;
import org.japo.java.libraries.UtilesUsuario;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
public final class PermisoUsuario implements Serializable {

    // Campos
    private int id;
    private int usuario;
    private String usuarioName;
    private int proceso;
    private String procesoInfo;
    private String info;

    // Constructor Predeterminado
    public PermisoUsuario() {
        id = UtilesPermisoUsuario.DEF_ID;
        usuario = UtilesUsuario.DEF_ID;
        usuarioName = UtilesUsuario.DEF_USER;
        proceso = UtilesProceso.DEF_ID;
        procesoInfo = UtilesProceso.DEF_INFO;
        info = UtilesPermisoUsuario.DEF_INFO;
    }

    // Constructor Parametrizado
    public PermisoUsuario(int id,
            int usuario, String usuarioName,
            int proceso, String procesoInfo,
            String info) {
        if (UtilesPermisoUsuario.validarId(id)) {
            this.id = id;
        } else {
            this.id = UtilesPermisoUsuario.DEF_ID;
        }

        if (UtilesUsuario.validarId(usuario)) {
            this.usuario = usuario;
        } else {
            this.usuario = UtilesUsuario.DEF_ID;
        }

        if (UtilesUsuario.validarUser(usuarioName)) {
            this.usuarioName = usuarioName;
        } else {
            this.usuarioName = UtilesUsuario.DEF_USER;
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

        if (UtilesPermisoUsuario.validarInfo(info)) {
            this.info = info;
        } else {
            this.info = UtilesPermisoUsuario.DEF_INFO;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (UtilesPermisoUsuario.validarId(id)) {
            this.id = id;
        }
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        if (UtilesUsuario.validarId(usuario)) {
            this.usuario = usuario;
        }
    }

    public String getUsuarioName() {
        return usuarioName;
    }

    public void setUsuarioName(String usuarioName) {
        if (UtilesUsuario.validarUser(usuarioName)) {
            this.usuarioName = usuarioName;
        }
    }

    public int getProceso() {
        return proceso;
    }

    public void setProceso(int proceso) {
        if (UtilesProceso.validarId(proceso)) {
            this.proceso = proceso;
        }
    }

    public String getProcesoInfo() {
        return procesoInfo;
    }

    public void setProcesoInfo(String procesoInfo) {
        if (UtilesProceso.validarInfo(procesoInfo)) {
            this.procesoInfo = procesoInfo;
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        if (UtilesPermisoUsuario.validarInfo(info)) {
            this.info = info;
        }
    }
}
