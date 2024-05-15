// LABORATORIO FP2 - Ejercicio 3
// Milene Pacheco

import java.util.Scanner;

public class exercise3{
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);       

        String [] soldier = new String[5];

        for (int i=0; i<soldier.length; i++){
            System.out.println("Enter name the soldier: "+ (i+1) + ":");
            soldier[i]=sc.nextLine();
        }
         System.out.println("The names of the soldiers are:");
        
        for (int i=0; i<5; i++){
            System.out.println("Soldier "+(i+1)+" : "+soldier[i]);
            
        }
    }
}