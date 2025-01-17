package org.example.cesar;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * Clase para validar el número de clave de desplazamiento de las letras del mensaje dentro de un rango de numeración
 * válida, acorde con el alfabeto en Español, y transformar el mensaje tanto para ocultarlo como para descifrarlo.
 **/
public class Validator {
    /**
     * Arreglo de tipo char que contiene las letras del alfabeto en español incluyendo la letra ñ
     */
    public static final char[] ALFABETO = {' ','a','b','c','d','e','f','g','h','i',
                                            'j','k','l','m','n','ñ','o','p','q',
                                            'r','s','t','u','v','w','x','y','z'};
    /**
     * Arreglo de tipo HashMap que contiene las letras del alfabeto en español como clave y un entero asociado al número
     * de posición correspondiente
     */
    public static final HashMap<Character,Integer> ALPHABET = new HashMap<>();

    static {
        ALPHABET.put('a',1);
        ALPHABET.put('b',2);
        ALPHABET.put('c',3);
        ALPHABET.put('d',4);
        ALPHABET.put('e',5);
        ALPHABET.put('f',6);
        ALPHABET.put('g',7);
        ALPHABET.put('h',8);
        ALPHABET.put('i',9);
        ALPHABET.put('j',10);
        ALPHABET.put('k',11);
        ALPHABET.put('l',12);
        ALPHABET.put('m',13);
        ALPHABET.put('n',14);
        ALPHABET.put('ñ',15);
        ALPHABET.put('o',16);
        ALPHABET.put('p',17);
        ALPHABET.put('q',18);
        ALPHABET.put('r',19);
        ALPHABET.put('s',20);
        ALPHABET.put('t',21);
        ALPHABET.put('u',22);
        ALPHABET.put('v',23);
        ALPHABET.put('w',24);
        ALPHABET.put('x',25);
        ALPHABET.put('y',26);
        ALPHABET.put('z',27);
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
            if(key>0 && key<27){
                return true;
            } else{
                System.out.println("Digite un número clave entre 1 y 26."); //A + 27 = Z
                return false;
            }
        }

    /**
     * Verifica si el archivo que contiene el mensaje a procesar existe
     * @param filePath variable tipo Path que contiene la ubicación y nombre del archivo
     * @return true/false si el archivo existe o no
     */
    public static boolean isFileExists(Path filePath) {
        // Check if the file exists
        return Files.exists(filePath);
    }

    /**
     * Calcula el número de posición de las nuevas letras para cifrar/descifrar el mensaje, a partir del número de clave
     * ingresado por el usuario, asignando las nuevas letras correspondiente a la posición del arreglo creado del
     * alfabeto
     */
    private static final BiFunction<Character, Integer, Integer> desplazarLetra =
            (letra, key) -> (ALPHABET.get(letra) + (key % 27) > 27)
                    ? ALPHABET.get(letra) + (key % 27) - 27
                    : ALPHABET.get(letra) + (key % 27);

    /**
     * Proceso que se encarga de cifrar/descifrar el mensaje
     * @param processedLine variable tipo string que contiene una línea del mensaje a cifrar/descifrar
     * @param key variable tipo entero que contiene el número de clave de desplazamiento de las letras en el mensaje
     * @param cifrado variable tipo StringBuilder que contiene la nueva linea del mensaje cifrado/descifrado
     */
    public static void procesar(@NotNull String processedLine, int key, StringBuilder cifrado){
        for (char character : processedLine.toCharArray()) {
            int nuevaLetra;
            if (Character.isUpperCase(character)) {
                // Desplazamiento para letras mayúsculas
                character = Character.toLowerCase(character);
                nuevaLetra = desplazarLetra.apply(character, key);
                character = Character.toUpperCase(ALFABETO[nuevaLetra]);
            } else if (Character.isLowerCase(character)) {
                // Desplazamiento para letras minúsculas
                nuevaLetra = desplazarLetra.apply(character, key);
                character = ALFABETO[nuevaLetra]; //Verificación
            }
            cifrado.append(character);
        }
    }
}
