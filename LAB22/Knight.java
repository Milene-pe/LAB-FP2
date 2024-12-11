
public class Knight extends Soldier {
    private String weapon = "Lance";
    private boolean isMounted = true;

    private final static int attackLevel = 13;
    private final static int defenseLevel = 7;

    public Knight(String name, Kingdom kingdom, int healthLevel, int row, int column) {
        super(name, kingdom, attackLevel, defenseLevel, healthLevel, row, column);
    }


    public void disassemble() {
        if (isMounted) {
            isMounted = false;
            this.changeWeapon();
        }
    }

    public void changeWeapon() {
        weapon = weapon.equals("Lance") ? "Sword" : "Lance";
    }

    public void mount() {
        if (!isMounted) {
            isMounted = true;
            this.changeWeapon();
        }
    }

    @Override
    public String toString() {
        String mounted = isMounted ? "Mounted" : "Disassembled";
        return super.toString() + "Weapon: " + weapon + ", Mounted: " + mounted;
    }
}