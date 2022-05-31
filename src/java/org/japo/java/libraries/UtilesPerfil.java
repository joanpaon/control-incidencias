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
package org.japo.java.libraries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.japo.java.dll.DLLPerfil;
import org.japo.java.entities.Perfil;
import org.japo.java.entities.Usuario;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
public final class UtilesPerfil {

    // Códigos de Perfiles Básicos
    public static final int VISIT_CODE = 0;
    public static final int BASIC_CODE = 100;
    public static final int ADMIN_CODE = 800;
    public static final int DEVEL_CODE = 900;

    // Nombres de Perfiles Básicos
    public static final String VISIT_NAME = "Visitante";
    public static final String BASIC_NAME = "Usuario";
    public static final String ADMIN_NAME = "Administrador";
    public static final String DEVEL_NAME = "Desarrollador";

    // Descripción de Perfiles Básicos
    public static final String VISIT_INFO = "Usuario NO Identificado";
    public static final String BASIC_INFO = "Usuario Identificado";
    public static final String ADMIN_INFO = "Usuario con Derechos Administrativos";
    public static final String DEVEL_INFO = "Usuario con Derechos de Desarrollo";

    // Valores por Defecto
    public static final int DEF_ID = 0;
    public static final String DEF_NOMBRE = VISIT_NAME;
    public static final String DEF_INFO = VISIT_INFO;

    // Expresiones regulares
    public static final String REG_NOMBRE = "[\\wáéíóúüñÁÉÍÓÚÜÑ]{6,20}";
    public static final String REG_INFO = "[\\wáéíóúüñÁÉÍÓÚÜÑ\\- ]{6,50}";

    private UtilesPerfil() {
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
                throw new IOException("ID de Perfil Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Perfil Incorrecta");
        }

        // Retorno
        return id;
    }

    public static final String obtenerNombreRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > Nombre
        String nombre = request.getParameter("nombre");

        // Validar User
        if (!validarNombre(nombre)) {
            throw new IOException("Nombre de Perfil Incorrecto");
        }

        // Retorno: Nombre
        return nombre;
    }

    public static final String obtenerInfoRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > Info
        String info = request.getParameter("info");

        // Validar User
        if (!validarInfo(info)) {
            throw new IOException("Info de Perfil Incorrecta");
        }

        // Retorno: Nombre
        return info;
    }

    public static final List<Perfil> listarPerfilesUsuario(
            ServletConfig config,
            HttpServletRequest request) {
        // BD > Lista de Perfiles
        List<Perfil> perfiles;

        // Request > Session
        HttpSession sesion = request.getSession(false);

        // Sesión > Usuario
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        // Capas de Datos
        DLLPerfil dllPerfil = new DLLPerfil(config);

        // Determinar Perfil Usuario
        switch (usuario.getPerfil()) {
            case DEVEL_CODE:
                // BD > Lista de Pefiles
                perfiles = dllPerfil.listar();
                break;
            case ADMIN_CODE:
                // BD > Lista de Pefiles
                perfiles = dllPerfil.listar();
                break;
            case BASIC_CODE:
            default:
                // Usuario Actual (Únicamente) > Lista de Usuarios
                Perfil perfil = dllPerfil.consultar(usuario.getPerfil());
                perfiles = new ArrayList<>();
                perfiles.add(perfil);
        }

        // Retorno: Lista de usuarios visibles por el perfil
        return perfiles;
    }

    public static final Perfil consultarPerfilIdRequest(
            ServletConfig config,
            HttpServletRequest request)
            throws IOException {
        // Capas de Negocio
        DLLPerfil dllPerfil = new DLLPerfil(config);

        // Request > Id Perfil
        int id = obtenerIdRequest(request);

        // Retorno: Perfil
        return dllPerfil.consultar(id);
    }

    public static final boolean validarId(int id) {
        return id >= DEF_ID;
    }

    public static final boolean validarNombre(String nombre) {
        return UtilesValidacion.validarDato(nombre, REG_NOMBRE);
    }

    public static boolean validarInfo(String info) {
        return UtilesValidacion.validarDato(info, REG_INFO);
    }
}
