package org.example.cesar;

import java.util.HashMap;
import java.util.Map;

public class AlphabetMap {
    public static final char[] ALFABETO = {' ','a','b','c','d','e','f','g','h','i',
            'j','k','l','m','n','ñ','o','p','q','r',
            's','t','u','v','w','x','y','z','á','é',
            'í','ó','ú',' ','.',','};

    public static Map<Character, Integer> convertirAHashMap() {
        Map<Character, Integer> alfabetoMap = new HashMap<>();
        for (int i = 1; i < ALFABETO.length; i++) {
            alfabetoMap.put(ALFABETO[i], i);
        }
        return alfabetoMap;
    }

    public static void main(String[] args) {
        Map<Character, Integer> alfabetoMap = convertirAHashMap();

        for (Map.Entry<Character, Integer> entry : alfabetoMap.entrySet()) {
            System.out.println("Clave: '" + entry.getKey() + "' -> Valor: " + entry.getValue());
        }
    }
}