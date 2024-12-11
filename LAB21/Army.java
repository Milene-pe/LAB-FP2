
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Army {
    private String kingdom;
    private ArrayList<Soldier> mySoldiers = new ArrayList<>();
    private HashMap<String, Integer> soldiersCounts = new HashMap<>();

    public static final int MAX_ARMIES_PER_KINGDOM = 10;
    public static final int MIN_ARMIES_PER_KINGDOM = 1;
    public static final ArrayList<String> KINGDOMS = new ArrayList<>();
    public static final HashMap<String, String> KINGDOM_COLORS = new HashMap<>();

    static {
        KINGDOMS.add("England");
        KINGDOMS.add("France");
        KINGDOMS.add("Holy Roman Empire");
        KINGDOMS.add("Castile-Aragon");
        KINGDOMS.add("Moors");

        KINGDOM_COLORS.put("England", "\u001B[33m");
        KINGDOM_COLORS.put("France", "\u001B[34m");
        KINGDOM_COLORS.put("Castile-Aragon", "\u001B[31m");
        KINGDOM_COLORS.put("Moors", "\u001B[32m");
        KINGDOM_COLORS.put("Holy Roman Empire", "\u001B[36m");
        KINGDOM_COLORS.put("", "\u001B[0m");
    }

    public Army(String kingdom, Map map) {
        this.kingdom = kingdom;
        createSoldiers(map);
        Map.applyTerritorialBonus(this, kingdom);
    }

    private void createSoldiers(Map map) {
        Random r = new Random();

        int numSoldiers = r.nextInt(Soldier.MAX_SOLDIERS_PER_ARMY) + 1;

        for (int i = 0; i < numSoldiers; i++) {
            String name;
            int healthLevel;
            int row = r.nextInt(Map.BOARD_SIZE), column = r.nextInt(Map.BOARD_SIZE);
            int extra = r.nextInt(5) + 1;

            while (map.getSoldier(row, column) != null) {
                row = r.nextInt(Map.BOARD_SIZE);
                column = r.nextInt(Map.BOARD_SIZE);
            }

            int type = r.nextInt(4) + 1;

            switch (type) {
                case 1:
                    name = "Lancer" + i + "X" + mySoldiers.size();
                    healthLevel = random(5, 8);
                    mySoldiers.add(new Lancer(name, kingdom, row, column, healthLevel, extra));
                    soldiersCounts.put("Lancer", soldiersCounts.getOrDefault("Lancer", 0) + 1);
                    break;
                case 2:
                    name = "Archer" + i + "X" + mySoldiers.size();
                    healthLevel = random(3, 5);
                    mySoldiers.add(new Archer(name, kingdom, row, column, healthLevel, extra));
                    soldiersCounts.put("Archer", soldiersCounts.getOrDefault("Archer", 0) + 1);
                    break;
                case 3:
                    name = "Swordsman" + i + "X" + mySoldiers.size();
                    healthLevel = random(8, 10);
                    mySoldiers.add(new Swordsman(name, kingdom, row, column, healthLevel, extra));
                    soldiersCounts.put("Swordsman", soldiersCounts.getOrDefault("Swordsman", 0) + 1);
                    break;
                case 4:
                    name = "Knight" + i + "X" + mySoldiers.size();
                    healthLevel = random(10, 12);
                    mySoldiers.add(new Knight(name, kingdom, row, column, healthLevel));
                    soldiersCounts.put("Knight", soldiersCounts.getOrDefault("Knight", 0) + 1);
                    break;
            }

            map.setSoldier(row, column, mySoldiers.get(mySoldiers.size() - 1));
        }
    }

    protected int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public String getKingdom() {
        return kingdom;
    }

    public ArrayList<Soldier> getMySoldiers() {
        return mySoldiers;
    }

    public String getKingdomColor() {
        return KINGDOM_COLORS.get(kingdom);
    }

    public void increaseHealthOfArmy(int amount) {
        for (Soldier soldier : mySoldiers) {
            soldier.increaseHealth(amount);
        }
    }

    public int calculateTotalHealth() {
        int totalHealth = 0;

        for (Soldier soldier : mySoldiers) {
            totalHealth += soldier.getCurrentHealth();
        }

        return totalHealth;
    }

    public Soldier findSoldierWithMaxHealth() {
        Soldier soldierWithMaxHealth = mySoldiers.get(0);

        for (int i = 1; i < mySoldiers.size(); i++) {
            if (mySoldiers.get(i).getCurrentHealth() > soldierWithMaxHealth.getCurrentHealth()) {
                soldierWithMaxHealth = mySoldiers.get(i);
            }
        }

        return soldierWithMaxHealth;
    }

    public void viewPowerRanking() {
        sortByPointsBubble();

        System.out.println("\nPower Ranking\n" + this.toString());
    }

    private void sortByPointsBubble() {
        for (int i = 0; i < mySoldiers.size() - 1; i++) {
            for (int j = 0; j < mySoldiers.size() - 1 - i; j++) {
                if (mySoldiers.get(j + 1).getHealthLevel() > mySoldiers.get(j).getHealthLevel()) {
                    Soldier temp = mySoldiers.get(j);
                    mySoldiers.set(j, mySoldiers.get(j + 1));
                    mySoldiers.set(j + 1, temp);
                }
            }
        }
    }

    public void displayKingdomCounts() {
        System.out.println(getKingdomColor() + "\nArmy Count by Kingdom:\n");
        System.out.print(KINGDOM_COLORS.get(""));

        for (String name : soldiersCounts.keySet()) {
            int count = soldiersCounts.get(name);
            System.out.println(name + ": " + count);
        }
    }

    public String toString() {
        String str = "";

        for (Soldier soldier : mySoldiers) {
            str += "\n- " + soldier.toString();
        }

        return str;
    }

    public static String[] selectTwoRandomKingdoms() {
        Random random = new Random();
        int index1 = random.nextInt(KINGDOMS.size());

        int index2;
        do {
            index2 = random.nextInt(KINGDOMS.size());
        } while (index2 == index1);

        String[] selectedKingdoms = { KINGDOMS.get(index1), KINGDOMS.get(index2) };
        return selectedKingdoms;
    }
}