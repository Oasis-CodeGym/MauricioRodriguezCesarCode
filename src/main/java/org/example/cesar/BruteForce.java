package org.example.cesar;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.example.cesar.FileManager.readFile;

public class BruteForce {
    public static void decryptByBruteForce(String filePath) {
        for(int i = 1; i <= 27; i++){
            try {
                String mensaje = readFile(filePath, 27 - i); //decifrar
                System.out.println("Mensaje descifrado con clave " + i + ":");
                System.out.println(mensaje);
                //
                boolean claveEncontrada = false;
                Set<String> keywords = new HashSet<>();
                keywords.add("con");
                keywords.add("de");
                keywords.add("desde");
                keywords.add("para");
                keywords.add("pero");
                keywords.add("por");
                keywords.add("sin");
                String[] words = mensaje.toLowerCase().split("\\W+");
                    for (String word : words) {
                        if (keywords.contains(word)) {
                            System.out.println("CLAVE DE DESCIFRADO CÉSAR ES es: " + i + "\n");
                            claveEncontrada = true;
                            break;
                        }
                    }
                    if(claveEncontrada == true){
                        break;
                    }
                //
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
