package org.example.cesar;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.example.cesar.BruteForce.decryptByBruteForce;
import static org.example.cesar.FileManager.readFile;
import static org.example.cesar.FileManager.writeFile;
import static org.example.cesar.Validator.*;
import static org.example.cesar.Cipher.ALFABETO;

//pasos para trabajar con git en intellij
//https://www.youtube.com/watch?v=G5tvUmPe7ok

/**
 * Clase principal que muestra el menú de opciones para cifrado y descifrado mediante código César.
 * **/
public class Menu {
    /**
     * Ruta del archivo que tiene el mensaje a encriptar
     **/
    //Ruta del archivo debe ser relativo y debe ser digitado por consola
    private static Scanner entrada = new Scanner(System.in);
    /**
     * Ruta del archivo que almacena el mensaje encriptado
     */
    private static int opcion; //Seleccionar la opción a realizar
    private static int key; //Número de clave de desplazamiento de las letras en el mensaje
    private static String mensaje;
    private static String rutaArchivo;
    private static File archivoAbrir;
    public static String[] validExtensions = {"txt", "doc"};

    /**
     * Menú de opciones
     * 1. Cifrado de mensaje contenido en un archivo de texto, digitando un número entero para encriptar el mensaje.
     * 2. Descifrado de un archivo de texto, a partir de un número entero conocido, para desencriptar el mensaje real.
     * 3. Descifrado mediante método de Fuerza Bruta, mediante iteración de números del 1 al 26, hasta descubrir
     * el mensaje real, mediante comparación de palabras contenidos en el mensaje, con palabras
     * almacenadas en un diccionario de datos.
     */
    public static void menuPrincipal() {
        System.out.println("Menú código César: ");
        System.out.println("1. Cifrar");
        System.out.println("2. Descifrar");
        System.out.println("3. Descifrar por método Brute Force");
        System.out.println("4. Salir");
        System.out.println("Seleccione una opción del menú: ");
    }

    /**
     * Método para abrir un archivo para leer el mensaje ya sea para cifrado o descifrado
     */
    public static void openFile(){
        System.out.println("Ingresa una ruta válida para abrir el archivo: ");
        //Ejemplo de rutas
        //../cesar/src/entrada.txt = dentro de la carpeta src
        //../entrada.txt =  dentro de la carpeta Proyecto Modulo 1
        rutaArchivo = entrada.nextLine().trim();
        archivoAbrir = new File(rutaArchivo);
        while(!archivoAbrir.exists()) {
            errorEnArchivo();
        }
        if(opcion == 1){
            key = toValid();
            try {
                mensaje = readFile(rutaArchivo, key);
                System.out.println("Ingresa la ruta donde quiere escribir el archivo: ");
                rutaArchivo = entrada.nextLine().trim();//debo validar que la extensiòn del archivo a crear exista
                while (validExtension(rutaArchivo) == false){
                    System.out.println("Archivo sin extensión o extensión no valida");
                    System.out.println("Ingresa la ruta donde quiere escribir el archivo: ");
                    rutaArchivo = entrada.nextLine();
                    archivoAbrir = new File(rutaArchivo);
                }
                //if(validExtension(rutaArchivo)){
                    writeFile(mensaje, rutaArchivo);
                    System.out.println(mensaje);
                //}else{
                //    System.out.println("Archivo sin extensión o extensión no valida");
                //    System.out.println("Ingresa la ruta donde quiere escribir el archivo: ");
                //    rutaArchivo = entrada.nextLine();
                //    archivoAbrir = new File(rutaArchivo);
                //}
            } catch (IOException | FileManager.FileWriteException e) {
                System.out.println("Error al leer o escribir en el archivo");
            }
            //opcion = 0; //Reinicia la variable para cargar el menú
        } else if(opcion == 2){
            key = toValid();
            try {//try de descifrar con clave conocida
                mensaje = readFile(rutaArchivo.trim(), (ALFABETO.length-1) - key); //decifrar
                System.out.println(mensaje);
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al leer o escribir en el archivo");
            }
            //opcion = 0; //Reinicia la variable para cargar el menú
        } else {
            decryptByBruteForce(rutaArchivo.trim());
            //opcion = 0; //Reinicia la variable para cargar el menú
        }
        opcion = 0;
    }

    public static void errorEnArchivo(){
        System.out.println("El archivo no existe en la ruta proporcionada.");
        System.out.println("Ingresa una ruta válida para abrir el archivo: ");

        rutaArchivo = entrada.nextLine();
        archivoAbrir = new File(rutaArchivo);
    }

    /**
     * Valida que la extensión del archivo que se crea, exista o sea valido
     * @param nombreArchivo variable que contiene la ruta con el nombre del archivo con la extensión creada
     * @return false si la extensión no es válida o no existe
     */
    public static boolean validExtension(String nombreArchivo) {
        int indicePunto = nombreArchivo.lastIndexOf(".");
        if (indicePunto == -1 || indicePunto == nombreArchivo.length() - 1){//indicePunto == nombreArchivo.length() - 1) {
            return false; // No tiene extensión válida
        }
        String extension = nombreArchivo.substring(indicePunto + 1).toLowerCase();

        for (String ext : validExtensions) {
            if (extension.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Entrada al menu del programa.
     * Muestra el menú de opciones y captura las entradas del usuario para ejecutar las acciones correspondientes.
     */
    public static void iniciar() {

        menuPrincipal();

        opcion = esNumero(entrada);

        do {
            switch (opcion) {
                case 1: //Cifrar el mensaje contenido en un archivo a partir de la clave de desplazamiento proporcionada
                  openFile();
                    break;
                case 2: //Decifrar el mensaje contenido en un archivo a partir de una clave de desplazamiento conocida
                    openFile();
                    break;
                case 3:
                    //Ejecuta el contenido en la clase BruteForce.java, para descifrar el mensaje contenido en una
                    //ubicación específica del archivo
                    openFile();
                    break;
                default: //Mostrar el menú si el número ingresado no está entre el intervalo de selección
                    menuPrincipal();
                    opcion = esNumero(entrada);
            }
        } while(!(opcion==4)); //Termina el programa al seleccionar la opción del menú correspondiente

        entrada.close();
        System.out.println("Fin del programa.");
    }
}

