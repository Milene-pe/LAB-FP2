
public class Exercise{
    public static void main(String[] args) {

        Rectangle rectangle1 = new Rectangle();
        System.out.println("Area: " + rectangle1.calculateArea());
        System.out.println("Perimeter: " + rectangle1.calculatePerimeter());
        System.out.println("Is square: " + rectangle1.isSquare());
    
        Rectangle rectangle2 = new Rectangle(4, 5);
        System.out.println("Area: " + rectangle2.calculateArea());
        System.out.println("Perimeter: " + rectangle2.calculatePerimeter());
        System.out.println("Is square: " + rectangle2.isSquare());
    
        Rectangle square = new Rectangle(5);
        System.out.println("Area: " + square.calculateArea());
        System.out.println("Perimeter: " + square.calculatePerimeter());
        System.out.println("Is square: " + square.isSquare());
    }  
}



