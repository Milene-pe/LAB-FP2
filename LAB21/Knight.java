
public class Knight extends Soldier {
    private String currentWeapon = "Lance";
    private boolean mounted = true;

    public Knight(String name, String kingdom, int row, int column, int healthLevel) {
        super(name, kingdom, row, column, healthLevel, 13, 7);
    }

    public void envelop() {
        int numberOfAttacks = mounted ? 3 : 2;

        for (int i = 0; i < numberOfAttacks; i++) {
            attack();
        }
    }

    public void disassemble() {
        if (mounted) {
            mounted = false;
            super.defend();
            this.changeWeapon();
        }
    }

    public void changeWeapon() {
        currentWeapon = currentWeapon.equals("Lance") ? "Sword" : "Lance";
    }

    public void mount() {
        if (!mounted) {
            mounted = true;
            super.attack();
            this.changeWeapon();
        }
    }

    @Override
    public String toString() {
        String status = mounted ? "Mounted" : "Disassembled";

        return super.toString() + ", Current Weapon: " + currentWeapon + ", Mounted: " + status;
    }
}