package org.example.cesar;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.cesar.BruteForce.decryptByBruteForce;
import static org.example.cesar.FileManager.readFile;
import static org.example.cesar.FileManager.writeFile;
import static org.example.cesar.Validator.checkNumber;
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
        System.out.println("Seleccione una opción del menú: ");
    }
    public static void main(String[] args) {

        String inputFilePath = "C:\\Users\\javie\\OneDrive\\Documents\\Java\\CodeGym\\Proyecto Modulo 1\\entrada.txt";
        String outFilePath = "C:\\Users\\javie\\OneDrive\\Documents\\Java\\CodeGym\\Proyecto Modulo 1\\salida.txt";

        int opcion; //Seleccionar la opción a realizar
        int key;
        String mensaje;

        Scanner entrada = new Scanner(System.in);

        menuPrincipal();

        while(true){
            try{
                // Verifica si la entrada es un número entero
                String option = entrada.nextLine().trim();
                opcion = checkNumber(option);
//                if(option.matches("\\d+")) {
//                    opcion = Integer.parseInt(option);
                if(opcion > 0 && opcion < 5) {
                    break;  // Sale del bucle si el número es válido
                }
//                }
            } catch(InputMismatchException e){
                System.out.println("Entrada no válida. Debes ingresar un número entero.");
                entrada.next(); // Descarta la entrada no válida
                menuPrincipal();
            }
        }

        do {
            switch (opcion) {
                case 1:
                    key = toValid();
                    try {
                        mensaje = readFile(inputFilePath, key);
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
                    menuPrincipal();
                    try{
                        // Verifica si la entrada es un número entero
                        String option = entrada.nextLine().trim();
                        opcion = checkNumber(option);
//                        if(option.matches("\\d+")) {
//                            opcion = Integer.parseInt(option);
                        if(opcion > 0 && opcion < 5) {
                            break;  // Sale del bucle si el número es válido
                        }
//                        }
                    } catch(InputMismatchException e){
                        System.out.println("Entrada no válida. Debes ingresar un número entero.");
                        entrada.next(); // Descarta la entrada no válida
                        menuPrincipal();
                    }
                    break;
            }
        } while(!(opcion==4));

        entrada.close();
        System.out.println("Fin del programa.");
    }

}
