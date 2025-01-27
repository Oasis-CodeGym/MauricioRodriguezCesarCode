package org.example.cesar;

import org.jetbrains.annotations.NotNull;

import java.util.InputMismatchException;

import static org.example.cesar.Cipher.ALFABETO;
import static org.example.cesar.Menu.getEntrada;
import static org.example.cesar.Menu.menuPrincipal;
//import static org.example.cesar.Cipher.ALFABETO;

/**
 * Clase para validar el número de clave de desplazamiento de las letras del mensaje dentro de un rango de numeración
 * válida, acorde con el alfabeto en Español, y transformar el mensaje tanto para ocultarlo como para descifrarlo.
 **/
public class Validator {

    private static final String[] validExtensions = {"txt", "doc"};

    private static String rutaArchivo;

    public static String getRutaArchivo() {
        return rutaArchivo;
    }

    public static void setRutaArchivo(String rutaArchivo) {
        Validator.rutaArchivo = rutaArchivo;
    }

    /**
     * El metodo esNumero valida que se ingrese un número entero, procesando datos no deseados hasta que se ingrese
     * una opción del menú entre 1 y 4.
     * getEntrada() variable de tipo Scanner para capturar la entrada de datos del número de opción ql usuario.
     * @return opcion regresa un número entero válido, correspondiente a una de las opciones del menú.
     */
//    public static int esNumero(@org.jetbrains.annotations.NotNull Scanner entrada) {
    public static int esNumero() {
        int opcion;
        while (true) {
            try {
                // Verifica si la entrada es un número entero
                String option = getEntrada().nextLine().trim();
                opcion = checkNumber(option);
                if (opcion > 0 && opcion < 5) {
                    break;  // Sale del bucle si el número es válido
                }
                else{
                    System.out.println("Ingrese un número válido.");
                    menuPrincipal();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Debes ingresar un número entero.");
                //entrada.next(); // Descarta el dato no valido de la entrada
                getEntrada().next(); // Descarta el dato no valido de la entrada
                menuPrincipal();
            }
        }
        return opcion;
    }

    /**
     * Verifica si la entrada ingresada por el usuario es un número entero
     * @param valor variable que contiene un dato ingresado por el usario
     * @return num retorna un número entero válido luego de su verificación
     */
    public static int checkNumber(@NotNull String valor){
        int num = 0;
        try{
            if(valor.matches("\\d+")) {
                num = Integer.parseInt(valor);
            }
        } catch (NumberFormatException e){
            System.out.println("Se excede el límite del valor numérico definido.");
        }
        return num;
    }

    /**
     * Valida que el número a ingresar esté dentro de un rango de numeración valida, acorde a la posición de las
     * letras del alfabeto en Español, que incluye la letra Ñ.
     * @return clave retorna un número entero si cumple las condiciones
     */

     private static int toValid(){
         int clave;
         while(true){
             System.out.println("Digite un número válido para la clave César:");
             try{
                 // Verifica si la entrada es un número entero
                 String dato = getEntrada().nextLine().trim();
                 clave = checkNumber(dato);
                 if(isAValidKey(clave)) {
                     break;  // Sale del bucle si el número es válido
                 }
             } catch(InputMismatchException e){
                 System.out.println("Entrada no válida. Debes ingresar un número entero, no negativo.");
                 getEntrada().next(); // Descarta la entrada no válida
             }
         }
         return clave;
     }

        public static int setToValid(){
            return toValid();
        }
    /**
     *
     * @param key variable tipo entero que contiene el número de clave de desplazamiento de las letras en el mensaje
     * @return true/false retorna verdadero o falso si el número de la clave está o no dentro de un rango específico
     */
     public static boolean isAValidKey(int key) {
         int limite = Integer.MAX_VALUE;
//        if(key>0 && key< (ALFABETO.length-1)){
         if(key > 0 && key < limite && (key % (ALFABETO.length-1) != 0)){
            return true;
         } else{
//        int limite = ALFABETO.length - 2;
//        System.out.println("Digite un número clave entre 1 y " + limite +".");
             System.out.println("Digite un número de clave válido, dentro del valor límite de 1 a " + limite +".");
             return false;
         }
     }

    /**
     * Valida que la extensión del archivo que se crea, exista o sea valido dentro del listado de excepciones
     * en el arreglo validExtensions
     * @param nombreArchivo variable que contiene la ruta con el nombre del archivo con la extensión creada
     * @return false si la extensión no es válida o no existe
     */
    private static boolean validExtension(String nombreArchivo) {
        int indicePunto = nombreArchivo.lastIndexOf(".");
        if (indicePunto == -1 || indicePunto == nombreArchivo.length() - 1){
            return false; // No tiene extensión válida
        }
        String extension = nombreArchivo.substring(indicePunto + 1).toLowerCase();
        for (String ext : validExtensions) {
            if (extension.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    public static boolean callValidExtension(){
        return validExtension(rutaArchivo);
    }
}
