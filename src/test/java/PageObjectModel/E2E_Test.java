package PageObjectModel;

import TestComponent.BaseTest;
import data.dataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CheckOutPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProductPage;

import java.util.HashMap;

public class E2E_Test extends BaseTest {

    @Test(dataProvider = "getUserData", dataProviderClass = dataReader.class)
    public void everShopE2ETest(HashMap<String, Object> input) throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.openLoginPage(input.get("url").toString());
        login.login(input.get("email").toString(),input.get("password").toString() );

        Assert.assertTrue(login.isLoginSuccessful(), " Login failed — check credentials or site response");



        HomePage home = new HomePage(driver);
        home.navigateToCategory(input.get("category").toString());
        home.actionFilter(new Actions(driver),
                input.get("minSliderOffset").toString(),
                input.get("size").toString(),
                input.get("color").toString(),
                input.get("brand").toString());


        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(input.get("productName").toString(),
                input.get("variantColor").toString(),
                input.get("variantSize").toString());
        productPage.proceedToCheckout();

        CheckOutPage checkOut = new CheckOutPage(driver);
        checkOut.fillshippingdetails(input.get("fullName").toString(),
                input.get("phNumber").toString(),
                input.get("address").toString(),
                input.get("city").toString(),
                Integer.parseInt(input.get("countryIndex").toString()),
                Integer.parseInt(input.get("provinceIndex").toString()),
                input.get("postCode").toString());

        checkOut.captureAlertMessage();





    }


}
