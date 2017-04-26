/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto02;

import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class Jugador implements Comparable<Object> {

    private String nombre;
    private int fallos = 0;
    private String[] letras = null;
    private int aciertos = 0;
    private ArrayList<String> palabras = new ArrayList<>();//guardar las palabras acertadas de cada jugador

    public Jugador() {

    }//

    public Jugador(String nombre) {
        this.nombre = nombre;

    }//

    @Override
    public boolean equals(Object o) {
        if (o instanceof Jugador) {//me va a de ser que es objeto que recibo como paramatro me dira si es o no un articulo

            Jugador a = (Jugador) o;
            return this.aciertos == a.getAciertos();//seran iguales cuando la descripcion sea la mimsa
        }

        return false;//
    }

    public void mostrarLetras() {//
        System.out.print("                                                         ");
        for (String l : this.getLetras()) {
            System.out.print(l);

        }
        System.out.println(" ");

    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the letras
     */
    public String[] getLetras() {
        return letras;
    }

    /**
     * @param letras the letras to set
     */
    public void setLetras(String[] letras) {
        this.letras = letras;
    }

    /**
     * @return the fallos
     */
    public int getFallos() {
        return fallos;
    }

    /**
     * @param fallos the fallos to set
     */
    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    /**
     * @return the acierto
     */
    public int getAciertos() {
        return aciertos;
    }

    /**
     * @param acierto the acierto to set
     */
    public void setAcierto(int aciertos) {
        this.aciertos = aciertos;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Jugador) {
            Jugador a = (Jugador) o;

            return Integer.compare(this.aciertos, a.getAciertos());

        }
        return -1;//    }

    }

    /**
     * @return the palabras
     */
    public ArrayList getPalabras() {
        return palabras;
    }

    /**
     * @param palabras the palabras to set
     */
    public void setPalabras(ArrayList palabras) {
        this.palabras = palabras;
    }
}
