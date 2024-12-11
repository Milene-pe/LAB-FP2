
public class Soldier {
    private String name;
    private int attackLevel;
    private int defenseLevel;
    private int healthLevel;
    private int currentHealthLevel;
    private boolean isAlive = true;
    private int row;
    private int column;
    private Kingdom kingdom;

    public static final int MAX_SOLDIERS_PER_ARMY = 10;

    public Soldier(String name, int attackLevel, int defenseLevel, int healthLevel) {
        this.name = name;
        this.attackLevel = attackLevel;
        this.defenseLevel = defenseLevel;
        this.healthLevel = healthLevel;
        this.currentHealthLevel = healthLevel;
    }

    public Soldier(String name, Kingdom kingdom, int attackLevel, int defenseLevel, int healthLevel, int row,
            int column) {
        this(name, attackLevel, defenseLevel, healthLevel);
        this.row = row;
        this.column = column;
        this.kingdom = kingdom;
    }

    public int getCurrentHealthLevel() {
        return currentHealthLevel;
    }

    public void setCurrentHealthLevel(int currentHealthLevel) {
        this.currentHealthLevel = currentHealthLevel;
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

    public void setColumn(int column) {
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public int getDefenseLevel() {
        return defenseLevel;
    }

    public void setDefenseLevel(int defenseLevel) {
        this.defenseLevel = defenseLevel;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getPosition() {
        return Integer.toString(row + 1) + (char) (column + 65);
    }

    public void die() {
        isAlive = false;
    }

    public void increaseHealth(int health) {
        currentHealthLevel += health;
    }

    public Soldier fight(Soldier enemy) {
        double totalHealth = currentHealthLevel + enemy.currentHealthLevel;
        double probability = currentHealthLevel / totalHealth;
        double random = Math.random();

        System.out.println("\nBattle at position " + getPosition() + "!\n");
        System.out.printf("%s has a probability of %.2f\n", kingdom.getName(), probability * 100);
        System.out.printf("%s has a probability of %.2f\n", enemy.kingdom.getName(), (1 - probability) * 100);

        if (random < probability) {
            System.out.println("\n" + kingdom.getName() + " wins the battle!");
            enemy.die();
            return this;
        } else {
            System.out.println("\n" + enemy.kingdom.getName() + " wins the battle!");
            die();
            return enemy;
        }
    }

    public String getAcronym() {
        String color = kingdom.getColor();
        String acronym = name.substring(0, 1) + String.format("%02d", currentHealthLevel);

        return color + acronym + Map.RESET;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Health: " + currentHealthLevel + ", Position: " + getPosition()
                + ", Is Alive: " + isAlive;
    }
}