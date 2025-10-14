package pageObject;

import Component.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractComponent {

    //page Factory
    @FindBy(xpath = "//input[@name='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordFiled;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement signInButton;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void openLoginPage(String url)
    {
        driver.get(url);
        waitForURlContains("account");
    }
    public void login(String email, String password)
    {
        sendkeys(emailField, email);
        sendkeys(passwordFiled, password);


        Click(signInButton);
        // ✅ Wait until login completes (either redirect or success message disappears)
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/account"),
                ExpectedConditions.urlContains("/"),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href,'/men')]"))
        ));
    }
    public boolean isLoginSuccessful() {
        try {
            // Check if account or home page loaded
            return driver.getCurrentUrl().contains("account") ||
                    driver.findElement(By.xpath("//a[contains(@href,'/men')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
