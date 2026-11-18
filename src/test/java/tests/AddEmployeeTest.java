package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.EmployeeListPage;
import utilities.TestDataReader;

import java.io.IOException;

public class AddEmployeeTest extends BaseTest {
    private EmployeeListPage employeeListPage;
    private AddEmployeePage addEmployeePage;

    @BeforeMethod
    public void setupAddEmployeePage() throws IOException {
        Allure.step("Login with valid credentials");
        performValidLogin();

        Allure.step("Navigate to Employee List page");
        employeeListPage = dashboardPage.goToEmployeeListPage();

        Allure.step("Navigate to Add Employee page");
        addEmployeePage = employeeListPage.goToAddEmployeePage();
    }

    @Test(dataProvider = "testData",dataProviderClass = TestDataReader.class)
    @Description("Add a new employee with an empty required field")
    public void testEmptyFirstName(String firstName, String lastName) {
        Allure.step("Enter the Last Name only and leave First Name empty");
        addEmployeePage.enterEmployeeName(firstName, lastName);

        Allure.step("Verify first name required validation message");
        Assert.assertEquals(addEmployeePage.getFirstNameReqMsg(),"Required",
                "Expected first name to be required");
    }

    @Test(dataProvider = "testData",dataProviderClass = TestDataReader.class)
    @Description("End-to-end — Add a new employee successfully")
    public void testSuccessfulAddEmployee(String firstName, String lastName) {
        Allure.step("Enter a valid First Name and Last Name");
        var empPersonalDetails = addEmployeePage.enterEmployeeName(firstName, lastName);

        Allure.step("Verify employee added successfully");
        Assert.assertTrue(addEmployeePage.isSuccessMessageDisplayed(),
                "Employee was not added successfully");

        Allure.step("Verify Personal Details page URL");
        Assert.assertTrue(empPersonalDetails.getURL().contains("/pim/viewPersonalDetails"));

        Allure.step("Navigate back to Employee List page");
        var employeeListPage = empPersonalDetails.goToEmployeeListPage();

        Allure.step("Verify Employee List page URL");
        Assert.assertTrue(employeeListPage.getURL().contains("/pim/viewEmployeeList"));

        String employeeName = firstName + " " + lastName;

        Allure.step("Search for the newly added employee");
        employeeListPage.searchForEmployee(employeeName);

        Allure.step("Verify employee is displayed in search results");
        Assert.assertTrue(employeeListPage.isEmployeeNameDisplayed(employeeName),
                "Employee Name is not displayed in the results table");
    }

}
