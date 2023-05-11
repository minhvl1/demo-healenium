package steps;

import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HealeniumTest extends BaseTest {

    private WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName);
        driver.get(url);
    }



    @Test
    public void Healenium(){
        driver.findElement(By.xpath("//textarea[@class='gLFyf']")).sendKeys("minh");
        driver.findElement(By.xpath("//textarea[@class='gLFyf1']")).sendKeys(Keys.ENTER);
    }
}
