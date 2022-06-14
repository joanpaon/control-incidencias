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
import org.japo.java.libraries.UtilesUsuario;

public final class Especialidad implements Serializable {

    // Campos
    private int id;
    private String nombre;
    private String info;
    private int responsable;
    private String responsableNombre;

    // Constructor Predeterminado
    public Especialidad() {
        id = UtilesEspecialidad.DEF_ID;
        nombre = UtilesEspecialidad.DEF_NOMBRE;
        info = UtilesEspecialidad.DEF_INFO;
        responsable = UtilesUsuario.DEF_ID;
        responsableNombre = UtilesUsuario.DEF_ALIAS;
    }

    // Constructor Parametrizado
    public Especialidad(int id, String nombre, String info,
            int responsable, String responsableNombre) {
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

        if (UtilesUsuario.validarId(responsable)) {
            this.responsable = responsable;
        } else {
            this.responsable = UtilesUsuario.DEF_ID;
        }

        if (UtilesUsuario.validarAlias(responsableNombre)) {
            this.responsableNombre = responsableNombre;
        } else {
            this.responsableNombre = UtilesUsuario.DEF_ALIAS;
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

    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        if (UtilesUsuario.validarId(responsable)) {
            this.responsable = responsable;
        }
    }

    public String getResponsableNombre() {
        return responsableNombre;
    }

    public void setResponsable(String responsableNombre) {
        if (UtilesUsuario.validarAlias(responsableNombre)) {
            this.responsableNombre = responsableNombre;
        }
    }

    @Override
    public final boolean equals(Object o) {
        boolean testOK = false;
        if (o instanceof Especialidad e) {
            testOK = true
                    && id == e.getId()
                    && nombre.equals(e.getNombre())
                    && info.equals(e.getInfo())
                    && responsable == e.getResponsable();
        }
        return testOK;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.info);
        hash = 67 * hash + this.responsable;
        return hash;
    }
}
