package org.example.cesar;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.example.cesar.FileManager;
public class Validator {

    public static final char[] ALFABETO = {' ','a','b','c','d','e','f','g','h','i',
                                            'j','k','l','m','n','ñ','o','p','q',
                                            'r','s','t','u','v','w','x','y','z'};
    public static HashMap<Character,Integer> alphabet = new HashMap<>();

    static {
        alphabet.put('a',1);
        alphabet.put('b',2);
        alphabet.put('c',3);
        alphabet.put('d',4);
        alphabet.put('e',5);
        alphabet.put('f',6);
        alphabet.put('g',7);
        alphabet.put('h',8);
        alphabet.put('i',9);
        alphabet.put('j',10);
        alphabet.put('k',11);
        alphabet.put('l',12);
        alphabet.put('m',13);
        alphabet.put('n',14);
        alphabet.put('ñ',15);
        alphabet.put('o',16);
        alphabet.put('p',17);
        alphabet.put('q',18);
        alphabet.put('r',19);
        alphabet.put('s',20);
        alphabet.put('t',21);
        alphabet.put('u',22);
        alphabet.put('v',23);
        alphabet.put('w',24);
        alphabet.put('x',25);
        alphabet.put('y',26);
        alphabet.put('z',27);
    }

    //Leer entrada de datos para validar
        public static int toValid(){
            int clave = 0;
            Scanner entrada = new Scanner(System.in);
            while(true){
                System.out.println("Digite un número válido para la clave César:");
                try{
                    // Verifica si la entrada es un número entero
                    //key = entrada.nextInt();
                    String dato = entrada.nextLine().trim();
                    if(dato.matches("\\d+")) {
                        clave = Integer.parseInt(dato);
                        if(isAValidKey(clave)) {
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
            return clave;
        }

        public static boolean isAValidKey(int key) {
        // Key check
            if(key>0 && key<27){
                return true;
            } else{
                System.out.println("Digite un número clave entre 1 y 26.");
                return false;
            }
        }

    public static boolean isFileExists(Path filePath) {
        // Check if the file exists
        if(Files.exists(filePath)){
            return true;
        } else {
            return false;
        }
    }
}
