package tests;

import base.BaseTest;
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
        performValidLogin();
        employeeListPage = dashboardPage.goToEmployeeListPage();
        Assert.assertTrue(employeeListPage.getURL().contains("/pim/viewEmployeeList"));
    }
    @Test(dataProvider = "testData", dataProviderClass = TestDataReader.class)
    public void testEmployeeSearch(String employeeName) {
        employeeListPage.searchForEmployee(employeeName);
        Assert.assertTrue(employeeListPage.isEmployeeNameDisplayed(employeeName),
                "Employee Name is not displayed in the results table");
    }
    @Test(dataProvider = "testData", dataProviderClass = TestDataReader.class)
    public void testNotEmployeeSearch(String employeeName) {
        employeeListPage.searchForEmployee(employeeName);
        Assert.assertEquals(employeeListPage.getNoRecordsMessage(),"No Records Found",
                "Expected No Records Found Message");
    }
    @Test
    public void testNavigateToAddEmployeePage() {
        AddEmployeePage addEmployeePage = employeeListPage.goToAddEmployeePage();
        Assert.assertTrue(addEmployeePage.getURL().contains("/pim/addEmployee"),"Add Employee page is not displayed");
        Assert.assertTrue(addEmployeePage.firstNameIsDisplayed(),"First Name is not displayed");
        Assert.assertTrue(addEmployeePage.lastNameIsDisplayed(),"Last Name is not displayed");
    }


}
