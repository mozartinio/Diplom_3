package pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPage {
    private final By signInLink = By.xpath(".//a[text()='Войти']");
    private final WebDriver driver;

    public RecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }
}
