public class Tablero {
    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;
    private static final String VACIO = " ";

    public static void main(String[] args) {
        String[][] tablero = new String[FILAS][COLUMNAS];
        inicializarTablero(tablero);
        mostrarTablero(tablero);
    }

    public static void inicializarTablero(String[][] tablero) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = VACIO;
            }
        }
    }

    public static void mostrarTablero(String[][] tablero) {
        System.out.print("   "); // Espacio para alinear las columnas
        for (int j = 0; j < COLUMNAS; j++) {
            char columna = (char) ('A' + j);
            System.out.print(columna + "   ");
        }
        System.out.println();

        for (int i = 0; i < FILAS; i++) {
            System.out.print((i + 1) + "  "); // Numerar las filas
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print("+---");
            }
            System.out.println("+");

            System.out.print("   "); // Espacio para alinear las columnas
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print("| " + tablero[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.print("   "); // Espacio para alinear las columnas
        for (int j = 0; j < COLUMNAS; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}


