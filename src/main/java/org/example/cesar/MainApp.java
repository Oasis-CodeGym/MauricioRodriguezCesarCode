package org.example.cesar;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.cesar.BruteForce.decryptByBruteForce;
import static org.example.cesar.FileManager.readFile;
import static org.example.cesar.FileManager.writeFile;
import static org.example.cesar.Validator.isAValidKey;
import static org.example.cesar.Validator.toValid;

//PENDIENTE
//ORGANIZAR EL CÓDIGO EN ORDEN
//CREAR UNA CLASE CIFRADO QUE HAGA EL CIFRADO Y EL DECIFRADO
//LA CLASE VALIDATOR DEBE VALIDAR LA ENTRADA DE LA CLAVE Y ASÍ MISMO VALIDAR LA EXISTENCIA DEL ARCHIVO

//pasos para trabajar con git en intellij
//https://www.youtube.com/watch?v=G5tvUmPe7ok
public class MainApp {
    private static void menuPrincipal() {
        System.out.println("Menú código César: ");
        System.out.println("1. Cifrar");
        System.out.println("2. Descifrar");
        System.out.println("3. Descifrar por método Brute Force");
        System.out.println("4. Salir");
        System.out.println("Seleccione una opción: ");
    }
    public static void main(String[] args) {

        String inputFilePath = "C:\\Users\\javie\\OneDrive\\Documents\\Java\\CodeGym\\Proyecto Modulo 1\\entrada.txt";
        String outFilePath = "C:\\Users\\javie\\OneDrive\\Documents\\Java\\CodeGym\\Proyecto Modulo 1\\salida.txt";

        int opcion = 0; //Seleccionar la opción a realizar
        int key = 0;

        Scanner entrada = new Scanner(System.in);

        menuPrincipal();
        opcion = entrada.nextInt();

        do {
            switch (opcion) {
                case 1:
                    key = toValid();
                    String mensaje;
                    try {
                        mensaje = readFile(inputFilePath, key);
//                        writeFile(mensaje, outFilePath, key);
                        writeFile(mensaje, outFilePath);
                        System.out.println(mensaje);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    opcion = 0;
                    break;
                case 2:
                    key = toValid();
                    try {
                        mensaje = readFile(outFilePath, 27 - key); //decifrar
                        System.out.println(mensaje);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    opcion = 0;
                    break;
                case 3:
                    decryptByBruteForce(outFilePath);
                    opcion = 0;
                    break;
                default:
                    System.out.println("Seleccione una opción del menú: ");
                    menuPrincipal();
                    opcion = entrada.nextInt();
                    break;
            }
        } while(!(opcion==4));

        entrada.close();
        System.out.println("Fin del programa.");
    }

}
