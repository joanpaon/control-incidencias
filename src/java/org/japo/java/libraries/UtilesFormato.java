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

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class UtilesFormato {

    private UtilesFormato() {
    }

    public static final String cambiarKebab2Camel(String cmd) {
        // String > String[]
        String[] items = cmd.split("-");

        // Constructor de String
        StringBuilder sb = new StringBuilder();

        // Bucle
        for (String item : items) {
            sb.append(capitalizar(item));
        }

        // Retorno
        return sb.toString();
    }

    public static final String capitalizar(String item) {
        if (item != null) {
            // Item > Inicial (Mays)
            char head = Character.toUpperCase(item.charAt(0));

            // Item > Resto (Mins)
            String tail = item.substring(1).toLowerCase();

            // head + tail > item
            item = head + tail;
        }

        return item;
    }
}
