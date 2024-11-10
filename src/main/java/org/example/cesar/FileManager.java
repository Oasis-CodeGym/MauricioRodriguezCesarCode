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
//        Path path = Paths.get(filePath);
//        byte[] bytes = Files.readAllBytes(path);
//        return new String(bytes, StandardCharsets.UTF_8);
        Path inputPath = Paths.get(filePath);
        StringBuilder cifrado = new StringBuilder();
//        boolean existe = isFileExists(inputPath);
        //Verifica si existe el archivo
//        if(existe){
        if(isFileExists(inputPath)){
//        if(Files.exists(inputPath)) {//
            try (InputStream inputStream = Files.newInputStream(inputPath);
                BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(inputStream))) {
//            try(BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))){//
                String line;
                String processedLine;
//            int key = 4;
                while ((line = reader.readLine()) != null) {
                    processedLine = line;
//                char[] chars = processedLine.toCharArray();
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
//                            char encryptedChar = (char) ((character + key - 'A') % 27 + 'A');
                            cifrado.append(encryptedChar);
                        } else if (Character.isLowerCase(character)) {
                        // Desplazamiento para letras minúsculas
                            //int posicion = ((character + key - ALFABETO[0]) % 27 + ALFABETO[0]);
                            //System.out.println("La posición de la letra es: " + posicion);
                            if(((alphabet.get(character))+key)>27){//z, 26+4=30, 30-26 = 4, 4 = D alpabeto
                                int nuevaLetra = alphabet.get(character) + (key%27) - 27;
                                //System.out.println("La nueva letra es " + ALFABETO[nuevaLetra]);
                                char encryptedChar = ALFABETO[nuevaLetra];
                                cifrado.append(encryptedChar);
                            }/*else if((alphabet.get(character)+key)==26){//z, 26+4=30, 30-26 = 4, 4 = D alpabeto
//                                int nuevaLetra = alphabet.get(character) + key - 26;
                                int nuevaLetra = alphabet.get(character) + key - 26;
                                System.out.println("La nueva letra es " + ALFABETO[nuevaLetra]);
                                char encryptedChar = ALFABETO[nuevaLetra];
                                cifrado.append(encryptedChar);
                            } */else{
//                                int nuevaLetra = alphabet.get(character) + (key%26);//a=0,0+4=4, 4=e
                                int nuevaLetra = alphabet.get(character) + (key%27);//a=0,0+4=4, 4=e
                                char encryptedChar = ALFABETO[nuevaLetra]; //
                                cifrado.append(encryptedChar);
                            }
//sirve//                            char encryptedChar = (char) ((character + key - 'a') % 26 + 'a');
//sirve//                            cifrado.append(encryptedChar);
                        } else {
                        // Mantener otros caracteres sin cambio
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
//            cifrado = "El archivo de entrada no existe: " + inputPath.toString();
        }
        return new String(cifrado);
//        System.out.println(cifrado);
    }

    public static void writeFile(String content, String filePath, int key) {
        // Logic for writing a file
//        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))){
        Path outputPath = Paths.get(filePath); // Archivo de salida
        if (!Files.exists(outputPath)) {
            try{
                Files.createFile(outputPath); // Crea el archivo si no existe
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (OutputStream outputStream = Files.newOutputStream(outputPath);
             BufferedWriter writer = new BufferedWriter(new java.io.OutputStreamWriter(outputStream))) {
                writer.write(content);
                writer.newLine(); // Agrega una nueva línea para cada línea procesada
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


