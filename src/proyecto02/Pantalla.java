/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto02;

import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class Pantalla {

    public static void menu() {
        System.out.println("*************************************");
        System.out.println("1. Introducir una nueva palabra");
        System.out.println("2. Introducir un nuevo jugador");
        System.out.println("3. jugar una ronda");
        System.out.println("4. mostrar puntuaciones");
        System.out.println("Otro.Fin");
        System.out.println("************************************");

    }

    public static void mensaje(String mensaje) {// Muestra por pantalla la cadena de mensaje

        System.out.print(mensaje);
    }

    public static String pideCadena(String mensaje) {

        //Muestra el mensaje por pantalla y pide una String por teclado.  Devuelve dicha String
        Scanner src = new Scanner(System.in);
        mensaje(mensaje);

        return src.nextLine();

    }

    public static int pideEnteroConLimites(int limMax, int limMin, String mensajeCorrecto, String mensajeError) {
        Scanner src = new Scanner(System.in);
        //Muestra el mensajeCorrecto por pantalla y pide un entero por teclado.  Cumpliendo la siguiente           
        //condición:  limMin<= numero <= limMax.  Si no se cumple la condición mostrará el mensajeError
        //  Devolverá dicho número
        int numero;
        System.out.println(mensajeCorrecto);
        numero = src.nextInt();
        while (limMin > numero || numero > limMax) {
            System.out.println(mensajeError);
            numero = src.nextInt();
        }
        return numero;
    }//

    public static int pideEntero(String mensaje) {//Muestra por pantalla la cadena de mensaje y luego pide un entero que será el parámetro devuelto.
        Scanner teclado = new Scanner(System.in);
        System.out.print(mensaje);//mensaje(mensaje); tambn sirve

        return teclado.nextInt();
    }//fin del metodo pide Entero

    public static int pideEnteroPositivoI(String mensajeCorrecto, String mensajeError) {
        //Muestra por pantalla la cadena de mensaje y luego pide un entero, se repetirá la acción hasta que el entero sea mayor que cero.  Dicho entero será el parámetro devuelto.
        Scanner teclado = new Scanner(System.in);
        int n;
//         System.out.println(mensajeCorrecto);
//         entero=teclado.nextInt();
        n = pideEntero(mensajeCorrecto);
        while (n < 0) {
//            System.out.println(mensajeError);  
//            entero=teclado.nextInt();
            n = pideEntero(mensajeError);
        }
        return n;
    }//fin del metodo pideEnteroPositivoI    

    public static int pideEnteroPositivo(String mensaje) {
        //Muestra por pantalla la cadena de mensaje y luego pide un entero, se repetirá la acción hasta que el entero sea mayor que cero.  Dicho entero será el parámetro devuelto.
        Scanner teclado = new Scanner(System.in);
        int entero;
        System.out.println(mensaje);
        entero = teclado.nextInt();
        while (entero < 0) {
            System.out.println("Número incorrecto, introduceloF de nuevo: ");
            entero = teclado.nextInt();
        }
        return entero;
    }//fin del metodo pideEnteroPositivo

}
