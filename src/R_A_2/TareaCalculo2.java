package R_A_2; /**
 * Esta clase  al ser ejecutada crea un hilo que genera un numero aleatorio
 * y lo suma a un computo global cada 10 segundos hasta que llega a un millon
 * @author Mparr
 * @version 1.0
 * @date 6/11/2024
 */
import java.util.Random;
public class TareaCalculo2 implements Runnable {
    //Variables globales
    public static Random random = new Random();
    public static int sumaAcumulada = 0;
    //Variables especificas de cada hilo
    private String nombre = "";

    /**
     * Metodo que genera un numero aleatorio entre 100 y 1000
     * y lo suma hasta que la suma acumulada alcance un millón.
     * @throws InterruptedException Si se interrumpe el hilo
     */
    public void run() {
        while (true) {
            try {
                int numeroAleatorio = random.nextInt(901) + 100;
                synchronized (TareaCalculo2.class) {
                    sumaAcumulada += numeroAleatorio;
                    if (sumaAcumulada >= 1000000) {
                        break;
                    }//Fin if
                }//Fin de synchronized
                System.out.println("Suma acumulada: " + sumaAcumulada + " Nombre del hilo: " + nombre);
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("El hilo ha sido interrumpido.");
                break;
            }//Fin try-catch
        }//Fin while
    }//Fin de run

    /**
     * Metodo de la clase para ponerle nombre al nuevo hilo
     * @param nombre- El nombre que le quieres poner al hilo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }//Fin de setnombre
}//Fin de TareaCalculo2