package ru.stqa.training.selenium.tests.litecartAdminTests;

import org.junit.Test;
import ru.stqa.training.selenium.tests.TestBase;

public class OpenLinksInNewWindows extends TestBase {

  @Test
  public void testOpenLinksInNewWindow() {
    app.navigateToLitecartAdmin();
    app.navigateToCountries();
    app.selectRandomCountry();
    app.openExternalLinks();
  }
}
