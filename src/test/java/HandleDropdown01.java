import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class HandleDropdown01 {

    /**
     * Handling dropdown menus.
     * Used:
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
        driver.get("https://www.amazon.com/");
    }

    @AfterClass
    public static void tearDown() throws InterruptedException {

        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void testSearchDDM() {

        WebElement dropDownMenu = driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]"));
        Select select = new Select(dropDownMenu);
        select.selectByVisibleText("Books");
        System.out.println(select.getFirstSelectedOption().getText());

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Java" + Keys.ENTER);

        WebElement resultReport = driver.findElement(By.xpath("//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div"));
        String resultText = resultReport.getText();
        String keyWord = "Java";

        Assert.assertTrue(resultText.contains(keyWord));
    }

    @Test
    public void testOptions() {

        WebElement dropDownMenu = driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]"));
        Select select = new Select(dropDownMenu);

        List<WebElement> options = select.getOptions();

        int expectedOptionCount = 20;
        int actualOptionCount = options.size();

        Assert.assertEquals(expectedOptionCount, actualOptionCount);
    }
}