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
package org.japo.java.pll.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.japo.java.libraries.UtilesComando;
import org.japo.java.libraries.UtilesEstatico;
import org.japo.java.libraries.UtilesServicio;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
@WebServlet(
        name = "Controller",
        urlPatterns = {"", "/public/*"},
        initParams = {
            @WebInitParam(name = "author", value = "JAPO Labs"),
            @WebInitParam(name = "version", value = "1.0.0")
        })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public final class Controller extends HttpServlet {

    private void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Análisis de la Petición
        if (request.getPathInfo().equals("/")) {
            // Recursos Dinámicos
            if (request.getParameter("svc") != null) {
                // Petición de Servicio: XML | JSON
                UtilesServicio.procesar(getServletConfig(), request, response);
            } else if (request.getParameter("cmd") != null) {
                // Peticion de Comando: Vista
                UtilesComando.procesar(getServletConfig(), request, response);
            } else {
                // Petición por Defecto - Vista Bienvenida
                response.sendRedirect("?cmd=visita-landing");
            }
        } else {
            // Recursos Estáticos
            UtilesEstatico.procesar(getServletConfig(), request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
