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

public class EmployeeListTest extends BaseTest {
    private EmployeeListPage employeeListPage;

    @BeforeMethod
    public void beforeMethod() throws IOException {
        Allure.step("Login with valid credentials");
        performValidLogin();

        Allure.step("Navigate to Employee List page");
        employeeListPage = dashboardPage.goToEmployeeListPage();

        Allure.step("Verify Employee List page URL");
        Assert.assertTrue(employeeListPage.getURL().contains("/pim/viewEmployeeList"));
    }

    @Test(dataProvider = "testData", dataProviderClass = TestDataReader.class)
    @Description("Search for an employee ")
    public void testEmployeeSearch(String employeeName) {
        Allure.step("Search for an existing employee");
        employeeListPage.searchForEmployee(employeeName);

        Allure.step("Verify employee is displayed in results table");
        Assert.assertTrue(employeeListPage.isEmployeeNameDisplayed(employeeName),
                "Employee Name is not displayed in the results table");
    }

    @Test(dataProvider = "testData", dataProviderClass = TestDataReader.class)
    @Description("Search for a non-existing employee")
    public void testNotEmployeeSearch(String employeeName) {
        Allure.step("Search for a non-existing employee");
        employeeListPage.searchForEmployee(employeeName);

        Allure.step("Verify No Records Found message");
        Assert.assertEquals(employeeListPage.getNoRecordsMessage(),"No Records Found",
                "Expected No Records Found Message");
    }

    @Test
    @Description("Open Add Employee page")
    public void testNavigateToAddEmployeePage() {
        Allure.step("Navigate to Add Employee page");
        AddEmployeePage addEmployeePage = employeeListPage.goToAddEmployeePage();

        Allure.step("Verify Add Employee page URL");
        Assert.assertTrue(addEmployeePage.getURL().contains("/pim/addEmployee"),
                "Add Employee page is not displayed");

        Allure.step("Verify First Name field is displayed");
        Assert.assertTrue(addEmployeePage.firstNameIsDisplayed(),
                "First Name is not displayed");

        Allure.step("Verify Last Name field is displayed");
        Assert.assertTrue(addEmployeePage.lastNameIsDisplayed(),
                "Last Name is not displayed");
    }


}
