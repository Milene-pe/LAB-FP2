public class Lancer extends Soldier {
    private int lanceLength;

    public Lancer(String name, String kingdom, int row, int column, int healthLevel, int lanceLength) {
        super(name, kingdom, row, column, healthLevel, 5, 10);
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