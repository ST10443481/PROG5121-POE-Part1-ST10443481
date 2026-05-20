package registrationlogin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Login class.
 * This class tests all validation methods, registration functionality,
 * and login authentication using JUnit 4 assertions.
 * 
 * @author Student
 * @version 1.0
 */
public class LoginTest {
    
    private Login login;
    
    public LoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("========================================");
        System.out.println("Starting Login Class Unit Tests");
        System.out.println("========================================\n");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("\n========================================");
        System.out.println("All Login Tests Completed");
        System.out.println("========================================");
    }
    
    @Before
    public void setUp() {
        login = new Login();
        System.out.println("Test Setup: New Login instance created");
    }
    
    @After
    public void tearDown() {
        login = null;
        System.out.println("Test Teardown: Login instance cleared\n");
    }

    // ==================== USERNAME VALIDATION TESTS ====================
    
    /**
     * Test of checkUserName method - Correctly formatted username
     * Test Data: "kyl_1"
     * Expected: true
     */
    @Test
    public void testCheckUserName_CorrectFormat() {
        System.out.println("TEST: checkUserName - Correct Format");
        System.out.println("Input: \"kyl_1\"");
        
        String username = "kyl_1";
        boolean expResult = true;
        boolean result = login.checkUserName(username);
        
        System.out.println("Expected: " + expResult + ", Actual: " + result);
        assertEquals(expResult, result);
        assertTrue(result);
        System.out.println("TEST PASSED\n");
    }
    
    /**
     * Test of checkUserName method - Incorrectly formatted username
     * Test Data: "kyle!!!!!!!"
     * Expected: false
     */
    @Test
    public void testCheckUserName_IncorrectFormat() {
        System.out.println("TEST: checkUserName - Incorrect Format");
        System.out.println("Input: \"kyle!!!!!!!\"");
        
        String username = "kyle!!!!!!!";
        boolean expResult = false;
        boolean result = login.checkUserName(username);
        
        System.out.println("Expected: " + expResult + ", Actual: " + result);
        assertEquals(expResult, result);
        assertFalse(result);
        System.out.println("TEST PASSED\n");
    }

    // ==================== PASSWORD COMPLEXITY TESTS ====================
    
    /**
     * Test of checkPasswordComplexity method - Meets all requirements
     * Test Data: "Ch&sec@ke99!"
     * Expected: true
     */
    @Test
    public void testCheckPasswordComplexity_MeetsRequirements() {
        System.out.println("TEST: checkPasswordComplexity - Meets Requirements");
        System.out.println("Input: \"Ch&sec@ke99!\"");
        
        String password = "Ch&sec@ke99!";
        boolean expResult = true;
        boolean result = login.checkPasswordComplexity(password);
        
        System.out.println("Expected: " + expResult + ", Actual: " + result);
        assertEquals(expResult, result);
        assertTrue(result);
        System.out.println("TEST PASSED\n");
    }
    
    /**
     * Test of checkPasswordComplexity method - Does not meet requirements
     * Test Data: "password"
     * Expected: false
     */
    @Test
    public void testCheckPasswordComplexity_DoesNotMeetRequirements() {
        System.out.println("TEST: checkPasswordComplexity - Does Not Meet Requirements");
        System.out.println("Input: \"password\"");
        
        String password = "password";
        boolean expResult = false;
        boolean result = login.checkPasswordComplexity(password);
        
        System.out.println("Expected: " + expResult + ", Actual: " + result);
        assertEquals(expResult, result);
        assertFalse(result);
        System.out.println("TEST PASSED\n");
    }

    // ==================== CELL PHONE NUMBER VALIDATION TESTS ====================
    
    /**
     * Test of checkCellPhoneNumber method - Correctly formatted
     * Test Data: "+27838968976"
     * Expected: true
     */
    @Test
    public void testCheckCellPhoneNumber_CorrectFormat() {
        System.out.println("TEST: checkCellPhoneNumber - Correct Format");
        System.out.println("Input: \"+27838968976\"");
        
        String cellPhoneNumber = "+27838968976";
        boolean expResult = true;
        boolean result = login.checkCellPhoneNumber(cellPhoneNumber);
        
        System.out.println("Expected: " + expResult + ", Actual: " + result);
        assertEquals(expResult, result);
        assertTrue(result);
        System.out.println("TEST PASSED\n");
    }
    
    /**
     * Test of checkCellPhoneNumber method - Incorrectly formatted
     * Test Data: "08966553"
     * Expected: false
     */
    @Test
    public void testCheckCellPhoneNumber_IncorrectFormat() {
        System.out.println("TEST: checkCellPhoneNumber - Incorrect Format");
        System.out.println("Input: \"08966553\"");
        
        String cellPhoneNumber = "08966553";
        boolean expResult = false;
        boolean result = login.checkCellPhoneNumber(cellPhoneNumber);
        
        System.out.println("Expected: " + expResult + ", Actual: " + result);
        assertEquals(expResult, result);
        assertFalse(result);
        System.out.println("TEST PASSED\n");
    }

    // ==================== REGISTRATION TESTS ====================
    
    /**
     * Test of registerUser method - Username incorrectly formatted
     */
    @Test
    public void testRegisterUser_UsernameIncorrectlyFormatted() {
        System.out.println("TEST: registerUser - Username Incorrectly Formatted");
        System.out.println("Input: username=\"kyle!!!!!!!\"");
        
        String username = "kyle!!!!!!!";
        String password = "Ch&sec@ke99!";
        String cellPhoneNumber = "+27838968976";
        String firstName = "John";
        String lastName = "Doe";
        
        String expResult = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String result = login.registerUser(username, password, cellPhoneNumber, firstName, lastName);
        
        System.out.println("Expected: \"" + expResult + "\"");
        System.out.println("Actual: \"" + result + "\"");
        assertEquals(expResult, result);
        System.out.println("TEST PASSED\n");
    }

    /**
     * Test of registerUser method - Password does not meet complexity requirements
     */
    @Test
    public void testRegisterUser_PasswordDoesNotMeetComplexity() {
        System.out.println("TEST: registerUser - Password Does Not Meet Complexity");
        System.out.println("Input: password=\"password\"");
        
        String username = "kyl_1";
        String password = "password";
        String cellPhoneNumber = "+27838968976";
        String firstName = "John";
        String lastName = "Doe";
        
        String expResult = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String result = login.registerUser(username, password, cellPhoneNumber, firstName, lastName);
        
        System.out.println("Expected: \"" + expResult + "\"");
        System.out.println("Actual: \"" + result + "\"");
        assertEquals(expResult, result);
        System.out.println("TEST PASSED\n");
    }

    /**
     * Test of registerUser method - Cell phone number incorrectly formatted
     */
    @Test
    public void testRegisterUser_CellPhoneIncorrectlyFormatted() {
        System.out.println("TEST: registerUser - Cell Phone Incorrectly Formatted");
        System.out.println("Input: cellPhoneNumber=\"08966553\"");
        
        String username = "kyl_1";
        String password = "Ch&sec@ke99!";
        String cellPhoneNumber = "08966553";
        String firstName = "John";
        String lastName = "Doe";
        
        String expResult = "Cell phone number incorrectly formatted or does not contain international code.";
        String result = login.registerUser(username, password, cellPhoneNumber, firstName, lastName);
        
        System.out.println("Expected: \"" + expResult + "\"");
        System.out.println("Actual: \"" + result + "\"");
        assertEquals(expResult, result);
        System.out.println("TEST PASSED\n");
    }

    /**
     * Test of registerUser method - Successful registration
     */
    @Test
    public void testRegisterUser_Successful() {
        System.out.println("TEST: registerUser - Successful Registration");
        System.out.println("Input: username=\"kyl_1\", password=\"Ch&sec@ke99!\", cell=\"+27838968976\"");
        
        String username = "kyl_1";
        String password = "Ch&sec@ke99!";
        String cellPhoneNumber = "+27838968976";
        String firstName = "John";
        String lastName = "Doe";
        
        String expResult = "Registration Successful!";
        String result = login.registerUser(username, password, cellPhoneNumber, firstName, lastName);
        
        System.out.println("Expected: \"" + expResult + "\"");
        System.out.println("Actual: \"" + result + "\"");
        assertEquals(expResult, result);
        System.out.println("TEST PASSED\n");
    }

    // ==================== LOGIN AUTHENTICATION TESTS ====================
    
    /**
     * Test of loginUser method - Successful login
     */
    @Test
    public void testLoginUser_Successful() {
        System.out.println("TEST: loginUser - Successful Login");
        
        login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976", "John", "Doe");
        System.out.println("  - Registered test user: kyl_1");
        System.out.println("  - Attempting login with correct credentials");
        
        boolean expResult = true;
        boolean result = login.loginUser("kyl_1", "Ch&sec@ke99!");
        
        System.out.println("Expected: " + expResult + ", Actual: " + result);
        assertEquals(expResult, result);
        assertTrue(result);
        System.out.println("TEST PASSED\n");
    }

    /**
     * Test of loginUser method - Failed login
     */
    @Test
    public void testLoginUser_Failed() {
        System.out.println("TEST: loginUser - Failed Login");
        
        login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976", "John", "Doe");
        System.out.println("  - Registered test user: kyl_1");
        System.out.println("  - Attempting login with incorrect credentials");
        
        boolean expResult = false;
        boolean result = login.loginUser("wrong_username", "wrong_password");
        
        System.out.println("Expected: " + expResult + ", Actual: " + result);
        assertEquals(expResult, result);
        assertFalse(result);
        System.out.println("TEST PASSED\n");
    }

    // ==================== LOGIN STATUS MESSAGE TESTS ====================
    
    /**
     * Test of returnLoginStatus method - Successful login message
     */
    @Test
    public void testReturnLoginStatus_Successful() {
        System.out.println("TEST: returnLoginStatus - Successful Login Message");
        
        login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976", "John", "Doe");
        System.out.println("  - Registered test user: kyl_1 (John Doe)");
        
        String expResult = "Welcome John, Doe it is great to see you again.";
        String result = login.returnLoginStatus("kyl_1", "Ch&sec@ke99!");
        
        System.out.println("Expected: \"" + expResult + "\"");
        System.out.println("Actual: \"" + result + "\"");
        assertEquals(expResult, result);
        System.out.println("TEST PASSED\n");
    }

    /**
     * Test of returnLoginStatus method - Failed login message
     */
    @Test
    public void testReturnLoginStatus_Failed() {
        System.out.println("TEST: returnLoginStatus - Failed Login Message");
        
        login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976", "John", "Doe");
        System.out.println("  - Registered test user: kyl_1 (John Doe)");
        System.out.println("  - Attempting login with wrong credentials");
        
        String expResult = "Username or password incorrect, please try again.";
        String result = login.returnLoginStatus("wrong_username", "wrong_password");
        
        System.out.println("Expected: \"" + expResult + "\"");
        System.out.println("Actual: \"" + result + "\"");
        assertEquals(expResult, result);
        System.out.println("TEST PASSED\n");
    }
}