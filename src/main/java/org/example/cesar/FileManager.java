package org.example.cesar;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.cesar.Validator.*;

public class FileManager {
    private static final Logger LOGGER = Logger.getLogger(FileManager.class.getName());

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
                    procesar(processedLine, key, cifrado);
                    cifrado.append(System.lineSeparator());
                }
            } catch (IOException e) {
                //e.printStackTrace();
                LOGGER.log(Level.SEVERE, "Error leyendo el archivo", e);
            }
        } else {
            System.out.println("El archivo de entrada no existe: " + inputPath);
        }
        return cifrado.toString();
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


