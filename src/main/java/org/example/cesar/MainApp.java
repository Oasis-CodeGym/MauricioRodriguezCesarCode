package org.example.cesar;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.cesar.BruteForce.decryptByBruteForce;
import static org.example.cesar.FileManager.readFile;
import static org.example.cesar.FileManager.writeFile;
import static org.example.cesar.Validator.checkNumber;
import static org.example.cesar.Validator.toValid;

//pasos para trabajar con git en intellij
//https://www.youtube.com/watch?v=G5tvUmPe7ok

/**
* Clase principal que muestra el menú de opciones para cifrado y descifrado mediante código César.
* **/
public class MainApp {
    /**
    * Ruta del archivo que tiene el mensaje a encriptar
    **/
    public static final String INPUT_FILE_PATH = "C:\\Users\\javie\\OneDrive\\Documents\\Java\\CodeGym\\Proyecto Modulo 1\\entrada.txt";
    /**
     * Ruta del archivo que almacena el mensaje encriptado
     */
    public static final String OUT_FILE_PATH = "C:\\Users\\javie\\OneDrive\\Documents\\Java\\CodeGym\\Proyecto Modulo 1\\salida.txt";

    /**
     * Menú de opciones
     * 1. Cifrado de mensaje contenido en un archivo de texto, digitando un número entero para encriptar el mensaje.
     * 2. Descifrado de un archivo de texto, a partir de un número entero conocido, para desencriptar el mensaje real.
     * 3. Descifrado mediante método de Fuerza Bruta, mediante iteración de números del 1 al 26, hasta descubrir
     * el mensaje real, mediante comparación de palabras contenidos en el mensaje, con palabras
     * almacenadas en un diccionario de datos.
     */
    private static void menuPrincipal() {
        System.out.println("Menú código César: ");
        System.out.println("1. Cifrar");
        System.out.println("2. Descifrar");
        System.out.println("3. Descifrar por método Brute Force");
        System.out.println("4. Salir");
        System.out.println("Seleccione una opción del menú: ");
    }

    /**
     * Valida que se ingrese un número entero, procesando datos no deseados hasta que se ingrese
     * una opción del menú entre 1 y 4.
     * @param entrada variable de tipo Scanner para capturar la entrada de datos del usuario.
     * @return opcion regresa un número entero válido, correspondiente a una de las opciones del menú.
     */
    private static int esNumero(@org.jetbrains.annotations.NotNull Scanner entrada) {
        int opcion;
        while (true) {
            try {
                // Verifica si la entrada es un número entero
                String option = entrada.nextLine().trim();
                opcion = checkNumber(option);
                if (opcion > 0 && opcion < 5) {
                    break;  // Sale del bucle si el número es válido
                }
                else{
                    System.out.println("Ingrese un número válido.");
                    menuPrincipal();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Debes ingresar un número entero.");
                entrada.next(); // Descarta la entrada no válida
                menuPrincipal();
            }
        }
        return opcion;
    }

    /**
     * Entrada principal del programa.
     * Muestra el menú de opciones y captura las entradas del usuario para ejecutar las acciones correspondientes.
     * @param args argumentos de la línea de comandos, que se agrega por defecto.
     */
    public static void main(String[] args) {

        int opcion; //Seleccionar la opción a realizar
        int key; //Número de clave de desplazamiento de las letras en el mensaje
        String mensaje;

        Scanner entrada = new Scanner(System.in);

        menuPrincipal();

        opcion = esNumero(entrada);

        do {
            switch (opcion) {
                case 1: //Cifrar el mensaje contenido en un archivo a partir de la clave de desplazamiento proporcionada
                    key = toValid();
                    try {
                        mensaje = readFile(INPUT_FILE_PATH, key);
                        writeFile(mensaje, OUT_FILE_PATH);
                        System.out.println(mensaje);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    opcion = 0; //Reinicia la variable para cargar el menú
                    break;
                case 2: //Decifrar el mensaje contenido en un archivo a partir de una clave de desplazamiento conocida
                    key = toValid();
                    try {
                        mensaje = readFile(OUT_FILE_PATH, 27 - key); //decifrar
                        System.out.println(mensaje);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    opcion = 0; //Reinicia la variable para cargar el menú
                    break;
                case 3:
                    //Ejecuta el contenido en la clase BruteForce.java, para descifrar el mensaje contenido en una
                    //ubicación específica del archivo
                    decryptByBruteForce(OUT_FILE_PATH);
                    opcion = 0; //Reinicia la variable para cargar el menú
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

