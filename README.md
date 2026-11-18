# OrangeHRM Automation Testing Project

UI test automation for the [OrangeHRM demo application](https://opensource-demo.orangehrmlive.com/) using Java, Selenium WebDriver, TestNG, and Maven.

## Tech Stack

- Java 17
- Selenium WebDriver 4.51.0
- TestNG 7.12.0
- Maven
- Jackson (JSON test data)
- Allure (test reporting)

## Test Coverage

The automated tests cover five main areas:

- **Login:** valid and invalid credentials, and empty-field validation.
- **Employee List:** employee search, no-result search, and navigation to Add Employee.
- **Add Employee:** required-field validation and successful employee creation.
- **Add User:** form fields and footer verification.
- **Dashboard:** sidebar menu visibility.

## Framework Design

- Page Object Model (POM) for organizing page locators and actions.
- Reusable `BaseTest` for browser setup, waits, login, and teardown.
- JSON-based test data managed through Jackson and TestNG DataProvider.
- Chrome, Firefox, and Edge browser support through `config.properties`.
- Allure annotations and reporting.

## Project Structure

```text
src/
├── main/java/pages/       # Page Object classes
└── test/
    ├── java/base/         # BaseTest and WebDriver setup
    ├── java/tests/        # Test classes
    ├── java/utilities/    # Configuration and test-data readers
    └── resources/         # config.properties and testdata.json
pom.xml
```

## Run the Tests

**Prerequisites:** Java JDK 17+, Maven, and a supported browser.

1. Clone the repository and open the project directory.
2. Set the browser and application URL in `src/test/resources/config.properties`.
3. Run:

```bash
mvn clean test
```

## Allure Report

After execution, generate and open the report (Allure Commandline must be installed):

```bash
allure serve target/allure-results
```

An existing generated report is also available in the `allure-report/` directory.

> **Note:** Tests run against a public demo website, so its availability and data may change. The Add Employee test creates a record in the demo application.
