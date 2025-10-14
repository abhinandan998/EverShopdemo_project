package pageObject;

import Component.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractComponent {

    @FindBy(xpath =  "(//input[@type='range'])[1]")
    WebElement minSlider;


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToCategory(String category)
    {
        String categoryLower = category.toLowerCase();
        By locator= By.xpath("(//a[@href='/" +categoryLower+ "'])[2]");
        Click(waitForClickable(locator));
        waitForURlContains(categoryLower);
    }

    public void actionFilter(Actions action,String offset, String size, String color, String brand )
    {
        waitForVisibility(minSlider);
        action.clickAndHold(minSlider)
                .moveByOffset(Integer.parseInt(offset),0)
                .release()
                .perform();

        WebElement sizeElement = driver.findElement(By.xpath("//span[normalize-space()='"+size+"']"));
        WebElement colorElement = driver.findElement(By.xpath("//span[normalize-space()='"+color+"']"));
        WebElement brandElement = driver.findElement(By.xpath("//span[normalize-space()='"+brand+"']"));

        Click(sizeElement);
        Click(colorElement);
        Click(brandElement);

    }
}
