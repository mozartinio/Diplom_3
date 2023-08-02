package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {
    private final By orderPlaceButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By profileEnterButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By accountEnterButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By burgerAssembleTitle = By.xpath(".//*[text()='Соберите бургер']");
    private final By menuBuns = By.xpath(".//span[text()='Булки']/..");
    private final By menuSauces = By.xpath(".//span[text()='Соусы']/..");
    private final By menuFillings = By.xpath(".//span[text()='Начинки']/..");
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitingForMainPageLoading() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(burgerAssembleTitle));
    }

    @Step("Проверка отображение кнопки заказа")
    public boolean orderPlaceButtonIsDisplayed() {
        return driver.findElement(orderPlaceButton).isDisplayed();
    }

    @Step("Нажатие на кнопку профиля")
    public void clickOnProfileEnterButton() {
        driver.findElement(profileEnterButton).click();
    }

    @Step("Нажатие на кнопку входа в аккаунт")
    public void clickOnAccountEnterButton() {
        driver.findElement(accountEnterButton).click();
    }
    @Step("Нажатие на меню булок")
    public void clickOnBunsMenu() {
        driver.findElement(menuBuns).click();
    }
    @Step("Нажатие на соус меню ")
    public void clickOnSaucesMenu() {
        driver.findElement(menuSauces).click();
    }
    @Step("Нажатие на меню начинок")
    public void clickOnFillingsMenu() {
        driver.findElement(menuFillings).click();
    }
    @Step("Проверка выбранного меню булок")
    public boolean bunsMenuIsSelected() {
        return driver.findElement(menuBuns).getAttribute("class").contains("current");
    }
    @Step("Проверка выбранного меню соусов")
    public boolean saucesMenuIsSelected() {
        return driver.findElement(menuSauces).getAttribute("class").contains("current");
    }
    @Step("Проверка выбранного меню начинок")
    public boolean fillingsMenuIsSelected() {
        return driver.findElement(menuFillings).getAttribute("class").contains("current");
    }
}
