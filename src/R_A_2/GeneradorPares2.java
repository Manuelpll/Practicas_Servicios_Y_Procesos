package R_A_2; /**
 * Este programa lanza un hilo que  crea dos archivo csv de 50.000 parejas
 * donde cada pareja esta formada con un identificador del archivo identificadores.txt
 * y un numero aleatorio entre 1 y el 20000
 * @author Mparr
 * @version 1.0
 * @date 9/11/2024
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorPares2 {
    private static final String identificadores = "identificadores.txt";
    private static final String archivo1 = "pares1.csv";
    private static final String archivo2 = "pares2.csv";
    private static final int lineas = 50000;
    /**
     * Metodo que ejecuta el codigo
     * @param args-Los argumentos de la linea de comandos
     */
    public static void main(String[] args) {
        generaIdentificadores();
        List<String> identificadores = leerIdentificadores();
        if (identificadores.isEmpty()) {
            System.err.println("No se encontraron identificadores en el archivo.");
            return;
        }//Fin if
        generarArchivoCSV(identificadores, archivo1);
        generarArchivoCSV(identificadores, archivo2);
    }//Fin main
    /**
     * Metodo que lee el archivo identificadores.txt y
     * lo tranforma a string
     * @return Todos los identificadores en un string
     */
    private static List<String> leerIdentificadores() {
        List<String> identificadores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(GeneradorPares2.identificadores))) {
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
     * Metodo que genera un archivo csv que contiene
     * 50000 parejas formadas por un identificador del archivo identificadores.txt
     * y un numero aleatorio entre 1 y el 20000
     * @param identificadores-El string con todos los identificadores
     */
    private static void generarArchivoCSV(List<String> identificadores, String nombreArchivo) {
        Random random = new Random();
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (int i = 0; i < lineas; i++) {
                String identificador = identificadores.get(random.nextInt(identificadores.size()));
                int numeroAleatorio = random.nextInt(20001);
                writer.write(identificador + "," + numeroAleatorio + System.lineSeparator());
            }//Fin for
            System.out.println("Archivo CSV generado con éxito: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
        }//Fin try-catch
    }//Fin de generarArchivoCSV
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
    }//Fin de geneararIdentificadores
}//Fin de GeneradorPares2
