
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VideoGame {
    private Army kingdom1;
    private Army kingdom2;
    private Map map = new Map();
    private Scanner sc = new Scanner(System.in);

    public VideoGame() {
        Map.chooseRandomTerritory();
        Kingdom[] kingdomNames = Army.chooseTwoKingdoms();
        this.kingdom1 = new Army(kingdomNames[0], map);
        this.kingdom2 = new Army(kingdomNames[1], map);
    }

    public void displayGameInformation() {
        System.out.println("\nTerritory: " + Map.getTerritory().getName());
        map.printMap();
        System.out.println("\n" + kingdom1);
        Map.applyTerritoryBonus(kingdom1);
        System.out.println("\n" + kingdom2);
        Map.applyTerritoryBonus(kingdom2);
    }

    public void play() {
        int currentTurn = 1;
        Kingdom winner = null;

        while (winner == null) {
            Army kingdom = (currentTurn == 1) ? kingdom2 : kingdom1;
            System.out.println("\nTerritory: " + Map.getTerritory().getName());
            map.printMap();
            kingdom1.getKingdomCounts();
            kingdom2.getKingdomCounts();

            playArmyTurn(kingdom);
            kingdom1.reCountKingdom();
            kingdom2.reCountKingdom();

            currentTurn = (currentTurn == 1) ? 2 : 1;

            winner = determineWinnerOfArmies();
        }

        map.printMap();
        System.out.println("\n" + winner.getColor() + "The Kingdom of " + winner.getName() + " wins!" + Map.RESET);
    }

    private void playArmyTurn(Army kingdom) {
        String color = kingdom.getKingdom().getColor(), name = kingdom.getKingdom().getName();
        System.out.println(color + "\nTurn of the Kingdom of " + name + Map.RESET);
        Soldier soldier = selectSoldier(kingdom);
        System.out.println("\n" + soldier);
        ArrayList<String> validMoves = getValidMoves(soldier);
        String coordinate = selectMove(validMoves);
        moveSoldier(soldier, coordinate);
    }

    private Soldier selectSoldier(Army kingdom) {
        Soldier soldier = null;
        String position = "";

        while (soldier == null) {
            System.out.print("\nSelect a soldier to move (e.g. 1A): ");
            position = sc.next().toUpperCase();

            if (!position.matches("^(10|[1-9])[A-J]$")) {
                System.out.println("\nInvalid position. Try again.");
                continue;
            }

            HashMap<String, Integer> rowColumn = convertPosition(position);
            int row = rowColumn.get("row"), column = rowColumn.get("column");
            soldier = map.getSoldier(row, column);

            if (!map.isOccupiedByAlly(row, column, kingdom.getKingdom())) {
                System.out.println("\nInvalid soldier. Try again.");
                soldier = null;
            }
        }

        return soldier;
    }

    private HashMap<String, Integer> convertPosition(String position) {
        HashMap<String, Integer> rowColumn = new HashMap<>();

        char column = Character.toUpperCase(position.charAt(position.length() - 1));
        int row = Integer.parseInt(position.substring(0, position.length() - 1)) - 1;
        int col = (int) column - 65;

        rowColumn.put("row", row);
        rowColumn.put("column", col);

        return rowColumn;
    }

    private ArrayList<String> getValidMoves(Soldier soldier) {
        ArrayList<String> validMoves = new ArrayList<>();
        int row = soldier.getRow(), column = soldier.getColumn();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newColumn = column + j;

                if (!map.isOccupiedByAlly(newRow, newColumn, soldier.getKingdom())
                        && map.isValidCoordinate(newRow, newColumn)) {
                    validMoves.add("" + (newRow + 1) + (char) (65 + newColumn));
                }
            }
        }

        return validMoves;
    }

    private String selectMove(ArrayList<String> validMoves) {
        String position;

        do {
            System.out.print("\nSelect move direction (e.g. 1A): ");
            position = sc.next().toUpperCase();
        } while (!position.matches("^(10|[1-9])[A-J]$") || !validMoves.contains(position));

        return position;
    }

    private void moveSoldier(Soldier soldier, String coordinate) {
        int currentRow = soldier.getRow(), currentColumn = soldier.getColumn();

        HashMap<String, Integer> rowColumn = convertPosition(coordinate);

        int destRow = rowColumn.get("row"), destColumn = rowColumn.get("column");

        Soldier winner = soldier;

        if (map.isOccupiedByEnemy(destRow, destColumn, soldier.getKingdom())) {
            Soldier enemy = map.getSoldier(destRow, destColumn);
            winner = soldier.fight(enemy);
            System.out.println("\n" + winner);
        }

        map.setSoldier(currentRow, currentColumn, null);
        map.setSoldier(destRow, destColumn, winner);
        winner.increaseHealth(1);
        winner.setRow(destRow);
        winner.setColumn(destColumn);
    }

    public Kingdom determineWinnerOfArmies() {
        if (kingdom1.getKingdomCountsMap().isEmpty()) {
            return kingdom2.getKingdom();
        } else if (kingdom2.getKingdomCountsMap().isEmpty()) {
            return kingdom1.getKingdom();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        String response;
        Scanner sc = new Scanner(System.in);

        do {
            VideoGame game = new VideoGame();
            game.displayGameInformation();
            game.play();
            System.out.println("\nDo you want to play again? (yes/no)");
            response = sc.nextLine();
        } while (response.equals("yes"));

        sc.close();
    }
}