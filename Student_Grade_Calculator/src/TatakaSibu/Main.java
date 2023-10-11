package Student_Grade_Calculator.src.TatakaSibu;

import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        int numberOfSubjects = 0;

        boolean validInput = false;
        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog("Enter the number of subjects:");
                numberOfSubjects = Integer.parseInt(input);

                if (numberOfSubjects <= 0) {
                    throw new IllegalArgumentException("Number of subjects must be greater than 0.");
                }
                validInput = true; // If no exception, exit the loop
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer for the number of subjects.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        int[] marks = new int[numberOfSubjects];
        for (int i = 0; i < numberOfSubjects; i++) {
            int subjectMark = -1; // Invalid initial value to trigger the do-while loop
            do {
                try {
                    String input = JOptionPane.showInputDialog("Enter marks obtained in subject " + (i + 1) + " (out of 100):");
                    subjectMark = Integer.parseInt(input);

                    if (subjectMark < 0 || subjectMark > 100) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a mark between 0 and 100.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer for the marks.");
                    subjectMark = -1; // Set an invalid value to trigger the do-while loop
                }
            } while (subjectMark < 0 || subjectMark > 100);

            marks[i] = subjectMark;
        }

        // Calculate the total marks.
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }

        // Calculate the average percentage.
        double averagePercentage = (double) totalMarks / numberOfSubjects;

        // Create a DecimalFormat object with the pattern "0.00" for two decimal places
        DecimalFormat df = new DecimalFormat("0.00");

        // Format the averagePercentage using the DecimalFormat
        String formattedPercentage = df.format(averagePercentage);

        // Calculate the grade.
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Display the results to the user.
        String message = "Results:\nTotal Marks: " + totalMarks +
                "\nAverage Percentage: " + formattedPercentage + "%" +
                "\nGrade: " + grade;
        JOptionPane.showMessageDialog(null, message);
    }
}
