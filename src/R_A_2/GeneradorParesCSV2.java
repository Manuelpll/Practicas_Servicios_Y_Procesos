package R_A_2; /**
 * Este programa lanza un 50 hilo que cada 1 genera 2 archivos csv
 * que contienen cada uno 100000 lineas  de parejas
 * formadas con un identificador del archivo identificadores.txt
 * y un numero aleatorio entre 1 y el 20000
 * @author Mparr
 * @version 2.0
 * @date 9/11/2024
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorParesCSV2 extends Thread {
    private List<String> identificadores;
    private String nombreArchivo;
    private static final int lineas = 100000;
    private static final int numeroMaximo = 20000;

    /**
     * Metodo que inicializa la clase de GeneradorParesCSV2
     * @param identificadores-La lista con todos los identificadores
     * @param nombreArchivo-El nombre del archivo que se va ha crear
     */
    public GeneradorParesCSV2(List<String> identificadores, String nombreArchivo) {
        this.identificadores = identificadores;
        this.nombreArchivo = nombreArchivo;
    }//Fin del contructor

    /**
     * Metodo que inicia el hilo que genera 
     * un csv de 100000 parejas formadas por
     * un identificador del archivo identificadores.txt
     * y un numero aleatorio entre 1 y el 20000
     */
    @Override
    public void run() {
        Random random = new Random();

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (int i = 0; i < lineas; i++) {
                String identificador = identificadores.get(random.nextInt(identificadores.size()));
                int numeroAleatorio = random.nextInt(numeroMaximo + 1);
                writer.write(identificador + "," + numeroAleatorio + System.lineSeparator());
            }//Fin for
            System.out.println("Archivo generado: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo " + nombreArchivo + ": " + e.getMessage());
        }//Fin try-catch
    }//Fin de run

    /**
     * Metodo que ejecuta el codigo
     * @param args-Los argumentos de la linea de comandos
     */
    public static void main(String[] args) {
        generaIdentificadores();
        List<String> identificadores = leerIdentificadores("identificadores.txt");
        if (identificadores.isEmpty()) {
            System.err.println("No se encontraron identificadores en el archivo.");
            return;
        }//Fin if
        List<Thread> hilos = new ArrayList<>();
        crearArchivosCSV(identificadores, hilos);
        esperarFinalizacion(hilos);
        System.out.println("Generación de archivos CSV completada.");
    }//Fin main

    /**
     * Metodo que espera a que todos los hilos se terminen
     * @param hilos-La lista donde estan todos los hilos
     */
    private static void esperarFinalizacion(List<Thread> hilos) {
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Un hilo fue interrumpido: " + e.getMessage());
            }//Fin try-catch
        }//Fin for
    }//Fin de esperarFinalizacion

    /**
     * Metodo que crea 100 archivos csv que contienen 100000 pares formados por un identificador del archivo identificadores.txt
     *  * y un numero aleatorio entre 1 y el 20000
     * @param identificadores-La lista con los identificadores
     * @param hilos-La lista donde almacenan los hilos
     */
    private static void crearArchivosCSV(List<String> identificadores, List<Thread> hilos) {
        for (int i = 1; i <= 50; i++) {
            String nombreArchivo1 = "archivo_" + i + "_1.csv";
            String nombreArchivo2 = "archivo_" + i + "_2.csv";
            GeneradorParesCSV2 generador1 = new GeneradorParesCSV2(identificadores, nombreArchivo1);
            generador1.start();
            hilos.add(generador1);
            GeneradorParesCSV2 generador2 = new GeneradorParesCSV2(identificadores, nombreArchivo2);
            generador2.start();
            hilos.add(generador2);
        }
    }

    /**
     * Metodo que lee el archivo identificadores.txt
     * y lo transforma en un string
     * @param archivo-El archivo donde estan los identificadores
     * @return Un string con todos los identificadores
     */
    private static List<String> leerIdentificadores(String archivo) {
        List<String> identificadores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                identificadores.add(linea.trim());
            }//Fin while
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de identificadores: " + e.getMessage());
        }//Fin try-catch
        return identificadores;
    }//Fin de leerIdentificadores

    /**
     * Metodo que genera el archivo identificadores.txt
     * con 200 identificadores unicos de 6 caracteres
     */

    private static void generaIdentificadores() {
        GeneradorIdentificadores generador = new GeneradorIdentificadores();
        generador.start();
        try {
            generador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//Fin try-catch
    }//Fin de generarIdentificadores

}//Fin de GeneradorParesCSV2