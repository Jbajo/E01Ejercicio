package com.cice.controlador;

/**
 * @author Javier Bajo Chacon  javier.bajochacon@gmail.com
 */
public class ValidadorDNI {
    /**
     * Método validar valida un DNI llamando a calcularLetraDNI
     * @param dni a validar
     * @return true or false si es válido
     */

    public static boolean validar(String dni) {

        if(dni.matches("^([0-9]{8}[A-Z])")) {
            if(ValidadorDNI.calcularLetraDNI(dni).equals(String.valueOf(dni.charAt(8))))
                return true;
            else
                return false;
        }
        return false;
    }

    /**
     * Método calcularLetraDNI calcula la letra del DNI
     * @return letra del DNI
     */
    public static String calcularLetraDNI(String dni) {
        int DNI = Integer.parseInt(dni.substring(0,8));
        String [] letra = {"T", "R", "W", "A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};

        return letra[DNI%23];

    }

}
