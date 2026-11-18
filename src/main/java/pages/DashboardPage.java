package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By header = By.tagName("h6");
    private By pimIcon = By.xpath("//a[contains(@href,'Pim')]");
    private By adminIcon = By.xpath("//a[contains(@href,'Admin')]");
    private By leaveIcon = By.xpath("//a[contains(@href,'Leave')]");
    private By timeIcon = By.xpath("//a[contains(@href,'Time')]");
    private By recruitmentIcon = By.xpath("//a[contains(@href,'Recruitment')]");
    private By myInfoIcon = By.xpath("//a[contains(@href,'MyDetails')]");
    private By performanceIcon = By.xpath("//a[contains(@href,'Performance')]");
    private By dashboardIcon = By.xpath("//a[contains(@href,'dashboard')]");
    private By directoryIcon = By.xpath("//a[contains(@href,'Directory')]");
    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    public String getURL() {
        wait.until(ExpectedConditions.urlContains("/dashboard/index"));
        return driver.getCurrentUrl();
    }
    public String getHeader() {
        var headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(header));
        return headerElement.getText();
    }
    public EmployeeListPage goToEmployeeListPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pimIcon)).click();
        return new EmployeeListPage(driver,wait);
    }
    public AdminPage goToAdminPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(adminIcon)).click();
        return new AdminPage(driver,wait);
    }
    public boolean isAdminIconDisplayed() {
        var adminField = wait.until(ExpectedConditions.visibilityOfElementLocated(adminIcon));
        return adminField.isDisplayed();
    }
    public boolean isPimIconDisplayed() {
        var pimField = wait.until(ExpectedConditions.visibilityOfElementLocated(pimIcon));
        return pimField.isDisplayed();
    }
    public boolean isLeaveIconDisplayed() {
        var leaveField = wait.until(ExpectedConditions.visibilityOfElementLocated(leaveIcon));
        return leaveField.isDisplayed();
    }
    public boolean isTimeIconDisplayed() {
        var timeField = wait.until(ExpectedConditions.visibilityOfElementLocated(timeIcon));
        return timeField.isDisplayed();
    }
    public boolean isRecruitmentIconDisplayed() {
        var recruitmentField = wait.until(ExpectedConditions.visibilityOfElementLocated(recruitmentIcon));
        return recruitmentField.isDisplayed();
    }
    public boolean isMyInfoIconDisplayed() {
        var myInfoField = wait.until(ExpectedConditions.visibilityOfElementLocated(myInfoIcon));
        return myInfoField.isDisplayed();
    }
    public boolean isPerformanceIconDisplayed() {
        var performanceField = wait.until(ExpectedConditions.visibilityOfElementLocated(performanceIcon));
        return performanceField.isDisplayed();
    }
    public boolean isDashboardIconDisplayed() {
        var dashboardField = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardIcon));
        return dashboardField.isDisplayed();
    }
    public boolean isDirectoryIconDisplayed() {
        var directoryField = wait.until(ExpectedConditions.visibilityOfElementLocated(directoryIcon));
        return directoryField.isDisplayed();
    }


}
