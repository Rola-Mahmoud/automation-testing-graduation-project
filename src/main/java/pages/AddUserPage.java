package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUserPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By userRole = By.xpath("//label[text()='User Role']");
    private By employeeName = By.xpath("//label[text()='Employee Name']");
    private By userName = By.xpath("//label[text()='Username']");
    private By password = By.xpath("//label[text()='Password']");
    private By footer = By.className("oxd-layout-footer");
    private By footerLink = By.xpath("//a[contains(text(),'OrangeHRM, Inc')]");

    public AddUserPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    public boolean isUserRoleDisplayed(){
        var userRoleField = wait.until(ExpectedConditions.visibilityOfElementLocated(userRole));
        return userRoleField.isDisplayed();
    }
    public boolean isEmployeeNameDisplayed(){
        var employeeNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName));
        return employeeNameField.isDisplayed();
    }
    public boolean isUserNameDisplayed(){
        var userNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
        return userNameField.isDisplayed();
    }
    public boolean isPasswordDisplayed(){
        var passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        return passwordField.isDisplayed();
    }
    public String getFooterText(){
        return scrollToFooter().getText();
    }
    public WebElement scrollToFooter(){
        var footerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(footer));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footerElement);
        return footerElement;
    }
    public void clickFooterLink(){
        scrollToFooter();
        var footerLinkField = wait.until(ExpectedConditions.visibilityOfElementLocated(footerLink));
        footerLinkField.click();
    }
    public String getNewTabUrl(){
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        return driver.getCurrentUrl();
    }

}
