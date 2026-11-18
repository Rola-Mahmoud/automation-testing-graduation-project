package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class DashboardTest extends BaseTest {
    @BeforeMethod
    public void setupDashboardPage() throws IOException {
        performValidLogin();

    }
    @Test
    @Description("Verify sidebar menu UI")
    public void testSideBar() {
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Verify Admin item is displayed in the sidebar");
        softAssert.assertTrue(dashboardPage.isAdminIconDisplayed(),
                "Admin item is not displayed in the sidebar");

        Allure.step("Verify PIM item is displayed in the sidebar");
        softAssert.assertTrue(dashboardPage.isPimIconDisplayed(),
                "PIM item is not displayed in the sidebar");

        Allure.step("Verify Leave item is displayed in the sidebar");
        softAssert.assertTrue(dashboardPage.isLeaveIconDisplayed(),
                "Leave item is not displayed in the sidebar");

        Allure.step("Verify Time item is displayed in the sidebar");
        softAssert.assertTrue(dashboardPage.isTimeIconDisplayed(),
                "Time item is not displayed in the sidebar");

        Allure.step("Verify Recruitment item is displayed in the sidebar");
        softAssert.assertTrue(dashboardPage.isRecruitmentIconDisplayed(),
                "Recruitment item is not displayed in the sidebar");

        Allure.step("Verify My Info item is displayed in the sidebar");
        softAssert.assertTrue(dashboardPage.isMyInfoIconDisplayed(),
                "My Info item is not displayed in the sidebar");

        Allure.step("Verify Performance item is displayed in the sidebar");
        softAssert.assertTrue(dashboardPage.isPerformanceIconDisplayed(),
                "Performance item is not displayed in the sidebar");

        Allure.step("Verify Dashboard item is displayed in the sidebar");
        softAssert.assertTrue(dashboardPage.isDashboardIconDisplayed(),
                "Dashboard item is not displayed in the sidebar");

        Allure.step("Verify Directory item is displayed in the sidebar");
        softAssert.assertTrue(dashboardPage.isDirectoryIconDisplayed(),
                "Directory item is not displayed in the sidebar");

        softAssert.assertAll();
    }
}
