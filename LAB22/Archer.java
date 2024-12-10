
public class Archer extends Soldier {
    private int numberOfArrows;

    private final static int attackLevel = 7;
    private final static int defenseLevel = 3;

    public Archer(String name, Kingdom kingdom, int healthLevel, int row, int column, int numberOfArrows) {
        super(name, kingdom, attackLevel, defenseLevel, healthLevel, row, column);
        this.numberOfArrows = numberOfArrows;
    }

    public void shootArrow() {
        if (numberOfArrows > 0) {
            numberOfArrows--;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Number of Arrows: " + numberOfArrows;
    }
}
