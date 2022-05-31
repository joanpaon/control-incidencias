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
import org.japo.java.libraries.UtilesEspecialidad;

public final class Especialidad implements Serializable {

    // Campos
    private int id;
    private String nombre;
    private String info;

    // Constructor Predeterminado
    public Especialidad() {
        id = UtilesEspecialidad.DEF_ID;
        nombre = UtilesEspecialidad.DEF_NOMBRE;
        info = UtilesEspecialidad.DEF_INFO;
    }

    // Constructor Parametrizado
    public Especialidad(int id, String nombre, String info) {
        if (UtilesEspecialidad.validarId(id)) {
            this.id = id;
        } else {
            this.id = UtilesEspecialidad.DEF_ID;
        }

        if (UtilesEspecialidad.validarNombre(nombre)) {
            this.nombre = nombre;
        } else {
            this.nombre = UtilesEspecialidad.DEF_NOMBRE;
        }

        if (UtilesEspecialidad.validarInfo(info)) {
            this.info = info;
        } else {
            this.info = UtilesEspecialidad.DEF_INFO;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (UtilesEspecialidad.validarId(id)) {
            this.id = id;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (UtilesEspecialidad.validarNombre(nombre)) {
            this.nombre = nombre;
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        if (UtilesEspecialidad.validarInfo(info)) {
            this.info = info;
        }
    }

    @Override
    public boolean equals(Object o) {
        boolean testOK = false;
        if (o instanceof Especialidad e) {
            testOK = true
                    && id == e.getId()
                    && nombre.equals(e.getNombre())
                    && info.equals(e.getInfo());
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
