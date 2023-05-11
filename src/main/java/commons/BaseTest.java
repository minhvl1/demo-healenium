package commons;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.AfterTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected SelfHealingDriver driver;

    protected SelfHealingDriver getBrowserDriver(String browserName) {
        if (driver == null) {
            try {
                switch (browserName) {
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
//                        driver = new FirefoxDriver(firefoxOptions);
                        WebDriver delegate = new FirefoxDriver(firefoxOptions);
                        driver = SelfHealingDriver.create(delegate);
                        break;

                    case "hfirefox":
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions hfirefoxOptions = new FirefoxOptions();
                        hfirefoxOptions.addArguments("--headless");
//                        driver = new FirefoxDriver(hfirefoxOptions);
                        WebDriver delegate1 = new FirefoxDriver(hfirefoxOptions);
                        driver = SelfHealingDriver.create(delegate1);
                        break;

                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("start-maximized");
                        options.addArguments("--remote-allow-origins=*");
//                        driver = new ChromeDriver(options);
                        WebDriver delegate2 = new ChromeDriver(options);
                        driver = SelfHealingDriver.create(delegate2);
                        break;

                    case "hchrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptionsoptions = new ChromeOptions();
                        chromeOptionsoptions.addArguments("--headless");
                        chromeOptionsoptions.addArguments("--disable-dev-shm-usage");
                        chromeOptionsoptions.addArguments("--no-sandbox");
                        chromeOptionsoptions.addArguments("--remote-allow-origins=*");
//                        driver = new ChromeDriver(chromeOptionsoptions);
                        WebDriver delegate3 = new ChromeDriver(chromeOptionsoptions);
                        driver = SelfHealingDriver.create(delegate3);
                        break;

                    case "hedge":
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions hegdeoptions = new EdgeOptions();
                        hegdeoptions.addArguments("--headless");
//                        driver = new EdgeDriver(hegdeoptions);
                        WebDriver delegate4 = new EdgeDriver(hegdeoptions);
                        driver = SelfHealingDriver.create(delegate4);
                        break;

                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("start-maximized");
                        edgeOptions.addArguments("--remote-allow-origins=*");
//                        driver = new EdgeDriver(edgeOptions);
                        WebDriver delegate5 =new EdgeDriver(edgeOptions);
                        driver = SelfHealingDriver.create(delegate5);
                        break;

                    default:
//                        driver = new FirefoxDriver();
//                        driver = new EdgeDriver();
                        WebDriver delegate6 = new ChromeDriver();
                        driver = SelfHealingDriver.create(delegate6);
                        break;

                }
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    @AfterTest
    public void TearDown() {
        driver.close();
    }

    private class BrowserCleanup implements Runnable {
        @Override
        public void run() {
            close();
        }
    }

    public void close() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (UnreachableBrowserException e) {
            System.out.println("Can not close the browser");
        }
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }
//    public WebDriver getDriver() {
//        return driver;
//    }
}
