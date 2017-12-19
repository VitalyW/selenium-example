package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyThirdTest extends TestBase {

  @Test
  public void myFirstTest() {
    driver.get("https://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnK")).click();
    wait.until(titleIs("webdriver - Google Search"));
  }

  @Test
  public void mySecondTest() {
    driver.get("https://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnK")).click();
    wait.until(titleIs("webdriver - Google Search"));
  }

  @Test
  public void myThirdTest() {
    driver.get("https://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("btnK")).click();
    wait.until(titleIs("webdriver - Google Search"));
  }

}