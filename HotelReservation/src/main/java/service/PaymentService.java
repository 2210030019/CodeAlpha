package service;

public class PaymentService {
    public boolean processPayment(double amount, String cardNumber, String expiryDate, String cvv) {
        // Perform basic payment validation
        if (isValidCardNumber(cardNumber) && isValidExpiryDate(expiryDate) && isValidCVV(cvv)) {
            // Simulate successful payment processing
            System.out.println("Processing payment of $" + amount);
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        // Implement basic card number validation logic
        // For example, check the length and ensure it contains only digits
        return cardNumber.length() == 16 && cardNumber.matches("\\d+");
    }

    private boolean isValidExpiryDate(String expiryDate) {
        // Implement basic expiry date validation logic
        // For example, check the format and ensure the date is in the future
        String[] parts = expiryDate.split("/");
        if (parts.length != 2) {
            return false;
        }
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);
        return month >= 1 && month <= 12 && year >= getCurrentYear();
    }

    private boolean isValidCVV(String cvv) {
        // Implement basic CVV validation logic
        // For example, check the length and ensure it contains only digits
        return cvv.length() == 3 && cvv.matches("\\d+");
    }

    private int getCurrentYear() {
        return java.time.Year.now().getValue();
    }
}