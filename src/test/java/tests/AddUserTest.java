package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddUserPage;
import pages.AdminPage;

import java.io.IOException;

public class AddUserTest extends BaseTest {
    private AdminPage adminPage;
    private AddUserPage addUserPage;

    @BeforeMethod
    public void setupAddUserPage() throws IOException {
        Allure.step("Login with valid credentials");
        performValidLogin();

        Allure.step("Navigate to Admin page");
        adminPage = dashboardPage.goToAdminPage();

        Allure.step("Navigate to Add User page");
        addUserPage = adminPage.goToAddUserPage();
    }

    @Test
    @Description("Verify Admin > Add User page")
    public void testAddUserFormFields(){
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Verify User Role field is displayed");
        softAssert.assertTrue(addUserPage.isUserRoleDisplayed(),
                "User Role field is not displayed");

        Allure.step("Verify Employee Name field is displayed");
        softAssert.assertTrue(addUserPage.isEmployeeNameDisplayed(),
                "Employee Name field is not displayed");

        Allure.step("Verify Username field is displayed");
        softAssert.assertTrue(addUserPage.isUserNameDisplayed(),
                "Username field is not displayed");

        Allure.step("Verify Password field is displayed");
        softAssert.assertTrue(addUserPage.isPasswordDisplayed(),
                "Password field is not displayed");
        softAssert.assertAll();
    }

    @Test
    @Description("Verify the OrangeHRM footer/branding link")
    public void testFooter(){
        Allure.step("Verify OrangeHRM copyright text is displayed");
        Assert.assertTrue(addUserPage.getFooterText().contains("OrangeHRM, Inc"),
                "OrangeHRM, Inc is not displayed in the footer");

        Allure.step("Click the footer link");
        addUserPage.clickFooterLink();

        Allure.step("Verify footer link opens the correct website in a new tab");
        Assert.assertTrue(addUserPage.getNewTabUrl().contains("orangehrm.com"),
                "Incorrect URL opened in the new tab");
    }
}
