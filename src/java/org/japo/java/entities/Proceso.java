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
import org.japo.java.libraries.UtilesProceso;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
public final class Proceso implements Serializable {

    // Campos
    private int id;
    private String nombre;
    private String info;

    // Constructor Predeterminado
    public Proceso() {
        id = UtilesProceso.DEF_ID;
        nombre = UtilesProceso.DEF_NOMBRE;
        info = UtilesProceso.DEF_INFO;
    }

    // Constructor Parametrizado
    public Proceso(int id, String nombre, String info) {
        if (UtilesProceso.validarId(id)) {
            this.id = id;
        } else {
            this.id = UtilesProceso.DEF_ID;
        }

        if (UtilesProceso.validarNombre(nombre)) {
            this.nombre = nombre;
        } else {
            this.nombre = UtilesProceso.DEF_NOMBRE;
        }

        if (UtilesProceso.validarInfo(info)) {
            this.info = info;
        } else {
            this.info = UtilesProceso.DEF_INFO;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (UtilesProceso.validarId(id)) {
            this.id = id;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (UtilesProceso.validarNombre(nombre)) {
            this.nombre = nombre;
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        if (UtilesProceso.validarInfo(info)) {
            this.info = info;
        }
    }

    @Override
    public boolean equals(Object o) {
        // Semáforo
        boolean testOK = false;

        // Validación Parámetro
        if (o instanceof Proceso p) {
            // Validación Perfil
            testOK = true
                    && p.getId() == id
                    && p.getNombre().equals(nombre)
                    && p.getInfo().equals(info);
        }

        // Retorno: Semáforo
        return testOK;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.info);
        return hash;
    }
}
