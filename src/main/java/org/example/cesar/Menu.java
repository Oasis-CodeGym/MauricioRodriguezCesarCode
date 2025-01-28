package org.example.cesar;

import java.io.File;
import java.util.Scanner;

import static org.example.cesar.FileManager.*;
import static org.example.cesar.Validator.*;

//pasos para trabajar con git en intellij
//https://www.youtube.com/watch?v=G5tvUmPe7ok

/**
 * Clase principal que muestra el menú de opciones para cifrado y descifrado mediante código César.
 * **/
public class Menu {
    /**
     * Ruta del archivo que tiene el mensaje a encriptar
     **/
    private static Scanner entrada;

    public static Scanner getEntrada() {
        if (entrada == null) {
            entrada = new Scanner(System.in);
        }
        return entrada;
    }

    /**
     * Variable que contendrá la ruta del archivo a leer y/o escribir
     */
    private static File archivoAbrir;

    public static File getArchivoAbrir(String path) {
        archivoAbrir = new File(path);
        return archivoAbrir;
    }

    private static int opcion; //Seleccionar la opción del menú a realizar

    public static int getOpcion() {
        return opcion;
    }

    public static void setOpcion(int opcion) {
        Menu.opcion = opcion;
    }

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
     * Entrada al menu del programa.
     * Muestra el menú de opciones y captura las entradas del usuario para ejecutar las acciones correspondientes.
     */
    public static void iniciar() {
        menuPrincipal();
        opcion = esNumero();
        do {
            switch (opcion) {
                case 1: //Cifrar el mensaje contenido en un archivo a partir de la clave de desplazamiento proporcionada
                    callOpenFile();
                    break;
                case 2: //Decifrar el mensaje contenido en un archivo a partir de una clave de desplazamiento conocida
                    callOpenFile();
                    break;
                case 3:
                    //Ejecuta el contenido en la clase BruteForce.java, para descifrar el mensaje contenido en una
                    //ubicación específica del archivo
                    callOpenFile();
                    break;
                default: //Mostrar el menú si el número ingresado no está entre el intervalo de selección
                    menuPrincipal();
                    opcion = esNumero();
            }
        } while(!(opcion==4)); //Termina el programa al seleccionar la opción del menú correspondiente
        getEntrada().close();
        System.out.println("Fin del programa.");
    }
}

