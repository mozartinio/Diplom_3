package ru.yandex.pageobjects;

import ru.yandex.user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

public class LoginPage {
    private final By emailInputBox = By.xpath(".//label[text()='Email']/..//input");
    private final By passwordInputBox = By.xpath(".//label[text()='Пароль']/..//input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By loginFormTitle = By.xpath(".//*[text() = 'Вход']");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitingForLoginFormLoading() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(loginFormTitle));
    }

    @Step("Ввод пароля")
    public void insertPassword(String password) {
        driver.findElement(passwordInputBox).sendKeys(password);
    }

    @Step("Ввод почты")
    public void insertEmail(String email) {
        driver.findElement(emailInputBox).sendKeys(email);
    }

    @Step("Нажатие на кнопку логина")
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }
    @Step("Ввод пароля и клик на кнопку логина")
    public void insertUserDataAndClickLoginButton(User user) {
        insertEmail(user.getEmail());
        insertPassword(user.getPassword());
        loginButtonClick();
    }

}
