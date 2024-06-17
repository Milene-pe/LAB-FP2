
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Army {
    private String kingdom;
    private ArrayList<Soldier> mySoldiers = new ArrayList<>();
    private int row;
    private int column;

    private static HashMap<String, Integer> armyCounts = new HashMap<>();

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

    public Army(String kingdom, int row, int column, ArrayList<Soldier> mySoldiers) {
        this.kingdom = kingdom;
        this.row = row;
        this.column = column;
        this.mySoldiers = mySoldiers;

        armyCounts.put(kingdom, armyCounts.getOrDefault(kingdom, 0) + 1);
    }

    public Army(String kingdom, int row, int column) {
        this.kingdom = kingdom;
        this.row = row;
        this.column = column;
        createSoldiers();

        armyCounts.put(kingdom, armyCounts.getOrDefault(kingdom, 0) + 1);
    }

    private void createSoldiers() {
        Random r = new Random();

        int numSoldiers = r.nextInt(Soldier.MAX_SOLDIERS_PER_ARMY) + 1;

        for (int i = 0; i < numSoldiers; i++) {
            String name = "Soldier" + i + "X" + kingdom.charAt(0) + armyCounts.getOrDefault(kingdom, 0);
            mySoldiers.add(new Soldier(name, kingdom, 0, 0));
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int col) {
        this.column = col;
    }

    public String getPosition() {
        return Integer.toString(row + 1) + (char) (column + 65);
    }

    public String getKingdom() {
        return kingdom;
    }

    public ArrayList<Soldier> getMySoldiers() {
        return mySoldiers;
    }

    public String getSoldierCount() {
        return KINGDOM_COLORS.get(kingdom) + kingdom + KINGDOM_COLORS.get("") + ": " + mySoldiers.size() + " soldiers.";
    }

    public void wasDefeated() {
        armyCounts.put(kingdom, armyCounts.get(kingdom) - 1);
    }

    public String getKingdomColor() {
        return KINGDOM_COLORS.get(kingdom);
    }

    public int calculateTotalHealth() {
        int totalHealth = 0;

        for (Soldier soldier : mySoldiers) {
            totalHealth += soldier.getCurrentHealth();
        }

        return totalHealth;
    }

    public Soldier findSoldierWithMaxHighestAttack() {
        Soldier soldierWithMaxHealth = mySoldiers.get(0);

        for (int i = 1; i < mySoldiers.size(); i++) {
            if (mySoldiers.get(i).getAttackLevel() > soldierWithMaxHealth.getAttackLevel()) {
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

    public String toString() {
        String str = "\nArmy " + getPosition() + " has " + mySoldiers.size() + " soldiers with total health of "
                + calculateTotalHealth() + ".\n";

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

    public static Set<String> getArmyCount() {
        return armyCounts.keySet();
    }

    public static int getArmyCount(String kingdom) {
        Integer count = armyCounts.get(kingdom);
        return count == null ? 0 : count;
    }

    public static void displayKingdomCounts() {
        System.out.println("\nArmy Count by Kingdom:\n");

        for (String kingdom : armyCounts.keySet()) {
            String color = KINGDOM_COLORS.get(kingdom);
            int count = armyCounts.get(kingdom);
            System.out.println(color + kingdom + KINGDOM_COLORS.get("") + ": " + count + " armies.");
        }
    }

    public static void resetArmyCounts() {
        armyCounts.clear();
    }
}