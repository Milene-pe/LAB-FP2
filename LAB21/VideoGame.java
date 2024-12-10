
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class VideoGame {
    private Army kingdom1;
    private Army kingdom2;
    private Map map = new Map();

    public VideoGame() {
        Map.chooseRandomTerritory();
        String[] kingdomNames = Army.selectTwoRandomKingdoms();
        kingdom1 = new Army(kingdomNames[0], map);
        kingdom2 = new Army(kingdomNames[1], map);
    }

    public void displayGameInformation() {
        showArmyStatistics(kingdom1);
        showArmyStatistics(kingdom2);

        System.out.println("\nTerritory: " + Map.getTerritory());
        map.printGameBoardOfSoldiers();
    }

    private void showArmyStatistics(Army kingdom) {
        String color = kingdom.getKingdomColor();
        System.out.println("\n" + color + kingdom.getKingdom() + Army.KINGDOM_COLORS.get("") + ".");
        System.out.println("\nData of all soldiers in order of creation:\n" + kingdom);
        System.out.println("\nSoldier with the highest health: " + kingdom.findSoldierWithMaxHealth().getName());
        double averageHealth = kingdom.calculateTotalHealth() / kingdom.getMySoldiers().size();
        System.out.println("\nAverage health points: " + averageHealth);
        kingdom.viewPowerRanking();
    }

    public void playGame() {
        Army winner = determineWinnerOfArmies();
        String color = winner.getKingdomColor();
        System.out.println("\n" + color + winner.getKingdom() + " has won the war!" + Army.KINGDOM_COLORS.get(""));
    }

    private Army determineWinnerOfArmies() {
        int healthArmy = kingdom1.calculateTotalHealth(), healthOppArmy = kingdom2.calculateTotalHealth();
        double totalHealth = healthArmy + healthOppArmy;
        double probabilityArmy = (healthArmy / totalHealth) * 100;
        double probabilityOppArmy = 100 - probabilityArmy;

        kingdom1.displayKingdomCounts();
        kingdom2.displayKingdomCounts();
        System.out.printf("\n%s has a probability of winning: %.2f%%.\n", kingdom1.getKingdom(), probabilityArmy);
        System.out.printf("%s has a probability of winning: %.2f%%.\n", kingdom2.getKingdom(), probabilityOppArmy);

        Random random = new Random();
        double randomNumber = random.nextDouble() * 100;

        Army winner = (randomNumber <= probabilityArmy) ? kingdom1 : kingdom2;

        return winner;
    }

    public static void main(String[] args) {
        String response;
        Scanner sc = new Scanner(System.in);

        do {
            VideoGame game = new VideoGame();
            game.displayGameInformation();
            game.playGame();

            System.out.print("\nDo you want to continue playing? (y/n) ");
            response = sc.next();
        } while (response.equalsIgnoreCase("y"));

        sc.close();
    }
}