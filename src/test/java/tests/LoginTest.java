package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utilities.TestDataReader;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "testData", dataProviderClass = TestDataReader.class)
    @Description("Login with valid credentials")
    public void testValidLogin(String username, String password) {
        Allure.step("Login with valid credentials");
        dashboardPage = loginPage.login(username, password);

        Allure.step("Verify dashboard URL");
        Assert.assertTrue(dashboardPage.getURL().contains("/dashboard/index"),
                "Expected URL to contain /dashboard/index");

        Allure.step("Verify dashboard header");
        Assert.assertTrue(dashboardPage.getHeader().contains("Dashboard"),
                "Expected Dashboard page to be displayed");
    }

    @Test(dataProvider = "testData", dataProviderClass = TestDataReader.class)
    @Description("Login with invalid credentials")
    public void testInvalidLogin(String username, String password) {
        Allure.step("Login with invalid credentials");
        loginPage.login(username, password);

        Allure.step("Verify invalid credentials error message");
        Assert.assertEquals(loginPage.getErrorMessage(),"Invalid credentials",
                "Expected invalid credentials validation");
    }

    @Test(dataProvider = "testData", dataProviderClass = TestDataReader.class)
    @Description("Login with empty fields")
    public void testEmptyFields(String username, String password) {
        Allure.step("Submit login form with empty fields");
        loginPage.login(username, password);

        Allure.step("Verify required fields validation messages");
        Assert.assertEquals(loginPage.getRequiredFieldsCount(),2,
                "Expected 2 required fields validation messages");
    }
}
