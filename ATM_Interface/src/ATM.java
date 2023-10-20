import javax.swing.*;

public class ATM {
    private BankAccount account;

    public ATM(double initialBalance) {
        account = new BankAccount(initialBalance);
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            String[] options = {"Check Balance", "Deposit", "Withdraw", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "ATM Machine",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Current Balance: $" + account.getBalance());
                    break;
                case 1:
                    double depositAmount = getAmount("Enter deposit amount:");
                    account.deposit(depositAmount);
                    JOptionPane.showMessageDialog(null, "Deposit Successful. Current Balance: $" + account.getBalance());
                    break;
                case 2:
                    double withdrawAmount = getAmount("Enter withdrawal amount:");
                    boolean success = account.withdraw(withdrawAmount);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Withdrawal Successful. Current Balance: $" + account.getBalance());
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient funds. Withdrawal failed.");
                    }
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, "Thank you for using the ATM!");
    }

    private double getAmount(String message) {
        double amount = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(message);
                amount = Double.parseDouble(input);
                if (amount <= 0) {
                    throw new NumberFormatException();
                }
                validInput = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid positive number.");
            }
        }
        return amount;
    }

    public static void main(String[] args) {
        double initialBalance = 1000; // Initial balance for the bank account
        ATM atm = new ATM(initialBalance);
        atm.run();
    }
}
