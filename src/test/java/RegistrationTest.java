import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.RegistrationPage;
import java.util.Random;

public class RegistrationTest {

    Random random = new Random();
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    String randomEmail;
    String randomPassword;
    String randomName;



    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/login");
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        getRandomCredentials();
        loginPage.getRegistrationButton().click();
    }

    @Test
    @DisplayName("Регистрация")
    @Step("Регистрация")
    public void registrationTest() {

        userRegistration();
        registrationPage.getRegistrationButton().click();
        Assertions.assertTrue(registrationPage.getEnterText().isDisplayed());
    }

    @Test
    @DisplayName("Регистрация с коротким паролем")
    @Step("Регистрация с коротким паролем")
    public void registrationErrorTest() {

        registrationPage.getLoginInput().sendKeys(randomName);
        registrationPage.getEmailInput().sendKeys(randomEmail);
        registrationPage.getPasswordInput().sendKeys("12345");
        registrationPage.getRegistrationButton().click();
        Assertions.assertTrue(registrationPage.getRegistrationError().isDisplayed());

    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    public void userRegistration() {

        registrationPage.getLoginInput().sendKeys(randomName);
        registrationPage.getEmailInput().sendKeys(randomEmail);
        registrationPage.getPasswordInput().sendKeys(randomPassword);
        registrationPage.getRegistrationButton().click();
    }

    public void getRandomCredentials() {
        randomEmail = 10000 + random.nextInt(10000) + "@yandex.ru";
        randomPassword = "password" + 10000 + random.nextInt(10000);
        randomName = "login" + 10000 + random.nextInt(10000);
    }

    public String getLogin() {
        return this.randomName;
    }
    public String getEmail() {
        return this.randomEmail;
    }
    public String getPassword() {
        return this.randomPassword;
    }


}