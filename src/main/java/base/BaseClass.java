package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.ScreenshotUtils;

public class BaseClass {
    protected static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            String testName = result.getMethod().getMethodName();
            String status;

            switch (result.getStatus()) {
                case ITestResult.SUCCESS:
                    status = "PASS";
                    break;
                case ITestResult.FAILURE:
                    status = "FAIL";
                    break;
                case ITestResult.SKIP:
                    status = "SKIP";
                    break;
                default:
                    status = "UNKNOWN";
            }

            ScreenshotUtils.captureAllureScreenshot(driver, testName + "_" + status); // <- MUST call this one
            driver.quit();
        }
    }

}
