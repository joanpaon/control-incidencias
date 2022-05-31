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
import org.japo.java.libraries.UtilesDependencia;
import org.japo.java.libraries.UtilesEspecialidad;
import org.japo.java.libraries.UtilesIncidencia;
import org.japo.java.libraries.UtilesPerfil;
import org.japo.java.libraries.UtilesUsuario;

public final class Incidencia implements Serializable {

    // Campos
    private int id;
    private String nombre;
    private String info;
    private int estado;
    private Date creacion;
    private int autor;
    private String autorNombre;
    private String autorPerfil;
    private int dependencia;
    private String dependenciaNombre;
    private int especialidad;
    private String especialidadNombre;

    // Constructor Predeterminado
    public Incidencia() {
        id = UtilesIncidencia.DEF_ID;
        nombre = UtilesIncidencia.DEF_NOMBRE;
        info = UtilesIncidencia.DEF_INFO;
        estado = UtilesIncidencia.DEF_ESTADO;
        creacion = UtilesIncidencia.DEF_CREACION;
        autor = UtilesUsuario.DEF_ID;
        autorNombre = UtilesUsuario.DEF_USER;
        autorPerfil = UtilesPerfil.DEF_NOMBRE;
        dependencia = UtilesDependencia.DEF_ID;
        dependenciaNombre = UtilesDependencia.DEF_NOMBRE;
        especialidad = UtilesEspecialidad.DEF_ID;
        especialidadNombre = UtilesEspecialidad.DEF_NOMBRE;
    }

    // Constructor Parametrizado
    public Incidencia(int id, String nombre, String info,
            int estado, Date creacion,
            int autor, String autorNombre, String autorPerfil,
            int dependencia, String dependenciaNombre,
            int especialidad, String especialidadNombre) {
        if (UtilesIncidencia.validarId(id)) {
            this.id = id;
        } else {
            this.id = UtilesIncidencia.DEF_ID;
        }

        if (UtilesIncidencia.validarNombre(nombre)) {
            this.nombre = nombre;
        } else {
            this.nombre = UtilesIncidencia.DEF_NOMBRE;
        }

        if (UtilesIncidencia.validarInfo(info)) {
            this.info = info;
        } else {
            this.info = UtilesIncidencia.DEF_INFO;
        }

        if (UtilesIncidencia.validarEstado(estado)) {
            this.estado = estado;
        } else {
            this.estado = UtilesIncidencia.DEF_ESTADO;
        }

        if (UtilesIncidencia.validarCreacion(creacion)) {
            this.creacion = creacion;
        } else {
            this.creacion = UtilesIncidencia.DEF_CREACION;
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

        if (UtilesDependencia.validarId(dependencia)) {
            this.dependencia = dependencia;
        } else {
            this.dependencia = UtilesDependencia.DEF_ID;
        }

        if (UtilesDependencia.validarNombre(dependenciaNombre)) {
            this.dependenciaNombre = dependenciaNombre;
        } else {
            this.dependenciaNombre = UtilesDependencia.DEF_NOMBRE;
        }

        if (UtilesEspecialidad.validarId(especialidad)) {
            this.especialidad = especialidad;
        } else {
            this.especialidad = UtilesEspecialidad.DEF_ID;
        }

        if (UtilesEspecialidad.validarNombre(especialidadNombre)) {
            this.especialidadNombre = especialidadNombre;
        } else {
            this.especialidadNombre = UtilesEspecialidad.DEF_NOMBRE;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (UtilesIncidencia.validarId(id)) {
            this.id = id;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (UtilesIncidencia.validarNombre(nombre)) {
            this.nombre = nombre;
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        if (UtilesIncidencia.validarInfo(info)) {
            this.info = info;
        }
    }

    public int getEstado() {
        return estado;
    }

    public void setCodigo(int estado) {
        if (UtilesIncidencia.validarEstado(estado)) {
            this.estado = estado;
        }
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        if (UtilesIncidencia.validarCreacion(creacion)) {
            this.creacion = creacion;
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
        if (UtilesPerfil.validarNombre(autorPerfil)) {
            this.autorPerfil = autorPerfil;
        }
    }

    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
        if (UtilesIncidencia.validarId(dependencia)) {
            this.dependencia = dependencia;
        }
    }

    public String getDependenciaNombre() {
        return dependenciaNombre;
    }

    public void setDependenciaNombre(String dependenciaNombre) {
        if (UtilesIncidencia.validarNombre(dependenciaNombre)) {
            this.dependenciaNombre = dependenciaNombre;
        }
    }

    public int getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(int especialidad) {
        if (UtilesEspecialidad.validarId(especialidad)) {
            this.especialidad = especialidad;
        }
    }

    public String getEspecialidadNombre() {
        return especialidadNombre;
    }

    public void setEspecialidadNombre(String especialidadNombre) {
        if (UtilesEspecialidad.validarNombre(especialidadNombre)) {
            this.especialidadNombre = especialidadNombre;
        }
    }

    @Override
    public boolean equals(Object o) {
        boolean testOK = false;
        if (o instanceof Incidencia i) {
            testOK = true
                    && id == i.getId()
                    && nombre.equals(i.getNombre())
                    && info.equals(i.getInfo())
                    && estado == i.getEstado()
                    && creacion.equals(i.getCreacion())
                    && autor == i.getAutor()
                    && dependencia == i.getDependencia()
                    && especialidad == i.getEspecialidad();
        }
        return testOK;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.info);
        hash = 59 * hash + this.estado;
        hash = 59 * hash + Objects.hashCode(this.creacion);
        hash = 59 * hash + this.autor;
        hash = 59 * hash + Objects.hashCode(this.autorNombre);
        hash = 59 * hash + Objects.hashCode(this.autorPerfil);
        hash = 59 * hash + this.dependencia;
        hash = 59 * hash + Objects.hashCode(this.dependenciaNombre);
        hash = 59 * hash + this.especialidad;
        hash = 59 * hash + Objects.hashCode(this.especialidadNombre);
        return hash;
    }
}
