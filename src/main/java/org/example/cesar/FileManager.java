package org.example.cesar;

import java.io.*;
import java.net.StandardSocketOptions;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

import org.example.cesar.Validator;

import static org.example.cesar.Validator.*;

public class FileManager {
    public static String readFile(String filePath, int key) throws IOException {
        Path inputPath = Paths.get(filePath);
        StringBuilder cifrado = new StringBuilder();
        //Verifica si existe el archivo
        if(isFileExists(inputPath)){
            try (InputStream inputStream = Files.newInputStream(inputPath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                String processedLine;
                while ((line = reader.readLine()) != null) {
                    processedLine = line;
                    for (char character : processedLine.toCharArray()) {
                        if (Character.isUpperCase(character)) {
                        // Desplazamiento para letras mayúsculas
                            char minuscula = Character.toLowerCase(character);
                            int nuevaLetra;
                            if(((alphabet.get(minuscula))+key)>27){
                                nuevaLetra = alphabet.get(minuscula) + (key%27) - 27;
                            } else {
                                nuevaLetra = alphabet.get(minuscula) + (key%27);
                            }
                            char encryptedChar = Character.toUpperCase(ALFABETO[nuevaLetra]);
                            cifrado.append(encryptedChar);
                        } else if (Character.isLowerCase(character)) {
                        // Desplazamiento para letras minúsculas
                            if(((alphabet.get(character))+key)>27){//z, 26+4=30, 30-26 = 4, 4 = D alpabeto
                                int nuevaLetra = alphabet.get(character) + (key%27) - 27;
                                char encryptedChar = ALFABETO[nuevaLetra];
                                cifrado.append(encryptedChar);
                            } else{
                                int nuevaLetra = alphabet.get(character) + (key%27);//a=0,0+4=4, 4=e
                                char encryptedChar = ALFABETO[nuevaLetra]; //
                                cifrado.append(encryptedChar);
                            }
                        } else {
                        // Mantener otros caracteres sin cambio: espacios, puntos, comas
                            cifrado.append(character);
                        }
                    }
                    cifrado.append(System.lineSeparator());
                }
        //    return new String(cifrado);
            } catch (IOException e) {
            e.printStackTrace();
            }
        } else {
            System.out.println("El archivo de entrada no existe: " + inputPath.toString());
        }
        return new String(cifrado);
    }

    public static void writeFile(String content, String filePath) {
//    public static void writeFile(String content, String filePath, int key) {
        Path outputPath = Paths.get(filePath); // Archivo de salida
        //!Files.exists(outputPath) es redundante porque Files.newBufferedWriter:
        //- Verifica internamente si el archivo existe.
        //- Lo crea si es necesario.
        try{
            if (!Files.exists(outputPath)) {
                Files.createFile(outputPath); // Crea el archivo si no existe
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //
        try (OutputStream outputStream = Files.newOutputStream(outputPath);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            writer.write(content);
            writer.newLine(); // Agrega una nueva línea para cada línea procesada
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo", e);        }
    }
}


