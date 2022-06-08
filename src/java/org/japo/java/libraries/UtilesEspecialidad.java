package org.japo.java.libraries;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import org.japo.java.dll.DLLEspecialidad;
import org.japo.java.entities.Especialidad;

public final class UtilesEspecialidad {

    // Valores Predeterminados
    public static final int DEF_ID = 0;
    public static final String DEF_NOMBRE = "Indefinida";
    public static final String DEF_INFO = "Especialidad Indefinida";

    // Expresiones Regulares
    public static final String REG_NOMBRE = "[\\w áéíóúüñÁÉÍÓÚÜÑçÇ]{3,50}";
    public static final String REG_INFO = "[\\w áéíóúüñÁÉÍÓÚÜÑçÇ\\-\\.#,]{3,255}";

    private UtilesEspecialidad() {
    }

    public static final boolean validarId(int id) {
        return id >= DEF_ID;
    }

    public static final boolean validarNombre(String nombre) {
        return nombre.matches(REG_NOMBRE);
    }

    public static final boolean validarInfo(String info) {
        return info.matches(REG_INFO);
    }

    public static final int obtenerIdRequest(
            HttpServletRequest request)
            throws IOException {
        // Referencia
        int id;

        // URL > ID Objeto
        try {
            id = Integer.parseInt(request.getParameter("id"));

            if (!validarId(id)) {
                throw new IOException("ID de Especialidad Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Especialidad Incorrecta");
        }

        // Retorno
        return id;
    }

    public static final String obtenerNombreRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > Nombre
        String valor = request.getParameter("nombre");

        // Validar User
        if (!validarNombre(valor)) {
            throw new IOException("Nombre de Especialidad Incorrecto");
        }

        // Retorno
        return valor;
    }

    public static final String obtenerInfoRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > Info
        String valor = request.getParameter("info");

        // Validar User
        if (!validarInfo(valor)) {
            throw new IOException("Info de Especialidad Incorrecta");
        }

        // Retorno
        return valor;
    }

    public static Especialidad consultarEspecialidadIdRequest(
            ServletConfig config,
            HttpServletRequest request)
            throws IOException {
        // Capas de Negocio
        DLLEspecialidad dllEspecialidad = new DLLEspecialidad(config);

        // Request > Id de Especialidad
        int id = obtenerIdRequest(request);

        // Retorno: Especialidad
        return dllEspecialidad.consultar(id);
    }
}
