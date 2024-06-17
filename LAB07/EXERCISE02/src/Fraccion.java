//LAB07-EXERCISE 01
//FUNDAMENTOS DE LA PROGRAMACION
//AUTOR.PACHECO ESQUINARILA MILENE

public class Fraccion {
    private int numerador;
    private int denominador;

    public Fraccion() {
        this(0, 1); 
    }
    public Fraccion(int numerador) {
        this(numerador, 1); 
    }
    public Fraccion(int numerador, int denominador) {
        if (denominador == 0) {
            System.out.println("El denominador no puede ser cero, se tomará que sea 1");
            denominador = 1;
        }
        this.numerador = numerador;
        this.denominador = denominador;
        simplificar();
    }
    public Fraccion(Fraccion fraccion) {
        this.numerador = fraccion.numerador;
        this.denominador = fraccion.denominador;
    }
    public void setNumerador(int numerador) {
        this.numerador = numerador;
        simplificar();
    }
    public int getNumerador() {
        return numerador;
    }
    public void setDenominador(int denominador) {
        if (denominador == 0) {
            System.out.println("El denominador no puede ser cero. No se realizará el cambio.");
            return;
        }
        this.denominador = denominador;
        simplificar();
    }
    public int getDenominador() {
        return denominador;
    }
    public String mostrarQuebrado() {
        return numerador + "/" + denominador;
    }
    public String mostrarDecimal() {
        double decimal = (double) numerador / denominador;
        return String.format("%.3f", decimal); 
    }
    public Fraccion sumar(Fraccion otra) {
        int nuevoDenominador = this.denominador * otra.denominador;
        int nuevoNumerador = this.numerador * otra.denominador + otra.numerador * this.denominador;
        return new Fraccion(nuevoNumerador, nuevoDenominador);
    }
    public Fraccion restar(Fraccion otra) {
        int nuevoDenominador = this.denominador * otra.denominador;
        int nuevoNumerador = this.numerador * otra.denominador - otra.numerador * this.denominador;
        return new Fraccion(nuevoNumerador, nuevoDenominador);
    }
    public Fraccion multiplicar(Fraccion otra) {
        int nuevoNumerador = this.numerador * otra.numerador;
        int nuevoDenominador = this.denominador * otra.denominador;
        return new Fraccion(nuevoNumerador, nuevoDenominador);
    }
    public Fraccion dividir(Fraccion otra) {
        if (otra.numerador == 0) {
            System.out.println("No hay division entre 0 , se retorna 0.");
            return new Fraccion();
        }
        int nuevoNumerador = this.numerador * otra.denominador;
        int nuevoDenominador = this.denominador * otra.numerador;
        return new Fraccion(nuevoNumerador, nuevoDenominador);
    }
    private void simplificar() {
        int mcd = mcd(numerador, denominador);
        numerador /= mcd;
        denominador /= mcd;
    }
    private int mcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return mcd(b, a % b);
    }
}
