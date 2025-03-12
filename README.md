# API Testing with RestAssured

This project contains automated API tests using **RestAssured** and **TestNG** to validate login and user-related operations.

## 🚀 Features
-  User authentication tests (Login API)
-  Fetching single and multiple users
-  User creation validation
-  Automated assertions using **TestNG**
-  Uses **SoftAssert** for multiple validations in a single test

## 📂 Project Structure
API_Testing_Project \
┣ 📂 src \
┃ \
┣ App.java \
┃ \
┣ LoginTests.java \
┃ \
┣ UserTests.java \
┣  README.md \
┣ .gitignore \
┗ pom.xml / build.gradle

## 🛠️ Setup & Installation

### Prerequisites
- Install **Java 11+**
- Install **Maven** (if using Maven)
- Install **IntelliJ IDEA** or any Java-supported IDE

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/GehadAshry/API-RestAssured.git
   cd API-RestAssured
2. If using Maven, install dependencies:
     ```sh
     mvn clean install
3. Run the tests:
     ```sh
     mvn test
     
### Test Cases

1. **Login API Tests**
- Valid login (returns a token)
- Invalid username (returns "Missing email or username")
- Invalid password (returns "Missing password")
  
2. **User API Tests**
- Get a single user by ID
- Get a list of users (pagination validation)
- Create a new user and verify response

### Built With:

- **RestAssured** - API Testing Framework
- **TestNG** - Test Framework
- **Maven** - Dependency Management




