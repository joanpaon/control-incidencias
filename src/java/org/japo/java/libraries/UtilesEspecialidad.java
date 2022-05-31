package org.japo.java.libraries;

public final class UtilesEspecialidad {

    // Valores Predeterminados
    public static final int DEF_ID = 0;
    public static final String DEF_NOMBRE = "Indefinida";
    public static final String DEF_INFO = "Especialidad Indefinida";

    // Expresiones Regulares
    public static final String REG_NOMBRE = "[\\w áéíóúüñÁÉÍÓÚÜÑçÇ]{3,50}";
    public static final String REG_INFO = "[\\w áéíóúüñÁÉÍÓÚÜÑçÇ]{3,255}";

    private UtilesEspecialidad() {
    }

    public static final boolean validarId(int id) {
        return id >= DEF_ID;
    }

    public static final boolean validarNombre(String nombre) {
        return nombre.matches(REG_NOMBRE);
    }

    public static final boolean validarInfo(String info) {
        return info.matches(REG_INFO);
    }
}
