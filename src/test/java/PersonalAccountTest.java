import io.qameta.allure.Step;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccountPage;

public class PersonalAccountTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private static RegistrationTest registrationTest;
    private String randomEmail;
    private String randomPassword;
    private PersonalAccountPage personalAccountPage;

    @BeforeAll
    public static void setUpBeforeAll() {
        registrationTest = new RegistrationTest();
        registrationTest.setUp();
        registrationTest.getRandomCredentials();
        registrationTest.userRegistration();
        registrationTest.tearDown();
    }

    @BeforeEach
    public void setUp() {
        // Nothing to do here
    }

    @ParameterizedTest
    @CsvSource({
            "CHROME",
            "YANDEX"
    })
    @DisplayName("Проверка входа в личный кабинет")
    @Step("Проверка входа в личный кабинет")
    public void enterPesonalAccountTest(String browserName) {
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
        wait.until(driver -> mainPage.getPersonalAccountButton().isDisplayed());
        mainPage.getPersonalAccountButton().click();

        personalAccountPage = new PersonalAccountPage(driver);
        wait.until(driver -> personalAccountPage.getExitButton().isDisplayed());

        Assertions.assertTrue(personalAccountPage.getExitButton().isDisplayed());
    }

    @ParameterizedTest
    @CsvSource({
            "CHROME",
            "YANDEX"
    })
    @DisplayName("Проверка выхода из личного кабинета")
    @Step("Проверка выхода из личного кабинета")
    public void exitFromAccountTest(String browserName) {
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> mainPage.getPersonalAccountButton().isDisplayed());
        mainPage.getPersonalAccountButton().click();

        personalAccountPage = new PersonalAccountPage(driver);
        wait.until(driver -> personalAccountPage.getExitButton().isDisplayed());
        personalAccountPage.getExitButton().click();

        loginPage = new LoginPage(driver);
        wait.until(driver -> loginPage.getLoginButton().isDisplayed());
        Assertions.assertTrue(loginPage.getLoginButton().isDisplayed());
    }

    @ParameterizedTest
    @CsvSource({
            "CHROME",
            "YANDEX"
    })
    @DisplayName("Проверка перехода в конструктор из личного кабинета")
    @Step("Проверка перехода в конструктор из личного кабинета")
    public void enterConstructorTest(String browserName) {
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> mainPage.getPersonalAccountButton().isDisplayed());
        mainPage.getPersonalAccountButton().click();

        personalAccountPage = new PersonalAccountPage(driver);
        wait.until(driver -> personalAccountPage.getConstructorButton().isDisplayed());
        personalAccountPage.getConstructorButton().click();

        mainPage = new MainPage(driver);
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
        if (driver!= null) {
            driver.quit();
        }
    }
}