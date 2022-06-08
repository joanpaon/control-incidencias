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
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.sql.DataSource;
import org.japo.java.entities.Especialidad;
import org.japo.java.libraries.UtilesServlet;

/**
 *
 * @author Joanpaon
 */
public final class DLLEspecialidad {

    // Bitácora
    private static final Logger logger = Logger.getLogger(DLLEspecialidad.class.getName());

    // DataSource
    private final DataSource ds;

    public DLLEspecialidad(ServletConfig config) {
        ds = UtilesServlet.obtenerDataSource(config);
    }

    public boolean borrar(int id) {
        // SQL
        final String SQL = ""
                + "DELETE FROM "
                + "especialidades "
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

    public Especialidad consultar(int id) {
        // SQL
        String sql = ""
                + "SELECT "
                + "* "
                + "FROM especialidades "
                + "WHERE "
                + "especialidades.id=?";

        // Entidad
        Especialidad especialidad = null;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);

                // BD > Entidad
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Fila Actual > Campos 
                        String nombre = rs.getString("nombre");
                        String info = rs.getString("info");

                        // Campos > Entidad
                        especialidad = new Especialidad(id, nombre, info);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Entidad
        return especialidad;
    }

    public Long contar() {
        // Número de Filas
        long filas = 0;

        // SQL
        String sql = ""
                + "SELECT "
                + "COUNT(*) "
                + "FROM "
                + "especialidades";

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

    public boolean insertar(Especialidad especialidad) {
        // SQL
        final String SQL = ""
                + "INSERT INTO "
                + "especialidades "
                + "("
                + "nombre, info"
                + ") "
                + "VALUES (?, ?)";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setString(1, especialidad.getNombre());
                ps.setString(2, especialidad.getInfo());

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }

    public List<Especialidad> listar() {
        // SQL
        String sql = ""
                + "SELECT "
                + "* "
                + "FROM "
                + "especialidades";

        // Lista Vacía
        List<Especialidad> especialidades = new ArrayList<>();

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String info = rs.getString("info");

                        // Campos > Entidad
                        Especialidad especialidad = new Especialidad(
                                id, nombre, info);

                        // Entidad > Lista
                        especialidades.add(especialidad);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return especialidades;
    }

    public List<Especialidad> paginar(long indice, long longitud) {
        // SQL
        String sql = ""
                + "SELECT "
                + "* "
                + "FROM "
                + "especialidades "
                + "LIMIT ?, ?";

        // Lista Vacía
        List<Especialidad> especialidades = new ArrayList<>();

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
                        String nombre = rs.getString("nombre");
                        String info = rs.getString("info");

                        // Campos > Entidad
                        Especialidad especialidad = new Especialidad(
                                id, nombre, info);

                        // Entidad > Lista
                        especialidades.add(especialidad);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return especialidades;
    }

    public boolean modificar(Especialidad especialidad) {
        // SQL
        final String SQL = ""
                + "UPDATE "
                + "especialidades "
                + "SET "
                + "nombre=?, info=? "
                + "WHERE "
                + "id=?";

        // Número de Registros Afectados
        int numReg = 0;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setString(1, especialidad.getNombre());
                ps.setString(2, especialidad.getInfo());
                ps.setInt(3, especialidad.getId());

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
