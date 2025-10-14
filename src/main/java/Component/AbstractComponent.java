package Component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    public WebDriver driver;
    public WebDriverWait wait;

    public  AbstractComponent(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }
    public void waitForVisibility(WebElement element)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public WebElement waitForClickable(By locator)
    {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public WebElement waitForClickable(WebElement element)
    {
         return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void Click(WebElement element)
    {
        waitForClickable(element).click();
    }
    public void Click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        driver.findElement(locator).click();
    }
    public void sendkeys(WebElement element, String text)
    {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }
    public void waitForURlContains(String text)
    {
        wait.until(ExpectedConditions.urlContains(text.toLowerCase()));
    }
    public WebElement findElement(By locator)
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForElementLocated(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
