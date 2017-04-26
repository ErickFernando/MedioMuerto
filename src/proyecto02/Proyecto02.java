/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto02;

import java.util.InputMismatchException;

/**
 *
 * @author cupueran
 */
public class Proyecto02 {

    static Juego juego = new Juego();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        juego.cargarPalabras();//
        muestraMenu();
    }

    public static void muestraMenu() {

        Pantalla.menu();
        boolean correcto;
        do {
            try {
                int op = Pantalla.pideEntero("Introduce una opción: \n");

                switch (op) {
                    case 1:
                        juego.ingresarPalabra();
                        muestraMenu();
                        break;
                    case 2:
                        juego.crearJugador();
                        muestraMenu();
                        break;
                    case 3:
                        juego.iniciarJuego();

                        muestraMenu();
                        break;
                    case 4:
                        juego.muestraPuntuaciones();
                        muestraMenu();
                        break;

                    default:
                        juego.guardarPalabrasFichero();
                        System.out.println("GRACIAS POR JUGAR...!!!!");
                        break;

                }
                correcto = true;
            } catch (InputMismatchException ex) {
                System.out.println("DEBES INTRODUCIR CARACTERES  NUMERICOS..!!");
                correcto = false;
            }
        } while (!correcto);

    }//

}
