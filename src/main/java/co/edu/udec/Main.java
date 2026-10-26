package co.edu.udec;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        int[][] escenario = LocalizadorAsientos.crearEscenario(5, 8); // asientos 1..40

        System.out.println("=== LOCALIZADOR DE ASIENTOS ===");
        System.out.println("Escenario de " + escenario.length + " filas x " + escenario[0].length + " columnas\n");
        LocalizadorAsientos.imprimir(escenario);

        demostracion(escenario);
        menu(escenario);
    }

    private static void demostracion(int[][] escenario) {
        System.out.println("\n--- DEMOSTRACIÓN: lineal vs binaria ---");
        int[] casos = {1, 20, 40, 77}; // inicio, centro, final, inexistente
        String[] nombres = {"inicio", "centro", "final", "inexistente"};
        for (int i = 0; i < casos.length; i++) {
            System.out.println("\nAsiento " + casos[i] + " (" + nombres[i] + ")");
            System.out.println("  Lineal : " + LocalizadorAsientos.busquedaLineal(escenario, casos[i]));
            System.out.println("  Binaria: " + LocalizadorAsientos.busquedaBinaria(escenario, casos[i]));
        }

        System.out.println("\n--- CASO DIFERENTE: escenario con el asiento 13 omitido ---");
        int[][] sin13 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {14, 15, 16, 17}
        };
        LocalizadorAsientos.imprimir(sin13);
        System.out.println("Asiento 13 -> " + LocalizadorAsientos.busquedaBinaria(sin13, 13));
        System.out.println("Asiento 14 -> " + LocalizadorAsientos.busquedaBinaria(sin13, 14));
    }

    private static void menu(int[][] escenario) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Buscar asiento (recorrido completo)");
            System.out.println("2. Buscar asiento (búsqueda binaria)");
            System.out.println("3. Comparar ambos métodos");
            System.out.println("4. Mostrar escenario");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = leerEntero(sc);
            switch (opcion) {
                case 1:
                    System.out.print("Número de asiento: ");
                    System.out.println(LocalizadorAsientos.busquedaLineal(escenario, leerEntero(sc)));
                    break;
                case 2:
                    System.out.print("Número de asiento: ");
                    System.out.println(LocalizadorAsientos.busquedaBinaria(escenario, leerEntero(sc)));
                    break;
                case 3:
                    System.out.print("Número de asiento: ");
                    int n = leerEntero(sc);
                    System.out.println("Lineal : " + LocalizadorAsientos.busquedaLineal(escenario, n));
                    System.out.println("Binaria: " + LocalizadorAsientos.busquedaBinaria(escenario, n));
                    break;
                case 4:
                    LocalizadorAsientos.imprimir(escenario);
                    break;
                case 0:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static int leerEntero(Scanner sc) {
        if (!sc.hasNext()) return 0;
        String texto = sc.next();
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
