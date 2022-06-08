/*
 * Copyright 2022 JAPO Labs.
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
package org.japo.java.dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.sql.DataSource;
import org.japo.java.entities.Notificacion;
import org.japo.java.libraries.UtilesServlet;

/**
 *
 * @author Joanpaon
 */
public final class DLLNotificacion {

    // Bitácora
    private static final Logger logger = Logger.getLogger(DLLNotificacion.class.getName());

    // DataSource
    private final DataSource ds;

    public DLLNotificacion(ServletConfig config) {
        ds = UtilesServlet.obtenerDataSource(config);
    }

    public boolean borrar(int id) {
        // SQL
        final String SQL = ""
                + "DELETE FROM "
                + "notificaciones "
                + "WHERE id=?";

        // Número de registros afectados
        int numReg = 0;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }

    public Notificacion consultar(int id) {
        // SQL
        String sql = ""
                + "SELECT "
                + "notificaciones.id AS id, "
                + "notificaciones.fecha AS fecha, "
                + "notificaciones.autor AS autor, "
                + "usuarios.user AS autor_nombre, "
                + "perfiles.nombre AS autor_perfil, "
                + "notificaciones.incidencia AS incidencia, "
                + "incidencias.titulo AS incidencia_titulo, "
                + "notificaciones.info AS info "
                + "FROM "
                + "notificaciones "
                + "INNER JOIN "
                + "usuarios ON usuarios.id = notificaciones.autor "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "INNER JOIN "
                + "incidencias ON incidencias.id = notificaciones.incidencia "
                + "WHERE "
                + "notificaciones.id=?";

        // Entidad
        Notificacion notificacion = null;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);

                // BD > Entidad
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Fila Actual > Campos 
                        Date fecha = rs.getTimestamp("fecha");
                        int autor = rs.getInt("autor");
                        String autorNombre = rs.getString("autor_nombre");
                        String autorPerfil = rs.getString("autor_perfil");
                        int incidencia = rs.getInt("incidencia");
                        String incidenciaTitulo = rs.getString("incidencia_titulo");
                        String info = rs.getString("info");

