import io.qameta.allure.Step;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import utils.BrowserProvider;

public class ConstructorTest {

    private WebDriver driver;
    private MainPage mainPage;

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Переход к разделу Начинки")
    @Step("Переход к разделу Начинки")
    public void fillingTest(String browserName) {
        driver = getDriver(browserName);
        driver.get("https://stellarburgers.nomoreparties.site");

        mainPage = new MainPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Увеличиваем таймаут

        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getFillingButton()));

        Point firstPos = mainPage.getFillingUnitButton().getLocation();
        mainPage.getFillingButton().click();
        wait.until(ExpectedConditions.visibilityOf(mainPage.getFillingUnitButton()));
        Point secondPos = mainPage.getFillingUnitButton().getLocation();

        Assertions.assertNotEquals(firstPos, secondPos,
                "Элемент не прокручен в видимую область");
        driver.quit();
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Переход к разделу Соусы")
    @Step("Переход к разделу Соусы")
    public void sauceTest(String browserName) throws InterruptedException {
        driver = getDriver(browserName);
        driver.get("https://stellarburgers.nomoreparties.site");
        mainPage = new MainPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getSauceButton()));

        Point firstPos = mainPage.getFillingUnitButton().getLocation();
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getSauceButton())).click();
        wait.until(ExpectedConditions.visibilityOf(mainPage.getFillingUnitButton()));
        Point secondPos = mainPage.getFillingUnitButton().getLocation();

        Assertions.assertNotEquals(firstPos, secondPos,
                "Элемент не прокручен в видимую область");
        driver.quit();
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Переход к разделу Булочки")
    @Step("Переход к разделу Булочки")
    public void bunTest(String browserName) throws InterruptedException {
        driver = getDriver(browserName);
        driver.get("https://stellarburgers.nomoreparties.site");
        mainPage = new MainPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getSauceButton()));
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getSauceButton())).click();
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getBunButton()));

        Point firstPos = mainPage.getFillingUnitButton().getLocation();
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getBunButton())).click();
        wait.until(ExpectedConditions.visibilityOf(mainPage.getFillingUnitButton()));
        Point secondPos = mainPage.getFillingUnitButton().getLocation();

        Assertions.assertNotEquals(firstPos, secondPos,
                "Элемент не прокручен в видимую область");
        driver.quit();
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
