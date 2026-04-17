package MiniBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class Bank {
    //private ArrayList<BankAccount> accounts = new ArrayList<>();

    public void addAccount(BankAccount acc) {
       // accounts.add(acc);
        String query =
        "INSERT INTO accounts " +
        "(account_number, account_type, customer_name, phone, address, balance) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)
        ) {

            pstmt.setString(1, acc.getAccountNumber());

            if (acc instanceof SavingsAccount)
                pstmt.setString(2, "Savings");
            else
                pstmt.setString(2, "Current");

            pstmt.setString(3, acc.customerName);
            pstmt.setString(4, acc.phone);
            pstmt.setString(5, acc.address);
            pstmt.setDouble(6, acc.balance);

            pstmt.executeUpdate();

            System.out.println("Account Added Successfully!");

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public BankAccount findAccount(String accNo) {
        // for (BankAccount acc : accounts) {
        //     if (acc.getAccountNumber().equals(accNo)) {
        //         return acc;
        //     }
        // }
        // return null;
            String query = "SELECT * FROM accounts WHERE account_number = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, accNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                String type = rs.getString("account_type");
                String customerName = rs.getString("customer_Name"); 
                String address = rs.getString("address"); 
                String phone = rs.getString("phone");

                if (type.equalsIgnoreCase("Savings")) {
                    return new SavingsAccount(accNo, balance, customerName, phone, address);
                } else {
                    return new CurrentAccount(accNo, balance, customerName, phone, address);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }
   
    public void deposit(String accNo, double amount){
        BankAccount acc= findAccount(accNo);

        if (acc == null) {
            System.out.println("Account Not Found");
            return;
        }

        acc.deposit(amount);
        updateBalance(accNo, acc.balance);

        System.out.println("Deposit Successful!");

        recordTransaction(
            "Deposit",
            accNo,
            null,
            amount
                        );

    }

    public void withdraw(String accNo, double amount){
        BankAccount acc= findAccount(accNo);

        if (acc == null) {
            System.out.println("Account Not Found");
            return;
        }

        boolean success= acc.withdraw(amount);

        if (success) {
            updateBalance(accNo, acc.balance);

            recordTransaction(
                "Withdraw",
                accNo,
                null,
                amount
            );
            System.out.println("Withdrawal Suceessful!");
        }
        else{
                System.out.println("Withdrawal Failed");
            }
    }

    public void transfer(String from, String to, double amount) {
        // BankAccount sender = findAccount(from);
        // BankAccount receiver = findAccount(to);

        // if (sender != null && receiver != null) {
        //     sender.withdraw(amount);
        //     receiver.deposit(amount);
        //     System.out.println("Transfer successful!");
        // } else {
        //     System.out.println("Account not found!");
        // }
        BankAccount sender = findAccount(from);
        BankAccount receiver = findAccount(to);

        if (sender == null || receiver == null) {
            System.out.println("Account not found!");
            return;
        }

        boolean success = sender.withdraw(amount);

        if (success) {

            receiver.deposit(amount);

            updateBalance(from, sender.balance);
            updateBalance(to, receiver.balance);

            recordTransaction(
                "Transfer",
                from,
                to,
                amount
            );

            System.out.println("Transfer successful!");

        } else {
            System.out.println("Transfer failed.");
        }
    }

    // UPDATE BALANCE
    public void updateBalance(
        String accNo,
        double newBalance
    ) {

        String query =
        "UPDATE accounts SET balance=? WHERE account_number=?";

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt =
                conn.prepareStatement(query)
        ) {

            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accNo);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    public void showAllAccounts() {
        // for (BankAccount acc : accounts) {
        //     acc.showDetails();
        // }
         String query = "SELECT * FROM accounts";

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt =
                conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()
        ) {

            while (rs.next()) {

                System.out.println("\n-------------------");
                System.out.println(
                    "Account No: " +
                    rs.getString("account_number")
                );

                System.out.println(
                    "Type: " +
                    rs.getString("account_type")
                );

                System.out.println(
                    "Name: " +
                    rs.getString("customer_name")
                );

                System.out.println(
                    "Phone: " +
                    rs.getString("phone")
                );

                System.out.println(
                    "Address: " +
                    rs.getString("address")
                );

                System.out.println(
                    "Balance: " +
                    rs.getDouble("balance")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //method used to record transaction details in db
    public void recordTransaction(
     String type,
     String from,
     String to,
      double amount
                    ) 
   {

        String query =
        "INSERT INTO transactions " +
        "(transaction_type, from_account, to_account, amount) " +
        "VALUES (?, ?, ?, ?)";

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt =
                conn.prepareStatement(query)
        ) {

            pstmt.setString(1, type);
            pstmt.setString(2, from);
            pstmt.setString(3, to);
            pstmt.setDouble(4, amount);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showTransactions() {

    String query =
    "SELECT * FROM transactions ORDER BY id DESC";

    try (
        Connection conn = DatabaseManager.getConnection();
        PreparedStatement pstmt =
            conn.prepareStatement(query);
        ResultSet rs =
            pstmt.executeQuery()
    ) {

        while (rs.next()) {

            System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("transaction_type") + " | " +
                rs.getString("from_account") + " | " +
                rs.getString("to_account") + " | " +
                rs.getDouble("amount") + " | " +
                rs.getTimestamp("transaction_time")
            );
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
}
