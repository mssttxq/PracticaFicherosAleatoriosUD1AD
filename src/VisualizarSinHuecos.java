import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class VisualizarSinHuecos {
    public static void VisualizarSinHuecos (String nombreFichero){
        Scanner teclado = new Scanner (System.in);

        System.out.println("Escribe el nombre de fichero lo de quieres visualizar información sin huecos");
        nombreFichero=teclado.nextLine();

        if(!nombreFichero.endsWith(".dat")){
            System.out.println("El fichero tiene que tener extención .dat");
            return;
        }

        try {
            RandomAccessFile archivo = new RandomAccessFile(new File(nombreFichero), "r");
            long numRegistros = archivo.length() / 36;

            System.out.println("Visualizando registros...");
            for(int i = 0; i<numRegistros; i++){
                archivo.seek(i * 36);
                int numEmpleado = archivo.readInt();

                if(numEmpleado != 0){
                    String apellido = archivo.readUTF().trim();
                    int departamento = archivo.readInt();
                    double salario = archivo.readDouble();
                    System.out.println("Numero de empleado: "+ numEmpleado);
                    System.out.println("Apellido: "+apellido);
                    System.out.println("Departamento: "+ departamento);
                    System.out.println("Salario: "+ salario);
                }
            }
            archivo.close();
        }catch (IOException e){
            System.out.println("Error al aceder al archvio: "+ e.getMessage());
        }
    }
}
