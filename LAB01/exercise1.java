// LABORATORIO FP2 - Ejercicio 1
// Milene Pacheco

import java.util.Scanner;

public class exercise1{
    public static void main(String[] args) {
        String soldier1, soldier2, soldier3, soldier4, soldier5;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingresar el nombre de los 5 soldados");
        soldier1 = entrada.nextLine();
        soldier2 = entrada.nextLine();
        soldier3 = entrada.nextLine();
        soldier4 = entrada.nextLine();
        soldier5 = entrada.nextLine();
       System.out.println("Los nombres de los 5 soldados son:"+"\n"+ soldier1+"\n"+soldier2+"\n"+soldier3+"\n"+soldier4+"\n"+soldier5);
    }
}