package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLoginInput() {
        return driver.findElement(By.xpath("//label[text()='Имя']/following-sibling::input[@name='name']"));
    }

    public WebElement getEmailInput() {
        return driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input[@name='name']"));
    }

    public WebElement getPasswordInput() {
        return driver.findElement(By.xpath("//input[@name='Пароль' and @type='password']"));
    }

    public WebElement getRegistrationButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/button"));
    }
    public WebElement getRegistrationError() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p"));
    }
    public WebElement getEnterText() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/h2"));
    }
    public WebElement getEnterButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a"));
    }

}
