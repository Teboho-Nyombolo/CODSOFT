package Number_Game;

import javax.swing.JOptionPane;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int maxAttempts = 10;
        int attempts = 0;
        int randomNumber = new Random().nextInt(100) + 1;

        JOptionPane.showMessageDialog(null, "Welcome to Number Game");

        while (attempts < maxAttempts) {
            String userInput = JOptionPane.showInputDialog("Guess a number between 1 and 100:");
            int userGuess;

            try {
                userGuess = Integer.parseInt(userInput);

                if (userGuess >= 1 && userGuess <= 100) {
                    if (userGuess == randomNumber) {
                        JOptionPane.showMessageDialog(null, "Congratulations! You guessed the correct number!");
                        double percentage = ((maxAttempts - attempts) / (double) maxAttempts) * 100;
                        JOptionPane.showMessageDialog(null, "You scored: " + percentage + "%");
                        break;
                    } else {
                        if (userGuess < randomNumber) {
                            JOptionPane.showMessageDialog(null, "Your guess is too low. Try again:");
                        } else {
                            JOptionPane.showMessageDialog(null, "Your guess is too high. Try again:");
                        }
                        attempts++;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 100.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Incorrect input. Please enter a number.");
            }
        }

        if (attempts >= maxAttempts) {
            JOptionPane.showMessageDialog(null, "You have ran out of attempts. The correct number was " + randomNumber);
            double percentage = ((maxAttempts - attempts) / (double) maxAttempts) * 100;
            JOptionPane.showMessageDialog(null, "You scored: " + percentage + "%");
        }

        String playAgain = JOptionPane.showInputDialog(null, "Do you want to play again? (Yes/No)");

        if (playAgain != null && playAgain.equalsIgnoreCase("Yes")) {
            main(args);
        } else {
            JOptionPane.showMessageDialog(null, "Thank you for playing!");
        }
    }
}
