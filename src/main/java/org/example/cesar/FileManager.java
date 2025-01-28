package org.example.cesar;

import java.io.*;
import java.nio.file.*;

import static org.example.cesar.BruteForce.decryptByBruteForce;
import static org.example.cesar.Cipher.ALFABETO;
import static org.example.cesar.Cipher.llamarProcesar;
import static org.example.cesar.Menu.*;
import static org.example.cesar.Validator.*;
import static org.example.cesar.Validator.getRutaArchivo;

/**
 * Clase para validar archivos, al momento de abrir y crear.
 */
public class FileManager {
    /**
     * Método para abrir un archivo para leer el mensaje ya sea para cifrado o descifrado
     */
    private static void openFile(){
        int key;
        String mensaje;
        System.out.println("Ingresa una ruta válida para abrir el archivo: ");
        //Ejemplo de rutas:
        //../cesar/src/entrada.txt = dentro de la carpeta src
        //../entrada.txt =  dentro de la carpeta Proyecto Modulo 1
        setRutaArchivo(getEntrada().nextLine().trim());
        while (!callValidExtension()){
            System.out.println("Archivo sin extensión o extensión no valida");
            System.out.println("Ingresa la ruta donde quiere escribir el archivo: ");
            setRutaArchivo(getEntrada().nextLine().trim());
        }
        while(!getArchivoAbrir(getRutaArchivo()).exists()) {
            errorEnArchivo();
        }
        if(getOpcion() == 1){
            key = setToValid();
            try {
                mensaje = readFile(getRutaArchivo(), key);
                if(!mensaje.isEmpty()){
                while(true){
                    System.out.println("Ingresa la ruta donde quiere escribir el archivo: ");
                    setRutaArchivo(getEntrada().nextLine().trim());
                    if (esRutaValida(getRutaArchivo()) && callValidExtension()){
                        //System.out.println("Ruta y extensión válidas.");
                        break;
                    }
                }
                writeFile(mensaje, getRutaArchivo());
                System.out.println("\n" + "Mensaje: " + "\n" + mensaje + "\n");
                } else{
                    System.out.println("No hay información contenida en el archivo.\n");
                }
            } catch (IOException | InvalidPathException | FileManager.FileWriteException e) {
                System.out.println("Error al leer o escribir en el archivo");
                openFile();
            }
        } else if(getOpcion() == 2){
            key = setToValid();
            try {//try de descifrar con clave conocida
                mensaje = readFile(getRutaArchivo().trim(), (ALFABETO.length-1) - (key%(ALFABETO.length-1))); //decifrar
                if(!mensaje.isEmpty()){
                    System.out.println("\n" + "Mensaje: " + "\n" + mensaje);
                } else {
                    System.out.println("No hay información contenida en el archivo.\n");
                }
            } catch (IOException | InvalidPathException | NullPointerException e) {
                System.out.println("Error al leer o escribir en el archivo");
            }
        } else {
            decryptByBruteForce(getRutaArchivo().trim());
        }
        setOpcion(0);
    }

    public static void callOpenFile(){
        openFile();
    }

    /**
     * Logger: para registrar errores o eventos relevantes
     * y poderlos almacenar en un archivo.
     */

    private static String readFile(String filePath, int key) throws IOException {
        Path inputPath = Paths.get(filePath);
        StringBuilder cifrado = new StringBuilder();
        //Verifica si existe el archivo
            try (InputStream inputStream = Files.newInputStream(inputPath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String processedLine;
                while ((processedLine = reader.readLine()) != null) {
                    llamarProcesar(processedLine, key, cifrado);
                    cifrado.append(System.lineSeparator()); //salto de línea
                }
            } catch (IOException | NullPointerException | FileWriteException e ) {
                //e.printStackTrace();
                //LOGGER.log(Level.SEVERE, "Error leyendo el archivo", e);
                System.out.println("Error al escribir en el archivo, \n no se escribió el nombre del archivo \n no existe o no se tiene acceso. \n" + e);
                menuPrincipal();
            }
        return cifrado.toString();
    }

    public static String callReadFile(String filePath, int key) throws IOException {
        return readFile(filePath, key);
    }

    private static boolean esRutaValida(String filePath) {
        try {
            Path path = Paths.get(filePath);
            // Verifica si el directorio padre existe o puede crearse
            Path parentDir = path.getParent();
            if (parentDir != null && Files.notExists(parentDir)) {
                System.out.println("La carpeta no existe. Creándola...");
                Files.createDirectories(parentDir);
            }
            return true;
        } catch (InvalidPathException e) {
            System.out.println("Ruta inválida: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("No se pudo crear la carpeta: " + e.getMessage());
        }
        return false;
    }

    private static void writeFile(String content, String filePath) {
        esRutaValida(filePath);
        Path outputPath = Paths.get(filePath); // Archivo de salida
        //!Files.exists(outputPath) es redundante porque Files.newBufferedWriter:
        //- Verifica internamente si el archivo existe.
        //- Lo crea si es necesario.
        try{
            if (!Files.exists(outputPath)) {
                Files.createFile(outputPath); // Crea el archivo si no existe
            }
        } catch (IOException | InvalidPathException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al escribir en archivo");
            callErrorEnArchivo();
        }
        try (OutputStream outputStream = Files.newOutputStream(outputPath);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            writer.write(content);
            writer.newLine(); // Agrega una nueva línea para cada línea procesada
        } catch (IOException e) {//se generó un error al escribir la ruta y presionar espacio
            //throw new RuntimeException("Error al escribir en el archivo", e);
            System.out.println("Error al escribir en archivo");
            callErrorEnArchivo();
        }
    }

    /////////OPCIÓN PARA MANEJAR LA EXCEPCIÓN COMPROBADA AL ESCRIBIR EL ARCHIVO
    public static class FileWriteException extends RuntimeException {
        public FileWriteException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }

    private static void errorEnArchivo(){
        System.out.println("El archivo no existe en la ruta proporcionada.");
        System.out.println("Ingresa una ruta válida para abrir el archivo: ");
        setRutaArchivo(getEntrada().nextLine().trim());
    }

    public static void callErrorEnArchivo(){
        errorEnArchivo();
    }
}


