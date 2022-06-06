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
package org.japo.java.bll.commands;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesPerfil;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public abstract class Command implements ICommand {

    // Referencias
    protected ServletConfig config;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    // Inicialización del Comando
    @Override
    public final void init(
            ServletConfig config,
            HttpServletRequest request,
            HttpServletResponse response) {
        this.config = config;
        this.request = request;
        this.response = response;
    }

    // Redirección de la Salida
    protected final void forward(String out) throws ServletException, IOException {
        // Validar Tipo de Salida
        if (out.startsWith("controller")) {
            // Elimina el prefijo
            out = out.replace("controller", "");

            // Redirección a Comando
            response.sendRedirect(out);
        } else {
            // Config > Views Path
            String ruta = config.getServletContext().getInitParameter("ruta-vistas");

            // Nombre Comando ( Petición ) > Nombre Vista ( Respuesta )
            out = String.format("%s/%s.jsp", ruta, out);

            // Contexto + Nombre Vista > Despachador
            RequestDispatcher dispatcher = request.getRequestDispatcher(out);

            // Despachador + Petición + Respuesta > Redirección a Vista
            dispatcher.forward(request, response);
        }
    }

    protected final boolean validarSesion(HttpServletRequest request) {
        // Semáforo
        boolean checkOK = false;

        // Request > Sesión
        HttpSession sesion = request.getSession(false);

        // Validación
        if (sesion != null) {
            // Sesion > Usuario
            Object usuario = sesion.getAttribute("usuario");

            // Valida Usuario
            checkOK = usuario instanceof Usuario;

            // Para validar la sesión de forma más afinada
            // se deberia comprobar que el usuario que refiere la sesión
            // está realmente registrado en la BD
            // y que en ese registro consta que el usuario ha iniciado
            // una sesión con el mismo id y ip que informa la sesión
            // y que el tiempo en que se inició no supera el tiempo
            // máximo que se considere
        }

        // Retorno: true | false
        return checkOK;
    }

    public static final boolean validarAccesoDevel(HttpSession sesion) {
        // Semáforo
        boolean checkOK;

        try {
            // Sesión > Usuario
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");

            // Usuario > Perfil
            int perfil = usuario.getPerfil();

            // Validar Perfil Desarrollador
            checkOK = perfil >= UtilesPerfil.DEVEL_CODE;
        } catch (NullPointerException e) {
            checkOK = false;
        }

        // Retorno: true | false
        return checkOK;
    }

    public static final boolean validarAccesoAdmin(HttpSession sesion) {
        // Semáforo
        boolean checkOK;

        try {
            // Sesión > Usuario
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");

            // Usuario > Perfil
            int perfil = usuario.getPerfil();

            // Validar Perfil Desarrollador
            checkOK = perfil >= UtilesPerfil.ADMIN_CODE;
        } catch (NullPointerException e) {
            checkOK = false;
        }

        // Retorno: true | false
        return checkOK;
    }

    public static final boolean validarAccesoBasic(HttpSession sesion) {
        // Semáforo
        boolean checkOK;

        try {
            // Sesión > Usuario
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");

            // Usuario > Perfil
            int perfil = usuario.getPerfil();

            // Validar Perfil Desarrollador
            checkOK = perfil >= UtilesPerfil.BASIC_CODE;
        } catch (NullPointerException e) {
            checkOK = false;
        }

        // Retorno: true | false
        return checkOK;
    }
}
