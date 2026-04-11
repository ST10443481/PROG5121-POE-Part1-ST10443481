package registrationlogin;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Main application class to demonstrate the registration and login feature.
 * This class provides a GUI-based user interface using JOptionPane for
 * testing the registration and login functionality.
 * 
 * @author Student
 * @version 1.0
 */
public class RegistrationLogin {
    
    /**
     * Main method - Entry point for the application
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        
        // Set system look and feel for better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Use default look and feel if system look and feel fails
        }
        
        Login login = new Login();
        
        // Welcome message
        JOptionPane.showMessageDialog(null, """
            Welcome to the Registration and Login System!                              
            Click OK to begin registration.""",
            "Registration and Login System",
            JOptionPane.INFORMATION_MESSAGE);
        
        // Registration Phase
        boolean registrationComplete = false;
        
        while (!registrationComplete) {
            String registrationResult = performRegistration(login);
            
            if (registrationResult.equals("Registration Successful!")) {
                JOptionPane.showMessageDialog(null,
                    "✓ Registration Successful!\n\n" +
                    "You may now log in with your credentials.",
                    "Registration Success",
                    JOptionPane.INFORMATION_MESSAGE);
                registrationComplete = true;
            } else {
                int choice = JOptionPane.showConfirmDialog(null,
                    "✗ " + registrationResult + "\n\n" +
                    "Would you like to try again?",
                    "Registration Failed",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE);
                
                if (choice != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null,
                        "Thank you for using the system. Goodbye!",
                        "Goodbye",
                        JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        }
        
        // Login Phase
        boolean loginComplete = false;
        int loginAttempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (!loginComplete && loginAttempts < MAX_ATTEMPTS) {
            String loginResult = performLogin(login, loginAttempts + 1, MAX_ATTEMPTS);
            
            if (loginResult.startsWith("Welcome")) {
                JOptionPane.showMessageDialog(null,
                    "✓ " + loginResult + "\n\n" +
                    "Access Granted!",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE);
                loginComplete = true;
                
                // Show welcome screen
                showWelcomeScreen(login);
                
            } else {
                loginAttempts++;
                
                if (loginAttempts < MAX_ATTEMPTS) {
                    int choice = JOptionPane.showConfirmDialog(null,
                        "✗ " + loginResult + "\n\n" +
                        "Attempts remaining: " + (MAX_ATTEMPTS - loginAttempts) + "\n\n" +
                        "Would you like to try again?",
                        "Login Failed",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE);
                    
                    if (choice != JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null,
                            "Thank you for using the system. Goodbye!",
                            "Goodbye",
                            JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                        "✗ Maximum login attempts reached.\n\n" +
                        "Access Denied. Please try again later.",
                        "Access Denied",
                        JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        }
    }
    
    /**
     * Handles the registration process using JOptionPane dialogs
     * 
     * @param login The Login instance
     * @return Registration result message
     */
    private static String performRegistration(Login login) {
        
        // Registration instructions
        JOptionPane.showMessageDialog(null,
            "═══════════════════════════════════════\n" +
            "         USER REGISTRATION\n" +
            "═══════════════════════════════════════\n\n" +
            "Please provide the following information:\n\n" +
            "Username Requirements:\n" +
            "  • Must contain an underscore (_)\n" +
            "  • Maximum 5 characters\n" +
            "  • Example: kyl_1\n\n" +
            "Password Requirements:\n" +
            "  • At least 8 characters long\n" +
            "  • At least one capital letter\n" +
            "  • At least one number\n" +
            "  • At least one special character\n" +
            "  • Example: Ch&sec@ke99!\n\n" +
            "Cell Phone Requirements:\n" +
            "  • Must include international code +27\n" +
            "  • Followed by 9 digits\n" +
            "  • Example: +27838968976",
            "Registration Requirements",
            JOptionPane.INFORMATION_MESSAGE);
        
        // Get username
        String username = "";
        while (username.trim().isEmpty()) {
            username = JOptionPane.showInputDialog(null,
                "Enter Username:\n" +
                "(Must contain '_' and max 5 characters)",
                "Username Input",
                JOptionPane.QUESTION_MESSAGE);
            
            if (username == null) {
                int choice = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to cancel registration?",
                    "Cancel Registration",
                    JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }
        
        // Get password with a custom password field
        JPasswordField passwordField = new JPasswordField();
        int passwordOption = JOptionPane.showConfirmDialog(null,
            passwordField,
            "Enter Password:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (passwordOption != JOptionPane.OK_OPTION) {
            System.exit(0);
        }
        String password = new String(passwordField.getPassword());
        
        // Get cell phone number
        String cellPhoneNumber = "";
        while (cellPhoneNumber.trim().isEmpty()) {
            cellPhoneNumber = JOptionPane.showInputDialog(null,
                "Enter Cell Phone Number:\n" +
                "(Format: +27XXXXXXXXX)",
                "Cell Phone Input",
                JOptionPane.QUESTION_MESSAGE);
            
            if (cellPhoneNumber == null) {
                System.exit(0);
            }
        }
        
        // Get first name
        String firstName = "";
        while (firstName.trim().isEmpty()) {
            firstName = JOptionPane.showInputDialog(null,
                "Enter First Name:",
                "First Name Input",
                JOptionPane.QUESTION_MESSAGE);
            
            if (firstName == null) {
                System.exit(0);
            }
        }
        
        // Get last name
        String lastName = "";
        while (lastName.trim().isEmpty()) {
            lastName = JOptionPane.showInputDialog(null,
                "Enter Last Name:",
                "Last Name Input",
                JOptionPane.QUESTION_MESSAGE);
            
            if (lastName == null) {
                System.exit(0);
            }
        }
        
        // Confirm registration details
        String confirmMessage = "Please confirm your registration details:\n\n" +
                               "Username: " + username + "\n" +
                               "Cell Phone: " + cellPhoneNumber + "\n" +
                               "Name: " + firstName + " " + lastName + "\n\n" +
                               "Is this information correct?";
        
        int confirm = JOptionPane.showConfirmDialog(null,
            confirmMessage,
            "Confirm Registration",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return "Registration cancelled by user.";
        }
        
        // Process registration
        return login.registerUser(username, password, cellPhoneNumber, firstName, lastName);
    }
    
    /**
     * Handles the login process using JOptionPane dialogs
     * 
     * @param login The Login instance
     * @param attempt Current login attempt number
     * @param maxAttempts Maximum allowed attempts
     * @return Login result message
     */
    private static String performLogin(Login login, int attempt, int maxAttempts) {
        
        JOptionPane.showMessageDialog(null,
            "USER LOGIN\n" +
            "Attempt " + attempt + " of " + maxAttempts + "\n\n" +
            "Please enter your credentials to continue.",
            "Login",
            JOptionPane.INFORMATION_MESSAGE);
        
        // Create custom login panel with username and password fields
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        
        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField
        };
        
        int option = JOptionPane.showConfirmDialog(null,
            message,
            "Login Credentials",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (option != JOptionPane.OK_OPTION) {
            System.exit(0);
        }
        
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        // Return login status
        return login.returnLoginStatus(username, password);
    }
    
    /**
     * Displays the welcome screen after successful login
     * 
     * @param login The Login instance with user details
     */
    private static void showWelcomeScreen(Login login) {
        String welcomeMessage = 
            "WELCOME TO YOUR ACCOUNT \n" +
            "User Information:\n" +
            "─────────────────────────────────────────────────\n" +
            "Username:        " + login.getUsername() + "\n" +
            "Full Name:       " + login.getFirstName() + " " + login.getLastName() + "\n" +
            "Cell Number:     " + login.getCellPhoneNumber() + "\n" +
            "─────────────────────────────────────────────────\n\n" +
            "Thank You!\n\n" +
            "Have a great day <3";
        
        JOptionPane.showMessageDialog(null,
            welcomeMessage,
            "Welcome " + login.getFirstName() + "!",
            JOptionPane.INFORMATION_MESSAGE);
    }
}