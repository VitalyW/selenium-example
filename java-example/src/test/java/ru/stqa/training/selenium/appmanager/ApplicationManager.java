package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class ApplicationManager {
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

  public void init() {
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

  public void stop() {
    //driver.quit();
    //driver = null;
  }
}
