package registrationlogin;

import java.util.Scanner;

/**
 * QuickChat Console Application
 * Main application that integrates login and messaging features.
 * Provides a complete console-based chat system with user authentication.
 * 
 * @author Student
 * @version 2.0
 */
public class QuickChat {
    
    private static Login login;
    private static boolean isLoggedIn = false;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        login = new Login();
        
        // Show welcome screen
        displayWelcomeScreen();
        
        // Registration and Login Phase
        if (performRegistrationAndLogin()) {
            isLoggedIn = true;
            
            // Show QuickChat welcome
            System.out.println();
            System.out.println("=========================================");
            System.out.println("       Welcome to QuickChat!              ");
            System.out.println("=========================================");
            System.out.println();
            System.out.println("Hello " + login.getFirstName() + " " + login.getLastName() + "!");
            System.out.println("You are now logged in and can send messages.");
            System.out.println();
            
            // Main menu loop
            runMainMenu();
        }
        
        scanner.close();
    }
    
    /**
     * Displays the initial welcome screen
     */
    private static void displayWelcomeScreen() {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("   REGISTRATION AND LOGIN SYSTEM          ");
        System.out.println("=========================================");
        System.out.println();
        System.out.println("Welcome! Please register or login to continue.");
        System.out.println();
    }
    
    /**
     * Handles user registration and login
     * @return true if login successful
     */
    private static boolean performRegistrationAndLogin() {
        boolean registered = false;
        
        // Registration Phase
        while (!registered) {
            String registrationResult = performRegistration();
            
            if (registrationResult.equals("Registration Successful!")) {
                System.out.println();
                System.out.println("[SUCCESS] Registration Successful!");
                System.out.println("You may now log in.");
                System.out.println();
                registered = true;
            } else {
                System.out.println();
                System.out.println("[ERROR] " + registrationResult);
                System.out.print("Would you like to try again? (yes/no): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                
                if (!choice.equals("yes") && !choice.equals("y")) {
                    System.out.println("Thank you for using the system. Goodbye!");
                    System.exit(0);
                }
                System.out.println();
            }
        }
        
        // Login Phase
        int loginAttempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (loginAttempts < MAX_ATTEMPTS) {
            String loginResult = performLogin(loginAttempts + 1, MAX_ATTEMPTS);
            
            if (loginResult.startsWith("Welcome")) {
                System.out.println();
                System.out.println("[SUCCESS] " + loginResult);
                System.out.println("Access Granted!");
                System.out.println();
                return true;
            } else {
                loginAttempts++;
                System.out.println();
                System.out.println("[ERROR] " + loginResult);
                
                if (loginAttempts < MAX_ATTEMPTS) {
                    System.out.println("Attempts remaining: " + (MAX_ATTEMPTS - loginAttempts));
                    System.out.print("Would you like to try again? (yes/no): ");
                    String choice = scanner.nextLine().trim().toLowerCase();
                    
                    if (!choice.equals("yes") && !choice.equals("y")) {
                        System.out.println("Thank you for using the system. Goodbye!");
                        System.exit(0);
                    }
                } else {
                    System.out.println("[ERROR] Maximum login attempts reached.");
                    System.out.println("Access Denied.");
                    System.exit(0);
                }
            }
        }
        return false;
    }
    
    /**
     * Handles the registration process via console
     */
    private static String performRegistration() {
        System.out.println("=========================================");
        System.out.println("         USER REGISTRATION               ");
        System.out.println("=========================================");
        System.out.println();
        System.out.println("Requirements:");
        System.out.println("- Username: Must contain '_' and max 5 characters");
        System.out.println("- Password: Min 8 chars, 1 capital, 1 number, 1 special");
        System.out.println("- Phone: Must include international code (e.g., +27XXXXXXXXX)");
        System.out.println();
        
        // Get username
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();
        
        // Get password
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        // Get cell phone
        System.out.print("Enter Cell Phone Number (+27XXXXXXXXX): ");
        String cellPhone = scanner.nextLine().trim();
        
        // Get names
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine().trim();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine().trim();
        
        return login.registerUser(username, password, cellPhone, firstName, lastName);
    }
    
    /**
     * Handles the login process via console
     */
    private static String performLogin(int attempt, int maxAttempts) {
        System.out.println("=========================================");
        System.out.println("            USER LOGIN                   ");
        System.out.println("=========================================");
        System.out.println("Attempt " + attempt + " of " + maxAttempts);
        System.out.println();
        
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        return login.returnLoginStatus(username, password);
    }
    
    /**
     * Runs the main QuickChat menu
     */
      private static void runMainMenu() {
        boolean running = true;
        
        while (running) {
            displayMainMenu();
            System.out.print("Select an option: ");
            String option = scanner.nextLine().trim();
            
            // Used if-else instead of switch for better string matching
            if (option.equals("1")) {
                handleSendMessages();
            } else if (option.equals("2")) {
                // Show Coming Soon message
                System.out.println();
                System.out.println("=========================================");
                System.out.println("         COMING SOON!                     ");
                System.out.println("=========================================");
                System.out.println("The 'Show Recently Sent Messages' feature");
                System.out.println("is currently under development.");
                System.out.println("Please check back later!");
                System.out.println("=========================================");
                System.out.println();
                
                // Wait for user to press Enter
                System.out.print("Press Enter to continue...");
                scanner.nextLine();
                
            } else if (option.equals("3")) {
                System.out.print("Are you sure you want to quit? (yes/no): ");
                String confirm = scanner.nextLine().trim().toLowerCase();
                if (confirm.equals("yes") || confirm.equals("y")) {
                    running = false;
                    System.out.println();
                    System.out.println("=========================================");
                    System.out.println("Thank you for using QuickChat!");
                    System.out.println("Goodbye " + login.getFirstName() + "!");
                    System.out.println("=========================================");
                }
            } else {
                System.out.println();
                System.out.println("Invalid option. Please select 1, 2, or 3.");
                System.out.println();
            }
        }
    }
    
    /**
     * Displays the main menu
     */
    private static void displayMainMenu() {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("           QUICKCHAT MENU                ");
        System.out.println("=========================================");
        System.out.println("User: " + login.getFirstName() + " " + login.getLastName());
        System.out.println("-----------------------------------------");
        System.out.println("1. Send Messages");
        System.out.println("2. Show Recently Sent Messages");
        System.out.println("3. Quit");
        System.out.println("=========================================");
        System.out.println();
    }
    
    /**
     * Handles the send messages feature
     */
    private static void handleSendMessages() {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("         SEND MESSAGES                    ");
        System.out.println("=========================================");
        System.out.println();
        
        // Ask for number of messages
        System.out.print("How many messages would you like to send? ");
        String numInput = scanner.nextLine().trim();
        
        int numMessages;
        try {
            numMessages = Integer.parseInt(numInput);
            if (numMessages <= 0) {
                System.out.println("Please enter a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please enter a valid number.");
            return;
        }
        
        // Reset counters for new message session
        Message.resetCounters();
        
        System.out.println();
        System.out.println("You will now enter " + numMessages + " message(s).");
        System.out.println();
        
        // Process each message
        for (int i = 1; i <= numMessages; i++) {
            boolean messageProcessed = processMessage(i, numMessages);
            
            if (!messageProcessed) {
                System.out.println();
                System.out.print("Do you want to continue sending messages? (yes/no): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("yes") && !choice.equals("y")) {
                    break;
                }
                System.out.println();
            }
        }
        
        // Display summary
        showMessageSummary();
    }
    
    /**
     * Processes a single message
     */
    private static boolean processMessage(int currentMessage, int totalMessages) {
        System.out.println("-----------------------------------------");
        System.out.println("Message " + currentMessage + " of " + totalMessages);
        System.out.println("-----------------------------------------");
        
        // Get recipient cell number
        System.out.print("Enter recipient's cell number (+27XXXXXXXXX): ");
        String recipient = scanner.nextLine().trim();
        
        // Validate recipient number
        Message tempMessage = new Message();
        String validationResult = tempMessage.checkRecipientCell(recipient);
        
        if (!validationResult.equals("Cell phone number successfully captured.")) {
            System.out.println("[ERROR] " + validationResult);
            return false;
        }
        
        // Get message content
        System.out.print("Enter your message (max 250 characters): ");
        String messageContent = scanner.nextLine().trim();
        
        // Validate message length
        String lengthValidation = tempMessage.validateMessageLength(messageContent);
        
        if (!lengthValidation.equals("Message ready to send.")) {
            System.out.println("[ERROR] " + lengthValidation);
            return false;
        }
        
        // Create the message
        Message message = new Message(recipient, messageContent);
        
        // Show message preview
        System.out.println();
        System.out.println("=========================================");
        System.out.println("         MESSAGE PREVIEW                 ");
        System.out.println("=========================================");
        System.out.println("Message ID:   " + message.getMessageID());
        System.out.println("Recipient:    " + message.getRecipientCell());
        System.out.println("Message:      " + message.getMessageContent());
        System.out.println("Message Hash: " + message.getMessageHash());
        System.out.println("=========================================");
        System.out.println();
        
        // Get user choice
        System.out.println("What would you like to do?");
        System.out.println("1. Send Message");
        System.out.println("2. Store Message");
        System.out.println("0. Disregard Message");
        System.out.print("Select option: ");
        
        String choice = scanner.nextLine().trim();
        
        String result;
        switch (choice) {
            case "1" -> result = message.sendMessage(1);
            case "2" -> result = message.sendMessage(2);
            case "0" -> result = message.sendMessage(0);
            default -> {
                System.out.println("Invalid option. Message disregarded.");
                return false;
            }
        }
        
        // Show result
        System.out.println();
        System.out.println(result);
        System.out.println();
        System.out.println("Message Details:");
        System.out.println("Message ID: " + message.getMessageID());
        System.out.println("Message Hash: " + message.getMessageHash());
        System.out.println("Recipient: " + message.getRecipientCell());
        System.out.println("Message: " + message.getMessageContent());
        System.out.println();
        
        return true;
    }
    
    /**
     * Displays summary of all messages
     */
    private static void showMessageSummary() {
        Message message = new Message();
        int totalSent = message.returnTotalMessages();
        
        System.out.println();
        System.out.println("=========================================");
        System.out.println("         MESSAGE SUMMARY                 ");
        System.out.println("=========================================");
        System.out.println();
        System.out.println("Total Messages Sent: " + totalSent);
        System.out.println();
        
        if (totalSent > 0) {
            System.out.println(message.printMessages());
        } else {
            System.out.println("No messages were sent in this session.");
        }
        System.out.println();
    }
}