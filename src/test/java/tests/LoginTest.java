package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utilities.TestDataReader;

public class LoginTest extends BaseTest {
    @Test(dataProvider = "testData", dataProviderClass = TestDataReader.class)
    public void testValidLogin(String username, String password) {
        dashboardPage = loginPage.login(username, password);
        Assert.assertTrue(dashboardPage.getURL().contains("/dashboard/index"),
                "Expected URL to contain /dashboard/index");
        Assert.assertTrue(dashboardPage.getHeader().contains("Dashboard"),"Expected Dashboard page to be displayed");
    }
    @Test(dataProvider = "testData", dataProviderClass = TestDataReader.class)
    public void testInvalidLogin(String username, String password) {
        loginPage.login(username, password);
        Assert.assertEquals(loginPage.getErrorMessage(),"Invalid credentials","Expected invalid credentials");
    }
    @Test(dataProvider = "testData", dataProviderClass = TestDataReader.class)
    public void testEmptyFields(String username, String password) {
        loginPage.login(username, password);
        Assert.assertEquals(loginPage.getRequiredFieldsCount(),2,"Expected 2 required fields validation messages");
    }
}
