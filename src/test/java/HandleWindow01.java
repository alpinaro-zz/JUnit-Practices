import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class HandleWindow01 {

    /**
     * Handling windows.
     * Used:
     * driver.switchTo().newWindow(WindowType.TAB)
     * driver.switchTo().newWindow(WindowType.WINDOW)
     * driver.switchTo().window("Window handle")
     */

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public static void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void testTitles() {

        // Amazon
        String wHandle1 = driver.getWindowHandle();
        driver.get("https://www.amazon.com/");
        String keywordTitle1 = "Amazon";
        String actualTitle1 = driver.getTitle();
        Assert.assertTrue(actualTitle1.contains(keywordTitle1));

        // Best Buy
        driver.switchTo().newWindow(WindowType.TAB);
        String wHandle2 = driver.getWindowHandle();
        driver.get("https://www.bestbuy.com/");
        String keywordTitle2 = "Best Buy";
        String actualTitle2 = driver.getTitle();
        Assert.assertTrue(actualTitle2.contains(keywordTitle2));

        // eBay
        driver.switchTo().newWindow(WindowType.WINDOW);
        String wHandle3 = driver.getWindowHandle();
        driver.get("https://www.ebay.com/");
        String keywordTitle3 = "eBay";
        String actualTitle3 = driver.getTitle();
        Assert.assertTrue(actualTitle3.contains(keywordTitle3));

        // Return
        driver.switchTo().window(wHandle1);
        String expectedURL = "https://www.amazon.com/";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(expectedURL, actualURL);
    }
}
