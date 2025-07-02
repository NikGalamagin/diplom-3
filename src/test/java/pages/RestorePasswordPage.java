package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RestorePasswordPage {
    private WebDriver driver;

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getEnterButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a"));
    }

}
