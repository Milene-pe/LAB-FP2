
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Map {
    private Soldier[][] boardOfSoldiers;

    private static String territory;
    private static HashMap<String, Set<String>> KINGDOM_TERRITORY_BONUSES = new HashMap<>();

    public static final int BOARD_SIZE = 10;

    static {
        Set<String> territories;

        territories = new HashSet<>();
        territories.add("Forest");
        KINGDOM_TERRITORY_BONUSES.put("England", territories);

        territories = new HashSet<>();
        territories.add("Open Field");
        KINGDOM_TERRITORY_BONUSES.put("France", territories);

        territories = new HashSet<>();
        territories.add("Mountain");
        KINGDOM_TERRITORY_BONUSES.put("Castile-Aragon", territories);

        territories = new HashSet<>();
        territories.add("Desert");
        KINGDOM_TERRITORY_BONUSES.put("Moors", territories);

        territories = new HashSet<>();
        territories.add("Forest");
        territories.add("Beach");
        territories.add("Open Field");
        KINGDOM_TERRITORY_BONUSES.put("Holy Roman Empire", territories);
    }

    public Map() {
        boardOfSoldiers = new Soldier[BOARD_SIZE][BOARD_SIZE];
    }

    public Soldier getSoldier(int row, int column) {
        return boardOfSoldiers[row][column];
    }

    public void setSoldier(int row, int column, Soldier soldier) {
        boardOfSoldiers[row][column] = soldier;
    }

    public void printGameBoardOfSoldiers() {
        String cellSeparator = "      |";
        String[] columnLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

        System.out.println("\nGame Board\n");

        System.out.println(" ____________________________________________________________________________");

        printEmptyRow();
        System.out.print("\n|      |");

        for (String letter : columnLetters)
            System.out.printf("  %s.  |", letter);

        printRowSeparator();

        for (int i = 0; i < boardOfSoldiers.length; i++) {
            System.out.println();
            printEmptyRow();

            System.out.printf("\n|  %02d  |", (i + 1));

            for (int j = 0; j < boardOfSoldiers[i].length; j++)
                if (boardOfSoldiers[i][j] != null)
                    System.out.print("  " + boardOfSoldiers[i][j].getAcronym() + " |");
                else
                    System.out.print(cellSeparator);

            printRowSeparator();
        }

        System.out.println();
    }

    private void printEmptyRow() {
        String cellSeparator = "      |";
        System.out.print("|");

        for (int j = 0; j < 11; j++)
            System.out.print(cellSeparator);
    }

    private void printRowSeparator() {
        String rowSeparator = "______|";
        System.out.print("\n|");

        for (int k = 0; k < 11; k++)
            System.out.print(rowSeparator);
    }

    public static String chooseRandomTerritory() {
        Random random = new Random();
        List<String> territories = Arrays.asList("Forest", "Open Field", "Mountain", "Desert", "Beach");

        int randomIndex = random.nextInt(territories.size());
        territory = territories.get(randomIndex);

        return territory;
    }

    public static String getTerritory() {
        return territory;
    }

    public static void applyTerritorialBonus(Army kingdom, String kingdomName) {
        String color = Army.KINGDOM_COLORS.get(kingdomName);

        if (KINGDOM_TERRITORY_BONUSES.get(kingdomName).contains(territory)) {
            System.out.println("\nThe kingdom " + color + kingdomName + Army.KINGDOM_COLORS.get("")
                    + " has received a +1 health bonus for its soldiers due to its territorial advantage in "
                    + territory + ".");

            kingdom.increaseHealthOfArmy(1);
        }
    }
}