import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

public class Assertions03 {

    /**
     * Testing a site's title, logo and search box.
     * Used:
     * Assert.assertEquals
     * Assert.assertNotEquals
     * Assert.assertTrue
     */

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.youtube.com/");
    }

    @AfterClass
    public static void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void testTitle() {

        String expectedTitle = "YouTube";
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Wrong Title", expectedTitle, actualTitle);
    }

    @Test
    public void testTitleWrong() {

        String wrongTitle = "youtube";
        String actualTitle = driver.getTitle();
        Assert.assertNotEquals("Wrong Title", wrongTitle, actualTitle);
    }

    @Test
    public void testImage() {

        WebElement logo = driver.findElement(By.xpath("//*[@id=\"logo-icon\"]"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void testSearchBox() {

        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        Assert.assertTrue(searchBox.isEnabled());
    }
}
