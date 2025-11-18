package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass1 {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups={"Sanity","Regression","Master","DataDriven"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {

        logger = LogManager.getLogger(this.getClass());
        FileReader file = new FileReader("./src/test/resources/Config.Properties");
        p = new Properties();
        p.load(file);

        String exec_env = p.getProperty("execution_env");

        // ------------------ REMOTE EXECUTION (GRID) --------------------------
        if (exec_env.equalsIgnoreCase("remote")) {

            logger.info("***** Running on Selenium Grid *****");

            if (br.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--no-sandbox");
                options.addArguments("--remote-allow-origins=*");

                driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
            }

            else if (br.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
            }

            else {
                throw new RuntimeException("Browser not supported for remote grid.");
            }
        }

        // ------------------ LOCAL EXECUTION -----------------------------------
        else if (exec_env.equalsIgnoreCase("local")) {

            logger.info("***** Running Locally *****");

            if (br.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            }
            else if (br.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            }
            else {
                throw new RuntimeException("Invalid browser for local execution.");
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("appURL"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups={"Sanity","Regression","Master","DataDriven"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String AlpaNumeric() {
        return RandomStringUtils.randomAlphabetic(5) + 
               "@" + 
               RandomStringUtils.randomNumeric(3);
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);
        String targetPath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetPath);

        source.renameTo(targetFile);
        return targetPath;
    }
}
