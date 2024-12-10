public class Archer extends Soldier {
    private int numberOfArrows;

    public Archer(String name, String kingdom, int row, int column, int healthLevel, int numberOfArrows) {
        super(name, kingdom, row, column, healthLevel, 7, 3);
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