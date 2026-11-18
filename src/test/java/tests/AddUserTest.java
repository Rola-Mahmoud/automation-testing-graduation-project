package tests;

import base.BaseTest;
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
        performValidLogin();
        adminPage = dashboardPage.goToAdminPage();
        addUserPage = adminPage.goToAddUserPage();
    }
    @Test
    public void testAddUserFormFields(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(addUserPage.isUserRoleDisplayed(),"User Role field is not displayed");
        softAssert.assertTrue(addUserPage.isEmployeeNameDisplayed(),"Employee Name field is not displayed");
        softAssert.assertTrue(addUserPage.isUserNameDisplayed(),"Username field is not displayed");
        softAssert.assertTrue(addUserPage.isPasswordDisplayed(),"Password field is not displayed");
        softAssert.assertAll();
    }
    @Test
    public void testFooter(){
        Assert.assertTrue(addUserPage.getFooterText().contains("OrangeHRM, Inc"),
                "OrangeHRM, Inc is not displayed in the footer");
        addUserPage.clickFooterLink();
        Assert.assertTrue(addUserPage.getNewTabUrl().contains("orangehrm.com"),"Incorrect URL opened in the new tab");
    }


}
