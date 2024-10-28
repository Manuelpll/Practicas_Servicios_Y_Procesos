package R_A_1;

import java.io.*;

/**
 * Este programa comprime tres archivos generando el respectivo .tar mostrando su ubicacion
 * @author Mparr
 * @version 1.0
 * @date 17/10/24
 */
public class ComprimirArchivos {
    /**
     * Metodo principal que ejecuta el codigo
     * @param args-Los argumentos de la linea de comandos
     * @throws IOException-Si falla la entrada o la salida del archivo
     * @throws InterruptedException-Si el proceso se interumpe
     */
    public static void main(String[] args) throws IOException {
        //Zona de inicializacion
        String archivoTar = "src/comprimidos.tar";
        String[] archivosAcomprimir = {"src\\R_A_1\\archivo1.txt",
                "src\\R_A_1\\archivo2.txt", "src\\R_A_1\\archivo3.txt"};//Aqui situas los archivos que quieres comprimir
        existenLosArchivos(archivosAcomprimir);//Verificacion de la existencia de los archivos
        //Zona de Ejecucion
        try {
            ProcessBuilder pb = new ProcessBuilder("tar", "-cf", archivoTar, "-T", "-");
            pb.redirectErrorStream(true);
            Process p = pb.start();
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
                for (String archivo : archivosAcomprimir) {
                    bw.write(archivo);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }//Fin try-catch
            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }//Fin while
            }catch (IOException e) {
            e.printStackTrace();
        }//Fin try-catch
            int exitCode = p.waitFor();
            comprobarComprimido(exitCode, archivoTar);
        }catch(IOException| InterruptedException e) {
        e.printStackTrace();
    }//Fin try-catch
}//Fin main

    /**
     * Metodo que comprueba si se ha comprimidos los archivos correctamente
     * @param exitCode-Codigo de salida del comando
     * @param archivoTar-Ruta donde va estar el archivo.tar
     */
    private static void comprobarComprimido(int exitCode, String archivoTar) {
        if (exitCode == 0) {
            System.out.println("Archivos comprimidos exitosamente en " + archivoTar);
        } else {
            System.out.println("Error al comprimir los archivos. Código de salida: " + exitCode);
        }//Fin if
    }

    /**
     * Metodo que verifica la existencia de los archivos y los crea si no existen
     * @param archivos-Los archivos que quieres verificar
     */
    public static void existenLosArchivos(String[] archivos) {
        for (String archivo : archivos) {
            File file = new File(archivo);
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        System.out.println("Archivo creado: " + archivo);
                    }//Fin if
                } catch (IOException e) {
                    System.err.println("No se pudo crear el archivo: " + archivo);
                    e.printStackTrace();
                }//Fin try-catch
            } else {
                System.out.println("El archivo ya existe: " + archivo);
            }//Fin if
        }//Fin for
    } //Fin de existenLosArchivos
}//Fin de ComprimirArchivos
