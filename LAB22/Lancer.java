
public class Lancer extends Soldier {
    private int lanceLength;

    private final static int attackLevel = 5;
    private final static int defenseLevel = 10;

    public Lancer(String name, Kingdom kingdom, int healthLevel, int row, int column, int lanceLength) {
        super(name, kingdom, attackLevel, defenseLevel, healthLevel, row, column);
        this.lanceLength = lanceLength;
    }

    public void schiltrom() {
        this.setDefenseLevel(this.getDefenseLevel() + 1);
    }

    @Override
    public String toString() {
        return super.toString() + ", Lance Length: " + lanceLength;
    }
}
