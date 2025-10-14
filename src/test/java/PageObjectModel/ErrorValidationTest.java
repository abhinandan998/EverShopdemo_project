package PageObjectModel;

import TestComponent.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObject.LoginPage;


public class ErrorValidationTest extends BaseTest {

    @Test
    public  void inValidLoginTest()
    {
        LoginPage login = new LoginPage(driver);
        login.openLoginPage("https://demo.evershop.io/account/login");
        login.login("abhinandan99@gmail.com","Abhi@12356");


        By alert = By.xpath("//div[@class ='text-critical mb-4']");
        String message = driver.findElement(alert).getText().trim();
        System.out.println("alert message :" +message);
        Assert.assertTrue(message.equalsIgnoreCase("Invalid email or password"));
    }

}
