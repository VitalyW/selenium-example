package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
  public WebDriver driver;
  public WebDriverWait wait;

  public boolean isElementPresent(By locator) {
    try {
//      wait.until((WebDriver d) -> d.findElement(locator)); // excplicit wait
      driver.findElement(locator);
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
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);  //implicit wait
    tlDriver.set(driver);
    wait = new WebDriverWait(driver, 10);

    Runtime.getRuntime().addShutdownHook(
            new Thread(() -> {
              driver.quit();
              driver = null;
            }));
  }

  public void stop() {
    //driver.quit();
    //driver = null;
  }

  public void navigateToLitecart() {
    driver.get("http://localhost/litecart/admin");
  }

  public void login(String username, String password) {
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.cssSelector("[type=submit]")).click();
  }


  public void iterateOverLeftMenuAndSubmenu(By locator) {
    List<WebElement> tableOnTheLeft = driver.findElements(locator);
    for (int i = 0; i < tableOnTheLeft.size(); i++) {
      driver.findElements(locator).get(i).click();
      List<WebElement> subMenue = driver.findElements(By.cssSelector("li#app- ul > li"));
      if (subMenue.size() > 0) {
        for (int j = 0; j < subMenue.size(); j++) {
          driver.findElements(By.cssSelector("li#app- ul > li")).get(j).click();
          driver.findElement(By.tagName("h1"));
        }
      } else {
        driver.findElement(By.tagName("h1"));
      }

    }
  }
}