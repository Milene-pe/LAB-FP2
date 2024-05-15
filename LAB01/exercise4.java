// LABORATORIO FP2 - Ejercicio 4
// Milene Pacheco

import java.util.Scanner;
public class exercise4 {
     public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);       

        String [] soldier = new String[5];
        for (int i=0; i<soldier.length ; i++){
            System.out.println("Enter name the soldier "+ (i+1) + ":");
            soldier[i]=sc.nextLine();
        }
        int [] level = new int[5];
        for (int i=0; i<5; i++){
            System.out.println("Enter level the soldier "+ (i+1) + ":");
            level[i]=sc.nextInt();
        }

         System.out.println("Name the soldiers "+"\t"+"Level the soldiers ");
         
        for (int i=0; i<5; i++){
            System.out.println("Soldier "+(i+1)+" : "+soldier[i]+ "         \t"+level[i]  );

        }
    }
}
