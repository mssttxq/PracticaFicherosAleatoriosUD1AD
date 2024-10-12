import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class InsertarRegistros {
    public static void InsertarRegistros(String nombreFichero, int numRegistros) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Escribe el nombre/la ruta del fichero:");
        nombreFichero = teclado.nextLine();

        if (!nombreFichero.endsWith(".dat")) {
            System.out.println("El fichero debe tener la extensión '.dat'.");
            return;
        }

        try {
            RandomAccessFile archivo = new RandomAccessFile(new File(nombreFichero), "rw");

            for (int i = 0; i < numRegistros; i++) {
                System.out.println("Introduce el número de empleado (no puede ser 0): ");
                int numEmpleado = teclado.nextInt();

                System.out.println("Introduce el apellido: ");
                teclado.nextLine(); // Consume newline
                String apellido = teclado.nextLine();

                System.out.println("Introduce el departamento: ");
                int departamento = teclado.nextInt();

                System.out.println("Introduce el salario: ");
                double salario = teclado.nextDouble();

                long posicion = (numEmpleado - 1) * 36;
                archivo.seek(posicion);

                // Verificar si ya existe el registro
                int numeroExistente = archivo.readInt();
                if (numeroExistente != 0) {
                    System.out.println("El empleado con número " + numEmpleado + " ya existe.");
                } else {
                    // Escribir los nuevos datos
                    archivo.seek(posicion);
                    archivo.writeInt(numEmpleado);
                    String apell = String.format("%-20s", apellido); // Ajusta a 20 caracteres
                    archivo.writeUTF(apell);
                    archivo.writeInt(departamento);
                    archivo.writeDouble(salario);
                    System.out.println("Registro insertado correctamente.");
                }
            }
            archivo.close();
        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        }
    }
}
