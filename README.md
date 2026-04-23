# 🏦 Bank Management System

> A Java Swing-based ATM/Bank Management desktop application backed by a MySQL (DBMS) database.

---

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Database Schema](#database-schema)
- [Project Structure](#project-structure)
- [Screenshots / Module Descriptions](#module-descriptions)
- [Setup & Installation](#setup--installation)
- [How to Run](#how-to-run)
- [Known Issues & Improvements](#known-issues--improvements)
- [Author](#author)

---

## 📌 Project Overview

**Bank Management System** is a desktop application that simulates an ATM/Bank interface. It allows users to sign up for a bank account, log in with their card number and PIN, and perform banking transactions such as deposits, withdrawals, PIN changes, balance enquiry, and viewing mini statements.

All data is stored and managed using a **MySQL relational database**, making it a complete **DBMS-based project**.

---

## ✨ Features

| Feature | Description |
|---|---|
| 🔐 User Sign Up | 3-page registration form collecting personal, additional, and account details |
| 🔑 Login | Secure login using Card Number + PIN |
| 💰 Deposit | Deposit any amount into the account |
| 💸 Withdrawal | Withdraw up to Rs. 10,000 per transaction |
| ⚡ Fast Cash | Quick withdrawal with preset amounts (Rs. 100, 500, 1000, 2000, 5000, 10000) |
| 📊 Mini Statement | View last 10 transactions with date, type, and amount |
| 💳 Balance Enquiry | View current account balance |
| 🔒 PIN Change | Change the 4-digit ATM PIN securely |
| 🚪 Exit | Safely exit the application |

---

## 🛠️ Tech Stack

- **Language:** Java (JDK 8+)
- **UI Framework:** Java Swing (AWT)
- **Database:** MySQL 8.x
- **JDBC Driver:** MySQL Connector/J
- **Additional Library:** JDateChooser (`com.toedter.calendar`) — for date picker in signup
- **IDE:** IntelliJ IDEA / Eclipse / NetBeans

---

## 🗄️ Database Schema

**Database Name:** `banksystem`

### Table: `login`
| Column | Type | Description |
|---|---|---|
| `form_number` | VARCHAR | Form/Application number |
| `card_number` | VARCHAR | 16-digit card number |
| `pin_number` | VARCHAR | 4-digit PIN |

### Table: `signup`
| Column | Type | Description |
|---|---|---|
| `form_number` | VARCHAR | Unique application form number |
| `name` | VARCHAR | Full name |
| `father_name` | VARCHAR | Father's name |
| `dob` | VARCHAR | Date of birth |
| `gender` | VARCHAR | Gender |
| `email` | VARCHAR | Email address |
| `marital_status` | VARCHAR | Married / Unmarried / Other |
| `address` | VARCHAR | Residential address |
| `city` | VARCHAR | City |
| `pincode` | VARCHAR | Postal PIN code |
| `state` | VARCHAR | State |

### Table: `signuptwo`
| Column | Type | Description |
|---|---|---|
| `form_number` | VARCHAR | Foreign key → signup |
| `religion` | VARCHAR | Religion |
| `category` | VARCHAR | General / OBC / SC / ST / Other |
| `income` | VARCHAR | Annual income range |
| `education` | VARCHAR | Educational qualification |
| `occupation` | VARCHAR | Occupation type |
| `pan_number` | VARCHAR | PAN card number |
| `aadhar_number` | VARCHAR | Aadhar card number |
| `senior_citizen` | VARCHAR | Yes / No |
| `existing_account` | VARCHAR | Yes / No |

### Table: `signupthree`
| Column | Type | Description |
|---|---|---|
| `form_number` | VARCHAR | Foreign key → signup |
| `account_type` | VARCHAR | Saving / Fixed Deposit / Current / Recurring |
| `card_number` | VARCHAR | Auto-generated 16-digit card number |
| `pin_number` | VARCHAR | Auto-generated 4-digit PIN |
| `facilities` | VARCHAR | Selected services (ATM, Internet Banking, etc.) |

### Table: `bank`
| Column | Type | Description |
|---|---|---|
| `pin_number` | VARCHAR | Foreign key → login |
| `date` | VARCHAR | Transaction date & time |
| `type` | VARCHAR | `Deposit` or `Withdrawl` |
| `amount` | VARCHAR | Transaction amount |

### SQL to Create Tables

```sql
CREATE DATABASE banksystem;
USE banksystem;

CREATE TABLE login (
    form_number VARCHAR(20),
    card_number VARCHAR(20),
    pin_number VARCHAR(10)
);

CREATE TABLE signup (
    form_number VARCHAR(20),
    name VARCHAR(100),
    father_name VARCHAR(100),
    dob VARCHAR(20),
    gender VARCHAR(10),
    email VARCHAR(100),
    marital_status VARCHAR(20),
    address VARCHAR(200),
    city VARCHAR(50),
    pincode VARCHAR(10),
    state VARCHAR(50)
);

CREATE TABLE signuptwo (
    form_number VARCHAR(20),
    religion VARCHAR(20),
    category VARCHAR(20),
    income VARCHAR(30),
    education VARCHAR(30),
    occupation VARCHAR(30),
    pan_number VARCHAR(20),
    aadhar_number VARCHAR(20),
    senior_citizen VARCHAR(5),
    existing_account VARCHAR(5)
);

CREATE TABLE signupthree (
    form_number VARCHAR(20),
    account_type VARCHAR(40),
    card_number VARCHAR(20),
    pin_number VARCHAR(10),
    facilities VARCHAR(200)
);

CREATE TABLE bank (
    pin_number VARCHAR(10),
    date VARCHAR(50),
    type VARCHAR(20),
    amount VARCHAR(20)
);
```

---

## 📁 Project Structure

```
bank.management.system/
│
├── Login.java           → Login screen (Card Number + PIN)
├── Signup.java          → Registration Page 1 (Personal Details)
├── Signup2.java         → Registration Page 2 (Additional Details)
├── Signup3.java         → Registration Page 3 (Account & Card Setup)
├── main_Class.java      → Main ATM Menu (Transaction Selection)
├── Deposit.java         → Deposit money
├── Withdrawl.java       → Custom amount withdrawal
├── FastCash.java        → Quick preset-amount withdrawal
├── BalanceEnquriy.java  → Check account balance
├── mini.java            → Mini statement (last 10 transactions)
├── Pin.java             → Change PIN
└── Connn.java           → Database connection utility (MySQL via JDBC)

icon/
├── bank.png             → Bank logo icon
├── atm2.png             → ATM background image
└── backbg.png           → Login page background
```

---

## 📐 Module Descriptions

### `Login.java`
The entry screen of the application. Users log in with their **Card Number** and **PIN**. On successful authentication, they are directed to the main ATM menu. New users can navigate to the Sign Up flow.

### `Signup.java` / `Signup2.java` / `Signup3.java`
A 3-page account registration flow:
- **Page 1:** Personal details (name, DOB, gender, email, address)
- **Page 2:** Additional info (religion, income, PAN, Aadhar)
- **Page 3:** Account type selection and auto-generation of Card Number & PIN

### `main_Class.java`
The central ATM dashboard with buttons for all 7 operations. Buttons include a hover effect for better UX.

### `Deposit.java`
Allows the user to deposit any custom amount. Validates that the field is not empty before inserting the transaction record.

### `Withdrawl.java`
Custom amount withdrawal with a maximum limit of **Rs. 10,000** per transaction. Validates sufficient balance before processing.

### `FastCash.java`
Provides 6 quick-select withdrawal amounts: Rs. 100, 500, 1000, 2000, 5000, 10000. Checks balance before debiting.

### `BalanceEnquriy.java`
Calculates the running balance by summing all Deposits and subtracting all Withdrawals for the logged-in PIN.

### `mini.java`
Displays the **last 10 transactions** in a scrollable panel. Deposits are shown in green and withdrawals in red. Also shows the masked card number and current total balance.

### `Pin.java`
Allows the user to change their 4-digit PIN. Updates across all three relevant tables (`bank`, `login`, `signupthree`).

### `Connn.java`
Database utility class that establishes a JDBC connection to the `banksystem` MySQL database.

---

## ⚙️ Setup & Installation

### Prerequisites

- Java JDK 8 or higher
- MySQL Server 8.x
- MySQL Connector/J JAR (add to project classpath)
- JCalendar library (`jcalendar-1.4.jar` or newer — for date picker)

### Step 1: Clone the Repository

```bash
git clone https://github.com/yourusername/bank-management-system.git
cd bank-management-system
```

### Step 2: Set Up the Database

1. Open MySQL Workbench or the MySQL CLI.
2. Run the SQL script from the [Database Schema](#database-schema) section above.
3. Verify the database `banksystem` and all 5 tables are created.

### Step 3: Update Database Credentials

Open `Connn.java` and update the credentials to match your MySQL setup:

```java
connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/banksystem",
    "root",        // ← your MySQL username
    "yourpassword" // ← your MySQL password
);
```

### Step 4: Add Libraries to Classpath

Add the following JARs to your project's build path:
- `mysql-connector-java-x.x.x.jar`
- `jcalendar-1.4.jar`

---

## ▶️ How to Run

1. Open the project in your IDE (IntelliJ / Eclipse / NetBeans).
2. Ensure all JAR dependencies are added to the classpath.
3. Run `Login.java` as the main entry point.
4. To create a new account, click **SIGN UP** and complete the 3-page form.
5. After sign-up, you will receive your auto-generated **Card Number** and **PIN** — note them down.
6. Log in using those credentials to access the ATM menu.

---

## 🐛 Known Issues & Suggested Improvements

| Issue | Suggestion |
|---|---|
| SQL Injection vulnerability in most queries | Use `PreparedStatement` throughout (currently only `mini.java` uses it) |
| Amount stored as VARCHAR in `bank` table | Change to `INT` or `DECIMAL` for proper numeric operations |
| PIN stored in plain text | Hash PINs using `BCrypt` before storing |
| No session timeout | Add inactivity timer to auto-logout |
| PIN change uses wrong column name | Queries reference `pin` instead of `pin_number` — fix for correct update |
| `Signup2.java` has a bug | Both `scitizen` and `eAccount` read from the same radio button group (`r1`/`r2`) — should use separate groups |
| No input validation on amounts | Add numeric checks and prevent negative values |
| Hard-coded window sizes | Make UI responsive or use layout managers |

---

## 👨‍💻 Author

**PVS Bank Management System**
Built as a DBMS mini/major project using Java Swing and MySQL.

---

## 📄 License

This project is intended for educational purposes only. Feel free to fork and improve it.
