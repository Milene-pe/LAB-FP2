
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Army {
    private Kingdom kingdom;
    private ArrayList<Soldier> mySoldiers = new ArrayList<>();
    private HashMap<String, Integer> kingdomCounts = new HashMap<>();

    public Army(Kingdom kingdom, Map map) {
        this.kingdom = kingdom;
        this.createSoldiers(map);
    }

    private void createSoldiers(Map map) {
        Random r = new Random();

        int numSoldiers = r.nextInt(Soldier.MAX_SOLDIERS_PER_ARMY) + 1;

        for (int i = 0; i < numSoldiers; i++) {
            String name;
            int healthLevel, extra = r.nextInt(5) + 1;
            int row = r.nextInt(Map.MAP_SIZE), column = r.nextInt(Map.MAP_SIZE);

            while (map.getSoldier(row, column) != null) {
                row = r.nextInt(Map.MAP_SIZE);
                column = r.nextInt(Map.MAP_SIZE);
            }

            int type = r.nextInt(4);

            switch (type) {
                case 0:
                    name = "Lancer" + i + "X" + mySoldiers.size();
                    healthLevel = r.nextInt(4) + 5;
                    mySoldiers.add(new Lancer(name, kingdom, healthLevel, row, column, extra));
                    kingdomCounts.put("Lancer", kingdomCounts.getOrDefault("Lancer", 0) + 1);
                    break;
                case 1:
                    name = "Archer" + i + "X" + mySoldiers.size();
                    healthLevel = r.nextInt(3) + 3;
                    mySoldiers.add(new Archer(name, kingdom, healthLevel, row, column, extra));
                    kingdomCounts.put("Archer", kingdomCounts.getOrDefault("Archer", 0) + 1);
                    break;
                case 2:
                    name = "Knight" + i + "X" + mySoldiers.size();
                    healthLevel = r.nextInt(3) + 10;
                    mySoldiers.add(new Knight(name, kingdom, healthLevel, row, column));
                    kingdomCounts.put("Knight", kingdomCounts.getOrDefault("Knight", 0) + 1);
                    break;
                case 3:
                    name = "Swordsman" + i + "X" + mySoldiers.size();
                    healthLevel = r.nextInt(3) + 8;
                    mySoldiers.add(new Swordsman(name, kingdom, healthLevel, row, column, extra));
                    kingdomCounts.put("Swordsman", kingdomCounts.getOrDefault("Swordsman", 0) + 1);
                    break;
            }
            map.setSoldier(row, column, mySoldiers.get(mySoldiers.size() - 1));
        }
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public ArrayList<Soldier> getMySoldiers() {
        return mySoldiers;
    }

    public void increaseHealthOfArmy(int health) {
        for (Soldier soldier : mySoldiers) {
            soldier.increaseHealth(health);
        }
    }

    public void getKingdomCounts() {
        String str = "\n" + kingdom.getColor() + "Army of " + kingdom.getName() + ":\n" + Map.RESET;

        for (String key : kingdomCounts.keySet()) {
            str += "\n- " + key + ": " + kingdomCounts.get(key);
        }

        System.out.println(str);
    }

    public HashMap<String, Integer> getKingdomCountsMap() {
        return kingdomCounts;
    }

    public void reCountKingdom() {
        kingdomCounts.clear();

        for (Soldier soldier : mySoldiers) {
            if (soldier.isAlive()) {
                String key = soldier.getClass().getSimpleName();
                kingdomCounts.put(key, kingdomCounts.getOrDefault(key, 0) + 1);
            }
        }
    }

    @Override
    public String toString() {
        String str = kingdom.getColor() + "Army of " + kingdom.getName() + ":\n" + Map.RESET;

        for (Soldier soldier : mySoldiers) {
            str += "\n- " + soldier;
        }

        return str;
    }

    public static Kingdom[] chooseTwoKingdoms() {
        Random r = new Random();
        int index1, index2;

        index1 = r.nextInt(Kingdom.values().length);
        do {
            index2 = r.nextInt(Kingdom.values().length);
        } while (index1 == index2);

        Kingdom[] kingdoms = { Kingdom.values()[index1], Kingdom.values()[index2] };
        return kingdoms;
    }
}