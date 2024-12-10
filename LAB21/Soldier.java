
public abstract class Soldier {
    private String name;
    private int attackLevel;
    private int defenseLevel;
    private int healthLevel;
    private int currentHealth;
    private int speed = 0;
    private String attitude = "defense";
    private boolean isAlive = true;
    private int row;
    private int column;
    private String kingdom;

    public static final int MAX_SOLDIERS_PER_ARMY = 10;
    public static final int MIN_SOLDIERS_PER_ARMY = 1;
    public static final int MAX_LEVEL = 5;
    public static final int MIN_LEVEL = 1;

    public Soldier(String name, String kingdom, int row, int column, int healthLevel) {
        this(name, row, column, healthLevel);
        this.kingdom = kingdom;
    }

    public Soldier(String name, int row, int column, int healthLevel) {
        this.name = name;
        this.attackLevel = random(MIN_LEVEL, MAX_LEVEL);
        this.defenseLevel = random(MIN_LEVEL, MAX_LEVEL);
        this.healthLevel = healthLevel;
        this.currentHealth = healthLevel;
        this.row = row;
        this.column = column;
    }

    public Soldier(String name, String kingdom, int row, int column, int healthLevel, int attackLevel, int defenseLevel) {
        this.name = name;
        this.kingdom = kingdom;
        this.row = row;
        this.column = column;
        this.healthLevel = healthLevel;
        this.currentHealth = healthLevel;
        this.attackLevel = attackLevel;
        this.defenseLevel = defenseLevel;
    }

    protected int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public void attack() {
        attitude = "attack";
        moveForward();
    }

    public void defend() {
        attitude = "defense";

        if (speed > 0)
            speed = 0;
        else
            speed--;
    }

    public void moveForward() {
        speed++;
    }

    public void moveBackward() {
        speed--;
    }

    public void beAttacked() {
        currentHealth--;

        if (currentHealth == 0)
            die();
    }

    public void flee() {
        attitude = "flee";
        speed += 2;
    }

    public void die() {
        isAlive = false;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getHealthLevel() {
        return healthLevel;
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

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
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

    public String getKingdom() {
        return kingdom;
    }

    public String getPosition() {
        return Integer.toString(row + 1) + (char) (column + 65);
    }

    public void increaseHealth(int amount) {
        currentHealth += amount;
    }

    public String getAcronym() {
        final String COLOR = Army.KINGDOM_COLORS.get(kingdom);
        final String RESET = Army.KINGDOM_COLORS.get("");

        return COLOR + name.substring(0, 1) + String.format("%02d", currentHealth) + RESET;
    }

    public String toString() {
        return "Name: " + name + ", Health: " + currentHealth + ", Position: " + getPosition() + ", Attitude: "
                + attitude + ", IsAlive: " + isAlive;
    }
}