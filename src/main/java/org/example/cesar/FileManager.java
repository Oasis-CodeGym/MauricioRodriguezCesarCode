package org.example.cesar;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.example.cesar.Validator.*;

/**
 * Clase para validar archivos, al momento de abrir y crear.
 */
public class FileManager {
    /**
     * Logger: para registrar errores o eventos relevantes
     * y poderlos almacenar en un archivo.
     */
//    private static final Logger LOGGER = Logger.getLogger(FileManager.class.getName());

    public static String readFile(String filePath, int key) throws IOException {
        Path inputPath = Paths.get(filePath);
        StringBuilder cifrado = new StringBuilder();

        //Verifica si existe el archivo
//        if(isFileExists(inputPath)){
            try (InputStream inputStream = Files.newInputStream(inputPath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String processedLine;
                while ((processedLine = reader.readLine()) != null) {
                    procesar(processedLine, key, cifrado);
                    cifrado.append(System.lineSeparator()); //salto de línea
                }
                //FileNotFoundException y No SuchFileException es subclase de IOException
//            } catch (IOException | FileWriteException | FileNotFoundException e ) {
            } catch (IOException | FileWriteException e ) {
                //e.printStackTrace();
                //LOGGER.log(Level.SEVERE, "Error leyendo el archivo", e);
                System.out.println("Error al escribir en el archivo: " + e);
            }
        //} else {
        //    System.out.println("El archivo de entrada no existe: " + inputPath);
       // }
        return cifrado.toString();
    }

    public static void writeFile(String content, String filePath) {
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

    /////////OPCIÓN PARA MANEJAR LA EXCEPCIÓN COMPROBADA
    public static class FileWriteException extends RuntimeException {
        public FileWriteException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }
}


