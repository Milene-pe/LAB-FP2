import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

// Clase Soldado
class Soldado {
    private String nombre;
    private int fila;
    private int columna;

    public Soldado(String nombre, int fila, int columna) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}

// Clase Ejercito
class Ejercito {
    private ArrayList<Soldado> soldados;
    private String nombre;
    private Color color;

    public Ejercito(String nombre, Color color) {
        this.nombre = nombre;
        this.color = color;
        this.soldados = new ArrayList<>();
    }

    public void generarSoldados(int cantidad, int filas, int columnas) {
        Random random = new Random();
        Set<String> posicionesOcupadas = new HashSet<>();

        for (int i = 0; i < cantidad; i++) {
            int fila, columna;
            String posicion;
            do {
                fila = random.nextInt(filas);
                columna = random.nextInt(columnas);
                posicion = fila + "," + columna;
            } while (posicionesOcupadas.contains(posicion));

            posicionesOcupadas.add(posicion);
            soldados.add(new Soldado(nombre + "-Soldado" + (i + 1), fila, columna));
        }
    }

    public ArrayList<Soldado> getSoldados() {
        return soldados;
    }

    public Color getColor() {
        return color;
    }
}
public class MapGUI {
    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mapa de Soldados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        JPanel gridPanel = new JPanel(new GridLayout(FILAS, COLUMNAS));
        JLabel[][] celdas = new JLabel[FILAS][COLUMNAS];

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                celdas[i][j] = new JLabel("", SwingConstants.CENTER);
                celdas[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel.add(celdas[i][j]);
            }
        }
        
        Ejercito ejercito1 = new Ejercito("Rojo", Color.RED);
        Ejercito ejercito2 = new Ejercito("Azul", Color.BLUE);

        ejercito1.generarSoldados(10, FILAS, COLUMNAS);
        ejercito2.generarSoldados(10, FILAS, COLUMNAS);

        colocarSoldadosEnMapa(ejercito1, celdas);
        colocarSoldadosEnMapa(ejercito2, celdas);

        frame.add(gridPanel);
        frame.setVisible(true);
    }

    private static void colocarSoldadosEnMapa(Ejercito ejercito, JLabel[][] celdas) {
        for (Soldado soldado : ejercito.getSoldados()) {
            int fila = soldado.getFila();
            int columna = soldado.getColumna();
            celdas[fila][columna].setText((char) ('A' + fila) + String.valueOf(columna + 1));
            celdas[fila][columna].setOpaque(true);
            celdas[fila][columna].setBackground(ejercito.getColor());
            celdas[fila][columna].setForeground(Color.WHITE);
        }
    }
}

