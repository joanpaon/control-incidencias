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
import org.japo.java.entities.Dependencia;
import org.japo.java.libraries.UtilesServlet;

/**
 *
 * @author Joanpaon
 */
public final class DLLDependencia {

    // Bitácora
    private static final Logger logger = Logger.getLogger(DLLDependencia.class.getName());

    // DataSource
    private final DataSource ds;

    public DLLDependencia(ServletConfig config) {
        ds = UtilesServlet.obtenerDataSource(config);
    }

    public List<Dependencia> listar() {
        // SQL
        String sql = ""
                + "SELECT "
                + "* "
                + "FROM "
                + "dependencias";

        // Lista Vacía
        List<Dependencia> dependencias = new ArrayList<>();

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
                        Dependencia dependencia = new Dependencia(
                                id, nombre, info);

                        // Entidad > Lista
                        dependencias.add(dependencia);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return dependencias;
    }

    public Long contar() {
        // Número de Filas
        long filas = 0;

        // SQL
        String sql = ""
                + "SELECT "
                + "COUNT(*) "
                + "FROM "
                + "dependencias";

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

    public List<Dependencia> paginar(long indice, long longitud) {
        // SQL
        String sql = ""
                + "SELECT "
                + "* "
                + "FROM "
                + "dependencias "
                + "LIMIT ?, ?";

        // Lista Vacía
        List<Dependencia> dependencias = new ArrayList<>();

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
                        Dependencia dependencia = new Dependencia(
                                id, nombre, info);

                        // Entidad > Lista
                        dependencias.add(dependencia);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return dependencias;
    }
}
