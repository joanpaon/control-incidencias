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
import java.util.Date;
import java.util.Objects;
import org.japo.java.libraries.UtilesIncidencia;
import org.japo.java.libraries.UtilesNotificacion;
import org.japo.java.libraries.UtilesPerfil;
import org.japo.java.libraries.UtilesUsuario;

public final class Notificacion implements Serializable {

    // Campos
    private int id;
    private Date fecha;
    private int autor;
    private String autorNombre;
    private String autorPerfil;
    private int incidencia;
    private String info;

    // Constructor Predeterminado
    public Notificacion() {
        id = UtilesIncidencia.DEF_ID;
        fecha = UtilesIncidencia.DEF_CREACION;
        autor = UtilesUsuario.DEF_ID;
        autorNombre = UtilesUsuario.DEF_USER;
        autorPerfil = UtilesPerfil.DEF_NOMBRE;
        incidencia = UtilesIncidencia.DEF_ID;
        info = UtilesIncidencia.DEF_INFO;
    }

    // Constructor Parametrizado
    public Notificacion(int id, Date fecha,
            int autor, String autorNombre, String autorPerfil,
            int incidencia, String info) {
        if (UtilesNotificacion.validarId(id)) {
            this.id = id;
        } else {
            this.id = UtilesNotificacion.DEF_ID;
        }

        if (UtilesNotificacion.validarFecha(fecha)) {
            this.fecha = fecha;
        } else {
            this.fecha = UtilesNotificacion.DEF_FECHA;
        }

        if (UtilesUsuario.validarId(autor)) {
            this.autor = autor;
        } else {
            this.autor = UtilesUsuario.DEF_ID;
        }

        if (UtilesUsuario.validarUser(autorNombre)) {
            this.autorNombre = autorNombre;
        } else {
            this.autorNombre = UtilesUsuario.DEF_USER;
        }

        if (UtilesPerfil.validarNombre(autorPerfil)) {
            this.autorPerfil = autorPerfil;
        } else {
            this.autorPerfil = UtilesPerfil.DEF_NOMBRE;
        }

        if (UtilesIncidencia.validarId(incidencia)) {
            this.incidencia = incidencia;
        } else {
            this.incidencia = UtilesIncidencia.DEF_ID;
        }

        if (UtilesNotificacion.validarInfo(info)) {
            this.info = info;
        } else {
            this.info = UtilesNotificacion.DEF_INFO;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (UtilesNotificacion.validarId(id)) {
            this.id = id;
        }
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        if (UtilesNotificacion.validarFecha(fecha)) {
            this.fecha = fecha;
        }
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        if (UtilesUsuario.validarId(autor)) {
            this.autor = autor;
        }
    }

    public String getAutorNombre() {
        return autorNombre;
    }

    public void setAutorNombre(String autorNombre) {
        if (UtilesUsuario.validarUser(autorNombre)) {
            this.autorNombre = autorNombre;
        }
    }

    public String getAutorPerfil() {
        return autorPerfil;
    }

    public void setAutorPerfil(String autorPerfil) {
        if (UtilesUsuario.validarUser(autorPerfil)) {
            this.autorPerfil = autorPerfil;
        }
    }

    public int getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(int incidencia) {
        if (UtilesIncidencia.validarId(incidencia)) {
            this.incidencia = incidencia;
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        if (UtilesNotificacion.validarInfo(info)) {
            this.info = info;
        }
    }

    @Override
    public boolean equals(Object o) {
        boolean testOK = false;
        if (o instanceof Notificacion n) {
            testOK = true
                    && id == n.getId()
                    && fecha.equals(n.getFecha())
                    && autor == n.getAutor()
                    && incidencia == n.getIncidencia()
                    && info.equals(n.getInfo());
        }
        return testOK;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + id;
        hash = 67 * hash + Objects.hashCode(this.fecha);
        hash = 67 * hash + autor;
        hash = 67 * hash + incidencia;
        hash = 67 * hash + Objects.hashCode(this.info);
        return hash;
    }
}
