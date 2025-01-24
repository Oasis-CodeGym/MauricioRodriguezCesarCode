package org.example.cesar;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.example.cesar.Cipher.ALPHABET;
import static org.example.cesar.FileManager.readFile;

public class BruteForce {
    public static void decryptByBruteForce(String filePath) {
        for(int i = 0; i <= ALPHABET.size(); i++){
            try {
                String mensaje = readFile(filePath, ALPHABET.size() - i); //decifrar
//                String mensaje = readFile(filePath, (ALFABETO.length - i -1)); //decifrar
                //System.out.println("Mensaje descifrado con clave " + i + ":");
                boolean claveEncontrada = false;
                Set<String> keywords = getStrings();
                String[] words = mensaje.toLowerCase().split("\\W+");
                    for (String word : words) {
                        if (keywords.contains(word)) {
                            System.out.println("CLAVE DE DESCIFRADO CÉSAR ES es: " + i + "\n");
                            claveEncontrada = true;
                            System.out.println(mensaje);
                            break;
                        }
                    }
                    if(claveEncontrada) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @NotNull
    private static Set<String> getStrings() {
        Set<String> keywords = new HashSet<>();
        keywords.add("con");
//        keywords.add("de");
        keywords.add("desde");
//        keywords.add("el");
//        keywords.add("la");
        keywords.add("las");
        keywords.add("los");
        keywords.add("para");
        keywords.add("pero");
        keywords.add("por");
        keywords.add("que");
//        keywords.add("se");
        keywords.add("sin");
        keywords.add("una");
        return keywords;
    }
}
