
public class Swordsman extends Soldier {
    private int lengthSword;
    private boolean wallShields = false;

    public Swordsman(String name, String kingdom, int row, int column, int healthLevel, int lengthSword) {
        super(name, kingdom, row, column, healthLevel, 10, 8);
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