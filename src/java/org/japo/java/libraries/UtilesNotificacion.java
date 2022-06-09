package org.japo.java.libraries;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import org.japo.java.dll.DLLNotificacion;
import org.japo.java.entities.Notificacion;

public final class UtilesNotificacion {

    // Estados de Incidencia
    public static final int INCIDENCIA_CERRADA = 0;
    public static final int INCIDENCIA_ABIERTA = 1;

    // Valores Predeterminados
    public static final int DEF_ID = 0;
    public static final Date DEF_FECHA = new Date();
    public static final int DEF_AUTOR = UtilesUsuario.DEF_ID;
    public static final int DEF_INCIDENCIA = UtilesIncidencia.DEF_ID;
    public static final String DEF_INFO = "Notificación Indefinida";

    // Expresiones Regulares
    public static final String REG_INFO = "[\\w áéíóúüñÁÉÍÓÚÜÑçÇ\\-\\.#,]{3,100}";

    private UtilesNotificacion() {
    }

    public static final boolean validarId(int id) {
        return id >= DEF_ID;
    }

    public static final boolean validarFecha(Date fecha) {
        return fecha != null;
    }

    public static final boolean validarInfo(String info) {
        return info.matches(REG_INFO);
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
                throw new IOException("ID de Notificación Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Notificación Incorrecta");
        }

        // Retorno
        return id;
    }

    public static final Date obtenerFechaRequest(
            HttpServletRequest request)
            throws IOException {
        // Referencia
        Date fecha;

        // Request > Dato
        try {
            fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").
                    parse(request.getParameter("fecha"));

            if (!validarFecha(fecha)) {
                throw new IOException("Fecha incorrecta");
            }
        } catch (ParseException e) {
            throw new IOException(e.getMessage());
        }

        // Retorno
        return fecha;
    }

    public static final int obtenerAutorRequest(
            HttpServletRequest request)
            throws IOException {
        // Referencia
        int autor;

        // Request > Dato
        try {
            autor = Integer.parseInt(request.getParameter("autor"));

            if (!UtilesUsuario.validarId(autor)) {
                throw new IOException("ID de Autor incorrecto");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Autor Incorrecto");
        }

        // Retorno
        return autor;
    }

    public static final int obtenerIncidenciaRequest(
            HttpServletRequest request)
            throws IOException {
        // Referencia
        int incidencia;

        // Request > Dato
        try {
            incidencia = Integer.parseInt(request.getParameter("incidencia"));

            if (!UtilesIncidencia.validarId(incidencia)) {
                throw new IOException("ID de Incidencia incorrecto");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Incidencia Incorrecto");
        }

        // Retorno
        return incidencia;
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

    public static Notificacion consultarNotificacionIdRequest(
            ServletConfig config,
            HttpServletRequest request)
            throws IOException {
        // Capas de Negocio
        DLLNotificacion dllNotificacion = new DLLNotificacion(config);

        // Request > Id de Notificación
        int id = obtenerIdRequest(request);

        // Retorno: Dependencia
        return dllNotificacion.consultar(id);
    }
}
