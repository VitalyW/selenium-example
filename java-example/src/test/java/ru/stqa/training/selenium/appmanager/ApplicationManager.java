package ru.stqa.training.selenium.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.model.ProductData;
import ru.stqa.training.selenium.model.RegisrationData;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
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

  public void navigateToLitecartAdmin() {
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

  public void navigateToCountries() {
    navigateToLitecartAdmin();
    login("admin", "admin");
    driver.findElement(By.xpath("//*[contains(@class, 'name') and contains(text(), 'Countries')]")).click();
  }

  public void navigateToGeoZones() {
    navigateToLitecartAdmin();
    login("admin", "admin");
    driver.findElement(By.xpath("//*[contains(@class, 'name') and contains(text(), 'Geo Zones')]")).click();
  }

  public void navigateToLitecartStore() {
    driver.get("http://localhost/litecart/en/");
  }

  public int iterateOverCampaignProducts() {
    List<WebElement> campaignStickers = driver.findElements(By.cssSelector("#box-campaign-products a.link .sticker"));
    return campaignStickers.size();
  }

  public int iterateOverPopularProducts() {
    List<WebElement> popularStickers = driver.findElements(By.cssSelector("#box-popular-products a.link .sticker"));
    return popularStickers.size();
  }

  public int iterateOverLatestProducts() {
    List<WebElement> latestStickers = driver.findElements(By.cssSelector("#box-latest-products a.link .sticker"));
    return latestStickers.size();
  }

  public int quantityOfProductsOnMainPage() {
    List<WebElement> quantityOfProductOnMainPage = driver.findElements(By.cssSelector(".box .product .link"));
    return quantityOfProductOnMainPage.size();
  }

  public int quantityOfAllStickersOnMainPage() {
    return iterateOverCampaignProducts() + iterateOverPopularProducts() + iterateOverLatestProducts();
  }

  public void initAddProduct() {
    click(By.cssSelector("#box-apps-menu li a[href*='doc=catalog']"));
    click(By.cssSelector("ul.list-inline.pull-right li:last-child a"));
  }

  public void initGeneralForm() {
    click(By.cssSelector("ul.nav.nav-tabs a[href='#tab-general']"));
  }

  public void initInformationForm() {
    click(By.cssSelector("ul.nav.nav-tabs a[href='#tab-information']"));
  }

  public void initPricesForm() {
    click(By.cssSelector("ul.nav.nav-tabs a[href='#tab-prices']"));
  }

  public void submitProductAdd() {
    click(By.cssSelector("[name=save]"));
  }

  public void fillGeneralForm(ProductData productData) {
    initGeneralForm();
    type(By.cssSelector("[name*=name]"), productData.getName());
    type(By.cssSelector("[name=code]"), productData.getCode());
    type(By.cssSelector("[name=quantity]"), productData.getQuantity());
    attach(By.cssSelector(("[name='new_images[]']")), productData.getPhoto());
//    submitProductAdd();
  }

  public void fillInformationForm(ProductData productData) {
    initInformationForm();
    type(By.cssSelector("[name=keywords]"), productData.getKeywords());
    type(By.cssSelector(".trumbowyg-editor"), productData.getDescription());
    type(By.cssSelector("[name*=head_title]"), productData.getTitle());
//    submitProductAdd();
  }

  public void fillPricesForm(ProductData productData) {
    initPricesForm();
    type(By.cssSelector("[name='prices[USD]']"), productData.getPrice());
    submitProductAdd();
  }

  public void attach(By locator, File file) {
    if (file != null) {
      driver.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }


  public void initRegistration() {
    click(By.cssSelector("#box-account-login .text-center"));
  }

  public void fillRegistrationForm(RegisrationData regisrationData) {
    type(By.cssSelector("input[name='tax_id']"), regisrationData.getTaxID());
    type(By.cssSelector("input[name='firstname']"), regisrationData.getFirstName());
    type(By.cssSelector("input[name='lastname']"), regisrationData.getLastName());
    type(By.cssSelector("input[name='address1']"), regisrationData.getAddress());
    type(By.cssSelector("input[name='postcode']"), regisrationData.getZipCode());
    type(By.cssSelector("input[name='city']"), regisrationData.getCity());
    select(By.cssSelector("select[name='country_code']"), regisrationData.getCountry());
    select(By.cssSelector("select[name='zone_code']"), regisrationData.getState());
    type(By.cssSelector("input[name='postcode']"), regisrationData.getZipCode());
    type(By.cssSelector("#box-create-account [name='email']"), regisrationData.getEmail());
    type(By.cssSelector("input[name='phone']"), regisrationData.getPhone());
    type(By.cssSelector("#box-create-account [name='password']"), regisrationData.getDesiredPassword());
    type(By.cssSelector("input[name='confirmed_password']"), regisrationData.getConfirmPassword());
  }

  public void submitRegistration() {
    click(By.cssSelector("button[name='create_account']"));
  }

  public void logout() {
    click(By.cssSelector("#box-account li:last-child a"));
  }

  public void signin(String emailAddress, String password) {
    type(By.cssSelector("input[name='email']"), emailAddress);
    type(By.cssSelector("input[name='password']"), password);
    click(By.cssSelector("button[name='login']"));
  }

  public void selectProduct1() {
    WebElement product = driver.findElement(By.cssSelector(".product"));
      product.click();
      if (isElementPresent(By.cssSelector("[name='options[Size]']"))) {
        WebElement select = driver.findElement(By.cssSelector("[name='options[Size]']"));
        Select dropDown = new Select(select);
        dropDown.selectByIndex(randomNumber());
      }
      addToCart();
      closeProductView();
      isElementPresen(By.cssSelector("#popular-products"));
      product = driver.findElement(By.cssSelector(".product"));
  }

  public void clickOnFirstProduct() {
    click(By.cssSelector("[class='image-wrapper']"));
  }

  public void selectRandomCountry() {
    Random random = new Random();
    int rand = random.nextInt(243) + 1;
    List<WebElement> listOfCountries = driver.findElements(By.cssSelector
            ("table[class='table table-striped data-table'] a[title='Edit']"));
    listOfCountries.get(rand).click();
  }

  public List<WebElement> getAllExternalLinks() {
    List<WebElement> externalLinks = driver.findElements(By.cssSelector("[name='country_form'] a[target='_blank']"));
    return externalLinks;
  }

  public void openExternalLinks() {
    List<WebElement> externalLinks = driver.findElements(By.cssSelector("[name='country_form'] a[target='_blank']"));
    for (WebElement link : externalLinks) {
      String oldWindow = driver.getWindowHandle();
      Set<String> oldWindows = driver.getWindowHandles();
      link.click();
      String newWindow = wait.until(waitForNewWindow(oldWindows));
      driver.switchTo().window(newWindow);
      driver.close();
      driver.switchTo().window(oldWindow);
    }
  }

  public ExpectedCondition<String> waitForNewWindow(Set<String> oldWindows) {
    return driver -> {
      Set<String> windows = driver.getWindowHandles();
      windows.removeAll(oldWindows);
      if (windows.size() > 0) {
        return windows.iterator().next();
      } else {
        return null;
      }
    };
  }

  public int randomNumber() {
    Random random = new Random();
    return random.nextInt(3) + 1;
  }

  public void closeProductView() {
    click(By.cssSelector("[aria-label='Close']"));
  }

  public void addToCart() {
    click(By.cssSelector("[name='add_cart_product']"));
  }

  public void checkOut() {
    click(By.cssSelector("#cart a"));
  }

  public int getQuantity() {
    String quantity = driver.findElement(By.cssSelector("#cart span.quantity")).getText();
    return Integer.parseInt(quantity);
  }

  public void waitForElement(By locator) {
      WebDriverWait wait = new WebDriverWait(driver, 5);
      WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
  }

  public void removeProductFromCart() {
    click(By.cssSelector("[title='Remove']"));
    waitForElement(By.cssSelector("#box-checkout-payment"));
  }

  public int getProductCountInCart() {
    List<WebElement> products = driver.findElements(By.cssSelector("[title='Remove']"));
    return products.size();
  }

  public boolean isElementPresen(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  public void click(By locator) {
    WebElement element = driver.findElement(locator);
    if (element.isDisplayed()) {
      element.click();
    }
  }

  public void type(By locator, String text) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(text);
  }

  public void select(By locator, String text) {
    Select element = new Select(driver.findElement(locator));
    element.selectByVisibleText(text);
  }

}