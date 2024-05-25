
public class Account {
    private String name;
    private double balance;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account(double balance, String name) {
        this.balance = balance;
        this.name = name;
    }

    public void withdraw(double a){
        if (a>0 && balance>=a){
            balance-=a;
            System.out.println("Withdrawal successfull. New balance: "+ balance);
        }
        else System.out.print("INSUFFICIENT BALANCE");
    }

    public void deposit(double b){
        if (b>0){
            balance+=b;
            System.out.println("Deposit successfull. New balance: "+ balance);
        }
    }

    public boolean isPremiun(){
        return balance>=10000;
    }

}
