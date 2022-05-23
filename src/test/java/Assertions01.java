import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Assertions01 {

    /**
     * Testing a site's URL, title, logo and a link.
     * Used:
     * Assert.assertEquals
     * Assert.assertTrue
     * Assert.assertFalse
     */

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.bestbuy.com/");
    }

    @AfterClass
    public static void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void testURL() {

        String expectedURL = "https://www.bestbuy.com/";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals("Wrong URL", expectedURL, actualURL);
    }

    @Test
    public void testTitle() {

        String undesiredKeyword = "Rest";
        String actualTitle = driver.getTitle();
        Assert.assertFalse("Wrong Title", actualTitle.contains(undesiredKeyword));
    }

    @Test
    public void testLogo() {

        WebElement logo = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/img"));
        Assert.assertTrue("Logo is not displayed!", logo.isDisplayed());
    }

    @Test
    public void testLinkFrancais() {

        WebElement linkFrancais = driver.findElement(By.xpath("/html/body/div[1]/ul/li[2]/button"));
        Assert.assertTrue("Français link is not displayed!", linkFrancais.isDisplayed());
    }
}
