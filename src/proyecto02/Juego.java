/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto02;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Home
 */
public class Juego implements Serializable {

    ArrayList<Jugador> jugadores = new ArrayList<>();//guarda los jugadores

    ArrayList<String> palabras = new ArrayList<>();
    // guarda las palabras
//    File fichero = new File("I:\\netbeans\\Proyecto02\\palabras.dat");//cargamos el fichero en la direccion que este guardado
    Fichero f = new Fichero("palabras.dat");//PONER LA DIRECCION DEL FICHERO CORRECTA

    final int LIMFALLOS = 5;
    boolean hayUnGanador = false;
    String secreta;

    public void cargarPalabras() {
        ArrayList<String> aux;//creamos un arrayList auxiliar para comprobar 
        aux = f.leerFichero();//
        palabras = new ArrayList<>();
        System.out.println(aux);//IMPPRIMIMOS LAS PALABRAS DEL FICHERO
        if (aux != null && aux.size() > 0) {//COMPROBAMOS 
            for (int i = 0; i < aux.size(); i++) {// RECORREMOS EL STRING AUX
                palabras.add(aux.get(i));//AÑADIMOS LAS PALABRAS AL ARRAY PALABRAS
            }
        }
    }//

    public void ingresarPalabra() {

        String palabra = Pantalla.pideCadena("Ingresa la palabra: \n");//pedimos la palabra
        palabras.add(palabra);// añadimos al array la palabra xDD
        Pantalla.mensaje("*********\n");

    }//fin del metodo ingresarPalabra()

    public void crearJugador() {

        Jugador jugador = new Jugador();
        String nombre = Pantalla.pideCadena("Ingresa el nombre del jugador: \n");
        jugador.setNombre(nombre);
        jugadores.add(jugador);
    }//

    public void inicializarJuego() {

        hayUnGanador = false;
        int i = (int) (Math.random() * (palabras.size() - 1));//devuelve un numero aleatorio entre 0 y el ultimo numero segun la lista de palabras
        System.out.println(i + " " + palabras.size());
        secreta = palabras.get(i);//obtiene palabra aleatoria

        for (Jugador jugador : jugadores) {//                    
            jugador.setFallos(0);
            String guiones = String.format("%" + secreta.length() + "s", "").replace(" ", "-;"); //creamos una cadena de guiones y puntos y coma     
            String[] letras = guiones.split(";"); //donde este el punto le corta la cadena y creal el array de guiones y el ";" desaparece
            jugador.setLetras(letras);//asignamos los guiones al jugador

        }
    }

    public void iniciarJuego() {
        if ((palabras != null) && (jugadores != null)) {//si palabras es distinto de null y jugadores tambn 
            if (!palabras.isEmpty()) {
                if (!jugadores.isEmpty()) {

                    inicializarJuego();
                    while (seguirJugando()) {//mientras no haya un ganador y haya jugadores con menos de 5 fallos
                        for (Jugador jugador : jugadores) {//
                            if (!hayUnGanador)//
                            {
                                if (jugador.getFallos() < LIMFALLOS) {
                                    jugar(jugador);//pedimos la letra
                                }

                            }
                        }//
                    }//fin del while
                } else {
                    System.out.println(" Para jugar debes crear al menos un jugador...no lo sabias??");
                }
            } else {
                System.out.println(" No hay palabras para jugar...");
            }
        }
    }//fin de iniciarJuego

    public void jugar(Jugador jugador) {

        String letra = "";//
        boolean correcto;

        //System.out.println(correcta);
        int f = jugador.getFallos();
        int a = jugador.getAciertos();
        System.out.println(secreta);
        String[] letras = jugador.getLetras();//cogemos las letras del jugador

        do {
            try {

                System.out.println("                                                ****************************");
                System.out.println("                                                JUGADOR: " + jugador.getNombre() + " FALLOS: " + jugador.getFallos());
                jugador.mostrarLetras();
                letra = Pantalla.pideCadena("                                                " + jugador.getNombre() + " introduce la letra: ");//
                validarLetras(letra);

                if (!secreta.contains(letra)) {
                    f++;
                    jugador.setFallos(f);

                } else {

                    for (int i = 0; i < secreta.length(); i++)//recorremos la palabras secreta para buscar el indice en el que se encuentra la letra
                    {
                        if ((secreta.charAt(i) + "").equals(letra))//si la letra esta en el indice 
                        {
                            letras[i] = letra;//asignamos la leetra en el array del indice que nos indique
                        }
                    }
                    jugador.setLetras(letras);//guardamos en el jugador el array letras actualizado.
                    // jugador.getLetras().add(letra);
                    // correcta "_", secreta)secreta.indexOf(letra);
                    jugador.mostrarLetras();

                }

                correcto = true;
            } catch (MyError e) {
                System.out.println("Vuele a introducir la letra \n" + e.getMessage());
                correcto = false;

            } finally {

                if (heGanado(jugador)) {
                    a++;
                    jugador.setAcierto(a);
                    System.out.println("                                                Has ganado...!!! FELICIDADES " + jugador.getNombre());

                    jugador.getAciertos();
                    ArrayList p = jugador.getPalabras();
                    p.add(secreta);
                    jugador.setPalabras(p);

                    hayUnGanador = true;
                }
                if (jugador.getFallos()== this.LIMFALLOS)
                    System.out.println("                                              PERDISTE...!!!!  "+jugador.getNombre());

            }

        } while (!correcto);

    }//fin del metodo pedir letra

    public void validarLetras(String letra) throws MyError {
        String letras = "qwertyuiopasdfghjklñzxcvbnm";

        MyError error;
        if (letra.length() > 1) {
            error = new MyError("                             SOLO PUEDES INTRODUCIR UNA LETRA.. \n");
            throw error;
        } else if (!letras.contains(letra)) {

            error = new MyError("                             ESA NO ES UNA LETRA... ");
            throw error;
        }
    }//validar letra

    public boolean seguirJugando() {
        boolean fallos;
        fallos = false;

        for (Jugador j : jugadores) {//recorremos los jugadores
            if (j.getFallos() < LIMFALLOS)//
            {
                fallos = true;

            }

        }

        return fallos && !hayUnGanador;
    }//

    public boolean heGanado(Jugador jugador) {

        for (String l : jugador.getLetras()) {
            if (l.equals("-")) {
                return false;
            }
        }

        return true;
    }//

    public void muestraPuntuaciones() {
        Collections.sort(jugadores);
        for (int i = (jugadores.size() - 1); i >= 0; i--) {
            System.out.println("PUNTUACIÓN DE: " + jugadores.get(i).getNombre() + "   " + jugadores.get(i).getAciertos() + " PUNTO/S");
            for (int j = 0; j < jugadores.get(i).getPalabras().size(); j++) {
                System.out.println(jugadores.get(i).getPalabras().get(j));//imprimimos las palabras acertadas de cada jugador
            }

        }

    }//

    public void guardarPalabrasFichero() {//
        f.escribirFichero(palabras);//guardaremos las palabras en el fichero
    }//

}//

