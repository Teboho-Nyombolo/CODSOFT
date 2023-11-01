package TatakaSibu;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrencyConverter {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

// Request user input for currencies and amount

        String fromCurrency = JOptionPane.showInputDialog("Enter the base currency (e.g., USD):").toUpperCase();
        String toCurrency = JOptionPane.showInputDialog("Enter the target currency (e.g., EUR):").toUpperCase();
        String amountString = JOptionPane.showInputDialog("Enter the amount to convert:");
        String apiKey = "aGr2AYJIlx9MWrCLH6ld38w6HuXSZgc2";

        try {
// Parse amountString to double
            double amount = Double.parseDouble(amountString);

            Request request = new Request.Builder()
                    .url("https://api.apilayer.com/exchangerates_data/convert?to="+toCurrency+"&from="+fromCurrency+"&amount="+amount)
                    .addHeader("apikey", apiKey)
                    .method("GET", null)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

// Parse API response and extract converted amount
            double convertedAmount = extractConvertedAmount(responseBody);

// Get current date and time
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String formattedDate = dateFormat.format(date);

// Display the information using JOptionPane
            String message = "Base Currency: " + fromCurrency + "\n" +
                    "Target Currency: " + toCurrency + "\n" +
                    "Amount: " + amount + "\n" +
                    "Converted Amount: " + convertedAmount + " " + toCurrency + "\n" +
                    "Date and Time: " + formattedDate;

            JOptionPane.showMessageDialog(null, message, "Conversion Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
// Handle invalid input for the amount
            JOptionPane.showMessageDialog(null, "Invalid input for amount. Please enter a valid number.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static double extractConvertedAmount(String responseBody) {
        try {
// Parse the JSON response
            JSONObject json = new JSONObject(responseBody);

// Extract the converted amount from the JSON object
            double convertedAmount =json.getDouble("result");
            return convertedAmount;
        } catch (Exception e) {
// Handle any JSON parsing exception
            e.printStackTrace();
            return 0.0; // Return a default value or handle the error according to your application's logic
        }
    }
}