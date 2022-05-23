import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Assertions02 {

    /**
     * Testing if an email input box gives a warning when an incorrect address is entered.
     * Used:
     * Assert.assertTrue
     */

    WebDriver driver;

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void testEmail() {

        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"email_create\"]")).sendKeys("useremailaddress.com" + Keys.ENTER);
        WebElement emailErrorMessage = driver.findElement(By.xpath("//*[@id=\"create_account_error\"]/ol/li"));
        Assert.assertTrue("Wrong email message is not displayed!", emailErrorMessage.isDisplayed());
    }
}
