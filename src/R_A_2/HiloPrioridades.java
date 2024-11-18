package R_A_2;

/**
 * Clase que contiene unos metodos para ser lanzada en un hilo
 * @author Mparr
 * @version 1.0
 * @date 12/11/2024
 */
public class HiloPrioridades extends Thread {

    /**
     * Metodo constructor que inicializa el nombre y la prioriadad del Hilo
     * @param nombre-El nombre del hilo
     * @param prioridad-La prioridad del hilo
     */
    public HiloPrioridades(String nombre, int prioridad) {
        setName(nombre);
        setPriority(prioridad);
    }//Fin HiloPrioridades

    /**
     * Metodo que inicia el hilo el cual comprueba
     * que prioridad tiene y llama al metodo correspondiente
     */
    @Override
    public void run() {
        System.out.println("Iniciando el hilo: " + getName());

        while (true) {
            switch (getPriority()) {
                case 1:
                    tarea1();
                    break;
                case 3:
                    tarea3();
                    break;
                case 5:
                    tarea5();
                    break;
                default:
                    System.out.println(getName() + " tiene una prioridad no manejada.");
                    break;
            }//Fin switch
            if (esperarSegundo()) break;
        } // Fin while
    } // Fin run

    /**
     * Metodo que espera un segundo antes de seguir
     * @return Retorna un false para que vuelva al  menu
     */
    private static boolean esperarSegundo() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return true;
        }//Fin try-catch
        return false;
    }//Fin de esperarSegundo

    /**
     * Metodo que muestra el nombre y
     * la prioridad si tiene 1 de prioridad
     */
    private void tarea1() {
        System.out.println(getName() + " (Prioridad " + getPriority() + ") --> tarea lenta");
    }//Fin de tarea1

    /**
     * Metodo que muestra el nombre y
     * la prioridad si tiene 3 de prioridad
     */
    private void tarea3() {
        System.out.println(getName() + " (Prioridad " + getPriority() + ") --> tarea normal");
    }//Fin de tarea3

    /**
     * Metodo que muestra el nombre y
     * la prioridad si tiene 5 de prioridad
     */
    private void tarea5() {
        System.out.println(getName() + " (Prioridad " + getPriority() + ") --> tarea rápida");
    }//Fin de tarea5

} // Fin de HiloPrioridades