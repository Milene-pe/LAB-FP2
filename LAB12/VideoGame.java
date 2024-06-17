
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class VideoGame {
    private static final int BOARD_SIZE = 10;

    private static Army[][] boardOfArmies;
    private static ArrayList<Army> kingdom1;
    private static ArrayList<Army> kingdom2;
    private static Soldier[][] boardOfSoldiers;

    public static void main(String[] args) {
        String response;
        Scanner sc = new Scanner(System.in);

        do {
            setupGame();

            playGame(sc);

            System.out.print("\nDo you want to continue playing? (y/n) ");
            response = sc.next();
        } while (response.equalsIgnoreCase("y"));

        sc.close();
    }

    public static void setupGame() {
        Army.resetArmyCounts();
        boardOfArmies = new Army[BOARD_SIZE][BOARD_SIZE];
        kingdom1 = new ArrayList<>();
        kingdom2 = new ArrayList<>();

        String[] kingdomNames = Army.selectTwoRandomKingdoms();

        createKingdom(kingdom1, kingdomNames[0]);
        menuOfSoldier(kingdom1, new Scanner(System.in));
        createKingdom(kingdom2, kingdomNames[1]);
        menuOfSoldier(kingdom2, new Scanner(System.in));
        displayGameInformation();
    }

    public static void displayGameInformation() {
        printGameBoardOfArmy();
        Army.displayKingdomCounts();
        displayKingdom(kingdom1);
        displayKingdom(kingdom2);
    }

    public static void createKingdom(ArrayList<Army> kingdom, String kingdomName) {
        System.out.println(Army.KINGDOM_COLORS.get(kingdomName));
        System.out.println("Kingdom management: " + kingdomName + Army.KINGDOM_COLORS.get(""));
        System.out.println("\n1. Auto-generate Armies");
        System.out.println("2. Input Army Data Manually");
        System.out.print("Enter your choice: ");

        int choice = getUserChoice(new Scanner(System.in));

        switch (choice) {
            case 1:
                autoGenerateKingdom(kingdom, kingdomName);
                break;
            case 2:
                manualGenerateKingdom(kingdom, kingdomName, new Scanner(System.in));
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 2.");
                createKingdom(kingdom, kingdomName);
        }
    }

    public static void autoGenerateKingdom(ArrayList<Army> kingdom, String kingdomName) {
        Random random = new Random();

        int numberOfArmies = random.nextInt(Army.MAX_ARMIES_PER_KINGDOM) + 1;

        for (int i = 0; i < numberOfArmies; i++) {
            int row = random.nextInt(BOARD_SIZE);
            int column = random.nextInt(BOARD_SIZE);

            while (boardOfArmies[row][column] != null) {
                row = random.nextInt(BOARD_SIZE);
                column = random.nextInt(BOARD_SIZE);
            }

            kingdom.add(new Army(kingdomName, row, column));
            boardOfArmies[row][column] = kingdom.get(i);
        }
    }

    public static void manualGenerateKingdom(ArrayList<Army> kingdom, String kingdomName, Scanner sc) {
        final int MIN_ARMIES = Army.MIN_ARMIES_PER_KINGDOM;
        final int MAX_ARMIES = Army.MAX_ARMIES_PER_KINGDOM;

        int numberOfArmies;
        do {
            System.out.print("\nEnter the number of armies to add to the kingdom (1 to 10): ");
            numberOfArmies = getUserChoice(sc);
        } while (numberOfArmies < MIN_ARMIES || numberOfArmies > MAX_ARMIES);

        String position;
        for (int i = 0; i < numberOfArmies; i++) {
            int row = 0, column = 0;
            do {
                System.out.print("\nEnter the position (e.g. 1A): ");
                position = sc.next().toUpperCase();

                try {
                    HashMap<String, Integer> rowColumn = convertPosition(position);
                    row = rowColumn.get("row");
                    column = rowColumn.get("column");
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("\nInvalid position. Try again.");
                }
            } while (!position.matches("^(10|[1-9])[A-J]$") || boardOfArmies[row][column] != null);

            ArrayList<Soldier> soldiers = createSoldiers(kingdomName, sc);
            kingdom.add(new Army(kingdomName, row, column, soldiers));
            boardOfArmies[row][column] = kingdom.get(i);
            System.out.println("\nArmy created successfully!");
            printGameBoardOfArmy();
        }
    }

    public static ArrayList<Soldier> createSoldiers(String kingdom, Scanner sc) {
        final int MIN_SOLDIERS = Soldier.MIN_SOLDIERS_PER_ARMY;
        final int MAX_SOLDIERS = Soldier.MAX_SOLDIERS_PER_ARMY;

        int numberOfSoldiers;
        do {
            System.out.print("\nEnter the number of soldiers to add to the army (1 to 10): ");
            numberOfSoldiers = getUserChoice(sc);
        } while (numberOfSoldiers < MIN_SOLDIERS || numberOfSoldiers > MAX_SOLDIERS);

        ArrayList<Soldier> soldiers = new ArrayList<>();

        for (int i = 0; i < numberOfSoldiers; i++) {
            System.out.print("\nEnter the soldier's name: ");
            String name = sc.next();
            System.out.print("Enter attack level: ");
            int attackLevel = getUserChoice(sc);
            System.out.print("Enter defense level: ");
            int defenseLevel = getUserChoice(sc);
            System.out.print("Enter health level: ");
            int healthLevel = getUserChoice(sc);
            soldiers.add(new Soldier(name, kingdom, attackLevel, defenseLevel, healthLevel, 0, 0));

            System.out.println("\nSoldier created successfully!");
        }

        return soldiers;
    }

    public static void displayMenu() {
        System.out.println("\n1. Modify Army Soldiers");
        System.out.println("2. Delete Army Soldiers");
        System.out.println("3. Find Soldier with Highest Attack");
        System.out.println("4. View Army Details");
        System.out.println("5. View Power Ranking");
        System.out.print("Enter your choice: ");
    }

    public static void menuOfSoldier(ArrayList<Army> kingdom, Scanner sc) {
        displayMenu();
        int choice = getUserChoice(sc);

        printGameBoardOfArmy();
        Army currentArmy = selectArmy(kingdom, sc);

        switch (choice) {
            case 1:
                modifySoldier(currentArmy.getMySoldiers(), sc);
                break;
            case 2:
                removeSoldier(currentArmy.getMySoldiers(), sc);
                break;
            case 3:
                System.out.println("\n" + currentArmy.findSoldierWithMaxHighestAttack());
                break;
            case 4:
                System.out.println("\n" + currentArmy.toString());
                break;
            case 5:
                currentArmy.viewPowerRanking();
                break;
            default:
                menuOfSoldier(kingdom, sc);
        }
    }

    public static void modifySoldier(ArrayList<Soldier> army, Scanner sc) {
        int position = -1;
        do {
            for (int i = 0; i < army.size(); i++)
                System.out.println((i + 1) + ". " + army.get(i));

            System.out.print("\nEnter the number of the soldier: ");
            position = getUserChoice(sc) - 1;
        } while (position < 0 || position >= army.size());

        Soldier soldierToModify = army.get(position);

        int option, newValue = 0;
        do {
            displayModifySoldierMenu();
            option = getUserChoice(sc);
        } while (option < 1 || option > 3);

        do {
            try {
                System.out.print("\nEnter the new value: ");
                newValue = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } while (newValue < 0);

        switch (option) {
            case 1:
                soldierToModify.setAttackLevel(newValue);
                break;
            case 2:
                soldierToModify.setDefenseLevel(newValue);
                break;
            case 3:
                soldierToModify.setCurrentHealth(newValue);
                break;
        }
        System.out.println("\nThe soldier has been successfully modified.");
    }

    public static void displayModifySoldierMenu() {
        System.out.println("\nSoldier Modification Submenu:");
        System.out.println("1. Modify attack level");
        System.out.println("2. Modify defense level");
        System.out.println("3. Modify current health");
    }

    public static void removeSoldier(ArrayList<Soldier> army, Scanner sc) {
        if (army.size() == Soldier.MIN_SOLDIERS_PER_ARMY) {
            System.out.println("\nUnable to delete the last soldier in the army. At least one soldier must remain.");
            return;
        }

        int position = -1;
        do {
            for (int i = 0; i < army.size(); i++)
                System.out.println((i + 1) + ". " + army.get(i));

            System.out.print("\nEnter the number of the soldier: ");
            position = getUserChoice(sc) - 1;
        } while (position < 0 || position >= army.size());

        Soldier removedSoldier = army.get(position);
        removedSoldier.die();
        army.remove(removedSoldier);
        System.out.println("\nSoldier successfully eliminated.");
    }

    public static void printGameBoardOfArmy() {
        String cellSeparator = "      |";
        String[] columnLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

        System.out.println(" ____________________________________________________________________________");

        printEmptyRow();
        System.out.print("\n|      |");

        for (String letter : columnLetters)
            System.out.printf("  %s.  |", letter);

        printRowSeparator();

        for (int i = 0; i < boardOfArmies.length; i++) {
            System.out.println();
            printEmptyRow();

            System.out.printf("\n|  %02d  |", (i + 1));

            for (int j = 0; j < boardOfArmies[i].length; j++)
                if (boardOfArmies[i][j] != null) {
                    String color = boardOfArmies[i][j].getKingdomColor();
                    String format = String.format("%02d", boardOfArmies[i][j].calculateTotalHealth());
                    System.out.print(color + "  " + format + Army.KINGDOM_COLORS.get("") + "  |");
                } else
                    System.out.print(cellSeparator);

            printRowSeparator();
        }

        System.out.println();
    }

    public static void printEmptyRow() {
        String cellSeparator = "      |";
        System.out.print("|");

        for (int j = 0; j < 11; j++)
            System.out.print(cellSeparator);
    }

    public static void printRowSeparator() {
        String rowSeparator = "______|";
        System.out.print("\n|");

        for (int k = 0; k < 11; k++)
            System.out.print(rowSeparator);
    }

    public static void displayKingdom(ArrayList<Army> kingdom) {
        String color = kingdom.get(0).getKingdomColor();
        String kingdomName = kingdom.get(0).getKingdom();

        System.out.println("\n" + color + kingdomName + Army.KINGDOM_COLORS.get("") + ".");

        for (int i = 0; i < kingdom.size(); i++) {
            System.out.println(kingdom.get(i));
        }
    }

    public static void playGame(Scanner sc) {
        int currentTurn = 1;
        String winner = null;

        while (winner == null) {
            ArrayList<Army> kingdom = (currentTurn == 1) ? kingdom1 : kingdom2;

            printGameBoardOfArmy();
            playArmyTurn(kingdom, sc);
            Army.displayKingdomCounts();

            currentTurn = (currentTurn == 1) ? 2 : 1;

            winner = determineWinnerOfArmies();
        }

        printGameBoardOfArmy();
        String color = Army.KINGDOM_COLORS.get(winner);
        System.out.print("\n" + color + winner + " has won the war!" + Army.KINGDOM_COLORS.get(""));
    }

    public static void playArmyTurn(ArrayList<Army> kingdom, Scanner sc) {
        String kingdomName = kingdom.get(0).getKingdom();
        String color = kingdom.get(0).getKingdomColor();
        System.out.println(color + "\nTurn of the Kingdom of " + kingdomName + "." + Army.KINGDOM_COLORS.get(""));
        Army army = selectArmy(kingdom, sc);
        System.out.println("\n" + army);
        ArrayList<String> validMoves = getValidMovesForArmy(army);
        String coordinate = selectMove(validMoves, sc);
        moveArmy(army, coordinate, sc);
    }

    public static Army selectArmy(ArrayList<Army> kingdom, Scanner sc) {
        Army army = null;
        String position;

        do {
            System.out.print("\nSelect a army (e.g. 1A): ");
            position = sc.next().toUpperCase();

            try {
                HashMap<String, Integer> rowColumn = convertPosition(position);
                int row = rowColumn.get("row"), column = rowColumn.get("column");
                army = boardOfArmies[row][column];
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("\nInvalid position. Try again.");
            }
        } while (!position.matches("^(10|[1-9])[A-J]$") || !kingdom.contains(army));

        return army;
    }

    public static HashMap<String, Integer> convertPosition(String position) {
        HashMap<String, Integer> rowColumn = new HashMap<>();

        char column = Character.toUpperCase(position.charAt(position.length() - 1));
        int row = Integer.parseInt(position.substring(0, position.length() - 1)) - 1;
        int col = (int) column - 65;

        rowColumn.put("row", row);
        rowColumn.put("column", col);

        return rowColumn;
    }

    public static ArrayList<String> getValidMovesForArmy(Army army) {
        ArrayList<String> validMoves = new ArrayList<>();
        int row = army.getRow(), column = army.getColumn();
        String kingdom = army.getKingdom();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newColumn = column + j;

                if (isValidMoveForArmy(newRow, newColumn, kingdom)) {
                    validMoves.add("" + (newRow + 1) + (char) (65 + newColumn));
                }
            }
        }

        return validMoves;
    }

    public static boolean isValidMoveForArmy(int destRow, int destColumn, String kingdom) {
        if (destRow < 0 || destRow >= BOARD_SIZE || destColumn < 0 || destColumn >= BOARD_SIZE) {
            return false;
        }

        Army destinationArmy = boardOfArmies[destRow][destColumn];
        return destinationArmy == null || !destinationArmy.getKingdom().equals(kingdom);
    }

    public static String selectMove(ArrayList<String> validMoves, Scanner sc) {
        String position;

        do {
            System.out.print("\nSelect move direction (e.g. 1A): ");
            position = sc.next().toUpperCase();
        } while (!position.matches("^(10|[1-9])[A-J]$") || !validMoves.contains(position));

        return position;
    }

    public static void moveArmy(Army army, String coordinate, Scanner sc) {
        int currentRow = army.getRow();
        int currentColumn = army.getColumn();

        HashMap<String, Integer> rowColumn = convertPosition(coordinate);

        int destRow = rowColumn.get("row");
        int destColumn = rowColumn.get("column");

        Army winner = army;

        if (enemyOccupiedSquareArmy(destRow, destColumn, army.getKingdom())) {
            Army enemyArmy = boardOfArmies[destRow][destColumn];
            winner = resolveBattleOfArmies(army, enemyArmy, sc);
        }

        boardOfArmies[currentRow][currentColumn] = null;
        boardOfArmies[destRow][destColumn] = winner;
        winner.setRow(destRow);
        winner.setColumn(destColumn);
    }

    public static boolean enemyOccupiedSquareArmy(int destRow, int destColumn, String kingdom) {
        return boardOfArmies[destRow][destColumn] != null
                && !boardOfArmies[destRow][destColumn].getKingdom().equals(kingdom);
    }

    public static Army resolveBattleOfArmies(Army army, Army oppArmy, Scanner sc) {
        System.out.println("\nBattle at position " + oppArmy.getPosition() + "!");

        System.out.println("\n1. Automatic Resolution");
        System.out.println("2. Play on the Battlefield");
        System.out.print("Enter your choice: ");

        int choice = getUserChoice(sc);
        Army winner = null;
        switch (choice) {
            case 1:
                winner = automaticResolution(army, oppArmy);
                break;
            case 2:
                winner = playOnBattlefield(army, oppArmy, sc);
                break;
            default:
                System.out.println("\nInvalid choice. Please enter a number between 1 and 2.");
                winner = resolveBattleOfArmies(army, oppArmy, sc);
        }

        return winner;
    }

    public static int getUserChoice(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number.\n");
                sc.nextLine();
            }
        }
    }

    public static Army automaticResolution(Army army, Army oppArmy) {
        int healthArmy = army.calculateTotalHealth(), healthOppArmy = oppArmy.calculateTotalHealth();
        double totalHealth = healthArmy + healthOppArmy;
        double probabilityArmy = (healthArmy / totalHealth) * 100;
        double probabilityOppArmy = 100 - probabilityArmy;

        System.out.println("\nBattle at position " + army.getPosition() + "!\n");

        System.out.printf("%s has a probability of winning: %.2f%%.\n", army.getKingdom(), probabilityArmy);
        System.out.printf("%s has a probability of winning: %.2f%%.\n", oppArmy.getKingdom(), probabilityOppArmy);

        Random random = new Random();
        double randomNumber = random.nextDouble() * 100;
        Army winner;

        if (randomNumber <= probabilityArmy) {
            winner = army;
            removeArmyFromKingdom(oppArmy);
        } else {
            winner = oppArmy;
            removeArmyFromKingdom(army);
        }

        System.out.println(winner.getKingdomColor() + "\n");
        System.out.printf("With a probability of %.2f%%, %s has won the battle!\n", randomNumber, winner.getKingdom());
        System.out.println(Army.KINGDOM_COLORS.get(""));

        return winner;
    }

    public static void removeArmyFromKingdom(Army army) {
        army.wasDefeated();

        if (kingdom1.contains(army))
            kingdom1.remove(army);
        else
            kingdom2.remove(army);
    }

    public static Army playOnBattlefield(Army army, Army oppArmy, Scanner sc) {
        System.out.println("\nGenerating battlefield...");

        boardOfSoldiers = new Soldier[BOARD_SIZE][BOARD_SIZE];
        distributeSoldiers(army);
        distributeSoldiers(oppArmy);

        System.out.println("\n" + army.getKingdomColor() + army.getKingdom() + Army.KINGDOM_COLORS.get(""));
        System.out.println(army);
        System.out.println("\n" + oppArmy.getKingdomColor() + oppArmy.getKingdom() + Army.KINGDOM_COLORS.get(""));
        System.out.println(oppArmy);

        Army winner = battleOfSoldiers(army, oppArmy, sc);

        return winner;
    }

    public static void distributeSoldiers(Army army) {
        Random r = new Random();
        int row, column;

        for (Soldier soldier : army.getMySoldiers()) {
            do {
                row = r.nextInt(BOARD_SIZE);
                column = r.nextInt(BOARD_SIZE);
            } while (boardOfSoldiers[row][column] != null);

            soldier.setRow(row);
            soldier.setColumn(column);
            boardOfSoldiers[row][column] = soldier;
        }
    }

    public static void printGameBoardOfSoldiers() {
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
                if (boardOfSoldiers[i][j] != null) {
                    String color = Army.KINGDOM_COLORS.get(boardOfSoldiers[i][j].getKingdom());
                    String format = String.format("%02d", boardOfSoldiers[i][j].getCurrentHealth());
                    System.out.print(color + "  " + format + Army.KINGDOM_COLORS.get("") + "  |");
                } else
                    System.out.print(cellSeparator);

            printRowSeparator();
        }

        System.out.println();
    }

    public static Army battleOfSoldiers(Army army, Army oppArmy, Scanner sc) {
        int currentTurn = 1;
        Army winner = null;

        while (winner == null) {
            Army currentArmy = (currentTurn == 1) ? army : oppArmy;

            printGameBoardOfSoldiers();
            Soldier remove = playSoldierTurn(currentArmy, sc);
            army.getMySoldiers().remove(remove);
            oppArmy.getMySoldiers().remove(remove);
            System.out.println("\nSoldier Count by Army:\n");
            System.out.println(army.getSoldierCount());
            System.out.println(oppArmy.getSoldierCount());

            currentTurn = (currentTurn == 1) ? 2 : 1;

            winner = determineWinnerOfSoldiers(army, oppArmy);
        }

        printGameBoardOfSoldiers();
        String color = winner.getKingdomColor();
        System.out.println("\n" + color + winner.getKingdom() + " has won the war!" + Army.KINGDOM_COLORS.get(""));
        Army defeated = (winner == army) ? oppArmy : army;
        removeArmyFromKingdom(defeated);
        return winner;
    }

    public static Soldier playSoldierTurn(Army army, Scanner sc) {
        String color = army.getKingdomColor();
        System.out.println(color + "\nKingdom Turn " + army.getKingdom() + "." + Army.KINGDOM_COLORS.get(""));
        Soldier soldier = selectSoldier(army.getMySoldiers(), sc);
        System.out.println("\n" + soldier);
        ArrayList<String> validMoves = getValidMovesForSoldier(soldier);
        String coordinate = selectMove(validMoves, sc);
        Soldier remove = moveSoldier(soldier, coordinate);
        return remove;
    }

    public static Soldier selectSoldier(ArrayList<Soldier> army, Scanner sc) {
        Soldier soldier = null;
        String position;

        do {
            System.out.print("\nSelect a soldier (e.g. 1A): ");
            position = sc.next().toUpperCase();

            try {
                HashMap<String, Integer> rowColumn = convertPosition(position);
                int row = rowColumn.get("row"), column = rowColumn.get("column");
                soldier = boardOfSoldiers[row][column];
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("\nInvalid position. Try again.");
            }
        } while (!position.matches("^(10|[1-9])[A-J]$") || !army.contains(soldier));

        return soldier;
    }

    public static ArrayList<String> getValidMovesForSoldier(Soldier soldier) {
        ArrayList<String> validMoves = new ArrayList<>();
        int row = soldier.getRow(), column = soldier.getColumn();
        String kingdom = soldier.getKingdom();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newColumn = column + j;

                if (isValidMoveForSoldier(newRow, newColumn, kingdom)) {
                    validMoves.add("" + (newRow + 1) + (char) (65 + newColumn));
                }
            }
        }

        return validMoves;
    }

    public static boolean isValidMoveForSoldier(int destRow, int destColumn, String kingdom) {
        if (destRow < 0 || destRow >= BOARD_SIZE || destColumn < 0 || destColumn >= BOARD_SIZE) {
            return false;
        }

        Soldier destinationArmy = boardOfSoldiers[destRow][destColumn];
        return destinationArmy == null || !destinationArmy.getKingdom().equals(kingdom);
    }

    public static Soldier moveSoldier(Soldier soldier, String coordinate) {
        int currentRow = soldier.getRow();
        int currentColumn = soldier.getColumn();

        HashMap<String, Integer> rowColumn = convertPosition(coordinate);

        int destRow = rowColumn.get("row");
        int destColumn = rowColumn.get("column");

        Soldier winner = soldier, enemySoldier = null;
        if (enemyOccupiedSquareSoldier(destRow, destColumn, soldier.getKingdom())) {
            enemySoldier = boardOfSoldiers[destRow][destColumn];
            winner = resolveBattle(soldier, enemySoldier);
        }

        boardOfSoldiers[currentRow][currentColumn] = null;
        boardOfSoldiers[destRow][destColumn] = winner;
        winner.setRow(destRow);
        winner.setColumn(destColumn);

        System.out.println(soldier == winner);
        return (winner == soldier) ? enemySoldier : soldier;
    }

    public static boolean enemyOccupiedSquareSoldier(int destRow, int destColumn, String kingdom) {
        return boardOfSoldiers[destRow][destColumn] != null
                && !boardOfSoldiers[destRow][destColumn].getKingdom().equals(kingdom);
    }

    public static Soldier resolveBattle(Soldier soldier, Soldier oppSoldier) {
        double totalHealth = soldier.getCurrentHealth() + oppSoldier.getCurrentHealth();
        double probabilitySoldier = (soldier.getCurrentHealth() / totalHealth) * 100;
        double probabilityOpp = 100 - probabilitySoldier;

        System.out.println("\nBattle at position " + soldier.getPosition() + "!\n");

        System.out.printf("%s has a probability of winning: %.2f%%.\n", soldier.getName(), probabilitySoldier);
        System.out.printf("%s has a probability of winning: %.2f%%.\n", oppSoldier.getName(), probabilityOpp);

        Random random = new Random();
        double randomNumber = random.nextDouble() * 100;
        Soldier winner;

        if (randomNumber <= probabilitySoldier) {
            winner = soldier;
        } else {
            winner = oppSoldier;
        }

        System.out.println(Army.KINGDOM_COLORS.get(winner.getKingdom()) + "\n");
        System.out.printf("With a probability of %.2f%%, %s has won the battle!\n", randomNumber, winner.getName());
        System.out.println(Army.KINGDOM_COLORS.get(""));
        winner.increaseHealth(1);

        return winner;
    }

    public static Army determineWinnerOfSoldiers(Army army, Army oppArmy) {
        ArrayList<Soldier> army1 = army.getMySoldiers();
        ArrayList<Soldier> army2 = oppArmy.getMySoldiers();

        if (army1.size() != 0 && army2.size() != 0) {
            return null;
        }

        return (army1.size() == 0) ? oppArmy : army;
    }

    public static String determineWinnerOfArmies() {
        ArrayList<String> namesKingdom = new ArrayList<>(Army.getArmyCount());
        String kingdomName1 = namesKingdom.get(0);
        String kingdomName2 = namesKingdom.get(1);

        if (Army.getArmyCount(kingdomName1) != 0 && Army.getArmyCount(kingdomName2) != 0) {
            return null;
        }

        return (Army.getArmyCount(kingdomName1) == 0) ? kingdomName2 : kingdomName1;
    }
}