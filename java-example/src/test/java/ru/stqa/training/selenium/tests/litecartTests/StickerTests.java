package ru.stqa.training.selenium.tests.litecartTests;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.training.selenium.tests.TestBase;

public class StickerTests extends TestBase {

  @Test
  public void testProductsHaveStickersOnMainPage() {
    app.navigateToLitecartStore();
    int allStickers = app.quantityOfAllStickersOnMainPage();
    int allProducts = app.quantityOfProductsOnMainPage();
    Assert.assertEquals(allStickers, allProducts);
  }
}
