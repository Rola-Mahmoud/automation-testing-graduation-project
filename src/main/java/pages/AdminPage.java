package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By userManagementItem = By.xpath("//span[normalize-space()='User Management']");
    private By usersOption = By.xpath("//a[text()='Users']");
    private By addButton = By.xpath("//button[contains(.,'Add')]");

    public AdminPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    public AddUserPage goToAddUserPage(){
        clickUserManagementItem();
        clickUsersOption();
        clickAddButton();
        return new AddUserPage(driver,wait);
    }
    public void clickUserManagementItem(){
        wait.until(ExpectedConditions.elementToBeClickable(userManagementItem)).click();
    }
    public void clickUsersOption(){
        wait.until(ExpectedConditions.elementToBeClickable(usersOption)).click();
    }
    public void clickAddButton(){
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

}
