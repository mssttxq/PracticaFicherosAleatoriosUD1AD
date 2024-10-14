import java.util.Scanner;

public class APP {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String opcion;
        String nombreFichero = "";


        do {
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("-----------------------------");
            System.out.println("MENU:");
            System.out.println("-----------------------------");
            System.out.println("1) Crear fichero directo");
            System.out.println("2) Vizualizar todos (huecos y sin los borrados)");
            System.out.println("3) Vizualizar registros (sin huecos y sin los borrados)");
            System.out.println("4) Consultar registro");
            System.out.println("5) Insertar n registros");
            System.out.println("6) Subir salario a un empleado");
            System.out.println("7) Borrar una registro");
            System.out.println("\n");
            System.out.println("Escribe la opcion que quieres elegir:");
            System.out.println("(si quieres salir tienes que dejarlo vacio)");
            opcion = teclado.nextLine();

            if (opcion.isEmpty()) {
                System.out.println("Saliendo del programa...");
                break;
            }


            switch (opcion) {
                case "1":
                    CrearFichero.CrearFichero(nombreFichero);
                    break;
                case "2":
                    VizualizarTodos.VisualizarRegistros(nombreFichero);
                    break;
                case "3":
                    VisualizarSinHuecos.VisualizarSinHuecos(nombreFichero);
                    break;
                case "4":
                    Consultar.ConsultarRegistro(nombreFichero);
                    break;
                case "5":
                    System.out.println("¿Cuántos registros deseas insertar?");
                    int numRegistros = teclado.nextInt();
                    teclado.nextLine(); // Ловим оставшийся символ новой строки
                    InsertarRegistros.InsertarRegistros(nombreFichero, numRegistros);
                    break;
                case "6":
                    SubirSalario.SubirSalario(nombreFichero);
                    break;
                case "7":
                    BorrarRegistro.BorrarRegistro(nombreFichero);
                    break;
                default:
                    System.out.println("Opción no válida, por favor elige una opción del 1 al 7.");
                    break;
            }


            System.out.println("\nPresiona Enter para continuar...");
            teclado.nextLine();

        } while (true);
        teclado.close();
    }
}
