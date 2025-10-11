package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class StandaloneTest {

    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.evershop.io/account/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //login page
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("basuabhinandan99@gmail.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Abhi@12345");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //homepage

        driver.findElement(By.xpath("(//a[@href='/men'])[2]")).click();

        //filtering to select product
        Actions action = new Actions(driver);
        WebElement minslider = driver.findElement(By.xpath("(//input[@type='range'])[1]"));
        WebElement maxslider = driver.findElement(By.xpath("(//input[@type='range'])[2]"));

        // Move left slider slightly right (increase min price)
        action.clickAndHold(minslider).moveByOffset(40, 0).release().perform();
        Thread.sleep(1000);

        //select size
        driver.findElement(By.xpath("//span[normalize-space()='M']")).click();
        Thread.sleep(1000);
        //select color
        driver.findElement(By.xpath("//span[normalize-space()='Red']")).click();
        Thread.sleep(1000);
        //select brand
        driver.findElement(By.xpath("//span[normalize-space()='Nike']")).click();
        Thread.sleep(1000);

        //select product
        driver.findElement(By.xpath("//span[normalize-space()='Nike air zoom pegasus 35']")).click();
        Thread.sleep(2000);
        //select variant
        driver.findElement(By.xpath("//a[normalize-space()='Red']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[normalize-space()='M']")).click();
        Thread.sleep(2000);
        //add to cart
        driver.findElement(By.xpath("//button[@type='button']")).click();
        Thread.sleep(2000);
        //view cart
        driver.findElement(By.xpath("//a[@class='add-cart-popup-button']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[normalize-space()='CHECKOUT']")).click();
        Thread.sleep(2000);

        //shipping details
        driver.findElement(By.xpath("//input[@name='address[full_name]']")).sendKeys("Abhinandan Basu");
        driver.findElement(By.xpath("//input[@name='address[telephone]']")).sendKeys("9144243431");
        driver.findElement(By.xpath("//input[@name='address[address_1]']")).sendKeys("Malancha Road");
        driver.findElement(By.xpath("//input[@name='address[city]']")).sendKeys("Kharagpur");
        Thread.sleep(1000);
        WebElement sdropdown = driver.findElement(By.xpath("//select[@id='address[country]']"));
        Select dropdown = new Select(sdropdown);
        dropdown.selectByIndex(3);
        WebElement pdropdown = driver.findElement(By.xpath("//select[@id='address[province]']"));
        Select drop = new Select(pdropdown);
        drop.selectByIndex(36);

        driver.findElement(By.xpath("//input[@name='address[postcode]']")).sendKeys("721304");

        //continue to payment
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement alertBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='alert']")));

        String nonshippableMessage = alertBox.getText().trim();
        System.out.println("Alert message: " + nonshippableMessage);



    }
}
