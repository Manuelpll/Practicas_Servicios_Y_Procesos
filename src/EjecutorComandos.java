import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Es una clase que encapsula la funcionalidad del sitema operativo y ejecuta comandos
 * con diferentes salidas y entradas.
 * @author Mparr
 * @version 1.0
 * @date 14/10/24
 */
public class EjecutorComandos {
    /**
     * Metodo principal que ejecuta el codigo
     * @param args-Los argumentos de la linea de comandos
     * @throws IOException-Si falla la entrada o la salida del archivo
     * @throws InterruptedException-Si el proceso se interumpe
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        //Zona de inicializacion :
        EjecutorComandos e = new EjecutorComandos();
        File fichero = new File("src\\salida.txt");
        //Zona de salida

        //Ejemplo 1:
        int pri_salida= e.ejecutarComando("echo Hola");
        System.out.println("Codigo de salida ejemplo 1:"+pri_salida);
        //Ejemplo 2:
        int seg_salida= e.ejecutarComandoConEntrada("echo ","Esto es lo que le paso");
        System.out.println("Codigo de salida ejemplo 2:"+seg_salida);
        //Ejemplo 3:
        int ter_salida= e.ejecutarComandoConRedireccion("echo Funciona corectamente",fichero);
        System.out.println("Codigo de salida ejemplo 3:"+ter_salida);

    }//Fin main


    /**
     * Metodo que ejecuta un comando  y
     * luego devuelve el codigo de salida si esta bien 0 y si no -3
     * @param comando-El comando que quieres ejecutar
     * @return El codigo de salida
     * @throws IOException Si se mete un input incorrecto
     * @throws InterruptedException Si se interrumpe el proceso antes de terminarlo
     */
    public int ejecutarComando(String comando)  {
          try {
              ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
              Process p = pb.start();
              p.waitFor();
              return p.exitValue();
          }catch (IOException | InterruptedException e){
              e.printStackTrace();
              return -3;
          }//Fin try-catch
    }//Fin de ejecutarComando


    /**
     * Metodo que ejecuta un comando , proporciona entrada estandar al proceso y
     * luego devuelve el codigo de salida si esta bien 0 y si no -3
     * @param comando-El comando que quieres ejecutar
     * @param entrada La entrada estandar al proceso
     * @throws IOException Si  se mete un input incorrecto
     * @throws InterruptedException Si se interrumpe el proceso antes de terminarlo
     * @return -El codigo de salida
     */
    public int ejecutarComandoConEntrada(String comando,String entrada)  {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            Process p = pb.start();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            bw.write(entrada);
            bw.flush();
            bw.close();
            p.waitFor();
            return p.exitValue();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
            return -3;
        }//Fin try-catch
    }//Fin de ejecutarComando


    /**
     *Metodo que ejecuta un comando , escribe la salida de ese comando en un fichero y
     * luego devuelve el codigo de salida si esta bien 0 y si no -3
     * @param comando-El comando que quieres ejecutar
     * @param archivoSalida-El archivo que contendra la salida del comando
     * @return El codigo de salida
     * @throws IOException Si  se mete un input incorrecto
     * @throws InterruptedException Si se interrumpe el proceso antes de terminarlo
     */
    public int ejecutarComandoConRedireccion(String comando, File archivoSalida){
        try {
            if(!archivoSalida.exists()){
                archivoSalida.createNewFile();
            }//Fin if
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            pb.redirectOutput(archivoSalida);
            Process p = pb.start();
            p.waitFor();
            return p.exitValue();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
            return -3;
        }//Fin try-catch
    }//Fin de ejecutarComandoConRedireccion
}//Fin de EjecutorComandos