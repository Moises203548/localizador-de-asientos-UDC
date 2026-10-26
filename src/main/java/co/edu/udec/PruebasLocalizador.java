package co.edu.udec;

public class PruebasLocalizador {
    private static int total = 0;
    private static int fallos = 0;

    private static void verificar(String nombre, boolean condicion) {
        total++;
        if (condicion) {
            System.out.println("[OK]    " + nombre);
        } else {
            fallos++;
            System.out.println("[FALLO] " + nombre);
        }
    }

    public static void main(String[] args) {
        int[][] e = LocalizadorAsientos.crearEscenario(4, 5);

        ResultadoBusqueda r = LocalizadorAsientos.busquedaLineal(e, 1);
        verificar("Lineal: asiento 1 en [0,0], 1 examinada",
                r.isEncontrado() && r.getFila() == 0 && r.getColumna() == 0 && r.getPosicionesExaminadas() == 1);
        r = LocalizadorAsientos.busquedaLineal(e, 20);
        verificar("Lineal: asiento 20 en [3,4], 20 examinadas",
                r.isEncontrado() && r.getFila() == 3 && r.getColumna() == 4 && r.getPosicionesExaminadas() == 20);
        r = LocalizadorAsientos.busquedaBinaria(e, 13);
        verificar("Binaria: asiento 13 en [2,2]",
                r.isEncontrado() && r.getFila() == 2 && r.getColumna() == 2);
        r = LocalizadorAsientos.busquedaBinaria(e, 20);
        verificar("Binaria: asiento 20 en [3,4]",
                r.isEncontrado() && r.getFila() == 3 && r.getColumna() == 4);

        verificar("Lineal: asiento 99 no existe", !LocalizadorAsientos.busquedaLineal(e, 99).isEncontrado());
        verificar("Binaria: asiento 0 no existe", !LocalizadorAsientos.busquedaBinaria(e, 0).isEncontrado());
        verificar("Binaria: asiento 21 no existe", !LocalizadorAsientos.busquedaBinaria(e, 21).isEncontrado());

        boolean coinciden = true;
        for (int n = 1; n <= 20; n++) {
            ResultadoBusqueda a = LocalizadorAsientos.busquedaLineal(e, n);
            ResultadoBusqueda b = LocalizadorAsientos.busquedaBinaria(e, n);
            if (a.getFila() != b.getFila() || a.getColumna() != b.getColumna()) coinciden = false;
        }
        verificar("Ambos métodos coinciden para los 20 asientos", coinciden);

        int[][] sin13 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {14, 15, 16, 17}
        };
        verificar("Matriz con asiento omitido está ordenada", LocalizadorAsientos.estaOrdenadaGlobalmente(sin13));
        verificar("Binaria: asiento 13 omitido no existe", !LocalizadorAsientos.busquedaBinaria(sin13, 13).isEncontrado());
        verificar("Binaria: asiento 14 en [3,0]", LocalizadorAsientos.busquedaBinaria(sin13, 14).getFila() == 3);

        int[][] desordenada = {{5, 1, 3}, {9, 7, 2}};
        verificar("Detecta matriz desordenada", !LocalizadorAsientos.estaOrdenadaGlobalmente(desordenada));
        boolean lanzo = false;
        try {
            LocalizadorAsientos.busquedaBinaria(desordenada, 7);
        } catch (IllegalStateException ex) {
            lanzo = true;
        }
        verificar("Binaria lanza excepción si no hay orden", lanzo);
        verificar("Lineal sí funciona en matriz desordenada", LocalizadorAsientos.busquedaLineal(desordenada, 7).isEncontrado());

        verificar("Matriz nula no rompe la búsqueda", !LocalizadorAsientos.busquedaLineal(null, 1).isEncontrado());
        verificar("Matriz vacía no rompe la búsqueda", !LocalizadorAsientos.busquedaBinaria(new int[0][0], 1).isEncontrado());
        int[][] uno = LocalizadorAsientos.crearEscenario(1, 1);
        verificar("Escenario de 1 asiento", LocalizadorAsientos.busquedaBinaria(uno, 1).isEncontrado());

        System.out.println();
        System.out.println("Pruebas: " + (total - fallos) + "/" + total + " correctas");
        if (fallos > 0) System.exit(1);
    }
}
