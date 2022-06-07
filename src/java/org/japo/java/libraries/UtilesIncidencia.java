package org.japo.java.libraries;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import org.japo.java.dll.DLLIncidencia;
import org.japo.java.entities.Incidencia;

public final class UtilesIncidencia {

    // Estados de Incidencia
    public static final int INCIDENCIA_CERRADA = 0;
    public static final int INCIDENCIA_ABIERTA = 1;

    // Valores Predeterminados
    public static final int DEF_ID = 0;
    public static final Date DEF_FECHA = new Date();
    public static final String DEF_TITULO = "Indefinida";
    public static final String DEF_INFO = "Incidencia Indefinida";
    public static final int DEF_ESTADO = INCIDENCIA_ABIERTA;

    // Expresiones Regulares
    public static final String REG_TITULO = "[\\w áéíóúüñÁÉÍÓÚÜÑçÇ]{3,30}";
    public static final String REG_INFO = "[\\w áéíóúüñÁÉÍÓÚÜÑçÇ]{3,100}";

    private UtilesIncidencia() {
    }

    public static final boolean validarId(int id) {
        return id >= DEF_ID;
    }

    public static final boolean validarTitulo(String titulo) {
        return titulo.matches(REG_TITULO);
    }

    public static final boolean validarInfo(String info) {
        return info.matches(REG_INFO);
    }

    public static final boolean validarEstado(int estado) {
        return estado == INCIDENCIA_CERRADA || estado == INCIDENCIA_ABIERTA;
    }

    public static final boolean validarFecha(Date fecha) {
        return fecha != null;
    }

    public static final int obtenerIdRequest(
            HttpServletRequest request)
            throws IOException {
        // Referencia
        int id;

        // Request > Dato
        try {
            id = Integer.parseInt(request.getParameter("id"));

            if (!validarId(id)) {
                throw new IOException("ID de Incidencia Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Incidencia Incorrecta");
        }

        // Retorno
        return id;
    }

    public static final String obtenerTituloRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > Dato
        String titulo = request.getParameter("titulo");

        // Validar Dato
        if (!validarTitulo(titulo)) {
            throw new IOException("Título de Incidencia Incorrecto");
        }

        // Retorno
        return titulo;
    }

    public static final String obtenerInfoRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > Dato
        String info = request.getParameter("info");

        // Validar Dato
        if (!validarInfo(info)) {
            throw new IOException("Info Incorrecta");
        }

        // Retorno
        return info;
    }

    public static final int obtenerDependenciaRequest(
            HttpServletRequest request)
            throws IOException {
        // Referencia
        int dependencia;

        // Request > Dato
        try {
            dependencia = Integer.parseInt(request.getParameter("dependencia"));

            if (!UtilesDependencia.validarId(dependencia)) {
                throw new IOException("Id de Dependencia Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("Dependencia Incorrecta");
        }

        // Retorno
        return dependencia;
    }

    public static final int obtenerEspecialidadRequest(
            HttpServletRequest request)
            throws IOException {
        // Referencia
        int especialidad;

        // Request > Dato
        try {
            especialidad = Integer.parseInt(request.getParameter("especialidad"));

            if (!UtilesEspecialidad.validarId(especialidad)) {
                throw new IOException("Id de Especialidad Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("Especialidad Incorrecta");
        }

        // Retorno
        return especialidad;
    }

    public static final Incidencia consultarIncidenciaIdRequest(
            ServletConfig config,
            HttpServletRequest request)
            throws IOException {
        // Capas de Negocio
        DLLIncidencia dllIncidencia = new DLLIncidencia(config);

        // Request > Id de Incidencia
        int id = obtenerIdRequest(request);

        // Retorno
        return dllIncidencia.consultar(id);
    }
}