                        // Campos > Entidad
                        notificacion = new Notificacion(
                                id, fecha,
                                autor, autorNombre, autorPerfil,
                                incidencia, incidenciaTitulo,
                                info);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Entidad
        return notificacion;
    }

    public Long contar() {
        // Número de Filas
        long filas = 0;

        // SQL
        String sql = ""
                + "SELECT "
                + "COUNT(*) "
                + "FROM "
                + "notificaciones";

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Ejecutar Sentencia
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        filas = rs.getLong(1);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: Filas Contadas
        return filas;
    }

    public boolean insertar(Notificacion notificacion) {
        // SQL
        final String SQL = ""
                + "INSERT INTO "
                + "notificaciones "
                + "("
                + "fecha, autor, incidencia, info"
                + ") "
                + "VALUES (?, ?, ?, ?)";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
//                ps.setDate(1, new java.sql.Date(notificacion.getFecha().getTime()));
                ps.setTimestamp(1, new java.sql.Timestamp(notificacion.getFecha().getTime()));
                ps.setInt(2, notificacion.getAutor());
                ps.setInt(3, notificacion.getIncidencia());
                ps.setString(4, notificacion.getInfo());

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }

    public List<Notificacion> listar() {
        // SQL
        String sql = ""
                + "SELECT "
                + "notificaciones.id AS id, "
                + "notificaciones.fecha AS fecha, "
                + "notificaciones.autor AS autor, "
                + "usuarios.user AS autor_nombre, "
                + "perfiles.nombre AS autor_perfil, "
                + "notificaciones.incidencia AS incidencia, "
                + "incidencias.titulo AS incidencia_titulo, "
                + "notificaciones.info AS info "
                + "FROM "
                + "notificaciones "
                + "INNER JOIN "
                + "usuarios ON usuarios.id = notificaciones.autor "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "INNER JOIN "
                + "incidencias ON incidencias.id = notificaciones.incidencia "
                + "ORDER BY notificaciones.fecha ASC";

        // Lista Vacía
        List<Notificacion> notificaciones = new ArrayList<>();

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        Date fecha = rs.getTimestamp("fecha");
                        int autor = rs.getInt("autor");
                        String autorNombre = rs.getString("autor_nombre");
                        String autorPerfil = rs.getString("autor_perfil");
                        int incidencia = rs.getInt("incidencia");
                        String incidenciaTitulo = rs.getString("incidencia_titulo");
                        String info = rs.getString("info");

                        // Campos > Entidad
                        Notificacion notificacion = new Notificacion(
                                id, fecha,
                                autor, autorNombre, autorPerfil,
                                incidencia, incidenciaTitulo,
                                info);

                        // Entidad > Lista
                        notificaciones.add(notificacion);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return notificaciones;
    }

    public List<Notificacion> listar(int incidencia) {
        // SQL
        String sql = ""
                + "SELECT "
                + "notificaciones.id AS id, "
                + "notificaciones.fecha AS fecha, "
                + "notificaciones.autor AS autor, "
                + "usuarios.user AS autor_nombre, "
                + "perfiles.nombre AS autor_perfil, "
                + "notificaciones.incidencia AS incidencia, "
                + "incidencias.titulo AS incidencia_titulo, "
                + "notificaciones.info AS info "
                + "FROM "
                + "notificaciones "
                + "INNER JOIN "
                + "usuarios ON usuarios.id = notificaciones.autor "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "INNER JOIN "
                + "incidencias ON incidencias.id = notificaciones.incidencia "
                + "WHERE "
                + "notificaciones.incidencia = ? "
                + "ORDER BY notificaciones.fecha ASC";

        // Lista Vacía
        List<Notificacion> notificaciones = new ArrayList<>();

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, incidencia);

                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        Date fecha = rs.getTimestamp("fecha");
                        int autor = rs.getInt("autor");
                        String autorNombre = rs.getString("autor_nombre");
                        String autorPerfil = rs.getString("autor_perfil");
                        String incidenciaTitulo = rs.getString("incidencia_titulo");
                        String info = rs.getString("info");

                        // Campos > Entidad
                        Notificacion notificacion = new Notificacion(
                                id, fecha,
                                autor, autorNombre, autorPerfil,
                                incidencia, incidenciaTitulo,
                                info);

                        // Entidad > Lista
                        notificaciones.add(notificacion);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return notificaciones;
    }

    public List<Notificacion> paginar(long indice, long longitud) {
        // SQL
        String sql = ""
                + "SELECT "
                + "notificaciones.id AS id, "
                + "notificaciones.fecha AS fecha, "
                + "notificaciones.autor AS autor, "
                + "usuarios.user AS autor_nombre, "
                + "perfiles.nombre AS autor_perfil, "
                + "notificaciones.incidencia AS incidencia, "
                + "incidencias.titulo AS incidencia_titulo, "
                + "notificaciones.info AS info "
                + "FROM "
                + "notificaciones "
                + "INNER JOIN "
                + "usuarios ON usuarios.id = notificaciones.autor "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "INNER JOIN "
                + "incidencias ON incidencias.id = notificaciones.incidencia "
                + "LIMIT ?, ?";

        // Lista Vacía
        List<Notificacion> notificaciones = new ArrayList<>();

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setLong(1, indice);
                ps.setLong(2, longitud);

                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        Date fecha = rs.getTimestamp("fecha");
                        int autor = rs.getInt("autor");
                        String autorNombre = rs.getString("autor_nombre");
                        String autorPerfil = rs.getString("autor_perfil");
                        int incidencia = rs.getInt("incidencia");
                        String incidenciaTitulo = rs.getString("incidencia_titulo");
                        String info = rs.getString("info");

                        // Campos > Entidad
                        Notificacion notificacion = new Notificacion(
                                id, fecha,
                                autor, autorNombre, autorPerfil,
                                incidencia, incidenciaTitulo,
                                info);

                        // Entidad > Lista
                        notificaciones.add(notificacion);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return notificaciones;
    }

    public boolean modificar(Notificacion notificacion) {
        // SQL
        final String SQL = ""
                + "UPDATE "
                + "notificaciones "
                + "SET "
                + "fecha=?, autor=?, incidencia=?, info=? "
                + "WHERE "
                + "id=?";

        // Número de Registros Afectados
        int numReg = 0;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setTimestamp(1, new java.sql.Timestamp(notificacion.getFecha().getTime()));
                ps.setInt(2, notificacion.getAutor());
                ps.setInt(3, notificacion.getIncidencia());
                ps.setString(4, notificacion.getInfo());
                ps.setInt(5, notificacion.getId());

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }

    public List<Notificacion> paginar(long indice, long longitud, int incidencia) {
        // SQL
        String sql = ""
                + "SELECT "
                + "notificaciones.id AS id, "
                + "notificaciones.fecha AS fecha, "
                + "notificaciones.autor AS autor, "
                + "usuarios.user AS autor_nombre, "
                + "perfiles.nombre AS autor_perfil, "
                + "notificaciones.incidencia AS incidencia, "
                + "incidencias.titulo AS incidencia_titulo, "
                + "notificaciones.info AS info "
                + "FROM "
                + "notificaciones "
                + "INNER JOIN "
                + "usuarios ON usuarios.id = notificaciones.autor "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "INNER JOIN "
                + "incidencias ON incidencias.id = notificaciones.incidencia "
                + "LIMIT ?, ? "
                + "WHERE "
                + "notificaciones.incidencia = ?";

        // Lista Vacía
        List<Notificacion> notificaciones = new ArrayList<>();

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setLong(1, indice);
                ps.setLong(2, longitud);
                ps.setInt(3, incidencia);

                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        Date fecha = rs.getTimestamp("fecha");
                        int autor = rs.getInt("autor");
                        String autorNombre = rs.getString("autor_nombre");
                        String autorPerfil = rs.getString("autor_perfil");
                        String incidenciaTitulo = rs.getString("incidencia_titulo");
                        String info = rs.getString("info");

                        // Campos > Entidad
                        Notificacion notificacion = new Notificacion(
                                id, fecha,
                                autor, autorNombre, autorPerfil,
                                incidencia, incidenciaTitulo,
                                info);

                        // Entidad > Lista
                        notificaciones.add(notificacion);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return notificaciones;
    }
}
