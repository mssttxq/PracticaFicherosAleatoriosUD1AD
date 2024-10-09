import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Consultar {
    public static void ConsultarRegistro(String nombreFichero) {
        Scanner teclado = new Scanner(System.in);

        // Pide al usuario el nombre del fichero que quiere consultar
        System.out.println("Escribe el nombre/la ruta del fichero (con extensión .dat): ");
        nombreFichero = teclado.nextLine();

        // Verifica si el nombre del fichero tiene la extensión '.dat'
        if (!nombreFichero.endsWith(".dat")) {
            System.out.println("Tienes que escribir el nombre del fichero con la extensión '.dat'.");
            return;
        }

        try {
            // Abre el archivo en modo lectura
            RandomAccessFile archivo = new RandomAccessFile(new File(nombreFichero), "r");

            // Pide al usuario el ID del empleado a consultar
            System.out.println("Introduce el número de empleado a consultar: ");
            int IDempleado = teclado.nextInt();

            // Calcular la posición del registro (cada registro ocupa 72 bytes)
            long posicion = (IDempleado - 1) * 36;

            // Comprobar si la posición es válida (que no supere la longitud del archivo)
            if (posicion >= archivo.length()) {
                System.out.println("El registro no existe. La posición calculada supera la longitud del fichero.");
                archivo.close();
                return; // Salir del método si la posición es inválida
            }

            // Mueve el puntero del archivo a la posición calculada
            archivo.seek(posicion);

            // Leer el número de empleado
            int numEmpleado = archivo.readInt();

            // Si el número de empleado es 0, el registro no existe
            if (numEmpleado == 0) {
                System.out.println("El registro no existe. El número de empleado es 0.");
                archivo.close();
                return;
            }
            String apellido = archivo.readUTF().trim();
            int departamento = archivo.readInt();
            double salario = archivo.readDouble();

            // Mostrar los datos del empleado
            System.out.println("Número de empleado: " + numEmpleado);
            System.out.println("Apellido: " + apellido);
            System.out.println("Departamento: " + departamento);
            System.out.println("Salario: " + salario);

            // Cerrar el archivo
            archivo.close();

        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        }
    }
}

