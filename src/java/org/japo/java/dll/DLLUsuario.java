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

import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.sql.DataSource;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesPerfil;
import org.japo.java.libraries.UtilesServlet;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class DLLUsuario {

    // Bitácora
    private static final Logger logger = Logger.getLogger(DLLUsuario.class.getName());

    // DataSource
    private final DataSource ds;

    public DLLUsuario(ServletConfig config) {
        ds = UtilesServlet.obtenerDataSource(config);
    }

    public List<Usuario> listar() {
        // SQL
        String sql = ""
                + "SELECT "
                + "usuarios.id AS id, "
                + "usuarios.user AS user, "
                + "usuarios.pass AS pass, "
                + "usuarios.avatar AS avatar, "
                + "usuarios.perfil AS perfil, "
                + "perfiles.nombre AS perfil_info "
                + "FROM "
                + "usuarios "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil ";

        // Lista Vacía
        List<Usuario> usuarios = new ArrayList<>();

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                

                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        String user = rs.getString("user");
                        String pass = rs.getString("pass");
                        String avatar = rs.getString("avatar");
                        int perfil = rs.getInt("perfil");
                        String perfilInfo = rs.getString("perfil_info");

                        // Campos > Entidad
                        Usuario usuario = new Usuario(id, user, pass, avatar, perfil, perfilInfo);

                        // Entidad > Lista
                        usuarios.add(usuario);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return usuarios;
    }

    public List<Usuario> listarUser() {
        // SQL
        String sql = ""
                + "SELECT "
                + "usuarios.id AS id, "
                + "usuarios.user AS user, "
                + "usuarios.pass AS pass, "
                + "usuarios.avatar AS avatar, "
                + "usuarios.perfil AS perfil, "
                + "perfiles.nombre AS perfil_info "
                + "FROM "
                + "usuarios "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "WHERE "
                + "usuarios.perfil < ?";

        // Lista Vacía
        List<Usuario> usuarios = new ArrayList<>();

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, UtilesPerfil.ADMIN_CODE);

                // Ejecutar Sentencia
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        String user = rs.getString("user");
                        String pass = rs.getString("pass");
                        String avatar = rs.getString("avatar");
                        int perfil = rs.getInt("perfil");
                        String perfilInfo = rs.getString("perfil_info");

                        // Campos > Entidad
                        Usuario usuario = new Usuario(id, user, pass, avatar, perfil, perfilInfo);

                        // Entidad > Lista
                        usuarios.add(usuario);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return usuarios;
    }

    public Usuario consultar(int id) {
        // SQL
        String sql = ""
                + "SELECT "
                + "usuarios.id AS id, "
                + "usuarios.user AS user, "
                + "usuarios.pass AS pass, "
                + "usuarios.avatar AS avatar, "
                + "usuarios.perfil AS perfil, "
                + "perfiles.nombre AS perfil_info "
                + "FROM "
                + "usuarios "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "WHERE usuarios.id=?";

        // Entidad
        Usuario usuario = null;

        try {
            try ( Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);

                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Fila Actual > Campos 
                        String user = rs.getString("user");
                        String pass = rs.getString("pass");
                        String avatar = rs.getString("avatar");
                        int perfil = rs.getInt("perfil");
                        String perfilInfo = rs.getString("perfil_info");

                        // Campos > Entidad
                        usuario = new Usuario(id, user, pass, avatar, perfil, perfilInfo);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Entidad
        return usuario;
    }

    public Usuario consultar(String user) {
        // SQL
        String sql = ""
                + "SELECT "
                + "usuarios.id AS id, "
                + "usuarios.user AS user, "
                + "usuarios.pass AS pass, "
                + "usuarios.avatar AS avatar, "
                + "usuarios.perfil AS perfil, "
                + "perfiles.nombre AS perfil_info "
                + "FROM "
                + "usuarios "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "WHERE "
                + "usuarios.user=?";

        // Entidad
        Usuario usuario = null;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setString(1, user);

                // BD > Lista de Entidades
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        String pass = rs.getString("pass");
                        String avatar = rs.getString("avatar");
                        int perfil = rs.getInt("perfil");
                        String perfilInfo = rs.getString("perfil_info");

                        // Campos > Entidad
                        usuario = new Usuario(id, user, pass, avatar, perfil, perfilInfo);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Entidad
        return usuario;
    }

    public boolean insertar(Usuario usuario) {
        // SQL
        String sql = ""
                + "INSERT INTO "
                + "usuarios "
                + "("
                + "user, pass, avatar, perfil"
                + ") "
                + "VALUES (?, ?, ?, ?)";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setString(1, usuario.getUser());
                ps.setString(2, usuario.getPass());
                ps.setString(3, usuario.getAvatar());
                ps.setInt(4, usuario.getPerfil());

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
        String sql = ""
                + "DELETE FROM usuarios "
                + "WHERE id=?";

        // Número de registros afectados
        int numReg = 0;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }

    public boolean modificar(Usuario usuario) {
        // SQL
        String sql = ""
                + "UPDATE "
                + "usuarios "
                + "SET "
                + "user=?, pass=?, avatar=?, perfil=? "
                + "WHERE "
                + "id=?";

        // Número de Registros Afectados
        int numReg = 0;

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setString(1, usuario.getUser());
                ps.setString(2, usuario.getPass());
                ps.setString(3, usuario.getAvatar());
                ps.setInt(4, usuario.getPerfil());
                ps.setInt(5, usuario.getId());

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (SQLException | NullPointerException ex) {
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
                + "usuarios";

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
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

    public Long contarUser() {
        // Número de Filas
        long filas = 0;

        // SQL
        String sql = ""
                + "SELECT "
                + "COUNT(*) "
                + "FROM "
                + "usuarios "
                + "WHERE "
                + "usuarios.perfil < ?";

        try {
            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, UtilesPerfil.ADMIN_CODE);

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

    public List<Usuario> paginar(long indice, int longitud) {
        // SQL
        String sql = ""
                + "SELECT "
                + "usuarios.id AS id, "
                + "usuarios.user AS user, "
                + "usuarios.pass AS pass, "
                + "usuarios.avatar AS avatar, "
                + "usuarios.perfil AS perfil, "
                + "perfiles.nombre AS perfil_info "
                + "FROM "
                + "usuarios "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "LIMIT ?, ?";

        // Lista Vacía
        List<Usuario> usuarios = new ArrayList<>();

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
                        String user = rs.getString("user");
                        String pass = rs.getString("pass");
                        String avatar = rs.getString("avatar");
                        int perfil = rs.getInt("perfil");
                        String perfilInfo = rs.getString("perfil_info");

                        // Campos > Entidad
                        Usuario usuario = new Usuario(id, user, pass, avatar, perfil, perfilInfo);

                        // Entidad > Lista
                        usuarios.add(usuario);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return usuarios;
    }
}
