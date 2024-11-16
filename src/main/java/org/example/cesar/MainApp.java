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
public class MainApp {
    public static final String INPUT_FILE_PATH = "C:\\Users\\javie\\OneDrive\\Documents\\Java\\CodeGym\\Proyecto Modulo 1\\entrada.txt";
    public static final String OUT_FILE_PATH = "C:\\Users\\javie\\OneDrive\\Documents\\Java\\CodeGym\\Proyecto Modulo 1\\salida.txt";
    private static void menuPrincipal() {
        System.out.println("Menú código César: ");
        System.out.println("1. Cifrar");
        System.out.println("2. Descifrar");
        System.out.println("3. Descifrar por método Brute Force");
        System.out.println("4. Salir");
        System.out.println("Seleccione una opción del menú: ");
    }

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

    public static void main(String[] args) {

        int opcion; //Seleccionar la opción a realizar
        int key;
        String mensaje;

        Scanner entrada = new Scanner(System.in);

        menuPrincipal();

        opcion = esNumero(entrada);

        do {
            switch (opcion) {
                case 1:
                    key = toValid();
                    try {
                        mensaje = readFile(INPUT_FILE_PATH, key);
                        writeFile(mensaje, OUT_FILE_PATH);
                        System.out.println(mensaje);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    opcion = 0;
                    break;
                case 2:
                    key = toValid();
                    try {
                        mensaje = readFile(OUT_FILE_PATH, 27 - key); //decifrar
                        System.out.println(mensaje);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    opcion = 0;
                    break;
                case 3:
                    decryptByBruteForce(OUT_FILE_PATH);
                    opcion = 0;
                    break;
                default:
                    menuPrincipal();
                    opcion = esNumero(entrada);
            }
        } while(!(opcion==4));

        entrada.close();
        System.out.println("Fin del programa.");
    }
}

