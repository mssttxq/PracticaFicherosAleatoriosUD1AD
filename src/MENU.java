import java.util.Scanner;

public class MENU {
    public static void main(String[] args) {
        Scanner teclado =new Scanner (System.in);
        String opcion;
        String nombreFichero="";

        while (true){
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("--------------------------------");
            System.out.println("MENU:");
            System.out.println("--------------------------------");
            System.out.println("1) Crear fichero directo.");
            System.out.println("2) Visualizar todos (huecos y sin los borrados)");
            System.out.println("3) Visualizar registros (sin huecos y sin los borrados)");
            System.out.println("4) Consultar registro");
            System.out.println("5) Insertar n registros");
            System.out.println("6) Subir salario a un empleado");
            System.out.println("7) Borrar un registro");
            System.out.println("\n");

            System.out.println("Escribe la opcion que quieres elegie (si quieres salir deja en vacio)");

            opcion = teclado.nextLine();

            if(opcion.isEmpty()){
                System.out.println("Saliendo del programa...");
                break;
            }
            switch (opcion){
                case "1":
                    CrearFichero.CrearFichero(nombreFichero);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                default:
                    System.out.println("Tienes que elegir una opcion valida");
            }
        }
        teclado.close();
    }
}
