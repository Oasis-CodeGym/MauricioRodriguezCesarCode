package org.example.cesar;

import org.jetbrains.annotations.NotNull;

import java.util.InputMismatchException;
import java.util.Scanner;
import static org.example.cesar.Menu.menuPrincipal;
import static org.example.cesar.Cipher.ALFABETO;

/**
 * Clase para validar el número de clave de desplazamiento de las letras del mensaje dentro de un rango de numeración
 * válida, acorde con el alfabeto en Español, y transformar el mensaje tanto para ocultarlo como para descifrarlo.
 **/
public class Validator {

    /**
     * El metodo esNumero valida que se ingrese un número entero, procesando datos no deseados hasta que se ingrese
     * una opción del menú entre 1 y 4.
     * @param entrada variable de tipo Scanner para capturar la entrada de datos del usuario.
     * @return opcion regresa un número entero válido, correspondiente a una de las opciones del menú.
     */
    public static int esNumero(@org.jetbrains.annotations.NotNull Scanner entrada) {
        int opcion;
        while (true) {
            try {
                // Verifica si la entrada es un número entero
                String option = entrada.nextLine().trim();
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
                entrada.next(); // Descarta el dato no valido de la entrada
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
        if(valor.matches("\\d+")) {
            num = Integer.parseInt(valor);
        }
        return num;
    }

    /**
     * Valida que el número a ingresar esté dentro de un rango de numeración valida, acorde a la posición de las
     * letras del alfabeto en Español, que incluye la letra Ñ.
     * @return clave retorna un número entero si cumple las condiciones
     */

        public static int toValid(){
            int clave;
            Scanner entrada = new Scanner(System.in);
            while(true){
                System.out.println("Digite un número válido para la clave César:");
                try{
                    // Verifica si la entrada es un número entero
                    String dato = entrada.nextLine().trim();
                    clave = checkNumber(dato);
                        if(isAValidKey(clave)) {
                            break;  // Sale del bucle si el número es válido
                        }
                } catch(InputMismatchException e){
                    System.out.println("Entrada no válida. Debes ingresar un número entero.");
                    entrada.next(); // Descarta la entrada no válida
                }
            }
            return clave;
        }

    /**
     *
     * @param key variable tipo entero que contiene el número de clave de desplazamiento de las letras en el mensaje
     * @return true/false retorna verdadero o falso si el número de la clave está o no dentro de un rango específico
     */
        public static boolean isAValidKey(int key) {
        // Key check
            if(key>0 && key< (ALFABETO.length-1)){
                return true;
            } else{
                int limite = ALFABETO.length - 2;
                System.out.println("Digite un número clave entre 1 y " + limite +"."); //A + 27 = Z
                return false;
            }
        }

//    private static final BiFunction<Character, Integer, Integer> desplazarLetra =
//        (letra, key) -> (ALPHABET.get(letra) + (key % (ALFABETO.length-1)) > (ALFABETO.length-1))//ALPHABET.lenght()
//        ? ALPHABET.get(letra) + (key % (ALFABETO.length-1)) - (ALFABETO.length-1)
//        : ALPHABET.get(letra) + (key % (ALFABETO.length-1));
            //(letra, key) -> (ALPHABET.get(letra) + (key % 27) > 27)//ALPHABET.lenght()
            //        ? ALPHABET.get(letra) + (key % 27) - 27
            //        : ALPHABET.get(letra) + (key % 27);
    //(letra, key) -> (ALPHABET.get(letra) + (key % 27) > 27)//ALPHABET.lenght()
    //        ? ALPHABET.get(letra) + (key % 27) - 27
    //        : ALPHABET.get(letra) + (key % 27);

//    public static void procesar(@NotNull String processedLine, int key, StringBuilder cifrado){
//        for (char character : processedLine.toCharArray()) {
//            int nuevaLetra;
//            if (Character.isUpperCase(character)) {
//                // Desplazamiento para letras mayúsculas
//                character = Character.toLowerCase(character);
//                nuevaLetra = desplazarLetra.apply(character, key);
//                character = Character.toUpperCase(ALFABETO[nuevaLetra]);
//            } else if (Character.isLowerCase(character)) {
                // Desplazamiento para letras minúsculas
///                nuevaLetra = desplazarLetra.apply(character, key);
//                character = ALFABETO[nuevaLetra]; //Verificación
//            } else {
//                // Desplazamiento para letras minúsculas
//                nuevaLetra = desplazarLetra.apply(character, key);
//                character = ALFABETO[nuevaLetra]; //Verificación
//            }
//            cifrado.append(character);
//        }
//    }
}
