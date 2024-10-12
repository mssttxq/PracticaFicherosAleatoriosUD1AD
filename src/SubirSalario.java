import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class SubirSalario {
    public static void SubirSalario (String nombreFichero){
        Scanner teclado = new Scanner (System.in);

        System.out.println("Escribe el nombre de fichero");
        nombreFichero = teclado.nextLine();

        if(!nombreFichero.endsWith(".dat")){
            System.out.println("El fcihero tiene que tener extensión .dat");
            return;
        }

        try {
            RandomAccessFile archivo = new RandomAccessFile(new File(nombreFichero), "rw");

            System.out.println("Escribe el numero de empleado");
            int numEmpleado = teclado.nextInt();

            long posicion = (numEmpleado - 1)* 36;
            if(posicion >= archivo.length()){
                System.out.println("El registro no existe");
                archivo.close();
                return;
            }

            archivo.seek(posicion);
            int empleado = archivo.readInt();
            if(empleado ==0){
                System.out.println("El registro no existe.");
                archivo.close();
                return;
            }

            archivo.readInt();
            archivo.readInt();
            double salarioActual = archivo.readDouble();

            System.out.println("Salario actual: "+ salarioActual);
            System.out.println("Escribe el inremento de salario:");
            double incremento = teclado.nextDouble();

            double nuevoSlario = salarioActual + incremento;

            archivo.seek(posicion +28);
            archivo.writeDouble(nuevoSlario);

            System.out.println("Salario acturlizado a: "+nuevoSlario);

            archivo.close();
        }catch (IOException e){
            System.out.println("No se pude acceder al archivo "+e.getMessage());
        }
    }
}
