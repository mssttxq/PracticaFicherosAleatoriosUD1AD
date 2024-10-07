import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class CrearFichero{

    public static void CrearFichero(String nombreFichero) {
        // Datos de los empleados
        String apellido[] = {"FERNANDEZ", "GIL", "LOPEZ", "RAMOS", "SEVILLA", "CASILLA", "REY"};
        int dep[] = {10, 20, 10, 10, 30, 30, 20};
        Double salario[] = {1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 1435.87, 2000.0};

        Scanner teclado = new Scanner(System.in);

        // Pedimos al usuario el nombre del fichero
        System.out.println("Escribe el nombre del fichero que quieres crear (con extensión .dat): ");
         nombreFichero = teclado.nextLine();

        // Comprobamos si el usuario ha dejado en blanco o no ha agregado la extensión .dat
        if (nombreFichero.isEmpty() || !nombreFichero.endsWith(".dat")) {
            System.out.println("El nombre del fichero es incorrecto. Debe tener la extensión '.dat'.");
            return;
        }

        try {
            // Crear o abrir el fichero con RandomAccessFile en modo lectura/escritura
            RandomAccessFile archivo = new RandomAccessFile(new File(nombreFichero), "rw");

            // Vamos a recorrer los arrays e insertar los datos en el fichero
            for (int i = 0; i < apellido.length; i++) {
                // Calcular el número de empleado (empieza desde 1)
                int numEmpleado = i + 1;

                // Posicionar el puntero del archivo al lugar adecuado para cada registro
                archivo.seek(i * 72); // Cada registro ocupará 72 bytes (longitud fija)

                // Escribir el número de empleado
                archivo.writeInt(numEmpleado);

                // Escribir el apellido (ajustado a 20 caracteres)
                String apell = String.format("%-20s", apellido[i]); // Rellenar a 20 caracteres
                archivo.writeUTF(apell);

                // Escribir el departamento
                archivo.writeInt(dep[i]);

                // Escribir el salario
                archivo.writeDouble(salario[i]);
            }

            System.out.println("Fichero '" + nombreFichero + "' creado correctamente con los datos de los empleados.");

            // Cerrar el archivo
            archivo.close();

        } catch (IOException e) {
            System.out.println("Error al crear o escribir en el fichero: " + e.getMessage());
        }
    }

}

