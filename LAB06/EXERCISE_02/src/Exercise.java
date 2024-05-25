public class Exercise {
    public static void main(String[] args) {

        Account account1 = new Account(10200, "SEBASTIAN");

        System.out.println(account1.getName()+" BALANCE:"+account1.getBalance());

        account1.withdraw(250);
        account1.deposit(540);
        System.out.println("Is Premiun: "+account1.isPremiun());
        
    }
}
