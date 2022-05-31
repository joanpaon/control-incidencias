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
import org.japo.java.entities.Perfil;
import org.japo.java.libraries.UtilesServlet;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class DLLPerfil {

    // Logger
    private static final Logger logger = Logger.getLogger(DLLPerfil.class.getName());

    // DataSource
    private final DataSource ds;

    public DLLPerfil(ServletConfig config) {
        ds = UtilesServlet.obtenerDataSource(config);
    }

    public List<Perfil> listar() {
        // SQL 
        String sql = ""
                + "SELECT "
                + "* "
                + "FROM "
                + "perfiles";

        // Lista Vacía
        List<Perfil> perfiles = new ArrayList<>();

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
                        Perfil perfil = new Perfil(id, nombre, info);

                        // Entidad > Lista
                        perfiles.add(perfil);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return perfiles;
    }

    public Perfil consultar(int id) {
        // SQL
        String sql = ""
                + "SELECT "
                + "* "
                + "FROM "
                + "perfiles "
                + "WHERE "
                + "perfiles.id=" + id;

        // Perfil Buscado
        Perfil perfil = null;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Fila Actual > Campos 
                        String nombre = rs.getString("nombre");
                        String info = rs.getString("info");

                        // Campos > Entidad
                        perfil = new Perfil(id, nombre, info);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return perfil;
    }

    public boolean insertar(Perfil perfil) {
        // SQL
        String sql = ""
                + "INSERT INTO "
                + "perfiles "
                + "("
                + "id, nombre, info "
                + ") "
                + "VALUES (?, ?, ?)";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, perfil.getId());
                ps.setString(2, perfil.getNombre());
                ps.setString(3, perfil.getInfo());

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }

    public boolean borrar(int id) {
        // SQL
        final String SQL = ""
                + "DELETE FROM "
                + "perfiles "
                + "WHERE "
                + "id=?";

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

    public boolean modificar(Perfil perfil) {
        // SQL
        final String SQL = ""
                + "UPDATE "
                + "perfiles "
                + "SET "
                + "nombre=?, info=? "
                + "WHERE id=?";

        // Número de Registros Afectados
        int numReg = 0;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setString(1, perfil.getNombre());
                ps.setString(2, perfil.getInfo());
                ps.setInt(3, perfil.getId());

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }

    public Long contar() {
        // Número de Filas
        long filas = 0;

        // SQL
        String sql = ""
                + "SELECT "
                + "COUNT(*) "
                + "FROM "
                + "perfiles";

        // Obtención del Contexto
        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        filas = rs.getLong(1);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: Filas Contadas
        return filas;
    }

    public List<Perfil> paginar(long indice, long longitud) {
        // SQL
        String sql = ""
                + "SELECT "
                + "* "
                + "FROM "
                + "perfiles "
                + "LIMIT ?, ?";

        // Lista Vacía
        List<Perfil> perfiles = new ArrayList<>();

        // Obtención del Contexto
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
                        Perfil perfil = new Perfil(id, nombre, info);

                        // Entidad > Lista
                        perfiles.add(perfil);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return perfiles;
    }
}
