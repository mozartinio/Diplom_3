package ru.yandex.pageobjects;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPage {
    private final By signInLink = By.xpath(".//a[text()='Войти']");
    private final WebDriver driver;

    public RecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на кнопку входа")
    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }
}
