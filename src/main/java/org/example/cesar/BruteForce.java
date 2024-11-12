package org.example.cesar;

import java.io.IOException;

import static org.example.cesar.FileManager.readFile;

public class BruteForce {
    public static void decryptByBruteForce(String filePath) {
        for(int i = 1; i <= 27; i++){
            try {
                String mensaje = readFile(filePath, 27 - i); //decifrar
                System.out.println("Descifrado " + i + ":");
                System.out.println(mensaje);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
