package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmployeePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstName = By.name("firstName");
    private By lastName = By.name("lastName");
    private By saveButton = By.xpath(
            "//button[@type='submit' and normalize-space()='Save']");
    private By firstNameRequiredMsg = By.xpath(
            "//input[@name='firstName']/../following-sibling::span");
    private By successMessage = By.xpath(
            "//*[contains(.,'Successfully Saved')]");

    public AddEmployeePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    public String getURL(){
        wait.until(ExpectedConditions.urlContains("/pim/addEmployee"));
        return driver.getCurrentUrl();
    }
    public boolean firstNameIsDisplayed(){
        var firstnameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        return firstnameField.isDisplayed();
    }
    public boolean lastNameIsDisplayed(){
        var lastnameField = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        return lastnameField.isDisplayed();
    }
    public EmployeePersonalDetails enterEmployeeName(String employeeFirstName, String employeeLastName){
        enterFirstName(employeeFirstName);
        enterLastName(employeeLastName);
        clickSaveButton();
        return new EmployeePersonalDetails(driver, wait);
    }
    private void enterFirstName(String employeeFirstName){
        var firstnameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        firstnameField.sendKeys(employeeFirstName);
    }
    private void enterLastName(String employeeLastName){
        var lastnameField = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        lastnameField.sendKeys(employeeLastName);
    }
    private void clickSaveButton(){
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }
    public String getFirstNameReqMsg(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameRequiredMsg)).getText();
    }
    public boolean isSuccessMessageDisplayed(){
        var successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return successMsg.isDisplayed();
    }
}
