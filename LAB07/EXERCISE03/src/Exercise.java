public class Exercise {
    public static void main(String[] args) {
        Fraccion f1 = new Fraccion(1, 2);
        Fraccion f2 = new Fraccion(1, 3);
        Fraccion f3 = new Fraccion(1, 4);
        Fraccion f4 = new Fraccion(1, 5);
        Fraccion f5 = new Fraccion(1, 6);

        Fraccion f = f1.sumar(f2).sumar(f3).restar(f4).multiplicar(f5);

        // Mostrar el resultado
        System.out.println("El resultado es : " + f.mostrarQuebrado());
    }
}
