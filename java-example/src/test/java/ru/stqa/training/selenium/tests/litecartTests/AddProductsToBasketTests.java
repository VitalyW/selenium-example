package ru.stqa.training.selenium.tests.litecartTests;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.training.selenium.tests.TestBase;

public class AddProductsToBasketTests extends TestBase {

  @Test
  public void testAddProductToBasket() {
    app.navigateToLitecartStore();
    while (app.getQuantity() < 3) {
      app.selectProduct1();
    }
    Assert.assertNotEquals(app.getQuantity(), 0);
    app.checkOut();
    while (app.getProductCountInCart() > 0) {
      app.removeProductFromCart();
    }
    app.driver.navigate().back();
    Assert.assertEquals(app.getQuantity(), 0);
  }
}
