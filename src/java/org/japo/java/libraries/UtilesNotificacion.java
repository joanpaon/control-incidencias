package org.japo.java.libraries;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

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
    public static final String REG_INFO = "[\\w áéíóúüñÁÉÍÓÚÜÑçÇ,\\.]{3,255}";

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
}
