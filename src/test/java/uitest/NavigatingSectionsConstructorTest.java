package uitest;

import constans.ApiEndpoints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.MainPage;
import setup.SetUpWebDriver;

import static org.junit.Assert.assertTrue;

public class NavigatingSectionsConstructorTest {
    public WebDriver driver;
    public MainPage mainPage;

    @Before
    public void setUp() {
        driver = SetUpWebDriver.setUpWDM();
        mainPage = new MainPage(driver);
        driver.get(ApiEndpoints.BASE_URL);
    }

    @Test
    public void checkSaucesPass() {
        mainPage.clickOnSaucesMenu();
        assertTrue("Выбрана вкладка соусов", mainPage.saucesMenuIsSelected());
    }

    @Test
    public void checkFillingsPass() {
        mainPage.clickOnFillingsMenu();
        assertTrue("Выбрана вкладка начинок", mainPage.fillingsMenuIsSelected());
    }

    @Test
    public void checkBunsPass() {
        mainPage.clickOnFillingsMenu();
        mainPage.clickOnBunsMenu();
        assertTrue("Выбрана вкладка соусов", mainPage.bunsMenuIsSelected());
    }

    @After
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }
}
