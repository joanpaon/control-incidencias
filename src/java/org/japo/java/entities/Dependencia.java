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
import java.util.Objects;
import org.japo.java.libraries.UtilesDependencia;

public final class Dependencia implements Serializable {

    // Campos
    private int id;
    private String nombre;
    private String info;

    // Constructor Predeterminado
    public Dependencia() {
        id = UtilesDependencia.DEF_ID;
        nombre = UtilesDependencia.DEF_NOMBRE;
        info = UtilesDependencia.DEF_INFO;
    }

    // Constructor Parametrizado
    public Dependencia(int id, String nombre, String info) {
        if (UtilesDependencia.validarId(id)) {
            this.id = id;
        } else {
            this.id = UtilesDependencia.DEF_ID;
        }

        if (UtilesDependencia.validarNombre(nombre)) {
            this.nombre = nombre;
        } else {
            this.nombre = UtilesDependencia.DEF_NOMBRE;
        }

        if (UtilesDependencia.validarInfo(info)) {
            this.info = info;
        } else {
            this.info = UtilesDependencia.DEF_INFO;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (UtilesDependencia.validarId(id)) {
            this.id = id;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (UtilesDependencia.validarNombre(nombre)) {
            this.nombre = nombre;
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        if (UtilesDependencia.validarInfo(info)) {
            this.info = info;
        }
    }

    @Override
    public boolean equals(Object o) {
        boolean testOK = false;
        if (o instanceof Dependencia d) {
            testOK = true
                    && id == d.getId()
                    && nombre.equals(d.getNombre())
                    && info.equals(d.getInfo());
        }
        return testOK;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.info);
        return hash;
    }
}
