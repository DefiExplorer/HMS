# HMS

## Hospital Management System (HMS)

### Project Structure

- **Main Java Class**: Driver Class `HMS`
- **Patient Class**
- **Doctor Class**

---

### Database Schema

- **Tables**:
  - `Patient` (ID, NAME, AGE, GENDER)
  - `Doctors` (ID, NAME, SPECIALITY)
  - `Appointments` (PATIENT_ID, DOC_ID, DATE_OF_APPOINTMENT)

- **Exception Handling**: Implemented where necessary
- **Output Formatting**: Displays data in tabular format

---

### Features

Various methods work together to provide the following options:
1. Add Patient Details
2. View All Patients
3. View All Doctors
4. Book Appointment

---

### Technologies and Concepts Used

This project highlights key programming skills and concepts:
- **Java**:
  - Core Java programming for logic and functionality
  - Methods to handle Create, Update, and Read operations
  - Control structures (loops, conditionals) for user interaction
- **Java JDBC**:
  - JDBC API for connecting Java to MySQL database
  - SQL queries to create, update, and read patient, doctor, and appointment data
  - Handling database connections, statements, and result sets
- **Object-Oriented Programming (OOPs)**:
  - **Encapsulation**: Classes like `Patient` and `Doctor` to bundle data and methods
  - **Abstraction**: Simplified logic for database operations
  - **Modularity**: Separate classes for distinct entities to improve code organization
- **Additional Skills**:
  - Exception handling to manage database and input errors
  - Tabular output formatting for user-friendly display

---

### How to Run the Project

- **IDE Used**: IntelliJ
- **Database**: MySQL Server
- **Requirements**:
  - MySQL Connector
  - Provide JDBC URL, username, and password
