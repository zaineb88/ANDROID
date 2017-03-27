package com.val.inflando;

/**
 * Created by vale on 11/05/16.
 */
public class StringUtil {

    public static String mensajeSalida (String nombre)
    {
        String mensaje = null;

            mensaje = "El nombre tiene " +nombre.length() + " letras";

        return mensaje;
    }
}
