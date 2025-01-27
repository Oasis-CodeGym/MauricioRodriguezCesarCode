package org.example.cesar;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.example.cesar.Cipher.ALPHABET;
import static org.example.cesar.FileManager.callReadFile;

public class BruteForce {
    public static void decryptByBruteForce(String filePath) {
        for(int i = 0; i <= ALPHABET.size(); i++){
            try {
                String mensaje = callReadFile(filePath, ALPHABET.size() - i); //decifrar
//                if(!mensaje.equals("")){
                if(!mensaje.isEmpty()){
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
                }else{
                    System.out.println("No hay información contenida en el archivo.\n");
                    break;
                }
            } catch (IOException e) {
                System.out.println("Error de lectura o escritura de archivo");
            }
        }
    }

    @NotNull
    private static Set<String> getStrings() {
        Set<String> keywords = new HashSet<>();
        keywords.add("con");
        keywords.add("desde");
        keywords.add("las");
        keywords.add("los");
        keywords.add("para");
        keywords.add("pero");
        keywords.add("por");
        keywords.add("que");
        keywords.add("sin");
        keywords.add("una");
//        keywords.add("se");
//        keywords.add("el");
//        keywords.add("la");
//        keywords.add("de");
        return keywords;
    }
}
