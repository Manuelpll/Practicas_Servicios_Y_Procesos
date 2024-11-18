package R_A_2;

/**
 * Este programa lanza 3 hilos de la clase HiloPrioridades y los inicia
 * @author Mparr
 * @version 1.0
 * @date 12/11/2024
 */public class Ejercicio_6 {

    /**
     * Metodo que ejecuta el codigo principal
     * @param args Los argumentos de la linea de comandos
     */
    public static void main(String[] args)  {
        HiloPrioridades prioridad1 = new HiloPrioridades("Primer Hilo",1);
        HiloPrioridades prioridad2 = new HiloPrioridades("Segundo Hilo",3);
        HiloPrioridades prioridad3= new HiloPrioridades("Tercer Hilo",5);
        iniciarHilos(prioridad1, prioridad2, prioridad3);
    }//Fin main

    /**
     * Metodo que inicia los 3 hilos
     * @param prioridad1-Primer hilo
     * @param prioridad2-Segundo hilo
     * @param prioridad3-Tercer hilo
     */
    private static void iniciarHilos(HiloPrioridades prioridad1, HiloPrioridades prioridad2, HiloPrioridades prioridad3)  {
        prioridad1.start();
        prioridad2.start();
        prioridad3.start();
    }//Fin de iniciarHilos
}//Fin de Ejercicio_6
