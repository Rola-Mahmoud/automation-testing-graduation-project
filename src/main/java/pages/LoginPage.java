package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By username = By.name("username");
    private By password = By.name("password");
    private By loginButton = By.tagName("button");
    private By errorMessage = By.xpath("//p[text()='Invalid credentials']");
    private By requiredMessages = By.xpath("//div[@class='oxd-form-row']//span[text()='Required']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    public DashboardPage login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
        return new DashboardPage(driver, wait);
    }
    private void setUsername(String name) {
        var usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        usernameField.clear();
        usernameField.sendKeys(name);
    }
    private void setPassword(String pass) {
        var passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        passwordField.clear();
        passwordField.sendKeys(pass);
    }
    private void clickLoginButton() {
        wait.until((ExpectedConditions.visibilityOfElementLocated(loginButton))).click();
    }
    public String getErrorMessage() {
        var errorMsgField = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return errorMsgField.getText();
    }
    public int getRequiredFieldsCount(){
        var requiredFields = wait.until(ExpectedConditions.numberOfElementsToBe(requiredMessages,2));
        return requiredFields.size();
    }
}
