package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeListPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By employeeNameInput = By.xpath(
            "//label[text()='Employee Name']/../following-sibling::div//input");
    private By searchButton = By.xpath("//button[contains(.,'Search')]");
    private By noRecordsMessage = By.xpath("//span[contains(.,'No Records Found')]");
    private By addEmployeeOption = By.xpath("//a[contains(text(),'Add Employee')]");

    public EmployeeListPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    public String getURL(){
        return driver.getCurrentUrl();
    }
    public void searchForEmployee(String employeeName){
        var employeeField = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameInput));
        employeeField.sendKeys(employeeName);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
    public boolean isEmployeeNameDisplayed(String employeeName){
        By name = By.xpath("//div[@class='oxd-table-body']//div[contains(.,'"+employeeName+"')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(name)).isDisplayed();
    }
    public String getNoRecordsMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsMessage)).getText();
    }
    public AddEmployeePage goToAddEmployeePage(){
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeOption)).click();
        return new AddEmployeePage(driver,wait);
    }
}
