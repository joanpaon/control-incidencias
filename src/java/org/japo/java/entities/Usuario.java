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
package org.japo.java.entities;

import java.io.Serializable;
import java.util.Objects;
import org.japo.java.libraries.UtilesPerfil;
import org.japo.java.libraries.UtilesUsuario;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
public final class Usuario implements Serializable {

    // Campos
    private int id;
    private String user;
    private String pass;
    private String avatar;
    private int perfil;
    private String perfilInfo;

    // Constructor Predeterminado
    public Usuario() {
        id = UtilesUsuario.DEF_ID;
        user = UtilesUsuario.DEF_USER;
        pass = UtilesUsuario.DEF_PASS;
        avatar = UtilesUsuario.DEF_AVATAR;
        perfil = UtilesPerfil.DEF_ID;
        perfilInfo = UtilesPerfil.DEF_INFO;
    }

    // Constructor Parametrizado
    public Usuario(int id, String user, String pass,
            String avatar, int perfil, String perfilInfo) {
        if (UtilesUsuario.validarId(id)) {
            this.id = id;
        } else {
            this.id = UtilesUsuario.DEF_ID;
        }

        if (UtilesUsuario.validarUser(user)) {
            this.user = user;
        } else {
            this.user = UtilesUsuario.DEF_USER;
        }

        if (UtilesUsuario.validarPass(pass)) {
            this.pass = pass;
        } else {
            this.pass = UtilesUsuario.DEF_PASS;
        }

        if (UtilesUsuario.validarAvatar(avatar)) {
            this.avatar = avatar;
        } else {
            this.avatar = UtilesUsuario.DEF_AVATAR;
        }

        if (UtilesPerfil.validarId(perfil)) {
            this.perfil = perfil;
        } else {
            this.perfil = UtilesPerfil.DEF_ID;
        }

        if (UtilesPerfil.validarInfo(perfilInfo)) {
            this.perfilInfo = perfilInfo;
        } else {
            this.perfilInfo = UtilesPerfil.DEF_INFO;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (UtilesUsuario.validarId(id)) {
            this.id = id;
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        if (UtilesUsuario.validarUser(user)) {
            this.user = user;
        }
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (UtilesUsuario.validarPass(pass)) {
            this.pass = pass;
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        if (UtilesUsuario.validarAvatar(avatar)) {
            this.avatar = avatar;
        }
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        if (UtilesPerfil.validarId(perfil)) {
            this.perfil = perfil;
        }
    }

    public String getPerfilInfo() {
        return perfilInfo;
    }

    public void setPerfilInfo(String perfilInfo) {
        if (UtilesPerfil.validarInfo(perfilInfo)) {
            this.perfilInfo = perfilInfo;
        }
    }

    @Override
    public boolean equals(Object o) {
        // Semáforo
        boolean testOK = false;

        // Validación Parámetro
        if (o instanceof Usuario u) {
            // Validación Usuario
            testOK = true
                    && u.getId() == id
                    && u.getUser().equals(user)
                    && u.getPass().equals(pass)
                    && u.getAvatar().equals(avatar)
                    && u.getPerfil() == perfil
                    && u.getPerfilInfo().equals(perfilInfo);
        }

        // Retorno: Semáforo
        return testOK;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.user);
        hash = 53 * hash + Objects.hashCode(this.pass);
        hash = 53 * hash + Objects.hashCode(this.avatar);
        hash = 53 * hash + this.perfil;
        hash = 53 * hash + Objects.hashCode(this.perfilInfo);
        return hash;
    }
}
