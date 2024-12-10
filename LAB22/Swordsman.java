public class Swordsman extends Soldier {
    private int lengthSword;
    private boolean wallShields = false;

    private final static int attackLevel = 10;
    private final static int defenseLevel = 8;

    public Swordsman(String name, Kingdom kingdom, int healthLevel, int row, int column, int lengthSword) {
        super(name, kingdom, attackLevel, defenseLevel, healthLevel, row, column);
        this.lengthSword = lengthSword;
    }

    public void wallShields() {
        wallShields = wallShields ? false : true;
    }

    @Override
    public String toString() {
        String status = wallShields ? "Activated" : "Disabled";
        return super.toString() + ", Length of Sword: " + lengthSword + ", Wall Shields: " + status;
    }
}