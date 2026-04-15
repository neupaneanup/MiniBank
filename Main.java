package MiniBank;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        // Sample accounts
        bank.addAccount(new SavingsAccount("S1001", 5000));
        bank.addAccount(new CurrentAccount("C1001", 3000));

        while (true) {
            System.out.println("\n1.Deposit 2.Withdraw 3.Transfer 4.Show One 5.Show All 6.Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Acc No: ");
                String acc = sc.next();
                System.out.print("Amount: ");
                double amt = sc.nextDouble();

                BankAccount a = bank.findAccount(acc);
                if (a != null) a.deposit(amt);

            } else if (choice == 2) {
                System.out.print("Acc No: ");
                String acc = sc.next();
                System.out.print("Amount: ");
                double amt = sc.nextDouble();

                BankAccount a = bank.findAccount(acc);
                if (a != null) a.withdraw(amt);

            } else if (choice == 3) {
                System.out.print("From: ");
                String from = sc.next();
                System.out.print("To: ");
                String to = sc.next();
                System.out.print("Amount: ");
                double amt = sc.nextDouble();

                bank.transfer(from, to, amt);

            } else if (choice == 4) {
                System.out.print("Acc No: ");
                String acc = sc.next();

                BankAccount a = bank.findAccount(acc);
                if (a != null) a.showDetails();

            } else if (choice == 5) {
                bank.showAllAccounts();

            } else {
                System.out.println("Exiting...");
                break;
            }
        }
    }
}