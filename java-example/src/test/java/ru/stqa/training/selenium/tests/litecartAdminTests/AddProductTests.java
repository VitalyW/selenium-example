package ru.stqa.training.selenium.tests.litecartAdminTests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.model.ProductData;
import ru.stqa.training.selenium.tests.TestBase;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddProductTests extends TestBase {

  private final static String PRODUCT_NAME = "name_test123";

  @Test
  public void testAddNewProduct() {
    app.navigateToLitecartAdmin();
    app.login("admin", "admin");
    app.initAddProduct();
    File photo = new File("src/test/resources/abcd.jpg");
    app.fillGeneralForm(new ProductData().withCode("testTest1").withName(PRODUCT_NAME)
            .withQuantity("1").withPhoto(photo));
    app.fillInformationForm(new ProductData().withKeywords("keyWords123").withDescription("Description")
            .withTitle("title 12"));
    app.fillPricesForm(new ProductData().withPrice("1500"));

    List<WebElement> addedProducts = app.driver.findElements(
            By.cssSelector("tbody tr[class=' semi-transparent'] td:nth-child(3)"));
    for (WebElement product : addedProducts) {
      assertEquals(PRODUCT_NAME, product.getText());
    }
  }

}
