package R_A_1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Es una clase que ejecuta varios procesos de comprimir archivos y luego muestra los resultados por pantalla
 * @author Mparr
 * @version 1.0
 * @date 23/10/24
 */
public class GestorTareas {
    /**
     * Metodo principal que ejecuta el codigo
     * @param args-Los argumentos de la linea de comandos
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<File> archivos = new ArrayList<>();
        ArrayList<Process> processes = new ArrayList<>();
        ArrayList<String> resultados = new ArrayList<>();
        System.out.println("Ingrese las  rutas de los archivos que quieres comprimir: para terminar pon 0 ");
        while(true){
            String ruta = sc.nextLine();
            if(ruta.equals("0")){
                break;
            }//Fin if
            File archivo = new File(ruta);
            if (!archivo.exists()) {
                try {
                    if (archivo.createNewFile()) {
                        System.out.println("Archivo creado: " + archivo);
                        archivos.add(archivo);
                        System.out.println("Se ha añadido el archivo para comprimir");
                    }//Fin if
                } catch (IOException e) {
                    System.err.println("No se pudo crear el archivo: " + archivo);
                    e.printStackTrace();
                }//Fin try-catch
            } else {
                System.out.println("El archivo ya existe: " + archivo);
                archivos.add(archivo);
                System.out.println("Se ha añadido el archivo para comprimir");
            }//Fin if
        }//Fin while
        comprimirArchivos(archivos, processes, resultados);
        mostrarCodigoSalida(processes, resultados, archivos);
        sc.close();
    }//Fin main


    /**
     * Metodo que muestra los resultados de la compresion de los archivos
     * @param processes-El arraylist donde estan todos los procesos
     * @param resultados-El arraylist donde estan todas las rutas de los comprimidos
     * @param archivos-El arraylist donde estan todos los archivos que se estan comprimiendo
     * @throws InterruptedException Si el metodo se interrumpe antes de finalizarse
     */
    private static void mostrarCodigoSalida(ArrayList<Process> processes, ArrayList<String> resultados, ArrayList<File> archivos) {
        for (int i = 0; i < processes.size(); i++) {
            Process proceso = processes.get(i);
            String nombreTar = resultados.get(i);
            try {
                int exitCode = proceso.waitFor();
                if (exitCode == 0) {
                    System.out.println("Compresión exitosa: " + nombreTar+"  Código de salida: " + exitCode);
                } else {
                    System.out.println("Error al comprimir " + archivos.get(i).getName() + "  Código de salida: " + exitCode);
                }//Fin if
            } catch (InterruptedException e) {
                System.err.println("El proceso fue interrumpido: " + e.getMessage());
            }//Fin try-catch
        }//Fin de for
    }//Fin de mostrarCodigoSalida


    /**
     * Metodo que ejecuta los comandos que comprimen los archivos , añade los procesos
     * y las rutas de los archivos comprimidos a unos arraylist
     * @param archivos-El arrylist de los archivos que se comprimen
     * @param processes-El arraylist donde se añaden los procesos
     * @param resultados-El arraylist estan las rutas de los comprimidos
     */
    private static void comprimirArchivos(ArrayList<File> archivos, ArrayList<Process> processes, ArrayList<String> resultados) {
        for (File archivo : archivos) {
            String nombreTar = archivo.getAbsolutePath() + ".tar";
            ProcessBuilder pb = new ProcessBuilder("tar", "-cvf", nombreTar, archivo.getAbsolutePath());
            pb.redirectErrorStream(true);
            try {
                Process proceso = pb.start();
                processes.add(proceso);
                resultados.add(nombreTar);
            } catch (IOException e) {
                System.err.println("Error al iniciar el proceso para " + archivo.getName() + ": " + e.getMessage());
            }//Fin try-catch
        }//Fin for
    }//Fin de comprimirArchivo
}//Fin de GestorTareas
