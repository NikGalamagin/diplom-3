import io.qameta.allure.Step;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import pages.RestorePasswordPage;
import utils.BrowserProvider;

public class LoginTest {

    private static RegistrationTest registrationTest;
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private String randomEmail;
    private String randomPassword;
    private RegistrationPage registrationPage;
    private RestorePasswordPage restorePasswordPage;

    @BeforeAll
    public static void setUpBeforeAll() {
        registrationTest = new RegistrationTest();
        registrationTest.setUp();
        registrationTest.getRandomCredentials();
        registrationTest.userRegistration();
        registrationTest.tearDown();
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Логин через кнопку личный кабинет")
    @Step("Логин через кнопку личный кабинет")
    public void loginWithLKTest(String browserName) throws InterruptedException {
        driver = getDriver(browserName);
        driver.get("https://stellarburgers.nomoreparties.site");
        randomEmail = registrationTest.getEmail();
        randomPassword = registrationTest.getPassword();

        mainPage = new MainPage(driver);
        mainPage.getPersonalAccountButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getLoginInput().sendKeys(randomEmail);
        loginPage.getPasswordInput().sendKeys(randomPassword);
        loginPage.getLoginButton().click();

        mainPage = new MainPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(driver -> mainPage.getCreateOrderButton().isDisplayed());
        Assertions.assertTrue(mainPage.getCreateOrderButton().isDisplayed());
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Логин через кнопку войти в аккаунт")
    @Step("Логин через кнопку войти в аккаунт")
    public void loginWithEnterAccountTest(String browserName) throws InterruptedException {
        driver = getDriver(browserName);
        driver.get("https://stellarburgers.nomoreparties.site");
        randomEmail = registrationTest.getEmail();
        randomPassword = registrationTest.getPassword();

        mainPage = new MainPage(driver);
        mainPage.getEnterAccountButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getLoginInput().sendKeys(randomEmail);
        loginPage.getPasswordInput().sendKeys(randomPassword);
        loginPage.getLoginButton().click();

        mainPage = new MainPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(driver -> mainPage.getCreateOrderButton().isDisplayed());
        Assertions.assertTrue(mainPage.getCreateOrderButton().isDisplayed());
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Логин через кнопку войти в регистрации")
    @Step("Логин через кнопку войти в регистрации")

    public void loginWithRegistrationEnterTest(String browserName) throws InterruptedException {
        driver = getDriver(browserName);
        driver.get("https://stellarburgers.nomoreparties.site");
        randomEmail = registrationTest.getEmail();
        randomPassword = registrationTest.getPassword();

        mainPage = new MainPage(driver);
        mainPage.getPersonalAccountButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getRegistrationButton().click();

        registrationPage = new RegistrationPage(driver);
        registrationPage.getEnterButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getLoginInput().sendKeys(randomEmail);
        loginPage.getPasswordInput().sendKeys(randomPassword);
        loginPage.getLoginButton().click();

        mainPage = new MainPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(driver -> mainPage.getCreateOrderButton().isDisplayed());
        Assertions.assertTrue(mainPage.getCreateOrderButton().isDisplayed());
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Логин через кнопку войти в регистрации")
    @Step("Логин через кнопку войти в регистрации")
    public void loginWithRestorePasswordTest(String browserName) throws InterruptedException {
        driver = getDriver(browserName);
        driver.get("https://stellarburgers.nomoreparties.site");
        randomEmail = registrationTest.getEmail();
        randomPassword = registrationTest.getPassword();

        mainPage = new MainPage(driver);
        mainPage.getPersonalAccountButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getRestorePasswordButton().click();

        restorePasswordPage = new RestorePasswordPage(driver);
        restorePasswordPage.getEnterButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getLoginInput().sendKeys(randomEmail);
        loginPage.getPasswordInput().sendKeys(randomPassword);
        loginPage.getLoginButton().click();

        mainPage = new MainPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(driver -> mainPage.getCreateOrderButton().isDisplayed());
        Assertions.assertTrue(mainPage.getCreateOrderButton().isDisplayed());
    }

    private WebDriver getDriver(String browserName) {
        switch (browserName) {
            case "CHROME":
                return new ChromeDriver();
            case "YANDEX":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                return new ChromeDriver(options);
            default:
                throw new UnsupportedOperationException("Unsupported browser");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}