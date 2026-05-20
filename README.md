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
