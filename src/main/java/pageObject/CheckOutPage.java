package pageObject;

import Component.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage extends AbstractComponent {

    @FindBy(xpath = "//input[@name='address[full_name]']")
    WebElement fullName;
    @FindBy(xpath = "//input[@name='address[telephone]']")
    WebElement phone;
    @FindBy(xpath = "//input[@name='address[address_1]']")
    WebElement address;
    @FindBy(xpath = "//input[@name='address[city]']")
    WebElement city;
    @FindBy(xpath = "//select[@id='address[country]']")
    WebElement countryDropDown;
    @FindBy(xpath = "//select[@id='address[province]']")
    WebElement provinceDropDown;
    @FindBy(xpath = "//input[@name='address[postcode]']")
    WebElement postCode;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement continueToPayment;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void fillshippingdetails(String name, String ph, String addr, String city1, int countryIndex, int provinceIndex, String zipCode)
    {
        sendkeys(fullName, name);
        sendkeys(phone,ph);
        sendkeys(address,addr);
        sendkeys(city,city1);

        new Select(countryDropDown).selectByIndex(countryIndex);
        new Select(provinceDropDown).selectByIndex(provinceIndex);
        sendkeys(postCode, zipCode);

        Click(continueToPayment);

    }
    public String captureAlertMessage()
    {
        By alertLocator = By.xpath("//div[@role='alert']");
        waitForElementLocated(alertLocator);
        String message = driver.findElement(alertLocator).getText().trim();
        System.out.println("Alert message: "+message);
        return message;


    }
}
