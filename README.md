# Library Management System

A simple Java-based Library Management System created using Maven and JUnit for Test-Driven Development (TDD).

---

## Features
- **Add Books**: Add new books with unique identifiers (ISBN), titles, authors, and publication years.
- **Borrow Books**: Borrow a book if it's available in the library.
- **Return Books**: Return a previously borrowed book.
- **View Available Books**: View a list of books currently available in the library.

---

## Prerequisites
- **Java**: Version 8 or higher.
- **Maven**: Version 3.8 or later.
- **JUnit**: For running unit tests.
- **IDE**: IntelliJ IDEA, VS Code, or any other preferred IDE.

---

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/theprathyaksh/lms-assessment.git
   ```
2. **Navigate to the Project Directory**:
   ```bash
   cd lms-assessment
   ```
3. **Build the Project**:
   ```bash
   mvn clean install
   ```
4. **Run Tests**:
   ```bash
   mvn test
   ```

---

## How to Run the Project
- **In IntelliJ IDEA**:
   1. Open the project in IntelliJ IDEA.
   2. Run the `main` method of your main class or execute Maven commands.
- **In VS Code**:
   1. Install the **Java Extension Pack**.
   2. Open the project folder and use the integrated terminal to build and test.

---

## Test Reports

After running the tests, the following HTML reports are generated. Use a browser to open the provided links to view test results.
The HTML Reports are attached in the folder. 

### **Add Books**
[View Test Report](http://localhost:63342/LibraryManagementSystem/Test%20Results%20-%20testAddBook.html)

### **Borrow Books**
[View Test Report](http://localhost:63342/LibraryManagementSystem/Test%20Results%20-%20testBorrowBook.html)

### **Return Books**
[View Test Report](http://localhost:63342/LibraryManagementSystem/Test%20Results%20-%20testReturnBook.html)

### **View Available Books**
[View Test Report](http://localhost:63342/LibraryManagementSystem/Test%20Results%20-%20testViewAvailableBooks.html)

---

## Contributing
If you'd like to contribute to this project:
1. Fork the repository.
2. Create a new branch for your feature/bug fix.
3. Submit a pull request for review.



