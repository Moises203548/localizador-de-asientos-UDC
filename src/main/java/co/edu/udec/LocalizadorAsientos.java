package co.edu.udec;

public class LocalizadorAsientos {

    private LocalizadorAsientos() {}

    public static int[][] crearEscenario(int filas, int columnas) {
        if (filas <= 0 || columnas <= 0) {
            throw new IllegalArgumentException("Las filas y columnas deben ser mayores que 0");
        }
        int[][] asientos = new int[filas][columnas];
        int numero = 1;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                asientos[i][j] = numero++;
            }
        }
        return asientos;
    }

    public static boolean esMatrizValida(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) return false;
        for (int[] fila : m) {
            if (fila == null || fila.length != m[0].length) return false;
        }
        return true;
    }

    public static boolean estaOrdenadaGlobalmente(int[][] m) {
        if (!esMatrizValida(m)) return false;
        int anterior = m[0][0];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (i == 0 && j == 0) continue;
                if (m[i][j] <= anterior) return false;
                anterior = m[i][j];
            }
        }
        return true;
    }


    public static ResultadoBusqueda busquedaLineal(int[][] m, int numeroAsiento) {
        if (!esMatrizValida(m)) return new ResultadoBusqueda(false, -1, -1, 0);
        int examinadas = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                examinadas++;
                if (m[i][j] == numeroAsiento) {
                    return new ResultadoBusqueda(true, i, j, examinadas);
                }
            }
        }
        return new ResultadoBusqueda(false, -1, -1, examinadas);
    }


    public static ResultadoBusqueda busquedaBinaria(int[][] m, int numeroAsiento) {
        if (!esMatrizValida(m)) return new ResultadoBusqueda(false, -1, -1, 0);
        if (!estaOrdenadaGlobalmente(m)) {
            throw new IllegalStateException("La búsqueda binaria requiere una matriz ordenada globalmente");
        }
        int columnas = m[0].length;
        int bajo = 0;
        int alto = m.length * columnas - 1;
        int examinadas = 0;
        while (bajo <= alto) {
            int medio = bajo + ((alto - bajo) >>> 1);
            int fila = medio / columnas;
            int columna = medio % columnas;
            int valor = m[fila][columna];
            examinadas++;
            if (valor == numeroAsiento) {
                return new ResultadoBusqueda(true, fila, columna, examinadas);
            }
            if (valor < numeroAsiento) {
                bajo = medio + 1;
            } else {
                alto = medio - 1;
            }
        }
        return new ResultadoBusqueda(false, -1, -1, examinadas);
    }

    public static void imprimir(int[][] m) {
        for (int[] fila : m) {
            StringBuilder sb = new StringBuilder();
            for (int v : fila) sb.append(String.format("%4d", v));
            System.out.println(sb);
        }
    }
}
