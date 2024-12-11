import javax.swing.*;
import java.awt.*;

public class TableroGUI {
    private JFrame frame;
    private JPanel panelTablero;
    private int filas = 10; 
    private int columnas = 10;

    public TableroGUI() {
        
        frame = new JFrame("Tablero de Batalla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(filas, columnas));

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JButton celda = new JButton();
                celda.setBackground(Color.LIGHT_GRAY);
                celda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panelTablero.add(celda);
            }
        }

        frame.add(panelTablero);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
      
        new TableroGUI();
    }
}

