public class Rectangle {
    private double large, width;

    public double getLarge() {
        return large;
    }

    public void setLarge(double large) {
        this.large = large;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Rectangle(double large, double width) {
        this.large = large;
        this.width = width;
    }

    public Rectangle(double size) {
        this.large = size;
        this.width = size;
    }

    public Rectangle() {
        this.large = 1;
        this.width = 1;
    }

    public double calculateArea(){
        return large*width;
    }

    public double calculatePerimeter(){
        return 2*large+2*width;
    }

    public boolean isSquare(){
        
        return large==width;

    }

}