import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class HandleDropdown02 {

    /**
     * Handling dropdown menus.
     * Used:
     * select.selectByIndex(int);
     * select.selectByValue("value of menu option");
     * select.selectByVisibleText("Name of menu option")
     * select.getOptions()
     */

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    @AfterClass
    public static void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void testDDM() {

        WebElement ddm = driver.findElement(By.xpath("//*[@id=\"dropdown\"]"));
        Select select = new Select(ddm);

        select.selectByIndex(1);
        System.out.println(select.getFirstSelectedOption().getText());

        select.selectByValue("2");
        System.out.println(select.getFirstSelectedOption().getText());

        select.selectByVisibleText("Option 1");
        System.out.println(select.getFirstSelectedOption().getText());

        List<WebElement> options = select.getOptions();
        for(WebElement w : options) {

            System.out.println(w.getAttribute("value"));
        }

        int expectedSize = 4;
        int actualSize = options.size();

        if(expectedSize == actualSize) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}
