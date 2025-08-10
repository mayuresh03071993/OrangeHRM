package tests;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.loginPage;

import java.time.Duration;
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class loginTest extends BaseClass {

    @Test
    public void validLoginTest() throws InterruptedException {
        loginPage lp = new loginPage(driver); // ✅ Initialize after driver is ready
        Thread.sleep(3000); // Optional: wait to visually verify (not recommended in real tests)
        lp.setTxtUserName("AdminTest");
        lp.setTxtPassword("admin123");
        Thread.sleep(3000); // Optional: wait to visually verify (not recommended in real tests)
        lp.setBtnLogin();
        Thread.sleep(3000); // Optional: wait to visually verify (not recommended in real tests)
        String expected_title="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        String actual_title=driver.getCurrentUrl();
        Assert.assertEquals(actual_title,expected_title, "Page title mismatch");

    }

    //@Ignore
    @Test(priority = 2)
    public void invalidUsername() {
        loginPage lp = new loginPage(driver);
        lp.setTxtUserName("wrongUser");
        lp.setTxtPassword("admin123");
        lp.setBtnLogin();
        String expected="Invalid credentials";
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errormsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")));
        String actual=errormsg.getText();
        Assert.assertEquals(actual,expected, "Login Failed");

    }

    //@Ignore
    @Test(priority = 3)
    public void invalidPassword()
    {
        loginPage lp=new loginPage(driver);
        lp.setTxtUserName("Admin");
        lp.setTxtPassword("adfajbjkhkjh");
        lp.setBtnLogin();
        String expected="Invalid credentials";
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errormsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")));
        String actual=errormsg.getText();
        Assert.assertEquals(actual,expected, "Login Failed");

    }

    //@Ignore
    @Test (priority = 4)
    public void emptyUsername() throws InterruptedException {
        loginPage lp=new loginPage(driver);
        lp.setTxtUserName("");
        lp.setTxtPassword("adfajbjkhkjh");
        lp.setBtnLogin();
        String expected="Required";
        String actual=driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")).getText();
        Assert.assertEquals(actual,expected, "Login Failed");

    }

    //@Ignore
    @Test (priority = 5)
    public void emptyPassword()
    {
        loginPage lp=new loginPage(driver);
        lp.setTxtUserName("Admin");
        lp.setTxtPassword("");
        lp.setBtnLogin();
        String expected="Required";
        String actual=driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")).getText();
        Assert.assertEquals(actual,expected, "Login Failed");
    }

    //@Ignore
    @Test (priority = 6)
    public void emptyUsernameAndPassword()
    {
        loginPage lp=new loginPage(driver);
        lp.setTxtUserName("");
        lp.setTxtPassword("");
        lp.setBtnLogin();
        String expected="Required";
        String actual=driver.findElement(By.xpath("//div[@class='orangehrm-login-slot-wrapper']//div[1]//div[1]//span[1]")).getText();
        Assert.assertEquals(actual,expected, "Login Failed");

    }
}
