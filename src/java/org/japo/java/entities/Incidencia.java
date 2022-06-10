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
    private String titulo;
    private String info;
    private int estado;
    private Date fecha;
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
        titulo = UtilesIncidencia.DEF_TITULO;
        info = UtilesIncidencia.DEF_INFO;
        estado = UtilesIncidencia.DEF_ESTADO;
        fecha = UtilesIncidencia.DEF_FECHA;
        autor = UtilesUsuario.DEF_ID;
        autorNombre = UtilesUsuario.DEF_ALIAS;
        autorPerfil = UtilesPerfil.DEF_NOMBRE;
        dependencia = UtilesDependencia.DEF_ID;
        dependenciaNombre = UtilesDependencia.DEF_NOMBRE;
        especialidad = UtilesEspecialidad.DEF_ID;
        especialidadNombre = UtilesEspecialidad.DEF_NOMBRE;
    }

    // Constructor Parametrizado
    public Incidencia(int id, String titulo, String info,
            int estado, Date fecha,
            int autor, String autorNombre, String autorPerfil,
            int dependencia, String dependenciaNombre,
            int especialidad, String especialidadNombre) {
        if (UtilesIncidencia.validarId(id)) {
            this.id = id;
        } else {
            this.id = UtilesIncidencia.DEF_ID;
        }

        if (UtilesIncidencia.validarTitulo(titulo)) {
            this.titulo = titulo;
        } else {
            this.titulo = UtilesIncidencia.DEF_TITULO;
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

        if (UtilesIncidencia.validarFecha(fecha)) {
            this.fecha = fecha;
        } else {
            this.fecha = UtilesIncidencia.DEF_FECHA;
        }

        if (UtilesUsuario.validarId(autor)) {
            this.autor = autor;
        } else {
            this.autor = UtilesUsuario.DEF_ID;
        }

        if (UtilesUsuario.validarAlias(autorNombre)) {
            this.autorNombre = autorNombre;
        } else {
            this.autorNombre = UtilesUsuario.DEF_ALIAS;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (UtilesIncidencia.validarTitulo(titulo)) {
            this.titulo = titulo;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        if (UtilesIncidencia.validarFecha(fecha)) {
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
        if (UtilesUsuario.validarAlias(autorNombre)) {
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
        if (UtilesIncidencia.validarTitulo(dependenciaNombre)) {
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
                    && titulo.equals(i.getTitulo())
                    && info.equals(i.getInfo())
                    && estado == i.getEstado()
                    && fecha.equals(i.getFecha())
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
        hash = 59 * hash + Objects.hashCode(this.titulo);
        hash = 59 * hash + Objects.hashCode(this.info);
        hash = 59 * hash + this.estado;
        hash = 59 * hash + Objects.hashCode(this.fecha);
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
