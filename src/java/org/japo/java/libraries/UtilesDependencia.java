package org.japo.java.libraries;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import org.japo.java.dll.DLLDependencia;
import org.japo.java.entities.Dependencia;
import static org.japo.java.libraries.UtilesDependencia.validarInfo;
import static org.japo.java.libraries.UtilesDependencia.validarNombre;

public final class UtilesDependencia {

    // Valores Predeterminados
    public static final int DEF_ID = 0;
    public static final String DEF_NOMBRE = "D00";
    public static final String DEF_INFO = "Dependencia Indefinida";

    // Expresiones Regulares
    public static final String REG_NOMBRE = "[\\w]{3,10}";
    public static final String REG_INFO = "[\\w áéíóúüñÁÉÍÓÚÜÑçÇ\\-\\.#]{3,100}";

    private UtilesDependencia() {
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
                throw new IOException("ID de Dependencia Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Dependencia Incorrecta");
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
            throw new IOException("Nombre de Dependencia Incorrecto");
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
            throw new IOException("Info de Dependencia Incorrecta");
        }

        // Retorno
        return valor;
    }

    public static Dependencia consultarDependenciaIdRequest(
            ServletConfig config,
            HttpServletRequest request)
            throws IOException {
        // Capas de Negocio
        DLLDependencia dllDependencia = new DLLDependencia(config);

        // Request > Id de Dependencia
        int id = obtenerIdRequest(request);

        // Retorno: Dependencia
        return dllDependencia.consultar(id);
    }
}
