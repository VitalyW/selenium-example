
package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class TestBase {

  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
  public WebDriver driver;
  public WebDriverWait wait;

  public boolean isElementPresent(By locator) {
    try {
      wait.until((WebDriver d) -> d.findElement(locator)); // excplicit wait
//      driver.findElement(locator);
      return true;
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    }
  }

  public boolean areElementsPresent(By locator) {
    return driver.findElements(locator).size() > 0;
  }

  @Before
  public void start() {
    if (tlDriver.get() != null) {
      driver = tlDriver.get();
      wait = new WebDriverWait(driver, 10);
      return;
    }
    driver = new ChromeDriver();
//    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  //implicit wait
    tlDriver.set(driver);
    wait = new WebDriverWait(driver, 10);

    Runtime.getRuntime().addShutdownHook(
            new Thread(() -> { driver.quit(); driver = null; }));
  }

  @After
  public void stop() {
    //driver.quit();
    //driver = null;
  }
}