import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class BorrarRegistro {
    public static void BorrarRegistro(String nombreFichero) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Escribe el nombre/la ruta del fichero:");
        nombreFichero = teclado.nextLine();

        if (!nombreFichero.endsWith(".dat")) {
            System.out.println("El fichero debe tener la extensión '.dat'.");
            return;
        }

        try {
            RandomAccessFile archivo = new RandomAccessFile(new File(nombreFichero), "rw");

            System.out.println("Introduce el número de empleado que quieres borrar: ");
            int numEmpleado = teclado.nextInt();

            long posicion = (numEmpleado - 1) * 36;
            if (posicion >= archivo.length()) {
                System.out.println("El registro no existe.");
                archivo.close();
                return;
            }

            archivo.seek(posicion);
            int empleado = archivo.readInt();
            if (empleado == 0) {
                System.out.println("El registro ya está vacío.");
            } else {
                archivo.seek(posicion);
                archivo.writeInt(0); // Marcar como vacío
                System.out.println("Registro borrado correctamente.");
            }

            archivo.close();
        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        }
    }
}
