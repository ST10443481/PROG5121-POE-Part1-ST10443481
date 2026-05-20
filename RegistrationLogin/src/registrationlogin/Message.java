package registrationlogin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Message class that handles message creation, validation, and storage.
 * Provides methods for managing messages with unique IDs, hashing, and file storage.
 * 
 * @author Student
 * @version 2.0
 */
public class Message {
    
    // Instance variables
    private String messageID;
    private String recipientCell;
    private String messageContent;
    private String messageHash;
    private int messageNumber;
    private String status; // sent, stored, disregarded
    
    // Static counter for total messages
    private static int totalMessagesSent = 0;
    private static int messageCounter = 1;
    
    // ArrayList to store all messages during session
    private static ArrayList<Message> allMessages = new ArrayList<>();
    
    // File path for storing messages
    private static final String STORAGE_FILE_PATH = "messages.txt";
    
    /**
     * Default constructor
     */
    public Message() {
        this.messageID = "";
        this.recipientCell = "";
        this.messageContent = "";
        this.messageHash = "";
        this.messageNumber = 0;
        this.status = "";
    }
    
    /**
     * Constructor with message details
     */
    public Message(String recipientCell, String messageContent) {
        this.recipientCell = recipientCell;
        this.messageContent = messageContent;
        this.messageNumber = messageCounter;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
        this.status = "pending";
    }
    
    /**
     * Generates a random 10-digit message ID
     * @return Generated message ID
     */
    private String generateMessageID() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        
        // Generate 10 random digits
        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }
        
        return id.toString();
    }
    
    /**
     * Checks if message ID is valid (not more than 10 characters)
     * @param messageID The message ID to check
     * @return true if valid, false otherwise
     */
    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10 && messageID.matches("\\d+");
    }
    
    /**
     * Checks if recipient cell number is valid
     * Must contain international code and be correct length
     * @param recipientCell The recipient's cell number
     * @return Validation result message
     */
    public String checkRecipientCell(String recipientCell) {
        // Check for null or empty
        if (recipientCell == null || recipientCell.isEmpty()) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        
        // Check if number starts with international code (+)
        if (recipientCell.startsWith("+")) {
            // Remove the + and check remaining digits
            String digitsOnly = recipientCell.substring(1).replaceAll("[^0-9]", "");
            
            // Check if total digits (including country code) is between 10 and 13
            if (digitsOnly.length() >= 10 && digitsOnly.length() <= 13) {
                return "Cell phone number successfully captured.";
            }
        }
        
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    
    /**
     * Creates a message hash containing first two numbers of message ID,
     * message number, and first and last words of the message
     * @return The generated message hash
     */
    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2 || messageContent == null || messageContent.isEmpty()) {
            return "";
        }
        
        // Get first two numbers of message ID
        String firstTwoID = messageID.substring(0, 2);
        
        // Get first and last words of message
        String[] words = messageContent.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        
        // Remove any non-alphanumeric characters from words
        firstWord = firstWord.replaceAll("[^A-Z0-9]", "");
        lastWord = lastWord.replaceAll("[^A-Z0-9]", "");
        
        // Create hash in format: 00:0:FIRSTWORDLASTWORD
        return firstTwoID + ":" + messageNumber + ":" + firstWord + lastWord;
    }
    
    /**
     * Processes the sending option chosen by user
     * @param option 1 for Send, 0 for Disregard, 2 for Store
     * @return Status message based on selection
     */
    public String sendMessage(int option) {
        switch (option) {
            case 1:
                this.status = "sent";
                totalMessagesSent++;
                messageCounter++;
                allMessages.add(this);
                storeMessage(this);
                return "Message successfully sent.";
                
            case 0:
                this.status = "disregarded";
                return "Press 0 to delete the message.";
                
            case 2:
                this.status = "stored";
                messageCounter++;
                allMessages.add(this);
                storeMessage(this);
                return "Message successfully stored.";
                
            default:
                return "Invalid option selected.";
        }
    }
    
    /**
     * Returns all messages sent during the session
     * @return Formatted string of all messages
     */
    public String printMessages() {
        if (allMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("         ALL MESSAGES SENT                \n");
        sb.append("=========================================\n\n");
        
        for (Message msg : allMessages) {
            sb.append("Message #").append(msg.getMessageNumber()).append("\n");
            sb.append("-----------------------------------------\n");
            sb.append("Message ID:     ").append(msg.getMessageID()).append("\n");
            sb.append("Message Hash:   ").append(msg.getMessageHash()).append("\n");
            sb.append("Recipient:      ").append(msg.getRecipientCell()).append("\n");
            sb.append("Message:        ").append(msg.getMessageContent()).append("\n");
            sb.append("Status:         ").append(msg.getStatus().toUpperCase()).append("\n");
            sb.append("-----------------------------------------\n\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Returns the total number of messages sent
     * @return Total messages sent count
     */
    public int returnTotalMessages() {
        return totalMessagesSent;
    }
    
    /**
     * Stores a message in a text file using pipe-delimited format
     * Each message is stored as: ID|Number|Hash|Recipient|Content|Status
     * @param message The message to store
     */
    public void storeMessage(Message message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STORAGE_FILE_PATH, true))) {
            // Write message as pipe-delimited record
            StringBuilder record = new StringBuilder();
            record.append(message.getMessageID()).append("|");
            record.append(message.getMessageNumber()).append("|");
            record.append(message.getMessageHash()).append("|");
            record.append(message.getRecipientCell()).append("|");
            record.append(message.getMessageContent().replace("|", " ")).append("|");
            record.append(message.getStatus());
            
            writer.write(record.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error storing message: " + e.getMessage());
        }
    }
    
    /**
     * Reads all stored messages from the file
     * @return ArrayList of stored messages
     */
    public static ArrayList<Message> readStoredMessages() {
        ArrayList<Message> storedMessages = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(STORAGE_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 6) {
                    Message msg = new Message();
                    msg.setMessageID(parts[0]);
                    msg.setMessageNumber(Integer.parseInt(parts[1]));
                    msg.setMessageHash(parts[2]);
                    msg.setRecipientCell(parts[3]);
                    msg.setMessageContent(parts[4]);
                    msg.setStatus(parts[5]);
                    storedMessages.add(msg);
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet or is empty
        }
        
        return storedMessages;
    }
    
    /**
     * Validates message length
     * @param message The message content
     * @return Validation result message
     */
    public String validateMessageLength(String message) {
        if (message == null || message.isEmpty()) {
            return "Message exceeds 250 characters by 250; please reduce the size.";
        }
        
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }
    
    // ==================== GETTERS AND SETTERS ====================
    
    public String getMessageID() {
        return messageID;
    }
    
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }
    
    public String getRecipientCell() {
        return recipientCell;
    }
    
    public void setRecipientCell(String recipientCell) {
        this.recipientCell = recipientCell;
    }
    
    public String getMessageContent() {
        return messageContent;
    }
    
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    
    public String getMessageHash() {
        return messageHash;
    }
    
    public void setMessageHash(String messageHash) {
        this.messageHash = messageHash;
    }
    
    public int getMessageNumber() {
        return messageNumber;
    }
    
    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public static int getTotalMessagesSent() {
        return totalMessagesSent;
    }
    
    public static ArrayList<Message> getAllMessages() {
        return allMessages;
    }
    
    public static void resetCounters() {
        totalMessagesSent = 0;
        messageCounter = 1;
        allMessages.clear();
    }
}