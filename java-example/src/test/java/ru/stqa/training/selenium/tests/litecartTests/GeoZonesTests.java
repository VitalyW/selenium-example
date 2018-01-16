package ru.stqa.training.selenium.tests.litecartTests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.tests.TestBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GeoZonesTests extends TestBase {

  @Test
  public void testCountriesSortedAlphabetically() {
    app.navigateToCountries();
    List<WebElement> countries = app.driver.findElements(By.cssSelector(".table td:nth-child(5)"));
    List<String> countriesText = countries.stream().map(WebElement::getText).collect(Collectors.toList());
    countriesText.remove(1);
    List<String> sortedCountries = new ArrayList<>(countriesText);
    Collections.sort(sortedCountries);
    for (int i = 0; i < sortedCountries.size(); i++) {
      Assert.assertEquals(sortedCountries.get(i), countriesText.get(i));
    }
  }

  @Test
  public void testCountriesGeoZones3() {
    app.navigateToCountries();
    WebElement table = app.driver.findElement(By.className("data-table"));
    List<WebElement> rows = table.findElements(By.tagName("tr"));
    List<String> countries = new ArrayList<>();
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      if (cells.get(5).getText().equals("0")) {
        countries.add(cells.get(4).findElement(By.tagName("a")).getAttribute("href"));
      }
    }
    comparison(countries, ".table td:nth-child(3)");
  }

  @Test
  public void testGeoZonesSorted() {
    app.navigateToGeoZones();
    WebElement table = app.driver.findElement(By.className("data-table"));
    List<WebElement> rows = table.findElements(By.tagName("tr"));
    List<String> countriesList = new ArrayList<>();
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      WebElement name = cells.get(2);
      countriesList.add(name.findElement(By.tagName("a")).getAttribute("href"));
    }
    comparison(countriesList, "tr td:nth-child(3)");
  }

  private void comparison(List<String> countriesList, String locator) {
    for (String country : countriesList) {
      app.driver.get(country);
      List<WebElement> geoZones = app.driver.findElements(By.cssSelector(locator));
      List<String> geoZonesNames = geoZones.stream().map(WebElement::getText).collect(Collectors.toList());
      List<String> sortedGeoZones = new ArrayList<>(geoZonesNames);
      Collections.sort(sortedGeoZones);
      for (int j = 0; j < sortedGeoZones.size(); j++) {
        Assert.assertEquals(sortedGeoZones.get(j), geoZonesNames.get(j));
      }
    }
  }
}