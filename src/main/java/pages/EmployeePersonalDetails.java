package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeePersonalDetails {
    private WebDriver driver;
    private WebDriverWait wait;
    private By employeeListOption = By.xpath("//a[contains(text(),'Employee List')]");

    public EmployeePersonalDetails(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    public String getURL(){
        wait.until(ExpectedConditions.urlContains("/pim/viewPersonalDetails"));
        return driver.getCurrentUrl();
    }
    public EmployeeListPage goToEmployeeListPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeListOption)).click();
        wait.until(ExpectedConditions.urlContains("/pim/viewEmployeeList"));
        return new EmployeeListPage(driver,wait);
    }
}
