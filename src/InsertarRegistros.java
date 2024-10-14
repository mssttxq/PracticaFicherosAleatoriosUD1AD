import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class InsertarRegistros {

    public static void InsertarRegistros(String nombreFichero, int numRegistros) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Escribe el nombre/la ruta del fichero (con extensión .dat): ");
        nombreFichero = teclado.nextLine();

        // Verifica si el nombre del fichero tiene la extensión '.dat'
        if (!nombreFichero.endsWith(".dat")) {
            System.out.println("Tienes que escribir el nombre del fichero con la extensión '.dat'.");
            return;
        }

        try {
            // Abre el archivo en modo lectura/escritura
            RandomAccessFile archivo = new RandomAccessFile(new File(nombreFichero), "rw");

            for (int i = 0; i < numRegistros; i++) {
                System.out.println("Introduce el número de empleado (no puede ser 0): ");
                int numEmpleado = teclado.nextInt();
                teclado.nextLine(); // Capturar el salto de línea restante

                // Verificar que el número de empleado no sea 0
                if (numEmpleado == 0) {
                    System.out.println("El número de empleado no puede ser 0.");
                    continue; // Saltar al siguiente registro
                }

                // Calcula la posición donde insertar el registro
                long posicion = (numEmpleado - 1) * 36;

                // Comprobar si la posición es válida (que no supere la longitud del archivo)
                if (posicion >= archivo.length()) {
                    // Si la posición supera la longitud del archivo, se puede insertar
                    archivo.seek(posicion);
                } else {
                    // Si ya hay un registro en esa posición, leer el número de empleado
                    archivo.seek(posicion);
                    int empleadoExistente = archivo.readInt();

                    // Si el número de empleado ya existe en la posición, no sobreescribimos
                    if (empleadoExistente != 0) {
                        System.out.println("El registro con ese número de empleado ya existe. No se insertará.");
                        continue;
                    }
                }

                // Pedir el apellido
                System.out.println("Introduce el apellido: ");
                String apellido = teclado.nextLine();
                apellido = String.format("%-10s", apellido); // Ajustar apellido a 10 caracteres

                // Pedir el departamento
                System.out.println("Introduce el número del departamento: ");
                int departamento = teclado.nextInt();

                // Pedir el salario
                System.out.println("Introduce el salario: ");
                double salario = teclado.nextDouble();
                teclado.nextLine(); // Capturar el salto de línea restante

                // Posicionar el puntero en la posición correcta e insertar los datos
                archivo.seek(posicion);
                archivo.writeInt(numEmpleado);  // Escribir el número de empleado
                archivo.writeUTF(apellido);     // Escribir el apellido ajustado a 10 caracteres
                archivo.writeInt(departamento); // Escribir el departamento
                archivo.writeDouble(salario);   // Escribir el salario

                System.out.println("Registro insertado correctamente.");
            }

            archivo.close(); // Cerrar el archivo después de la operación

        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        }
    }
}

