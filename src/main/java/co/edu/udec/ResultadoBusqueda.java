package co.edu.udec;

public class ResultadoBusqueda {
    private final boolean encontrado;
    private final int fila;
    private final int columna;
    private final int posicionesExaminadas;

    public ResultadoBusqueda(boolean encontrado, int fila, int columna, int posicionesExaminadas) {
        this.encontrado = encontrado;
        this.fila = fila;
        this.columna = columna;
        this.posicionesExaminadas = posicionesExaminadas;
    }

    public boolean isEncontrado() { return encontrado; }
    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public int getPosicionesExaminadas() { return posicionesExaminadas; }

    @Override
    public String toString() {
        if (encontrado) {
            return "Encontrado en [fila=" + fila + ", columna=" + columna + "]" + " | posiciones examinadas: " + posicionesExaminadas;
        }
        return "No existe en el escenario | posiciones examinadas: " + posicionesExaminadas;
    }
}
