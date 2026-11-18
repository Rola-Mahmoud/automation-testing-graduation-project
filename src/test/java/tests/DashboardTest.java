package tests;

import base.BaseTest;
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
    public void testSideBar() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(dashboardPage.isAdminIconDisplayed(),"Admin item is not displayed");
        softAssert.assertTrue(dashboardPage.isPimIconDisplayed(),"PIM item is not displayed");
        softAssert.assertTrue(dashboardPage.isLeaveIconDisplayed(),"Leave item is not displayed");
        softAssert.assertTrue(dashboardPage.isTimeIconDisplayed(),"Time item is not displayed");
        softAssert.assertTrue(dashboardPage.isRecruitmentIconDisplayed(),"Recruitment item is not displayed");
        softAssert.assertTrue(dashboardPage.isMyInfoIconDisplayed(),"My Info item is not displayed");
        softAssert.assertTrue(dashboardPage.isPerformanceIconDisplayed(),"Performance item is not displayed");
        softAssert.assertTrue(dashboardPage.isDashboardIconDisplayed(),"Dashboard item is not displayed");
        softAssert.assertTrue(dashboardPage.isDirectoryIconDisplayed(),"Directory item is not displayed");
        softAssert.assertAll();
    }
}
