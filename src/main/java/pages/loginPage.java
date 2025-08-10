package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@name='username']")
    WebElement txtUserName;

    @FindBy(xpath = "//input[@name='password']")
    WebElement txtPassword;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement btnLogin;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
    WebElement invalid_creds;


    //By errorMessage= By.xpath("//p[contains(@class,'oxd-text oxd-text--p oxd-alert-content-text')]"));

    public loginPage(WebDriver driver) {
        this.driver = driver;  // ✅ Assign the driver
        PageFactory.initElements(driver, this);
    }

    public void setTxtUserName(String user) {
       new WebDriverWait(driver, Duration.ofSeconds(20)).
               until(ExpectedConditions.visibilityOf(txtUserName)).sendKeys(user);

//        txtUserName.clear();
//        txtUserName.sendKeys(user);
    }

    public void setTxtPassword(String pass) {
        txtPassword.clear();
        txtPassword.sendKeys(pass);
    }

    public void setBtnLogin() {
        btnLogin.click();
    }

    public void errorinvalidcreds()
    {
        String abc=invalid_creds.getText();
    }

}
