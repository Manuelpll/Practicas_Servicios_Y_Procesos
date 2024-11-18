package R_A_2;

/**
 * Este programa lanza 10 hilos donde cada hilo escribe en un archivo
 * un numero creciente de 10 en 10 de palabras aleatorias siendo
 * así que el archivo tendra 550 lineas
 * @author Mparr
 * @version 1.0
 * @date 11/11/2024
 */
public class Colaborar {
    /**
     * Metodo que ejecuta el codigo principal
     * @param args Los argumentos de la linea de comandos
     */
    public static void main(String[] args) {
        String fichero = "colaborarFichero.txt"; 
        int numeroHilos = 10;
        ejecutarHilos(numeroHilos, fichero);
        System.out.println("Fichero generado con éxito: " + fichero);
    }//Fin main

    /**
     *Metodo que genera 10 hilos donde cada hilo escribe su numero
     * de palabras dependiendo de su posicion en un fichero
     * @param numeroHilos-La cantidad de hilos que se van a lanzar
     * @param fichero-El fichero donde se va a escribir las palabras
     */
    private static void ejecutarHilos(int numeroHilos, String fichero) {
        for (int i = 1; i <= numeroHilos; i++) {
            int numPalabras = i * 10;
            Thread hilo = new Thread(new Lenguaje(numPalabras, fichero));
            hilo.start();
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }//Fin try-catch
        }//Fin for
    }//Fin de ejecutarHilos
}//Fin de Colaborar