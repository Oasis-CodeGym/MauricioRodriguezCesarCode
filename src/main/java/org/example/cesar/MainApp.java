package org.example.cesar;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

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
    public static void main(String[] args) {
        // Logic for selecting the operating mode, calling the appropriate methods
        //
        int prueba = 0;
        int opcion = 0; //Seleccionar la opción a realizar
        int key = 0;
        key = toValid();

        //PARA PROBAR SoLAMENTE
/*        Scanner entrada = new Scanner(System.in);

        while(true){
            System.out.println("Digite un número válido para la clave César:");
            try{
                // Verifica si la entrada es un número entero
                //key = entrada.nextInt();
                String dato = entrada.nextLine().trim();
                if(dato.matches("\\d+")) {
                    key = Integer.parseInt(dato);
                    if(isAValidKey(key)) {
                        break;  // Sale del bucle si el número es válido
                    } //else {
                      //  System.out.println("Digite un número válido.");
                        //entrada.next();
                    //}
                }
            } catch(InputMismatchException e){
                System.out.println("Entrada no válida. Debes ingresar un número entero.");
                entrada.next(); // Descarta la entrada no válida
            }
        }
*/

/*        do {
            System.out.println("Digite la clave");
            key = entrada.nextInt();
        } while(!isAValidKey(key));
*/
        String inputFilePath = "C:\\Users\\javie\\OneDrive\\Documents\\Java\\CodeGym\\Proyecto Modulo 1\\entrada.txt";
        String outFilePath = "C:\\Users\\javie\\OneDrive\\Documents\\Java\\CodeGym\\Proyecto Modulo 1\\salida.txt";
        String mensaje;
        try {
            //Scanner desplazamiento = new Scanner(System.in);
            //mensaje = readFile(inputFilePath, key);
            mensaje = readFile(inputFilePath, key);
            System.out.println(mensaje);
            writeFile(mensaje, outFilePath, key);
//            mensaje = readFile(outFilePath, 26 - (key % 26)); //decifrar
            mensaje = readFile(outFilePath, 27 - key); //decifrar
            System.out.println(mensaje);
//            switch(opcion){
//                case 0:
//                    break;
//                default:
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
