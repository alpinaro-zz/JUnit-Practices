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

public class HandleAlerts01 {

    /**
     * Handling Javascript alerts.
     * Used:
     * driver.switchTo().alert().accept()
     * driver.switchTo().alert().dismiss()
     * driver.switchTo().alert().sendKeys("Writing in text box")
     */

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @AfterClass
    public static void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void testAlertAccept() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[1]/button")).click();
        Thread.sleep(500);
        driver.switchTo().alert().accept();

        WebElement result = driver.findElement(By.xpath("//*[@id=\"result\"]"));
        String expectedResult = "You successfully clicked an alert";
        String actualResult = result.getText();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAlertDismiss() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[2]/button")).click();
        Thread.sleep(500);
        driver.switchTo().alert().dismiss();

        WebElement result = driver.findElement(By.xpath("//*[@id=\"result\"]"));
        String keyword = "successfully";
        String actualResult = result.getText();
        Assert.assertFalse(actualResult.contains(keyword));
    }

    @Test
    public void testAlertPrompt() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[3]/button")).click();
        Thread.sleep(500);
        driver.switchTo().alert().sendKeys("Alper");
        driver.switchTo().alert().accept();

        WebElement result = driver.findElement(By.xpath("//*[@id=\"result\"]"));
        String keyword = "Alper";
        String actualResult = result.getText();
        Assert.assertTrue(actualResult.contains(keyword));
    }
}
