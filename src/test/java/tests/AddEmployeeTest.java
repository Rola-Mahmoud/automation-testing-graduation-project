package tests;

import base.BaseTest;
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
        performValidLogin();
        employeeListPage = dashboardPage.goToEmployeeListPage();
        addEmployeePage = employeeListPage.goToAddEmployeePage();
    }
    @Test(dataProvider = "testData",dataProviderClass = TestDataReader.class)
    public void testEmptyFirstName(String firstName, String lastName) {
        addEmployeePage.enterEmployeeName(firstName, lastName);
        Assert.assertEquals(addEmployeePage.getFirstNameReqMsg(),"Required","Expected first name to be required");
    }
    @Test(dataProvider = "testData",dataProviderClass = TestDataReader.class)
    public void testSuccessfulAddEmployee(String firstName, String lastName) {
        var empPersonalDetails = addEmployeePage.enterEmployeeName(firstName, lastName);
        Assert.assertTrue(addEmployeePage.isSuccessMessageDisplayed(),"Employee was not added successfully");
        Assert.assertTrue(empPersonalDetails.getURL().contains("/pim/viewPersonalDetails"));
        var employeeListPage = empPersonalDetails.goToEmployeeListPage();
        Assert.assertTrue(employeeListPage.getURL().contains("/pim/viewEmployeeList"));
        String employeeName = firstName + " " + lastName;
        employeeListPage.searchForEmployee(employeeName);
        Assert.assertTrue(employeeListPage.isEmployeeNameDisplayed(employeeName),
                "Employee Name is not displayed in the results table");
    }

}
