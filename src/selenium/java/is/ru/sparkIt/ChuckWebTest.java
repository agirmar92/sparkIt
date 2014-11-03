package is.ru.sparkit;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.WebDriver;

public class ChuckWebTest {

    static WebDriver driver;
    static String baseUrl;
    static String port;

    @BeforeClass
    public static void before() {
        // Will be run before all tests in class are run
        driver = new FirefoxDriver();
        port = System.getenv("PORT");
        if(port == null) {
            port = "4572";
        }
        baseUrl = "http://localhost:" + port;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void after() {
        // Will be run after all tests in class have run
        driver.close();
    }

    @Before
    public void setup() {
        // Will be run before each test in class is run
        ;
    }

    @Test
    public void shouldFail() {
        driver.get(baseUrl + "/");
        assertEquals("Kisan mikla", driver.getTitle());
    }

    @Test
    public void assertTitle() {
        driver.get(baseUrl + "/");
        assertEquals("Chuck Norris Jokes", driver.getTitle());
    }

    @Test
    public void assertSpecificJoke() {
        driver.get(baseUrl + "/specific.html");
        driver.findElement(By.id("number")).clear();
        driver.findElement(By.id("number")).sendKeys("5");
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
        String expectedJoke = "Chuck Norris lost his virginity before his dad did.";
        String foundJoke = driver.findElement(By.id("results")).getText();
        assertEquals(foundJoke, expectedJoke);
    }
}
