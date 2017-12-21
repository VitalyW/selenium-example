package ru.stqa.training.selenium.tests.litecartAdminTests;

import org.junit.Test;
import org.openqa.selenium.By;
import ru.stqa.training.selenium.tests.TestBase;

public class TestLeftMenu extends TestBase {

  @Test
  public void testLeftMenuAndSubmenu() {
    app.navigateToLitecart();
    app.login("admin", "admin");
    app.iterateOverLeftMenuAndSubmenu(By.cssSelector("#box-apps-menu #app-"));
  }

}
