package registrationlogin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Message class.
 * Tests message validation, hash creation, sending options, and storage.
 * 
 * @author Student
 * @version 2.0
 */
public class MessageTest {
    
    private Message message;
    
    public MessageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("========================================");
        System.out.println("Starting Message Class Unit Tests");
        System.out.println("========================================\n");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("\n========================================");
        System.out.println("All Message Tests Completed");
        System.out.println("========================================");
    }
    
    @Before
    public void setUp() {
        message = new Message();
        Message.resetCounters();
        System.out.println("Test Setup: New Message instance created");
    }
    
    @After
    public void tearDown() {
        message = null;
        Message.resetCounters();
        System.out.println("Test Teardown: Message instance cleared\n");
    }

    // ==================== MESSAGE ID TESTS ====================
    
    @Test
    public void testCheckMessageID_Valid() {
        System.out.println("TEST: checkMessageID - Valid");
        String messageID = "1234567890";
        boolean result = message.checkMessageID(messageID);
        assertTrue("Message ID should be valid", result);
        System.out.println("TEST PASSED\n");
    }
    
    @Test
    public void testCheckMessageID_Invalid() {
        System.out.println("TEST: checkMessageID - Invalid (too long)");
        String messageID = "12345678901";
        boolean result = message.checkMessageID(messageID);
        assertFalse("Message ID should be invalid", result);
        System.out.println("TEST PASSED\n");
    }

    // ==================== MESSAGE LENGTH TESTS ====================
    
    @Test
    public void testValidateMessageLength_Success() {
        System.out.println("TEST: validateMessageLength - Success");
        String messageContent = "Hi Mike, can you join us for dinner tonight?";
        String expResult = "Message ready to send.";
        String result = message.validateMessageLength(messageContent);
        assertEquals(expResult, result);
        System.out.println("Expected: \"" + expResult + "\"");
        System.out.println("Actual: \"" + result + "\"");
        System.out.println("TEST PASSED\n");
    }
    
    @Test
    public void testValidateMessageLength_Failure() {
        System.out.println("TEST: validateMessageLength - Failure");
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            sb.append("x");
        }
        String longMessage = sb.toString();
        
        String result = message.validateMessageLength(longMessage);
        assertTrue("Should indicate message is too long", 
                   result.contains("exceeds 250 characters"));
        assertTrue("Should indicate excess characters", 
                   result.contains("by 10"));
        System.out.println("Result: \"" + result + "\"");
        System.out.println("TEST PASSED\n");
    }

    // ==================== RECIPIENT CELL TESTS ====================
    
    @Test
    public void testCheckRecipientCell_Success() {
        System.out.println("TEST: checkRecipientCell - Success");
        String recipientCell = "+27718693002";
        String expResult = "Cell phone number successfully captured.";
        String result = message.checkRecipientCell(recipientCell);
        assertEquals(expResult, result);
        System.out.println("Expected: \"" + expResult + "\"");
        System.out.println("Actual: \"" + result + "\"");
        System.out.println("TEST PASSED\n");
    }
    
    @Test
    public void testCheckRecipientCell_Failure() {
        System.out.println("TEST: checkRecipientCell - Failure");
        String recipientCell = "08575975889";
        String expResult = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        String result = message.checkRecipientCell(recipientCell);
        assertEquals(expResult, result);
        System.out.println("Expected: \"" + expResult + "\"");
        System.out.println("Actual: \"" + result + "\"");
        System.out.println("TEST PASSED\n");
    }

    // ==================== MESSAGE HASH TESTS ====================
    
    @Test
    public void testCreateMessageHash_TestCase1() {
        System.out.println("TEST: createMessageHash - Test Case 1");
        
        Message testMessage = new Message("+27718693002", "Hi Mike can you join us for dinner tonight");
        testMessage.setMessageID("0012345678");
        testMessage.setMessageNumber(0);
        
        String result = testMessage.createMessageHash();
        System.out.println("Generated Hash: " + result);
        
        assertEquals("00:0:HITONIGHT", result);
        System.out.println("TEST PASSED\n");
    }
    
    @Test
    public void testCreateMessageHash_TestCase2() {
        System.out.println("TEST: createMessageHash - Test Case 2");
        
        Message testMessage = new Message("08575975889", "Hi Keegan did you receive the payment");
        testMessage.setMessageID("0098765432");
        testMessage.setMessageNumber(1);
        
        String result = testMessage.createMessageHash();
        System.out.println("Generated Hash: " + result);
        
        assertTrue("Hash should contain 'HI'", result.contains("HI"));
        assertTrue("Hash should contain 'PAYMENT'", result.contains("PAYMENT"));
        System.out.println("TEST PASSED\n");
    }
    
    @Test
    public void testCreateMessageHash_MultipleMessages() {
        System.out.println("TEST: createMessageHash - Multiple Messages Loop");
        
        String[][] testCases = {
            {"+27718693002", "Hi Mike can you join us for dinner tonight", "0012345678", "0"},
            {"+27838968976", "Hello Sarah the meeting is at 3pm", "0012345679", "1"},
            {"+27123456789", "Please confirm your attendance for the event", "0012345680", "2"}
        };
        
        for (int i = 0; i < testCases.length; i++) {
            Message testMessage = new Message(testCases[i][0], testCases[i][1]);
            testMessage.setMessageID(testCases[i][2]);
            testMessage.setMessageNumber(Integer.parseInt(testCases[i][3]));
            
            String hash = testMessage.createMessageHash();
            System.out.println("Message " + (i + 1) + " Hash: " + hash);
            
            assertNotNull("Hash should not be null", hash);
            assertFalse("Hash should not be empty", hash.isEmpty());
            assertTrue("Hash should contain colon separator", hash.contains(":"));
        }
        System.out.println("TEST PASSED\n");
    }

    // ==================== MESSAGE ID GENERATION TEST ====================
    
    @Test
    public void testMessageIDGenerated() {
        System.out.println("TEST: Message ID Generated");
        
        Message testMessage = new Message("+27718693002", "Test message");
        String messageID = testMessage.getMessageID();
        
        assertNotNull("Message ID should not be null", messageID);
        assertEquals("Message ID should be 10 characters", 10, messageID.length());
        assertTrue("Message ID should be numeric", messageID.matches("\\d{10}"));
        
        System.out.println("Message ID generated: " + messageID);
        System.out.println("TEST PASSED\n");
    }

    // ==================== SEND MESSAGE OPTIONS TESTS ====================
    
    @Test
    public void testSendMessage_Send() {
        System.out.println("TEST: sendMessage - Option 1 (Send)");
        
        Message testMessage = new Message("+27718693002", "Hi Mike can you join us for dinner tonight");
        String result = testMessage.sendMessage(1);
        
        assertEquals("Message successfully sent.", result);
        assertEquals("sent", testMessage.getStatus());
        assertEquals(1, Message.getTotalMessagesSent());
        System.out.println("Result: " + result);
        System.out.println("TEST PASSED\n");
    }
    
    @Test
    public void testSendMessage_Disregard() {
        System.out.println("TEST: sendMessage - Option 0 (Disregard)");
        
        Message testMessage = new Message("+27718693002", "Test message");
        String result = testMessage.sendMessage(0);
        
        assertEquals("Press 0 to delete the message.", result);
        assertEquals("disregarded", testMessage.getStatus());
        System.out.println("Result: " + result);
        System.out.println("TEST PASSED\n");
    }
    
    @Test
    public void testSendMessage_Store() {
        System.out.println("TEST: sendMessage - Option 2 (Store)");
        
        Message testMessage = new Message("+27718693002", "Test message for storage");
        String result = testMessage.sendMessage(2);
        
        assertEquals("Message successfully stored.", result);
        assertEquals("stored", testMessage.getStatus());
        System.out.println("Result: " + result);
        System.out.println("TEST PASSED\n");
    }

    // ==================== TOTAL MESSAGES TEST ====================
    
    @Test
    public void testReturnTotalMessages() {
        System.out.println("TEST: returnTotalMessages");
        
        Message.resetCounters();
        
        Message msg1 = new Message("+27718693002", "First test message");
        msg1.sendMessage(1);
        
        Message msg2 = new Message("+27123456789", "Second test message");
        msg2.sendMessage(1);
        
        int total = message.returnTotalMessages();
        assertEquals(2, total);
        System.out.println("Total messages: " + total);
        System.out.println("TEST PASSED\n");
    }

    // ==================== PRINT MESSAGES TEST ====================
    
    @Test
    public void testPrintMessages() {
        System.out.println("TEST: printMessages");
        
        Message.resetCounters();
        
        Message msg1 = new Message("+27718693002", "Hi Mike can you join us for dinner tonight");
        msg1.sendMessage(1);
        
        String output = message.printMessages();
        System.out.println(output);
        
        assertTrue("Output should contain message details", output.contains("Hi Mike"));
        assertTrue("Output should contain recipient", output.contains("+27718693002"));
        System.out.println("TEST PASSED\n");
    }
}