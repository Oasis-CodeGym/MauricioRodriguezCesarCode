package org.example.cesar;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.function.BiFunction;

public class Cipher {

    /**
     * Arreglo de tipo char que contiene las letras del alfabeto en español incluyendo la letra ñ
     * y algunos carácteres especiales utilizados en escritura
     */
    public static final char[] ALFABETO = {'ª','a','b','c','d','e','f','g','h','i',
            'j','k','l','m','n','ñ','o','p','q','r',
            's','t','u','v','w','x','y','z','á','é',
            'í','ó','ú',' ','.',','};

    /**
     * El método convertirAHashmap se encarga de crear el arreglo tipo HashMap del alfabeto y los carácteres especiales
     * a partir del arreglo tipo char, para la identificar rápidamente los caracteres, para la codificación y
     * decodificación del mensaje.
     * @return alfabetoMap que contiene el diccionario del alfabeto asociado a un número.
     */
    public static HashMap<Character, Integer> convertirAHashMap() { //Puedo usar un indexOf() del array char pero es más lento
        HashMap<Character, Integer> alfabetoMap = new HashMap<>();
        for (int i = 1; i < ALFABETO.length; i++) {
            alfabetoMap.put(ALFABETO[i], i);
        }
        return alfabetoMap;
    }

    /**
     * Arreglo de tipo HashMap que contendrá las letras del alfabeto en español como clave y un entero asociado al número
     * de posición correspondiente
     */
    public static final HashMap<Character,Integer> ALPHABET = convertirAHashMap();

    /**
     * Calcula el número de posición de las nuevas letras para cifrar/descifrar el mensaje, a partir del número de clave
     * ingresado por el usuario, asignando las nuevas letras correspondiente a la posición del arreglo creado del
     * alfabeto
     */
    private static final BiFunction<Character, Integer, Integer> desplazarLetra =
            (letra, key) -> (ALPHABET.get(letra) + (key % (ALFABETO.length-1)) > (ALFABETO.length-1))//ALPHABET.lenght()
                    ? ALPHABET.get(letra) + (key % (ALFABETO.length-1)) - (ALFABETO.length-1)
                    : ALPHABET.get(letra) + (key % (ALFABETO.length-1));

    /**
     * Proceso que se encarga de cifrar/descifrar el mensaje
     * @param processedLine variable tipo string que contiene una línea del mensaje a cifrar/descifrar
     * @param key variable tipo entero que contiene el número de clave de desplazamiento de las letras en el mensaje
     * */
    private static void procesar(@NotNull String processedLine, int key, StringBuilder cifrado){
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
            } else {
                // Desplazamiento para carácteres especiales
                nuevaLetra = desplazarLetra.apply(character, key);
                character = ALFABETO[nuevaLetra]; //Verificación
            }
            cifrado.append(character);
        }
    }

//    public static void llamarProcesar(String processedLine, int key, StringBuilder cifrado){
    public static void llamarProcesar(String processedLine, int key, StringBuilder cifrado){
        procesar(processedLine, key, cifrado);
    }

}
