
package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import ru.stqa.training.selenium.appmanager.ApplicationManager;

public class TestBase {

  public final ApplicationManager app = new ApplicationManager();

  @Before
  public void start() {
    app.init();
  }

  @After
  public void tearDown() {
    app.stop();

  }
}