package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getCreateOrderButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button"));
    }
    public WebElement getPersonalAccountButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/nav/a/p"));
    }
    public WebElement getEnterAccountButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button"));
    }
    public WebElement getFillingUnitButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/ul[3]/a[6]/img"));
    }
    public WebElement getFillingButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]"));
    }
    public WebElement getSauceButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]"));
    }
    public WebElement getBunButton() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]"));
    }

}
