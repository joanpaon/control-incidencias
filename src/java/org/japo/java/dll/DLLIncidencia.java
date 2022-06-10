/*
 * Copyright 2022 Joanpaon.
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
import org.japo.java.entities.Incidencia;
import org.japo.java.libraries.UtilesServlet;

/**
 *
 * @author Joanpaon
 */
public final class DLLIncidencia {

    // Bitácora
    private static final Logger logger = Logger.getLogger(DLLIncidencia.class.getName());

    // DataSource
    private final DataSource ds;

    public DLLIncidencia(ServletConfig config) {
        ds = UtilesServlet.obtenerDataSource(config);
    }

    public List<Incidencia> listar() {
        // SQL
        String sql = ""
                + "SELECT "
                + "incidencias.id AS id, "
                + "incidencias.titulo AS titulo, "
                + "incidencias.info AS info, "
                + "incidencias.estado AS estado, "
                + "incidencias.fecha AS fecha, "
                + "incidencias.autor AS autor, "
                + "usuarios.alias AS autor_nombre, "
                + "perfiles.nombre AS autor_perfil, "
                + "incidencias.dependencia AS dependencia, "
                + "dependencias.nombre AS dependencia_nombre, "
                + "incidencias.especialidad AS especialidad, "
                + "especialidades.nombre AS especialidad_nombre "
                + "FROM "
                + "incidencias "
                + "INNER JOIN "
                + "usuarios ON usuarios.id = incidencias.autor "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "INNER JOIN "
                + "dependencias ON dependencias.id = incidencias.dependencia "
                + "INNER JOIN "
                + "especialidades ON especialidades.id = incidencias.especialidad";

        // Lista Vacía
        List<Incidencia> incidencias = new ArrayList<>();

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        String titulo = rs.getString("titulo");
                        String info = rs.getString("info");
                        int estado = rs.getInt("estado");
                        Date fecha = rs.getTimestamp("fecha");
                        int autor = rs.getInt("autor");
                        String autorNombre = rs.getString("autor_nombre");
                        String autorPerfil = rs.getString("autor_perfil");
                        int dependencia = rs.getInt("dependencia");
                        String dependenciaNombre = rs.getString("dependencia_nombre");
                        int especialidad = rs.getInt("especialidad");
                        String especialidadNombre = rs.getString("especialidad_nombre");

                        // Campos > Entidad
                        Incidencia incidencia = new Incidencia(
                                id, titulo, info, estado, fecha,
                                autor, autorNombre, autorPerfil,
                                dependencia, dependenciaNombre,
                                especialidad, especialidadNombre);

                        // Entidad > Lista
                        incidencias.add(incidencia);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return incidencias;
    }

    public List<Incidencia> listar(int autor) {
        // SQL
        String sql = ""
                + "SELECT "
                + "incidencias.id AS id, "
                + "incidencias.titulo AS titulo, "
                + "incidencias.info AS info, "
                + "incidencias.estado AS estado, "
                + "incidencias.fecha AS fecha, "
                + "incidencias.autor AS autor, "
                + "usuarios.alias AS autor_nombre, "
                + "perfiles.nombre AS autor_perfil, "
                + "incidencias.dependencia AS dependencia, "
                + "dependencias.nombre AS dependencia_nombre, "
                + "incidencias.especialidad AS especialidad, "
                + "especialidades.nombre AS especialidad_nombre "
                + "FROM "
                + "incidencias "
                + "INNER JOIN "
                + "usuarios ON usuarios.id = incidencias.autor "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "INNER JOIN "
                + "dependencias ON dependencias.id = incidencias.dependencia "
                + "INNER JOIN "
                + "especialidades ON especialidades.id = incidencias.especialidad "
                + "WHERE "
                + "incidencias.autor = ?";

        // Lista Vacía
        List<Incidencia> incidencias = new ArrayList<>();

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, autor);

                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        String titulo = rs.getString("titulo");
                        String info = rs.getString("info");
                        int estado = rs.getInt("estado");
                        Date fecha = rs.getTimestamp("fecha");
                        String autorNombre = rs.getString("autor_nombre");
                        String autorPerfil = rs.getString("autor_perfil");
                        int dependencia = rs.getInt("dependencia");
                        String dependenciaNombre = rs.getString("dependencia_nombre");
                        int especialidad = rs.getInt("especialidad");
                        String especialidadNombre = rs.getString("especialidad_nombre");

