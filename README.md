# QuickChat - Messaging Application (Part 2)

A Java console-based messaging application that extends the registration and login system (Part 1) with message sending, validation, and storage capabilities.

![Java Version](https://img.shields.io/badge/Java-8%2B-orange)
![License](https://img.shields.io/badge/License-MIT-blue)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)
![Part](https://img.shields.io/badge/Part-2-blueviolet)

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Application Flow](#application-flow)
- [Message System](#message-system)
- [Validation Rules](#validation-rules)
- [Project Structure](#project-structure)
- [Testing](#testing)
- [Test Data](#test-data)
- [GitHub Actions CI/CD](#github-actions-cicd)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

## 🎯 Overview

QuickChat is Part 2 of a multi-phase programming project. It builds upon the registration and login system from Part 1, adding a complete messaging system that allows authenticated users to:

- Send messages to recipients with international phone numbers
- Store messages for later sending
- Disregard unwanted messages
- View message summaries with unique tracking IDs and hash codes
- Persist messages to file storage

## ✨ Features

### Part 1 Features (Inherited)
- User registration with validation
- Secure login with multiple attempts
- Username, password, and phone number validation

### Part 2 New Features

#### Message Management
- **Auto-generated Message IDs**: Random 10-digit unique identifiers for each message
- **Message Hashing**: Automatic hash generation combining ID prefix, message number, and content
- **Message Counter**: Auto-incrementing message numbers
- **Recipient Validation**: International phone number format verification
- **Content Validation**: 250-character limit enforcement with excess reporting

#### Message Actions
- **Send Message**: Immediately sends and stores the message
- **Store Message**: Saves message for later sending
- **Disregard Message**: Discards the current message

#### Data Storage
- **File-based Storage**: Messages persisted to `messages.txt`
- **Pipe-delimited Format**: Structured data storage for easy retrieval
- **Session Tracking**: All messages tracked during active session

#### User Interface
- **Console-based Menu**: Clean numeric menu system
- **Message Preview**: View message details before sending
- **Session Summary**: Total messages sent displayed after completion
- **Coming Soon Placeholder**: Feature placeholder for future development

## 🛠 Technologies Used

- **Java 8+** - Core programming language
- **JUnit 4** - Unit testing framework
- **Java I/O** - File reading and writing operations
- **Java Util** - ArrayList, Random, Scanner utilities
- **Regular Expressions** - Pattern matching for validation
- **NetBeans IDE** - Development environment
- **GitHub Actions** - CI/CD pipeline for automated testing

## 📦 Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- NetBeans IDE (recommended) or any Java IDE
- JUnit 4 library (for running tests)
- Git (for version control)

🚀 Usage
Starting the Application
Run the application

Right-click on QuickChat.java

Select Run File or press Shift + F6

Registration

text
Enter Username: kyl_1
Enter Password: Ch&sec@ke99!
Enter Cell Phone Number: +27838968976
Enter First Name: John
Enter Last Name: Doe
Login

text
Username: kyl_1
Password: Ch&sec@ke99!
Main Menu

text
=========================================
           QUICKCHAT MENU                
=========================================
User: John Doe
-----------------------------------------
1. Send Messages
2. Show Recently Sent Messages
3. Quit
=========================================
Sending Messages
Select option 1 from the main menu

Enter the number of messages to send

For each message:

Enter recipient's cell number (e.g., +27718693002)

Enter message content (max 250 characters)

Review message preview

Choose action: Send (1), Store (2), or Disregard (0)

Example Message Flow
text
Select an option: 1

How many messages would you like to send? 2

Message 1 of 2
Enter recipient's cell number: +27718693002
Enter your message: Hi Mike, can you join us for dinner tonight?

MESSAGE PREVIEW
Message ID:   5839201746
Recipient:    +27718693002
Message:      Hi Mike, can you join us for dinner tonight?
Message Hash: 58:1:HITONIGHT

1. Send Message
2. Store Message
0. Disregard Message
Select option: 1

Message successfully sent.
📐 Application Flow






















💬 Message System
Message ID Generation
Random 10-digit number generated automatically

Unique identifier for each message

Format: XXXXXXXXXX (e.g., 5839201746)

Message Hash Format
Structure: XX:Y:FIRSTWORDLASTWORD

XX: First two digits of Message ID

Y: Message number

FIRSTWORD: First word of message (uppercase)

LASTWORD: Last word of message (uppercase)

Example:

text
Message: "Hi Mike, can you join us for dinner tonight"
Message ID: 0012345678
Message Number: 0
Hash: 00:0:HITONIGHT
Message Storage Format
Messages are stored in messages.txt in pipe-delimited format:

text
5839201746|1|58:1:HITONIGHT|+27718693002|Hi Mike can you join us for dinner tonight|sent
✅ Validation Rules
Message Content Rules
Rule	Description	Error Message
Max Length	250 characters	"Message exceeds 250 characters by X; please reduce the size."
Success	Within limit	"Message ready to send."
Recipient Number Rules
Rule	Description	Error Message
International Code	Must start with +	"Cell phone number is incorrectly formatted..."
Digit Count	10-13 digits after code	"Cell phone number is incorrectly formatted..."
Success	Valid format	"Cell phone number successfully captured."
Message ID Rules
Rule	Description
Length	Maximum 10 characters
Format	Numeric digits only
📁 Project Structure
text
RegistrationLogin/
│
├── src/
│   └── registrationlogin/
│       ├── Login.java              # User authentication (Part 1)
│       ├── Message.java            # Message handling (Part 2)
│       └── QuickChat.java          # Main application (Part 2)
│
├── test/
│   └── registrationlogin/
│       ├── LoginTest.java          # Login unit tests (Part 1)
│       └── MessageTest.java        # Message unit tests (Part 2)
│
├── .github/
│   └── workflows/
│       └── tests.yml               # GitHub Actions CI/CD
│
├── messages.txt                    # Message storage file (auto-generated)
├── build.xml                       # Ant build configuration
├── manifest.mf                     # Manifest file
├── nbproject/                      # NetBeans project files
├── .gitignore                      # Git ignore rules
├── LICENSE                         # MIT License
└── README.md                       # Project documentation
🧪 Testing
Running Tests in NetBeans
Right-click on LoginTest.java or MessageTest.java

Select Test File or press Ctrl + F6

View results in the Test Results window

Running Tests from Command Line
bash
# Compile
javac -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar registrationlogin/*.java

# Run Login Tests
java -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore registrationlogin.LoginTest

# Run Message Tests
java -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore registrationlogin.MessageTest
Test Coverage
LoginTest.java (Part 1)
Test Method	Test Data	Expected
testCheckUserName_CorrectFormat	kyl_1	true
testCheckUserName_IncorrectFormat	kyle!!!!!!!	false
testCheckPasswordComplexity_MeetsRequirements	Ch&sec@ke99!	true
testCheckPasswordComplexity_DoesNotMeetRequirements	password	false
testCheckCellPhoneNumber_CorrectFormat	+27838968976	true
testCheckCellPhoneNumber_IncorrectFormat	08966553	false
MessageTest.java (Part 2)
Test Method	Test Data	Expected
testValidateMessageLength_Success	Hi Mike...	Message ready to send.
testValidateMessageLength_Failure	260 chars	Message exceeds 250 characters by 10...
testCheckRecipientCell_Success	+27718693002	Cell phone number successfully captured.
testCheckRecipientCell_Failure	08575975889	Error message
testCreateMessageHash_TestCase1	See test data	00:0:HITONIGHT
testSendMessage_Send	Option 1	Message successfully sent.
testSendMessage_Disregard	Option 0	Press 0 to delete the message.
testSendMessage_Store	Option 2	Message successfully stored.
📊 Test Data
Test Case 1
Field	Value
Num Messages	2
Recipient	+27718693002
Message	Hi Mike, can you join us for dinner tonight?
Action	Send
Expected Hash	00:0:HITONIGHT
Test Case 2
Field	Value
Recipient	08575975889
Message	Hi Keegan, did you receive the payment?
Action	Discard
Expected Result	Disregarded
🔄 GitHub Actions CI/CD
This project includes a CI/CD pipeline that automatically runs tests on push.

Workflow File: .github/workflows/tests.yml
yaml
name: Java CI with JUnit Tests

on:
  push:
    branches: [ main, KhanbanTasks ]
  pull_request:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
    
    - name: Compile and Test
      run: |
        mkdir -p build/classes build/test-classes
        find src -name "*.java" > sources.txt
        javac -d build/classes @sources.txt
        find test -name "*.java" > test-sources.txt
        javac -cp build/classes:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar -d build/test-classes @test-sources.txt
        java -cp build/classes:build/test-classes:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore registrationlogin.LoginTest registrationlogin.MessageTest
🤝 Contributing
Git Workflow
Create feature branch

bash
git checkout -b KhanbanTasks
Make changes and commit

bash
git add .
git commit -m "Add message sending functionality"
Push to GitHub

bash
git push origin KhanbanTasks
Create Pull Request on GitHub

Coding Standards
Follow Java naming conventions (camelCase for methods, PascalCase for classes)

Write descriptive comments for all methods

Include unit tests for new features

Keep methods focused and single-purpose

Maintain the main branch as production-ready code

🐛 Known Issues
"Show Recently Sent Messages" - Currently displays "Coming Soon" as the feature is still in development

Message file persistence - Messages are stored in messages.txt in the project root directory

Case sensitivity - All inputs are case-sensitive for validation

📝 Assignment Requirements Met
Learning Outcomes
✅ Create and work with loops

✅ Handle and manipulate strings

✅ Create classes, methods, and OOP constructs

✅ Use decisions and control structures

✅ Produce an application that accepts input and returns output

✅ Implement unit testing

✅ Use GitHub for version control

Part 2 Specific Requirements
✅ Users can only send messages after successful login

✅ Welcome message "Welcome to QuickChat" displayed

✅ Numeric menu with 3 options

✅ Application runs until user selects quit

✅ Users define number of messages to enter

✅ Unique message ID (random 10-digit number)

✅ Auto-incremented message number

✅ Recipient cell validation

✅ 250-character message limit

✅ Message hash generation

✅ Send, Store, Disregard options

✅ Message storage to file

✅ Message summary display

✅ Comprehensive unit tests


🙏 Acknowledgments
Java Documentation (Oracle) - Pattern class reference

JUnit Framework - Testing documentation

NetBeans IDE - Development environment

GitHub Student Developer Pack - CI/CD tools
