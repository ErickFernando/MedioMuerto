/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto02;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Alumno
 */
public class Fichero {

    private String nombreFichero;

    public Fichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
        File f = new File(nombreFichero);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                System.out.println("IMPOSIBLE CREAR EL FICHERO");
                System.out.println("Es posible que no este guardada la dirección del fichero correctamente, cambia la dirección..");
            }
        }
    }//

    public void escribirFichero(ArrayList objetos) {
        ObjectOutputStream fw = null;
        try {
            fw = new ObjectOutputStream(new FileOutputStream(nombreFichero));//le pasamos el nombre del fichero
            for (Object o : objetos) {
                fw.writeObject(o);
                System.out.println(o);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("FICHERO INACCESIBLE");
        } catch (IOException ex) {
            System.out.println("IMPOSIBLE ESCRIBIR EN EL FICHERO");
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                System.out.println("IMPOSIBLE CERRAR EL FICHERO");

            }
        }
    }//

    public ArrayList leerFichero() {
        ArrayList objetos = new ArrayList();
        ObjectInputStream fr = null;
        try {
            fr = new ObjectInputStream(new FileInputStream(nombreFichero));
            while (true) {
                objetos.add(fr.readObject());
            }
        } catch (EOFException ex) {
            return objetos;
        } catch (FileNotFoundException ex) {
            System.out.println("IMPOSIBLE ABRIR FICHERO");
            return null;
        } catch (IOException ex) {
            System.out.println("ERROR DE FICHEROS");
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("FICHERO CORRUPTO");
            return null;
        }

    }//

    /**
     * @return the nombreFichero
     */
    public String getNombreFichero() {
        return nombreFichero;
    }

    /**
     * @param nombreFichero the nombreFichero to set
     */
    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }
}
