public class Exercise {
    public static void main(String[] args) {

        Fraccion fraccion1 = new Fraccion(3, 4);
        Fraccion fraccion2 = new Fraccion(1, 2);

        System.out.println("Fracción 1 en formato quebrado: " + fraccion1.mostrarQuebrado());
        System.out.println("Fracción 1 en formato decimal: " + fraccion1.mostrarDecimal());
        System.out.println("Fracción 2 en formato quebrado: " + fraccion2.mostrarQuebrado());
        System.out.println("Fracción 2 en formato decimal: " + fraccion2.mostrarDecimal());

        Fraccion suma = fraccion1.sumar(fraccion2);
        Fraccion resta = fraccion1.restar(fraccion2);
        Fraccion multiplicacion = fraccion1.multiplicar(fraccion2);
        Fraccion division = fraccion1.dividir(fraccion2);

        System.out.println("Suma: " + suma.mostrarQuebrado());
        System.out.println("Resta: " + resta.mostrarQuebrado());
        System.out.println("Multiplicación: " + multiplicacion.mostrarQuebrado());
        System.out.println("División: " + division.mostrarQuebrado());
    }
}
