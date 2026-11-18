package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.TestDataReader;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;

    public static WebDriver getDriver() {
        return driver.get();
    }
    public static WebDriverWait getWait() {
        return wait.get();
    }

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");
        WebDriver webDriver;
        if (browser.equals("chrome")) {
            webDriver = new ChromeDriver();
        } else if (browser.equals("firefox")){
            webDriver = new FirefoxDriver();
        } else if (browser.equals("edge")){
            webDriver = new EdgeDriver();
        }else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.set(webDriver);
        getDriver().manage().window().maximize();
        getDriver().get(ConfigReader.getProperty("base.url"));
        int waitTime = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(waitTime)));
        loginPage = new LoginPage(getDriver(), getWait());
    }
    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
            wait.remove();
        }
    }

    protected void performValidLogin() throws IOException {

        Object[] credentials = TestDataReader.getTestData(
                "validLogin", "username", "password"
        );

        String username = (String) credentials[0];
        String password = (String) credentials[1];

        dashboardPage = loginPage.login(username, password);

        Assert.assertTrue(
                dashboardPage.getURL().contains("/dashboard/index"),
                "Login failed"
        );
    }
}