                        // Campos > Entidad
                        Incidencia incidencia = new Incidencia(
                                id, titulo, info, estado, fecha,
                                autor, autorNombre, autorPerfil,
                                dependencia, dependenciaNombre,
                                especialidad, especialidadNombre);

                        // Entidad > Lista
                        incidencias.add(incidencia);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return incidencias;
    }

    public Long contar() {
        // Número de Filas
        long filas = 0;

        // SQL
        String sql = ""
                + "SELECT "
                + "COUNT(*) "
                + "FROM "
                + "incidencias";

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

    public Long contar(int autor) {
        // Número de Filas
        long filas = 0;

        // SQL
        String sql = ""
                + "SELECT "
                + "COUNT(*) "
                + "FROM "
                + "incidencias "
                + "WHERE "
                + "incidencias.autor = ?";

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, autor);

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

    public List<Incidencia> paginar(long indice, long longitud) {
        // SQL
        String sql = ""
                + "SELECT "
                + "incidencias.id AS id, "
                + "incidencias.titulo AS titulo, "
                + "incidencias.info AS info, "
                + "incidencias.estado AS estado, "
                + "incidencias.fecha AS fecha, "
                + "incidencias.autor AS autor, "
                + "usuarios.alias AS autor_nombre, "
                + "perfiles.nombre AS autor_perfil, "
                + "incidencias.dependencia AS dependencia, "
                + "dependencias.nombre AS dependencia_nombre, "
                + "incidencias.especialidad AS especialidad, "
                + "especialidades.nombre AS especialidad_nombre "
                + "FROM "
                + "incidencias "
                + "INNER JOIN "
                + "usuarios ON usuarios.id = incidencias.autor "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "INNER JOIN "
                + "dependencias ON dependencias.id = incidencias.dependencia "
                + "INNER JOIN "
                + "especialidades ON especialidades.id = incidencias.especialidad "
                + "LIMIT ?, ?";

        // Lista Vacía
        List<Incidencia> incidencias = new ArrayList<>();

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
                        String titulo = rs.getString("titulo");
                        String info = rs.getString("info");
                        int estado = rs.getInt("estado");
                        Date fecha = rs.getTimestamp("fecha");
                        int autor = rs.getInt("autor");
                        String autorNombre = rs.getString("autor_nombre");
                        String autorPerfil = rs.getString("autor_perfil");
                        int dependencia = rs.getInt("dependencia");
                        String dependenciaNombre = rs.getString("dependencia_nombre");
                        int especialidad = rs.getInt("especialidad");
                        String especialidadNombre = rs.getString("especialidad_nombre");

                        // Campos > Entidad
                        Incidencia incidencia = new Incidencia(
                                id, titulo, info, estado, fecha,
                                autor, autorNombre, autorPerfil,
                                dependencia, dependenciaNombre,
                                especialidad, especialidadNombre);

                        // Entidad > Lista
                        incidencias.add(incidencia);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return incidencias;
    }

    public List<Incidencia> paginar(long indice, long longitud, int autor) {
        // SQL
        String sql = ""
                + "SELECT "
                + "incidencias.id AS id, "
                + "incidencias.titulo AS titulo, "
                + "incidencias.info AS info, "
                + "incidencias.estado AS estado, "
                + "incidencias.fecha AS fecha, "
                + "incidencias.autor AS autor, "
                + "usuarios.alias AS autor_nombre, "
                + "perfiles.nombre AS autor_perfil, "
                + "incidencias.dependencia AS dependencia, "
                + "dependencias.nombre AS dependencia_nombre, "
                + "incidencias.especialidad AS especialidad, "
                + "especialidades.nombre AS especialidad_nombre "
                + "FROM "
                + "incidencias "
                + "INNER JOIN "
                + "usuarios ON usuarios.id = incidencias.autor "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "INNER JOIN "
                + "dependencias ON dependencias.id = incidencias.dependencia "
                + "INNER JOIN "
                + "especialidades ON especialidades.id = incidencias.especialidad "
                + "WHERE "
                + "incidencias.autor = ? "
                + "LIMIT ?, ?";

        // Lista Vacía
        List<Incidencia> incidencias = new ArrayList<>();

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, autor);
                ps.setLong(2, indice);
                ps.setLong(3, longitud);

                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        String titulo = rs.getString("titulo");
                        String info = rs.getString("info");
                        int estado = rs.getInt("estado");
                        Date fecha = rs.getTimestamp("fecha");
                        String autorNombre = rs.getString("autor_nombre");
                        String autorPerfil = rs.getString("autor_perfil");
                        int dependencia = rs.getInt("dependencia");
                        String dependenciaNombre = rs.getString("dependencia_nombre");
                        int especialidad = rs.getInt("especialidad");
                        String especialidadNombre = rs.getString("especialidad_nombre");

                        // Campos > Entidad
                        Incidencia incidencia = new Incidencia(
                                id, titulo, info, estado, fecha,
                                autor, autorNombre, autorPerfil,
                                dependencia, dependenciaNombre,
                                especialidad, especialidadNombre);

                        // Entidad > Lista
                        incidencias.add(incidencia);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return incidencias;
    }

    public Incidencia consultar(int id) {
        // SQL
        String sql = ""
                + "SELECT "
                + "incidencias.id AS id, "
                + "incidencias.titulo AS titulo, "
                + "incidencias.info AS info, "
                + "incidencias.estado AS estado, "
                + "incidencias.fecha AS fecha, "
                + "incidencias.autor AS autor, "
                + "usuarios.alias AS autor_nombre, "
                + "perfiles.nombre AS autor_perfil, "
                + "incidencias.dependencia AS dependencia, "
                + "dependencias.nombre AS dependencia_nombre, "
                + "incidencias.especialidad AS especialidad, "
                + "especialidades.nombre AS especialidad_nombre "
                + "FROM "
                + "incidencias "
                + "INNER JOIN "
                + "usuarios ON usuarios.id = incidencias.autor "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "INNER JOIN "
                + "dependencias ON dependencias.id = incidencias.dependencia "
                + "INNER JOIN "
                + "especialidades ON especialidades.id = incidencias.especialidad "
                + "WHERE "
                + "incidencias.id=?";

        // Entidad
        Incidencia incidencia = null;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);

                // BD > Entidad
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Fila Actual > Campos 
                        String titulo = rs.getString("titulo");
                        String info = rs.getString("info");
                        int estado = rs.getInt("estado");
                        Date fecha = rs.getTimestamp("fecha");
                        int autor = rs.getInt("autor");
                        String autorNombre = rs.getString("autor_nombre");
                        String autorPerfil = rs.getString("autor_perfil");
                        int dependencia = rs.getInt("dependencia");
                        String dependenciaNombre = rs.getString("dependencia_nombre");
                        int especialidad = rs.getInt("especialidad");
                        String especialidadNombre = rs.getString("especialidad_nombre");

                        // Campos > Entidad
                        incidencia = new Incidencia(
                                id, titulo, info, estado, fecha,
                                autor, autorNombre, autorPerfil,
                                dependencia, dependenciaNombre,
                                especialidad, especialidadNombre);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Entidad
        return incidencia;
    }

    public Incidencia consultar(Date fecha, int autor) {
        // SQL
        String sql = ""
                + "SELECT "
                + "incidencias.id AS id, "
                + "incidencias.titulo AS titulo, "
                + "incidencias.info AS info, "
                + "incidencias.estado AS estado, "
                + "incidencias.fecha AS fecha, "
                + "incidencias.autor AS autor, "
                + "usuarios.alias AS autor_nombre, "
                + "perfiles.nombre AS autor_perfil, "
                + "incidencias.dependencia AS dependencia, "
                + "dependencias.nombre AS dependencia_nombre, "
                + "incidencias.especialidad AS especialidad, "
                + "especialidades.nombre AS especialidad_nombre "
                + "FROM "
                + "incidencias "
                + "INNER JOIN "
                + "usuarios ON usuarios.id = incidencias.autor "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "INNER JOIN "
                + "dependencias ON dependencias.id = incidencias.dependencia "
                + "INNER JOIN "
                + "especialidades ON especialidades.id = incidencias.especialidad "
                + "WHERE "
                //               + "incidencias.fecha=? "
                //                + "AND "
                + "incidencias.autor=? "
                + "ORDER BY incidencias.fecha DESC";

        // Entidad
        Incidencia incidencia = null;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
//                ps.setTimestamp(1, new java.sql.Timestamp(fecha.getTime()));
//                ps.setInt(2, autor);
                ps.setInt(1, autor);

                // BD > Entidad
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        String titulo = rs.getString("titulo");
                        String info = rs.getString("info");
                        int estado = rs.getInt("estado");
                        String autorNombre = rs.getString("autor_nombre");
                        String autorPerfil = rs.getString("autor_perfil");
                        int dependencia = rs.getInt("dependencia");
                        String dependenciaNombre = rs.getString("dependencia_nombre");
                        int especialidad = rs.getInt("especialidad");
                        String especialidadNombre = rs.getString("especialidad_nombre");

                        // Campos > Entidad
                        incidencia = new Incidencia(
                                id, titulo, info, estado, fecha,
                                autor, autorNombre, autorPerfil,
                                dependencia, dependenciaNombre,
                                especialidad, especialidadNombre);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Entidad
        return incidencia;
    }

    public boolean modificar(Incidencia incidencia) {
        // SQL
        String sql = ""
                + "UPDATE "
                + "incidencias "
                + "SET "
                + "titulo=?, info=?, estado=?, fecha=?, "
                + "autor=?, dependencia=?, especialidad=? "
                + "WHERE "
                + "id=?";

        // Número de Registros Afectados
        int numReg = 0;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setString(1, incidencia.getTitulo());
                ps.setString(2, incidencia.getInfo());
                ps.setInt(3, incidencia.getEstado());
                ps.setTimestamp(4, new java.sql.Timestamp(incidencia.getFecha().getTime()));
                ps.setInt(5, incidencia.getAutor());
                ps.setInt(6, incidencia.getDependencia());
                ps.setInt(7, incidencia.getEspecialidad());
                ps.setInt(8, incidencia.getId());

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }

    public boolean insertar(Incidencia incidencia) {
        // SQL
        String sql = ""
                + "INSERT INTO "
                + "incidencias "
                + "("
                + "titulo, info, estado, fecha, autor, "
                + "dependencia, especialidad"
                + ") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setString(1, incidencia.getTitulo());
                ps.setString(2, incidencia.getInfo());
                ps.setInt(3, incidencia.getEstado());
                ps.setTimestamp(4, new java.sql.Timestamp(incidencia.getFecha().getTime()));
                ps.setInt(5, incidencia.getAutor());
                ps.setInt(6, incidencia.getDependencia());
                ps.setInt(7, incidencia.getEspecialidad());

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }

    public boolean borrar(int id) {
        // SQL
        final String SQL = ""
                + "DELETE FROM "
                + "incidencias "
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
}
