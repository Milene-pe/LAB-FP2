import java.util.Scanner;

public class exercise2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String soldier1, soldier2, soldier3, soldier4, soldier5;
        int level1, level2, level3, level4, level5;

        System.out.println("Ingrese el nombre del Jugador 1:");
        soldier1 = scanner.nextLine();
        System.out.println("Ingrese el nombre del Jugador 2:");
        soldier2 = scanner.nextLine();
        System.out.println("Ingrese el nombre del Jugador 3:");
        soldier3 = scanner.nextLine();
        System.out.println("Ingrese el nombre del Jugador 4:");
        soldier4 = scanner.nextLine();
        System.out.println("Ingrese el nombre del Jugador 5:");
        soldier5 = scanner.nextLine();

        System.out.println("Ingrese el nivel del Jugador 1 (1:novato, 2:profesional, 3:veterano, 4:estrella):");
        level1 = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese el nivel del Jugador 2 (1:novato, 2:profesional, 3:veterano, 4:estrella):");
        level2 = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese el nivel del Jugador 3 (1:novato, 2:profesional, 3:veterano, 4:estrella):");
        level3 = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese el nivel del Jugador 4 (1:novato, 2:profesional, 3:veterano, 4:estrella):");
        level4 = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese el nivel del Jugador 5 (1:novato, 2:profesional, 3:veterano, 4:estrella):");
        level5 = Integer.parseInt(scanner.nextLine());

        System.out.println("Jugador 1: " + soldier1 + ", Nivel: " + obtenerNivel(level1));
        System.out.println("Jugador 2: " + soldier2 + ", Nivel: " + obtenerNivel(level2));
        System.out.println("Jugador 3: " + soldier3 + ", Nivel: " + obtenerNivel(level3));
        System.out.println("Jugador 4: " + soldier4 + ", Nivel: " + obtenerNivel(level4));
        System.out.println("Jugador 5: " + soldier5 + ", Nivel: " + obtenerNivel(level5));
    }

    // Método para obtener la categoría del nivel según el entero ingresado
    public static String obtenerNivel(int nivel) {
        String categoria;
        switch (nivel) {
            case 1:
                categoria = "Novato";
                break;
            case 2:
                categoria = "Profesional";
                break;
            case 3:
                categoria = "Veterano";
                break;
            case 4:
                categoria = "Estrella";
                break;
            default:
                categoria = "NIVEL INGRESADO NO ES VALIDO";
                break;
        }
        return categoria;
    }
}