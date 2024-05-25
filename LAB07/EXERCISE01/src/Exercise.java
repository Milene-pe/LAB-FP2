public class Exercise {
    public static void main(String[] args) {
        Fecha fecha1 = new Fecha();
        Fecha fecha2 = new Fecha(1999);
        Fecha fecha3 = new Fecha(5, 2020);
        Fecha fecha4 = new Fecha(15, 6, 2022);
        Fecha fecha5 = new Fecha(fecha4); 
        Fecha fecha6 = new Fecha(1500, false); 

        System.out.println("Fecha 1: " + fecha1);
        System.out.println("Fecha 2: " + fecha2);
        System.out.println("Fecha 3: " + fecha3);
        System.out.println("Fecha 4: " + fecha4);
        System.out.println("Fecha 5: " + fecha5);
        System.out.println("Fecha 6: " + fecha6);
    }
}
