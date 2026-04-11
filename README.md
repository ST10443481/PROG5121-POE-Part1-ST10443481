# PROG5121-POE-Part1-ST10443481

# Registration and Login System

A Java-based registration and authentication system with a graphical user interface built using JOptionPane. This application provides secure user registration with comprehensive validation and a user-friendly login system.

![Java Version](https://img.shields.io/badge/Java-8%2B-orange)
![License](https://img.shields.io/badge/License-MIT-blue)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)

## ✨ Features

### Registration
- **Username Validation**: Must contain an underscore (_) and be maximum 5 characters long
- **Password Complexity**: Enforces strong password requirements
- **Phone Number Validation**: Validates South African international format (+27XXXXXXXXX)
- **User-Friendly Interface**: GUI-based input using JOptionPane dialogs
- **Input Confirmation**: Review registration details before submission

### Login
- **Secure Authentication**: Verifies credentials against registered user data
- **Multiple Attempts**: Allows up to 3 login attempts before access denial
- **Personalized Welcome**: Greets users by their full name upon successful login
- **Error Handling**: Clear error messages for failed login attempts

### Security Features
- Password masking during input
- Password complexity enforcement
- Input validation at multiple levels
- Maximum login attempt limitation

## 🛠 Technologies Used

- **Java** - Core programming language
- **Swing/JOptionPane** - Graphical user interface components
- **JUnit 4** - Unit testing framework
- **Regular Expressions** - Pattern matching for validation
- **NetBeans IDE** - Development environment

## 📦 Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- NetBeans IDE (recommended) or any Java IDE
- JUnit 4 library (for running tests)
