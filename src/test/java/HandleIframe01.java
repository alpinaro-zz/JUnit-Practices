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

public class HandleIframe01 {

    /**
     * Handling iframes.
     * Used:
     * driver.switchTo().defaultContent()
     * driver.switchTo().frame(iFrame)
     */

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://the-internet.herokuapp.com/iframe");
    }

    @AfterClass
    public static void tearDown() throws InterruptedException {

        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void testHeader() {

        driver.switchTo().defaultContent();
        WebElement header = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3"));
        Assert.assertTrue(header.isEnabled());
        System.out.println(header.getText());
    }

    @Test
    public void testTextBox() {

        WebElement iFrame = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iFrame);
        WebElement textBox = driver.findElement(By.xpath("//*[@id=\"tinymce\"]"));
        textBox.clear();
        textBox.sendKeys("Hello World!");
    }

    @Test
    public void testFooter() {

        driver.switchTo().defaultContent();
        WebElement footer = driver.findElement(By.xpath("//*[@id=\"page-footer\"]/div/div/a"));
        Assert.assertTrue(footer.isDisplayed());
        System.out.println(footer.getText());
    }
}
