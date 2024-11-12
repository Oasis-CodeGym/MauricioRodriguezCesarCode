package org.example.cesar;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Validator {

    public static final char[] ALFABETO = {' ','a','b','c','d','e','f','g','h','i',
                                            'j','k','l','m','n','ñ','o','p','q',
                                            'r','s','t','u','v','w','x','y','z'};
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

    //Verificar si es número
    public static int checkNumber(String option){
        int opcion = 0;
        if(option.matches("\\d+")) {
            opcion = Integer.parseInt(option);
        }
        return opcion;
    }

    //Leer entrada de datos para validar
        public static int toValid(){
            int clave;
            Scanner entrada = new Scanner(System.in);
            while(true){
                System.out.println("Digite un número válido para la clave César:");
                try{
                    // Verifica si la entrada es un número entero
                    String dato = entrada.nextLine().trim();
                    clave = checkNumber(dato);
//                    if(dato.matches("\\d+")) {
//                        clave = Integer.parseInt(dato);
                        if(isAValidKey(clave)) {
                            break;  // Sale del bucle si el número es válido
                        }
//                    }
                } catch(InputMismatchException e){
                    System.out.println("Entrada no válida. Debes ingresar un número entero.");
                    entrada.next(); // Descarta la entrada no válida
                }
            }
//            entrada.close();
            return clave;
        }

        public static boolean isAValidKey(int key) {
        // Key check
            if(key>0 && key<27){
                return true;
            } else{
                System.out.println("Digite un número clave entre 1 y 26.");
                return false;
            }
        }

    public static boolean isFileExists(Path filePath) {
        // Check if the file exists
        return Files.exists(filePath);
    }

    private static final BiFunction<Character, Integer, Integer> desplazarLetra =
            (letra, key) -> (ALPHABET.get(letra) + (key % 27) > 27)
                    ? ALPHABET.get(letra) + (key % 27) - 27
                    : ALPHABET.get(letra) + (key % 27);

    public static void procesar(String processedLine, int key, StringBuilder cifrado){
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
