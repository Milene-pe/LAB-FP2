public class Soldier {
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
    private int army;

    public static final int MAX_SOLDIERS_PER_ARMY = 10;
    public static final int MAX_HEALTH = 5;
    public static final int MIN_HEALTH = 1;

    public Soldier(String name, int health, int row, int col) {
        this.name = name;
        this.healthLevel = health;
        this.currentHealth = health;
        this.row = row;
        this.column = col;
    }

    public Soldier(String name, int health, int row, int col, int army) {
        this.name = name;
        this.healthLevel = health;
        this.currentHealth = health;
        this.row = row;
        this.column = col;
        this.army = army;
    }

    public Soldier(String name, int attack, int defense, int health, int row, int col, int army) {
        this.name = name;
        this.attackLevel = attack;
        this.defenseLevel = defense;
        this.healthLevel = health;
        this.currentHealth = health;
        this.row = row;
        this.column = col;
        this.army = army;
    }

    public String getName() {
        return name;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int health) {
        this.healthLevel = health;
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

    public String getAcronym() {
        return name.substring(0, 1) + healthLevel;
    }

    public int getArmy() {
        return army;
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

    public void setCurrentHealth(int newHealth) {
        currentHealth = newHealth;
    }

    public String toString() {
        String status = isAlive ? "Alive" : "Dead";

        return "- Name: " + name + ", Attack Level: " + attackLevel + ", Defense Level: " + defenseLevel
                + ", Health Level: " + healthLevel + ", Current Health: " + currentHealth + ", Speed: " + speed
                + ", Attitude: " + attitude + ", Status: " + status + ", Row: " + (row + 1) + ", Column: "
                + (char) (column + 65);
    }
}