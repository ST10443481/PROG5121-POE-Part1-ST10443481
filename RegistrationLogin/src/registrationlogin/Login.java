package registrationlogin;

import java.util.regex.Pattern;

/**
 * Login class that handles user registration and authentication
 * Provides methods for validating username, password, and cell phone number
 * as well as registering and authenticating users.
 * 
 * @author Student
 * @version 1.0
 */
public class Login {
    
    // Instance variables to store user details
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    
    /**
     * Default constructor initializing empty user details
     */
    public Login() {
        this.username = "";
        this.password = "";
        this.cellPhoneNumber = "";
        this.firstName = "";
        this.lastName = "";
    }
    
    /**
     * Checks if username contains an underscore and is no more than 5 characters long
     * 
     * @param username The username to validate
     * @return true if username meets format requirements, false otherwise
     */
    public boolean checkUserName(String username) {
        // Check for null username
        if (username == null) {
            return false;
        }
        
        // Check for underscore and length requirement (max 5 characters)
        return username.contains("_") && username.length() <= 5;
    }
    
    /**
     * Checks if password meets all complexity requirements:
     * - At least 8 characters long
     * - Contains at least one capital letter
     * - Contains at least one number
     * - Contains at least one special character
     * 
     * @param password The password to validate
     * @return true if password meets all requirements, false otherwise
     */
    public boolean checkPasswordComplexity(String password) {
        // Check for null password or insufficient length
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        // Iterate through each character to check requirements
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
            
            // Early exit if all requirements are met
            if (hasCapital && hasNumber && hasSpecial) {
                break;
            }
        }
        
        return hasCapital && hasNumber && hasSpecial;
    }
    
    /**
     * Checks if cell phone number contains international country code 
     * and is correctly formatted.
     * Regular expression pattern referenced from:
     * Java Documentation - Pattern class (Oracle, 2023)
     * 
     * @param cellPhoneNumber The cell phone number to validate
     * @return true if cell phone number is correctly formatted, false otherwise
     */
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        // Pattern for South African international format: +27 followed by exactly 9 digits
        // Total length: 12 characters including '+'
        // Example: +27838968976
        String cellPhonePattern = "^\\+27[0-9]{9}$";
        
        // Check for null and pattern match
        return cellPhoneNumber != null && Pattern.matches(cellPhonePattern, cellPhoneNumber);
    }
    
    /**
     * Registers a user with the provided details after validating all inputs
     * 
     * @param username The username to register
     * @param password The password for the account
     * @param cellPhoneNumber The user's cell phone number
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @return Registration status message indicating success or specific error
     */
    public String registerUser(String username, String password, 
                               String cellPhoneNumber, String firstName, String lastName) {
        
        // Check username format
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        // Check password complexity
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        // Check cell phone number format
        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        // All validations passed - store user details
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        
        return "Registration Successful!";
    }
    
    /**
     * Verifies login credentials against stored user details
     * 
     * @param usernameInput The username entered for login
     * @param passwordInput The password entered for login
     * @return true if credentials match stored user, false otherwise
     */
    public boolean loginUser(String usernameInput, String passwordInput) {
        // Check if a user has been registered first
        if (this.username.isEmpty() || this.password.isEmpty()) {
            return false;
        }
        
        // Verify credentials match
        return this.username.equals(usernameInput) && this.password.equals(passwordInput);
    }
    
    /**
     * Returns the login status message based on authentication result
     * 
     * @param usernameInput The username entered for login
     * @param passwordInput The password entered for login
     * @return Login status message welcoming the user or indicating failure
     */
    public String returnLoginStatus(String usernameInput, String passwordInput) {
        if (loginUser(usernameInput, passwordInput)) {
            return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    // ==================== GETTER METHODS ====================
    
    /**
     * Gets the stored username
     * @return The current username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Gets the stored password
     * @return The current password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Gets the stored cell phone number
     * @return The current cell phone number
     */
    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
    
    /**
     * Gets the stored first name
     * @return The current first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Gets the stored last name
     * @return The current last name
     */
    public String getLastName() {
        return lastName;
    }
}