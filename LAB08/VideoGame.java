import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class VideoGame {
    public static final int BOARD_SIZE = 10;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        String response;
        Scanner sc = new Scanner(System.in);

        do {
            Soldier[][] board = new Soldier[BOARD_SIZE][BOARD_SIZE];
            ArrayList<Soldier> army1 = new ArrayList<Soldier>();
            ArrayList<Soldier> army2 = new ArrayList<Soldier>();

            createArmy(board, army1, 1);
            createArmy(board, army2, 2);

            printGameBoard(board);

            System.out.println(ANSI_GREEN + "\nArmy Number 1" + ANSI_RESET);
            showArmyStatistics(army1);

            System.out.println(ANSI_CYAN + "\nArmy Number 2" + ANSI_RESET);
            showArmyStatistics(army2);

            determineWinner(army1, army2);
            System.out.print("\nDo you want to continue playing? (y/n) ");
            response = sc.next();

        } while (response.equalsIgnoreCase("y"));

        sc.close();
    }

    public static void createArmy(Soldier[][] board, ArrayList<Soldier> army, int armyNumber) {
        Random r = new Random();

        int numSoldiers = r.nextInt(Soldier.MAX_SOLDIERS_PER_ARMY) + 1;

        for (int i = 0; i < numSoldiers; i++) {
            String name = "Soldier" + i + "X" + armyNumber;
            int healthLevel = r.nextInt(Soldier.MAX_HEALTH - Soldier.MIN_HEALTH + 1) + Soldier.MIN_HEALTH;
            int attackLevel = r.nextInt(Soldier.MAX_HEALTH - Soldier.MIN_HEALTH + 1) + Soldier.MIN_HEALTH;
            int defenseLevel = r.nextInt(Soldier.MAX_HEALTH - Soldier.MIN_HEALTH + 1) + Soldier.MIN_HEALTH;
            int row = r.nextInt(BOARD_SIZE);
            int column = r.nextInt(BOARD_SIZE);

            while (board[row][column] != null) {
                row = r.nextInt(BOARD_SIZE);
                column = r.nextInt(BOARD_SIZE);
            }

            army.add(new Soldier(name, attackLevel, defenseLevel, healthLevel, row, column, armyNumber));
            board[row][column] = army.get(i);
        }
    }

    public static void printGameBoard(Soldier[][] board) {
        String cellSeparator = "      |";
        String[] columnLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

        System.out.println(" ____________________________________________________________________________");

        printEmptyRow();
        System.out.print("\n|      |");

        for (String letter : columnLetters)
            System.out.printf("  %s.  |", letter);

        printRowSeparator();

        for (int i = 0; i < board.length; i++) {
            System.out.println();
            printEmptyRow();

            System.out.printf("\n|  %02d  |", (i + 1));

            for (int j = 0; j < board[i].length; j++)
                if (board[i][j] != null) {
                    String color = (board[i][j].getArmy() == 1) ? ANSI_GREEN : ANSI_CYAN;
                    String format = String.format("%02d", board[i][j].getHealthLevel());
                    System.out.print(color + "  " + format + ANSI_RESET + "  |");
                } else
                    System.out.print(cellSeparator);

            printRowSeparator();
        }

        System.out.println();
    }

    public static void printEmptyRow() {
        String cellSeparator = "      |";
        System.out.print("|");

        for (int j = 0; j < 11; j++) {
            System.out.print(cellSeparator);
        }
    }

    public static void printRowSeparator() {
        String rowSeparator = "|";
        System.out.print("\n|");

        for (int k = 0; k < 11; k++) {
            System.out.print(rowSeparator);
        }
    }

    public static void showArmyStatistics(ArrayList<Soldier> army) {
        System.out.println("\nData of all soldiers in order of creation:\n");
        displaySoldiers(army);
        System.out.println("\nSoldier with the highest health: " + findSoldierWithMaxHealth(army).getName());
        System.out.println("\nAverage health points: " + calculateAverageHealth(army));
        System.out.println("\nTotal army health level: " + getTotalArmyHealth(army));
        System.out.println("\nPower ranking of soldiers:\n");
        if ((int) (Math.random() + 1) == 1)
            sortByPointsBubble(army);
        else
            sortByPointsInsertion(army);
        displaySoldiers(army);
    }

    public static Soldier findSoldierWithMaxHealth(ArrayList<Soldier> army) {
        Soldier soldierWithMaxHealth = army.get(0);

        for (Soldier soldier : army) {
            if (soldier.getHealthLevel() > soldierWithMaxHealth.getHealthLevel()) {
                soldierWithMaxHealth = soldier;
            }
        }

        return soldierWithMaxHealth;
    }

    public static double calculateAverageHealth(ArrayList<Soldier> army) {
        double totalHealth = getTotalArmyHealth(army);

        return totalHealth / army.size();
    }

    public static int getTotalArmyHealth(ArrayList<Soldier> army) {
        int totalHealth = 0;

        for (Soldier soldier : army) {
            totalHealth += soldier.getHealthLevel();
        }

        return totalHealth;
    }

    public static void displaySoldiers(ArrayList<Soldier> army) {
        for (Soldier soldier : army) {
            System.out.println(soldier);
        }
    }

    public static void sortByPointsBubble(ArrayList<Soldier> army) {
        for (int i = 0; i < army.size() - 1; i++) {
            for (int j = 0; j < army.size() - 1 - i; j++) {
                if (army.get(j + 1).getHealthLevel() > army.get(j).getHealthLevel()) {
                    Soldier temp = army.get(j);
                    army.set(j, army.get(j + 1));
                    army.set(j + 1, temp);
                }
            }
        }
    }

    public static void sortByPointsInsertion(ArrayList<Soldier> army) {
        for (int i = 1; i < army.size(); i++) {
            Soldier temp = army.get(i);
            int j = i - 1;
            while (j >= 0 && temp.getHealthLevel() > army.get(j).getHealthLevel()) {
                army.set(j + 1, army.get(j));
                j--;
            }
            army.set(j + 1, temp);
        }
    }

    public static void determineWinner(ArrayList<Soldier> army1, ArrayList<Soldier> army2) {
        int totalHealthLevelArmy1 = getTotalArmyHealth(army1);
        int totalHealthLevelArmy2 = getTotalArmyHealth(army2);

        System.out.println("\nHealth Scores:\n");
        System.out.println(ANSI_GREEN + "Army 1: " + totalHealthLevelArmy1 + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Army 2: " + totalHealthLevelArmy2 + ANSI_RESET);

        if (totalHealthLevelArmy1 > totalHealthLevelArmy2) {
            System.out.println(ANSI_GREEN + "\nArmy 1 has won the war!" + ANSI_RESET);
        } else if (totalHealthLevelArmy2 > totalHealthLevelArmy1) {
            System.out.println(ANSI_CYAN + "\nArmy 2 has won the war!" + ANSI_RESET);
        } else {
            System.out.println("\nThe armies have tied.");
        }
    }
}