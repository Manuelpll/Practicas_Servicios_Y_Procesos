package R_A_2;

/**
 * Este programa muestra los diferentes estados
 * que puede estar un hilo lanzando un hilo
 * @author Mparr
 * @version 1.0
 * @date 12/11/2024
 */
public class EstadosHilos {
    private static final Object lock = new Object();

    /**
     * Clase que se dedica a mostrar los diferentes
     * estados que puede estar un hilo
     */
    private static class Estados extends Thread {

        /**
         * Metodo que pone en marcha el hilo de la clase
         */
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } // Fin try-catch
            synchronized (lock) {
                System.out.println("Estado actual del hilo : BLOCK");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } // Fin try-catch
            } // Fin synchronized
        } // Fin run
    } // Fin de Estados

    /**
     * Metodo que ejecuta el codigo
     * @param args-los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        Thread estados = new Thread(new Estados());
        System.out.println("Estado actual del hilo : "+estados.getState());
        estados.start();
        System.out.println("Estado actual del hilo : "+estados.getState());
        try {
            Thread.sleep(1000);
            System.out.println("Estado actual del hilo: " + estados.getState());
            Thread.sleep(3000);
            System.out.println("Estado actual del hilo: " + estados.getState());
            synchronized (lock) {
                lock.notify();
            } // Fin synchronized
            estados.join();
            System.out.println("Estado final : " + estados.getState()+"/"+"DEAD");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // Fin try-catch
    } // Fin main
} // Fin de EstadosHilos