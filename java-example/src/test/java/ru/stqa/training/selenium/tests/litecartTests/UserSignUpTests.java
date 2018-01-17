package ru.stqa.training.selenium.tests.litecartTests;

import org.junit.Test;
import ru.stqa.training.selenium.model.RegisrationData;
import ru.stqa.training.selenium.tests.TestBase;

import java.util.Random;

public class UserSignUpTests extends TestBase {
  private final static String EMAIL_ADDRESS = generateEmail();
  private final static String PASSWORD = "password";

  @Test
  public void testUserSignUp() {
    app.navigateToLitecartStore();
    app.initRegistration();
    app.fillRegistrationForm(new RegisrationData()
            .withTaxID("1234").withCompany("Apple").withFirstName("John").withLastName("Nelson").withAddress("123 Street")
            .withZipCode("12345").withCity("Chicago").withCountry("United States").withState("Texas")
            .withEmail(EMAIL_ADDRESS).withPhone("123456789").withDesiredPassword(PASSWORD).withConfirmPassword(PASSWORD));
    app.submitRegistration();
    app.logout();
    app.signin(EMAIL_ADDRESS, PASSWORD);
  }

  private static String generateEmail() {
    String candidateChars = "abcdefghopqrstvwxyz";
    StringBuilder stringBuilder = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 5; i++) {
      stringBuilder.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
    }
    return stringBuilder + "@test.com";
  }
}

