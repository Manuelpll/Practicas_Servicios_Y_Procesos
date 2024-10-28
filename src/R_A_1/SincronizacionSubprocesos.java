package R_A_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Es una clase que muestra atraves de un comando en el PowerShell de Windows
 * el numero de palabras de un archivo , el de lineas de otro archivo y un combinado de las dos
 * @author Mparr
 * @version 1.0
 * @date 21/10/24
 */
public class SincronizacionSubprocesos {
    /**
     * Metodo principal que ejecuta el codigo
     * @param args-Los argumentos de la linea de comandos
     */
    public static void main(String[] args) {
      //Zona Inicializacion
        String archivo1="src\\R_A_1\\archivo1.txt";
        String archivo2="src\\R_A_1\\archivo2.txt";
        try{
            int todasLasLineas = ejecutarEnPowerShell("Get-Content " + archivo1 + " | Measure-Object -Line");
            int totalDePalabras = ejecutarEnPowerShell("Get-Content " + archivo2 + " | Measure-Object -Word");
            //Zona de salida
            System.out.println("Total líneas en el primer archivo: " + todasLasLineas + "\n" +
                    "Total palabras en el segundo archivo: " + totalDePalabras + "\n" +
                    "Total de líneas y palabras: " + (todasLasLineas + totalDePalabras));
        }catch(IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }//Fin try-catch
    }//Fin main


    /**
     * Este metodo  ejecuta un comando en la PowerShell
     * contando las lineas o palabras del archivo y devolviendoselo al metodo principal
     * @param comando-El comando correspondiente
     * @return El numero correspondiente de lineas o palabras
     * @throws IOException Si se mete un input incorrecto
     * @throws InterruptedException Si se interrumpe el proceso antes de terminarlo
     */
    private static int ejecutarEnPowerShell(String comando) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-Command", comando);
        Process p = pb.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String resultado;
            while ((resultado = reader.readLine()) != null) {
                if (resultado.trim().matches("^\\d+$")) {
                    return Integer.parseInt(resultado.trim());
                }//Fin if
            }//Fin while
        }//Fin try
        p.waitFor();
        return 0;
    }//Fin de ejecutarEnPowerShell


    }//Fin de SincronizacionSubprocesos