package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getRegistrationButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a"));
    }
    public WebElement getLoginInput() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input"));
    }
    public WebElement getPasswordInput() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input"));
    }
    public WebElement getLoginButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/button"));
    }
    public WebElement getRestorePasswordButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a"));
    }


}
