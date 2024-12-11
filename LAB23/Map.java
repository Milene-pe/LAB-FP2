
public class Map {
    private Soldier[][] map;

    private static TerrainType territory;

    public final static int MAP_SIZE = 10;
    public final static String RESET = "\u001B[0m";

    public Map() {
        map = new Soldier[MAP_SIZE][MAP_SIZE];
    }

    public Soldier getSoldier(int row, int column) {
        return map[row][column];
    }

    public void setSoldier(int row, int column, Soldier soldier) {
        map[row][column] = soldier;
    }

    public void removeSoldier(int row, int column) {
        map[row][column] = null;
    }

    public boolean isOccupied(int row, int column) {
        if (!isValidCoordinate(row, column))
            return false;

        return map[row][column] != null;
    }

    public boolean isOccupiedByEnemy(int row, int column, Kingdom kingdom) {
        return isOccupied(row, column) && map[row][column].getKingdom() != kingdom;
    }

    public boolean isOccupiedByAlly(int row, int column, Kingdom kingdom) {
        return isOccupied(row, column) && map[row][column].getKingdom() == kingdom;
    }

    public boolean isValidCoordinate(int row, int column) {
        return row >= 0 && row < MAP_SIZE && column >= 0 && column < MAP_SIZE;
    }

    public void printMap() {
        String cellSeparator = "      |";
        String[] columnLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

        System.out.println("\nGame Board\n");

        System.out.println(" ____________________________________________________________________________");

        printEmptyRow();
        System.out.print("\n|      |");

        for (String letter : columnLetters)
            System.out.printf("  %s.  |", letter);

        printRowSeparator();

        for (int i = 0; i < map.length; i++) {
            System.out.println();
            printEmptyRow();

            System.out.printf("\n|  %02d  |", (i + 1));

            for (int j = 0; j < map[i].length; j++)
                if (map[i][j] != null)
                    System.out.print("  " + map[i][j].getAcronym() + " |");
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

    public static TerrainType getTerritory() {
        return territory;
    }

    public static void chooseRandomTerritory() {
        territory = TerrainType.values()[(int) (Math.random() * TerrainType.values().length)];
    }

    public static void applyTerritoryBonus(Army kingdom) {
        if (territory.hasBonus(kingdom.getKingdom())) {
            System.out.println("\nThe kingdom " + kingdom.getKingdom().getColor() + kingdom.getKingdom().getName()
                    + RESET + " has received a +1 health bonus for its soldiers due to its territorial advantage in "
                    + territory + ".");
            kingdom.increaseHealthOfArmy(1);
        }
    }
}