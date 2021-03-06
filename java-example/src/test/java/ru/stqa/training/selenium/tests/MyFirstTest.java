package ru.stqa.training.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest extends TestBase {

  @Test
  public void myFirstTest() {
    app.driver.get("https://www.google.com/");
    app.driver.findElement(By.name("q")).sendKeys("webdriver");
    WebElement btnG = app.driver.findElement(By.name("btnK"));
    btnG.click();
    app.wait.until(titleIs("webdriver - Google Search"));
  }

  @Test
  public void mySecondTest() {
    app.driver.get("https://www.google.com/");
    app.driver.findElement(By.name("q")).sendKeys("webdriver");
    app.driver.findElement(By.name("btnK")).click();
    app.wait.until(titleIs("webdriver - Google Search"));
  }

  @Test
  public void myThirdTest() {
    app.driver.get("https://www.google.com/");
    app.driver.findElement(By.name("q")).sendKeys("webdriver");
    app.driver.findElement(By.name("btnK")).click();
    app.wait.until(titleIs("webdriver - Google Search"));
  }

}
