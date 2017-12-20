package ru.stqa.training.selenium.tests;

import org.junit.Test;
import ru.stqa.training.selenium.tests.TestBase;

public class TestLeftMenues extends TestBase {

  @Test
  public void testLeftMenuesAreClickable() {
    app.driver.get("http://localhost/litecart/admin");
  }

}
