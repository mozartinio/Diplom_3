package uitest;

import constans.ApiEndpoints;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import user.User;
import user.UserRandom;
import user.UserSteps;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.ProfilePage;
import setup.SetUpWebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PersonalProfileTest {

    public static String accessToken, refreshToken;
    public static User user;
    public WebDriver driver;
    public ProfilePage profilePage;
    public MainPage mainPage;
    public LoginPage loginPage;

    @Before
    public void setUp() {
        driver = SetUpWebDriver.setUpWDM();
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        loginPage = new LoginPage(driver);
        driver.get(ApiEndpoints.BASE_URL);
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem("accessToken", accessToken);
        localStorage.setItem("refreshToken", refreshToken);
    }
    @After
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }
    @BeforeClass
    public static void beforeClass() {
        RestAssured.baseURI = ApiEndpoints.BASE_URL;
        user = UserRandom.createNewRandomUser();
        Response response = UserSteps.createUser(user);
        accessToken = response.path("accessToken");
        refreshToken = response.path("refreshToken");
    }
    @AfterClass
    public static void afterClass() {
        UserSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка выхода из профиля по кнопке 'Выход' в личном кабинете пользователя")
    public void checkProfileExitButton() {
        driver.get(ApiEndpoints.BASE_URL + "/account");
        profilePage.waitingForProfilePageLoading();
        profilePage.menuItemExitButtonClick();
        loginPage.waitingForLoginFormLoading();
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        assertNull("Токен пользователя пустой", localStorage.getItem("accessToken"));
        assertEquals("Зашли на страницу логина после выхода", ApiEndpoints.BASE_URL + ApiEndpoints.LOGIN, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка перехода на главную страницу после авторизации пользователя")
    public void checkTransitionToMainPageAfterUserLoggedIn() {
        driver.get(ApiEndpoints.BASE_URL + "/account");
        profilePage.waitingForProfilePageLoading();
        profilePage.stellarBurgerLogotypeClick();
        mainPage.waitingForMainPageLoading();
        assertEquals("Перешли на главную страницу после авторизации", ApiEndpoints.BASE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на главную страницу из личного кабинета по кнопке с логотипом в шапке")
    public void checkTransitionFromProfileByClickOnLogoButton() {
        driver.get(ApiEndpoints.BASE_URL + "/account");
        profilePage.waitingForProfilePageLoading();
        profilePage.headerConstructorButtonClick();
        mainPage.waitingForMainPageLoading();
        assertEquals("Перешли на главную страницу после нажатия на логотип", ApiEndpoints.BASE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в личный кабинет пользователя с главной страницы")
    public void checkFromMainToPersonalPassage() {
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnProfileEnterButton();
        profilePage.waitingForProfilePageLoading();
        assertEquals("Перешли в личный кабинет после нажатия кнопки 'Личный Кабинет' на главной странице", ApiEndpoints.BASE_URL + ApiEndpoints.PROFILE, driver.getCurrentUrl());
    }
}