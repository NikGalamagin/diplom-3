package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonalAccountPage {
    private WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getExitButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button"));

    }
    public WebElement getConstructorButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a/p"));

    }
}
