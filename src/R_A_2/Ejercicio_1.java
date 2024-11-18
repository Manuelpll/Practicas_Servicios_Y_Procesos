package R_A_2;

/**
 * Este programa lanza 30 hilo que generan cada 10 segundos un numero aleatorio
 * y lo suma a un computo global hasta que el siguiente numero que el computo
 * global sea un millon
 * @author Mparr
 * @version 1.0
 * @date 6/11/2024
 */
public class Ejercicio_1 {
    /**
     * Metodo que ejecuta el codigo principal
     * @param args Los argumentos de la linea de comandos
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            TareaCalculo2 tarea = new TareaCalculo2();
            tarea.setNombre("Hilo-" + i);
            Thread hilo = new Thread(tarea);
            hilo.start();
        }//Fin de for
    }//Fin de main
}//Fin de Ejercicio_1
