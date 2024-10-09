import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class VizualizarTodos {
        public static void VisualizarRegistros(String nombreFichero) {
            Scanner teclado =new Scanner (System.in);

            System.out.println("Escribe el nombre/ la ruta del fichero");
            nombreFichero=teclado.nextLine();

            // Verifica si el nombre del fichero tiene la extensión '.dat'
            if (!nombreFichero.endsWith(".dat")) {
                System.out.println("Tienes que escribir el nombre del fichero con la extensión '.dat'.");
                return;
            }
            try {
                RandomAccessFile archivo = new RandomAccessFile(new File(nombreFichero), "r");

                long numRegistros = archivo.length() / 36;

                System.out.println("Visualizando todos los registros...");


                for (int i = 0; i < numRegistros; i++) {
                    archivo.seek(i * 36);

                    int numEmpleado = archivo.readInt();

                    String apellido = archivo.readUTF().trim();

                    int departamento = archivo.readInt();

                    double salario = archivo.readDouble();

                    if (numEmpleado == 0) {
                        System.out.println("Registro " + (i + 1) + " vacío.");
                    } else {
                        System.out.println("Registro " + (i + 1) + ":");
                        System.out.println("Número de empleado: " + numEmpleado);
                        System.out.println("Apellido: " + apellido);
                        System.out.println("Departamento: " + departamento);
                        System.out.println("Salario: " + salario);
                        System.out.println("------------------------------");
                    }
                }

                archivo.close();

            } catch (IOException e) {
                System.out.println("Error al acceder al archivo: " + e.getMessage());
            }
        }


    }




