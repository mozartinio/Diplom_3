package ru.yandex.pageobjects;

import io.qameta.allure.Step;
import ru.yandex.user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    private final By nameInputBox = By.xpath(".//label[text()='Имя']/..//input");
    private final By emailInputBox = By.xpath(".//label[text()='Email']/..//input");
    private final By passwordInputBox = By.xpath(".//*[text()='Пароль']/..//input");
    private final By signUpButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By signUpWrongPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private final By signInLink = By.xpath(".//a[text()='Войти']");
    private final By signUpFormTitle = By.xpath(".//main//h2[text()='Регистрация']");

    private final WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод данных для входа")
    public void insertUserSignUpData(User user) {
        insertName(user.getName());
        insertEmail(user.getEmail());
        insertPassword(user.getPassword());
    }

    public void waitingForSignUpPageLoading() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(signUpFormTitle));
    }
    @Step("Ввод имени")
    public void insertName(String name) {
        driver.findElement(nameInputBox).sendKeys(name);
    }
    @Step("Ввод почты")
    public void insertEmail(String email) {
        driver.findElement(emailInputBox).sendKeys(email);
    }
    @Step("Ввод пароля")
    public void insertPassword(String password) {
        driver.findElement(passwordInputBox).sendKeys(password);
    }
    @Step("Нажатие на кнопку регистрации")
    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }
    @Step("Нажатие на кнопку входа")
    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }
    @Step("Проверка ошибки при вводе некорректного пароля")
    public boolean checkSignUpWrongPasswordError() {
        return driver.findElement(signUpWrongPasswordMessage).isDisplayed();
    }
}