package pageObject;

import Component.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractComponent {


    By addToCartButton = By.xpath("//button[@type='button']");


    By viewCartButton = By.xpath( "//a[@class='add-cart-popup-button']");


    By checkOutButton = By.xpath("//span[normalize-space()='CHECKOUT']");



    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }
    public void selectProduct(String productName, String variantSize, String variantColor) throws InterruptedException {
        WebElement product = driver.findElement(By.xpath("//span[normalize-space()='"+productName+"']"));
        Click(product);

        WebElement variantColor1 = driver.findElement(By.xpath("//a[normalize-space()='"+variantColor+"']"));

        Click(variantColor1);
        Thread.sleep(2000);



        WebElement variantSize1 = driver.findElement(By.xpath("//a[normalize-space()='"+variantSize+"']"));

        Click(variantSize1);
        Thread.sleep(2000);



    }
    public void proceedToCheckout()
    {

       Click(findElement(addToCartButton));
       Click(findElement(viewCartButton));
       Click(findElement(checkOutButton));

    }
}
